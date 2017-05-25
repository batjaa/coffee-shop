package com.batjaa.coffeeshop.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "PMD_PRODUCT")
public class ProductEntity extends AbstractEntity {

	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "price")
	private double price;

	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private ProductType type;

	public ProductEntity() {
	}
	
	public ProductEntity(String name) {
		this.name = name;
	}

	public ProductEntity(String name, String description, double price, ProductType type) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public ProductType getType() {
		return type;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

}
