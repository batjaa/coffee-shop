package com.batjaa.coffeeshop.domain.dao;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.batjaa.coffeeshop.domain.model.TestEntity;

public interface TestRepository extends CrudRepository<TestEntity, Long> {
	Collection<TestEntity> findAll();
}
