package com.nokia.ordermgmt.repository;

import org.springframework.data.repository.CrudRepository;
import com.nokia.ordermgmt.model.Shipping;

/**
 * @author Kishore Prakash
 *
 */
public interface ShippingRepository extends CrudRepository<Shipping, String>{

}
