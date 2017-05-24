package com.batjaa.coffeeshop.web.facade.impl;

import java.lang.reflect.Type;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.batjaa.coffeeshop.config.SecurityConfig.Roles;
import com.batjaa.coffeeshop.config.auth.firebase.FirebaseTokenHolder;
import com.batjaa.coffeeshop.domain.model.TestEntity;
import com.batjaa.coffeeshop.service.FirebaseService;
import com.batjaa.coffeeshop.service.TestService;
import com.batjaa.coffeeshop.service.UserService;
import com.batjaa.coffeeshop.service.impl.UserServiceImpl;
import com.batjaa.coffeeshop.service.shared.RegisterUserInit;
import com.batjaa.coffeeshop.util.StringUtil;
import com.batjaa.coffeeshop.web.config.WebConfig;
import com.batjaa.coffeeshop.web.dto.test.TestJson;
import com.batjaa.coffeeshop.web.dto.test.TestRequestJson;
import com.batjaa.coffeeshop.web.facade.WebFacade;

@Service
public class WebFacadeImpl implements WebFacade {

	@Autowired(required = false)
	private FirebaseService firebaseService;

	@Autowired
	private TestService testService;

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

	@Transactional
	@Override
	@Secured(value = Roles.ROLE_USER)
	public TestJson createTest(TestRequestJson json) {
		TestEntity testEntity = testService.create(json.getName());
		return modelMapper.map(testEntity, TestJson.class);
	}

	@Transactional
	@Override
	@Secured(value = Roles.ROLE_USER)
	public List<TestJson> getTaskList() {
		Type listType = new TypeToken<List<TestJson>>() {
		}.getType();

		return modelMapper.map(testService.findAll(), listType);
	}
}
