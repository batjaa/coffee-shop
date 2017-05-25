package com.batjaa.coffeeshop.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.stereotype.Service;

import com.batjaa.coffeeshop.domain.dao.ProductRepository;
import com.batjaa.coffeeshop.domain.model.ProductEntity;
import com.batjaa.coffeeshop.domain.model.ProductType;
import com.batjaa.coffeeshop.service.ProductService;
import com.batjaa.coffeeshop.util.StringUtil;

@Service
public class ProductServiceImpl implements ProductService {

	private final static String COUNTER_TEST = "rs.pscode.entity.product.";

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CounterService counterService;
	
	@Transactional
	@Override
	public List<ProductEntity> findAll() {
		return productRepository.findAll();
	}

	@Transactional
	@Override
	public ProductEntity create(String name, String description, double price, String type) {
		if (StringUtil.isBlank(name)) {
			throw new IllegalArgumentException("ProductNameIsBlank");
		}
		ProductType productType = ProductType.BREAKFAST;
		try {
			productType = (ProductType) ProductType.valueOf(type);
		} catch (Exception e) {
			System.out.println("No valid type found");
		}
		//TODO Create event here
		counterService.increment(COUNTER_TEST + "created");
		
		ProductEntity product = new ProductEntity(name, description, price, productType);
		
		return productRepository.save(product);
	}
}
