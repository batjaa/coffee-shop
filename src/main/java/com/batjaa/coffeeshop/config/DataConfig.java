package com.batjaa.coffeeshop.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.batjaa.coffeeshop.domain.dao.DaoPackage;

@EnableJpaRepositories(basePackageClasses = DaoPackage.class)
public class DataConfig {

}
