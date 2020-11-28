package com.Promineotech.inventoryManagement.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Promineotech.inventoryManagement.entity.Product;
import com.Promineotech.inventoryManagement.repository.ProductRepository;

@Service
public class ProductService {

	private static final Logger logger = LogManager.getLogger(ProductService.class);

	@Autowired
	private ProductRepository repo;

	public Iterable<Product> getProducts() {
		return repo.findAll();

	}

	public Product createProduct(Product product) {
		return repo.save(product);
	}

	public Product updateProduct(Product product, Long id) throws Exception {
		try {
			Product oldProduct = repo.findById(id).get();
			oldProduct.setDescription(product.getDescription());
			oldProduct.setName(product.getName());
			oldProduct.setPrice(product.getPrice());
			return repo.save(oldProduct);
		} catch (Exception e) {
			logger.error("Exception occured whilr tring to update product: " + id, e);
			throw new Exception("Unable to update product.");
		}

	}
	
	public void removeProduct(Long id) throws Exception {
		try {
			repo.deleteById(id);
		} catch (Exception e) {
			logger.error("Exception occured while trying to delete product: " + id, e);
			throw new Exception("Unable to delete product.");
		}

	}
}
