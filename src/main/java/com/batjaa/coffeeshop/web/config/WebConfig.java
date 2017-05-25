package com.batjaa.coffeeshop.web.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.batjaa.coffeeshop.domain.model.ProductEntity;
import com.batjaa.coffeeshop.web.dto.product.ProductJson;

@Configuration
public class WebConfig {
	public final static String MODEL_MAPPER = "ModelMapperWeb";

	@Bean(name = MODEL_MAPPER)
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.addConverter(new Converter<ProductEntity, ProductJson>() {

			public ProductJson convert(MappingContext<ProductEntity, ProductJson> context) {
				ProductEntity entity = context.getSource();
				ProductJson productJson = context.getDestination();
				productJson.setId(entity.getId());
				productJson.setName(entity.getName());

				return productJson;
			}
		});

		return mapper;
	}
}
