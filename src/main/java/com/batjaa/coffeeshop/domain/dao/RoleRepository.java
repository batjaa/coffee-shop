package com.batjaa.coffeeshop.domain.dao;

import org.springframework.data.repository.CrudRepository;

import com.batjaa.coffeeshop.domain.model.RoleEntity;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

	RoleEntity findByAuthority(String authority);
}
