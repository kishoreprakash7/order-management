package com.nokia.ordermgmt.repository;

import org.springframework.data.repository.CrudRepository;
import com.nokia.ordermgmt.model.Invoice;

/**
 * @author Kishore Prakash
 *
 */
public interface InvoiceRepository extends CrudRepository<Invoice, String>{

}
