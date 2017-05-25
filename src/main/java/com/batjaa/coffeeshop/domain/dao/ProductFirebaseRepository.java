package com.batjaa.coffeeshop.domain.dao;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.batjaa.coffeeshop.domain.model.ProductEntity;

public interface ProductFirebaseRepository extends CrudRepository<ProductEntity, Long> {
	List<ProductEntity> findAll();
}
