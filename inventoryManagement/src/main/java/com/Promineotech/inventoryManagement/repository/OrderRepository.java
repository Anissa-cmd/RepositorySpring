package com.Promineotech.inventoryManagement.repository;

import org.springframework.data.repository.CrudRepository;

import com.Promineotech.inventoryManagement.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
