package com.nokia.ordermgmt.repository;

import org.springframework.data.repository.CrudRepository;
import com.nokia.ordermgmt.model.Inventory;

/**
 * @author Kishore Prakash
 *
 */
public interface InventoryRepository extends CrudRepository<Inventory, String>{

}
