package com.batjaa.coffeeshop.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.batjaa.coffeeshop.web.facade.WebFacade;

@RestController
public class ProductResource {

	@Autowired
	private WebFacade webFacade;

	@Value("${env}")
	private String environment;

	@RequestMapping(value = "/api/open/products", method = RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.OK)
	public List<?> apiGetProducts() {
		return null;
	}

}
