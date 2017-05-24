package com.batjaa.coffeeshop.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.batjaa.coffeeshop.domain.model.UserEntity;
import com.batjaa.coffeeshop.service.shared.RegisterUserInit;

public interface UserService extends UserDetailsService {

	UserEntity registerUser(RegisterUserInit init);

}
