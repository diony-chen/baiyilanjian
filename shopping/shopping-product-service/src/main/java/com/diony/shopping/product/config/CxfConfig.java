package com.diony.shopping.user.config;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.feature.LoggingFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CxfConfig {

	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		SpringBus bus = new SpringBus();
		bus.getFeatures().add(new LoggingFeature());
		return bus;
	}

}