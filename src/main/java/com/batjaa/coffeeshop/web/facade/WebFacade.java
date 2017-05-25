package com.batjaa.coffeeshop.web.facade;

import java.util.List;

import com.batjaa.coffeeshop.web.dto.product.ProductJson;

public interface WebFacade {

	void registerUser(String firebaseToken);

	List<ProductJson> getProductList();
	
	ProductJson createProduct(String name, String description, double price, String type);
}
