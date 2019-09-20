FROM openjdk:8
ADD target/order-management-0.0.1-SNAPSHOT.jar ordermgmt.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "ordermgmt.jar"]