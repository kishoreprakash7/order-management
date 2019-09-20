# order-management
Order Management SPRINTBOOT microservice project

1: Connect to cassandra instance on minikube and execute the below commands on cqlsh -

CREATE KEYSPACE mykeyspace WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };
USE mykeyspace;
#CRUD operations
CREATE TABLE inventory ( productName TEXT PRIMARY KEY, productID TEXT, price INT, status TEXT);
CREATE TABLE invoice ( invoiceNumber TEXT PRIMARY KEY, orderDate TEXT, productList list<TEXT>, totalPrice INT);
CREATE TABLE shipping ( shippingID TEXT PRIMARY KEY, orderDate TEXT, productList list<TEXT>, invoiceNumber TEXT);

2: Connect to kafka instance on minikube and execute the below command to create the topic "shippingMessage" -

kafka-topics --zookeeper 127.0.0.1:2181 --create --topic shippingMessage --partitions 3 --replication-factor 1


3: Order Management SPRINGBOOT application deployment on minikube

> minikube start {starts minikube}
> kubectl cluster-info {check cluster information and if minikube is up and running}
> minikube ssh {ssh into the VM}
> cd {project location} - [traverse into the location of the project checked out from GIT, GIT URL : https://github.com/kishoreprakash7/order-management.git]
> docker build -f Dockerfile -t ordermgmt . {build a docker image called ordermgmt}
> kubectl run ordermgmt --image=ordermgmt:latest \ --port=8082 --image-pull-policy Never {run ordermgmt image on default port 8082}
> kubectl expose deployment ordermgmt --type=NodePort {will assign a port for ordermgmt}

> minikube dashboard {will open up the UI for kubernetes deployments}

4: How to USE the ordermgmt application {execute below on POSTMAN} - 

ADD products: {Add any product using URL and JSON body given below. By default the status will be updated as "Available"}
POST : http://{IP address}:{PORT}/nokia/ordermgmt/addProducts
{
"productName" : "apple",
"productID" : "1000",
"price" : "100"
}

FETCH products: {Fetch all the products present in inventory}
GET : http://{IP address}:{PORT}/nokia/ordermgmt/getAllProducts

ORDER products: {Order 'Available' products of your choice. Use JSON body}
POST : http://{IP address}:{PORT}/nokia/ordermgmt/placeOrder
{"orderItems" : ["mango","papaya","apple"]}

Note : Once the invoice is generated the invoiceNumber is posted via kafka to the shipping module and shippingID is generated and an entry is created in the shipping table.

Note: If 'Unavailable' products are ordered they will be excluded from the cart and order for other items will be placed. 
If invalid products are entered application will throw error an error message.

KAFKA: Specify the invoice number which will be sent across via Kafka topic to the shipping module for shipping ID to be generated
POST : http://{IP address}:{PORT}/nokia/kafka/publish/{invoiceNumber}
Example : http://{IP address}:{PORT}/nokia/kafka/publish/d4486860-d89b-4385-a68e-40c515a2668e

UPDATE products: {Update status of products to "Unavailable" if product has gone out of stock}
PUT : http://{IP address}:{PORT}/nokia/ordermgmt/updateProductStatus?productName={name}&status={status}
Example : http://{IP address}:{PORT}/nokia/ordermgmt/updateProductStatus?productName=mango&status=Unavailable

DELETE products: {Delete any product by productName}
DELETE : http://{IP address}:{PORT}/nokia/ordermgmt/deleteProduct?productName={name}
Example : http://{IP address}:{PORT}/nokia/ordermgmt/deleteProduct?productName=apple

5: Swagger implemention URL's -

http://{IP address}:{PORT}/nokia/v2/api-docs
http://{IP address}:{PORT}/nokia/swagger-ui.html

6: Performance metric URL's -

Actuator:
http://{IP address}:{PORT}/nokia/actuator

Prometheus:
http://{IP address}:{PORT}/nokia/actuator/prometheus
