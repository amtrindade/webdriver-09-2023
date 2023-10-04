package com.page;

import static com.core.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.core.GlobalProperty;

public class LoginPage {
	
	public LoginPage open() {
		getDriver().get(GlobalProperty.getProperty("webdriver.test.url"));
		return this;
	}
	
	public LoginPage inputEnvironment(String workspace) {
		
		WebElement tfEnvironment = getDriver().findElement(By.id("workspace"));
		tfEnvironment.clear();
		tfEnvironment.sendKeys(workspace);		
		return this;
	}
	
	public LoginPage inputUserName(String username) {
		
		WebElement tfUserName = getDriver().findElement(By.id("username"));
		tfUserName.clear();
		tfUserName.sendKeys(username);		
		return this;
	}
	
	public LoginPage inputPassword(String password) {
		WebElement tfPass = getDriver().findElement(By.id("password"));
		tfPass.clear();
		tfPass.sendKeys(password);
		
		return this;
	}
	
	public MainPage submitFormValid() {
		WebElement btnLogar = getDriver().findElement(By.id("submit_button"));
		btnLogar.click();
		
		return new MainPage();
	}
	
	public LoginPage submitFormInvalid() {
		WebElement btnLogar = getDriver().findElement(By.id("submit_button"));
		btnLogar.click();
		
		return this;
	}
	
	public String getMessageError() {
		WebElement messageError = getDriver()
				.findElement(By.xpath("//li[@class='nm-li nm-message-error']"));		
		return messageError.getText();
	}

}
