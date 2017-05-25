package com.batjaa.coffeeshop.service;

import java.util.List;

import com.batjaa.coffeeshop.domain.model.ProductEntity;

public interface ProductService {

	List<ProductEntity> findAll();

	ProductEntity create(String name, String description, double price, String type);

}
