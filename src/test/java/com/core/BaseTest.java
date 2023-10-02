package com.core;

import static com.core.DriverFactory.getDriver;
import static com.core.DriverFactory.killDriver;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public abstract class BaseTest {
	
	@Rule
	public TestName testName = new TestName(); 
	
	
	@After
	public void tearDown() throws Exception {
		//screenshot da tela
		File screen = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		//salva o arquivo de screenshot na pasta
		FileUtils.copyFile(screen, new File("target" + File.separator + testName.getMethodName()+".jpg"));
		
		killDriver();
	}		

}
