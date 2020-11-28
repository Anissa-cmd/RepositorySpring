package com.Promineotech.inventoryManagement.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Promineotech.inventoryManagement.entity.Order;
import com.Promineotech.inventoryManagement.service.OrderService;
import com.Promineotech.inventoryManagement.util.OrderStatus;

@RestController
@RequestMapping("customer/{id}/orders")
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> createCustomer(@RequestBody Set<Long> productIds, @PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.submitNewOrder(productIds, id), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/{orderId}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateorder(@RequestBody Order order, @PathVariable Long orderId) {
		try {
			if (order.getStatus().equals(OrderStatus.CANCELED)) {
				return new ResponseEntity<Object>(service.cancelOrder(orderId), HttpStatus.OK);
			} else if (order.getStatus().equals(OrderStatus.DELIVERED)) {
				return new ResponseEntity<Object>(service.completeOrder(orderId), HttpStatus.OK);
			}
			return new ResponseEntity<Object>("Invalid update request.", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
