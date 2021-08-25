package com.diony.shopping.user.service.impl;

import com.diony.shopping.user.service.SimpleTest;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

@WebService(endpointInterface = "com.diony.shopping.user.service.impl.SimpleTest", serviceName = "SimpleTest")
@Component
public class SimpleTestImpl implements SimpleTest {

	@Override
	public String getTestStr(String name, int age) {
		String str = String.format("我的名字是：%s，年纪是：%d。", name, age);
		return str;
	}

}