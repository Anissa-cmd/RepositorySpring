package com.Promineotech.inventoryManagement.repository;

import org.springframework.data.repository.CrudRepository;

import com.Promineotech.inventoryManagement.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{

}
