package com.batjaa.coffeeshop.web.facade.impl;

import java.lang.reflect.Type;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.batjaa.coffeeshop.config.auth.firebase.FirebaseTokenHolder;
import com.batjaa.coffeeshop.domain.model.ProductEntity;
import com.batjaa.coffeeshop.domain.model.ProductType;
import com.batjaa.coffeeshop.service.FirebaseService;
import com.batjaa.coffeeshop.service.ProductService;
import com.batjaa.coffeeshop.service.UserService;
import com.batjaa.coffeeshop.service.impl.UserServiceImpl;
import com.batjaa.coffeeshop.service.shared.RegisterUserInit;
import com.batjaa.coffeeshop.util.StringUtil;
import com.batjaa.coffeeshop.web.config.WebConfig;
import com.batjaa.coffeeshop.web.dto.product.ProductJson;
import com.batjaa.coffeeshop.web.facade.WebFacade;

@Service
public class WebFacadeImpl implements WebFacade {

	@Autowired(required = false)
	private FirebaseService firebaseService;
	
	@Autowired
	private ProductService productService;

	@Autowired
	@Qualifier(value = UserServiceImpl.NAME)
	private UserService userService;

	@Autowired
	@Qualifier(WebConfig.MODEL_MAPPER)
	private ModelMapper modelMapper;

	@Transactional
	@Override
	public void registerUser(String firebaseToken) {
		if (StringUtil.isBlank(firebaseToken)) {
			throw new IllegalArgumentException("FirebaseTokenBlank");
		}
		FirebaseTokenHolder tokenHolder = firebaseService.parseToken(firebaseToken);
		userService.registerUser(new RegisterUserInit(tokenHolder.getUid(), tokenHolder.getEmail()));
	}

	@Override
	public List<ProductJson> getProductList() {
		Type listType = new TypeToken<List<ProductJson>>() {
		}.getType();

		return modelMapper.map(productService.findAll(), listType);
	}

	@Override
	public ProductJson createProduct(String name, String description, double price, String type) {
		ProductEntity product = productService.create(name, description, price, type);
		return modelMapper.map(product, ProductJson.class);
	}
	
}
