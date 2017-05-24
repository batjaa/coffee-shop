package com.batjaa.coffeeshop.web.facade.impl;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.batjaa.coffeeshop.config.auth.firebase.FirebaseTokenHolder;
import com.batjaa.coffeeshop.service.FirebaseService;
import com.batjaa.coffeeshop.service.UserService;
import com.batjaa.coffeeshop.service.impl.UserServiceImpl;
import com.batjaa.coffeeshop.service.shared.RegisterUserInit;
import com.batjaa.coffeeshop.util.StringUtil;
import com.batjaa.coffeeshop.web.config.WebConfig;
import com.batjaa.coffeeshop.web.facade.WebFacade;

@Service
public class WebFacadeImpl implements WebFacade {

	@Autowired(required = false)
	private FirebaseService firebaseService;

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
}
