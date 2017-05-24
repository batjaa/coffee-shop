package com.batjaa.coffeeshop.service.impl;

import org.springframework.stereotype.Service;

import com.batjaa.coffeeshop.config.auth.firebase.FirebaseTokenHolder;
import com.batjaa.coffeeshop.service.FirebaseService;
import com.batjaa.coffeeshop.service.shared.FirebaseParser;
import com.batjaa.coffeeshop.spring.conditionals.FirebaseCondition;

@Service
@FirebaseCondition
public class FirebaseServiceImpl implements FirebaseService {
	@Override
	public FirebaseTokenHolder parseToken(String firebaseToken) {
		return new FirebaseParser().parseToken(firebaseToken);
	}
}
