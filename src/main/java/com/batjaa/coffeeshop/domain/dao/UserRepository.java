package com.batjaa.coffeeshop.domain.dao;

import org.springframework.data.repository.CrudRepository;

import com.batjaa.coffeeshop.domain.model.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

	UserEntity findByUsername(String username);

}
