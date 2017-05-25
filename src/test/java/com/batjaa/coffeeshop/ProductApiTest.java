package com.batjaa.coffeeshop;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.batjaa.coffeeshop.domain.dao.ProductRepository;
import com.batjaa.coffeeshop.domain.model.ProductEntity;
import com.batjaa.coffeeshop.domain.model.ProductType;
import com.batjaa.coffeeshop.web.config.WebConfig;
import com.batjaa.coffeeshop.web.dto.product.ProductRequestJson;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class ProductApiTest {
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	@Autowired
	@Qualifier(WebConfig.MODEL_MAPPER)
	private ModelMapper modelMapper;
	
	@SuppressWarnings("rawtypes")
	private HttpMessageConverter mappingJackson2HttpMessageConverter;
	
	private List<ProductEntity> productList = new ArrayList<ProductEntity>();
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }
	
	@Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        productRepository.deleteAll();
        productList.add(productRepository.save(new ProductEntity("Lemon", "Very sour", 1.5, ProductType.LUNCH)));
        productList.add(productRepository.save(new ProductEntity("Lime", "Less sour", 1.25, ProductType.DINNER)));
    }
	
	@Test
    public void productsEndpointExists() throws Exception {
        mockMvc.perform(get("/api/open/products"))
                .andExpect(status().isOk());
    }
	
	@Test
    public void getAllProducts() throws Exception {
        mockMvc.perform(get("/api/open/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(productList.get(0).getId())))
                .andExpect(jsonPath("$[0].name", is(productList.get(0).getName())));
    }
	
	@Test
    public void createProduct() throws Exception {
       ProductRequestJson product = new ProductRequestJson();
       product.setName("Orange");
       System.out.println(json(product));
        this.mockMvc.perform(post("/api/open/products")
                .contentType(contentType)
                .content(json(product)))
                .andExpect(status().isCreated());
    }
	
	@SuppressWarnings("unchecked")
	protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
