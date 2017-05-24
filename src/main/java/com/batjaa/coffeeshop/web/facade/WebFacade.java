package com.batjaa.coffeeshop.web.facade;

import java.util.List;

import com.batjaa.coffeeshop.web.dto.test.TestJson;
import com.batjaa.coffeeshop.web.dto.test.TestRequestJson;

public interface WebFacade {

	void registerUser(String firebaseToken);

	TestJson createTest(TestRequestJson json);

	List<TestJson> getTaskList();

}
