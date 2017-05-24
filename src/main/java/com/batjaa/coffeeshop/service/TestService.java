package com.batjaa.coffeeshop.service;

import java.util.Collection;

import com.batjaa.coffeeshop.domain.model.TestEntity;

public interface TestService {

	Collection<TestEntity> findAll();

	TestEntity create(String name);

}
