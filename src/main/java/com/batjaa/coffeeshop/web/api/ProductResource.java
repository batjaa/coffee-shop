package com.batjaa.coffeeshop.web.api;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.batjaa.coffeeshop.domain.model.ProductEntity;
import com.batjaa.coffeeshop.web.config.WebConfig;
import com.batjaa.coffeeshop.web.dto.product.ProductJson;
import com.batjaa.coffeeshop.web.dto.product.ProductRequestJson;
import com.batjaa.coffeeshop.web.facade.WebFacade;

@RestController
public class ProductResource {

	@Autowired
	private WebFacade webFacade;

	@Autowired
	@Qualifier(WebConfig.MODEL_MAPPER)
	private ModelMapper modelMapper;
	
	@Value("${env}")
	private String environment;

	@RequestMapping(value = "/api/open/products", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ProductJson> apiGetProducts() {
		return webFacade.getProductList();
	}

	@RequestMapping(value = "/api/open/products", method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ProductJson apiProductCreate(@RequestBody ProductRequestJson json) {
		System.out.println(json);
		System.out.println(json.getName());
		return webFacade.createProduct(json.getName(), json.getDescription(), json.getPrice(), json.getType());
	}
}
