package com.core;

import static com.core.DriverFactory.killDriver;

import org.junit.After;

public abstract class BaseTest {
	
	@After
	public void tearDown() throws Exception {
		killDriver();
	}		

}
