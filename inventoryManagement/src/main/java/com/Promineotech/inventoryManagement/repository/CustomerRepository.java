package com.Promineotech.inventoryManagement.repository;

import org.springframework.data.repository.CrudRepository;

import com.Promineotech.inventoryManagement.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
