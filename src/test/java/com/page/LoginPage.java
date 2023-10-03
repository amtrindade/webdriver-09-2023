package com.page;

import static com.core.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage {
	
	public LoginPage open() {
		getDriver().get("https://center.umov.me/");
		return this;
	}
	
	public LoginPage inputEnvironment(String workspace) {
		
		WebElement tfEnvironment = getDriver().findElement(By.id("workspace"));
		tfEnvironment.sendKeys(workspace);		
		return this;
	}
	
	public LoginPage inputUserName(String username) {
		
		WebElement tfUserName = getDriver().findElement(By.id("username"));
		tfUserName.sendKeys(username);		
		return this;
	}
	
	public LoginPage inputPassword(String password) {
		WebElement tfPass = getDriver().findElement(By.id("password"));
		tfPass.sendKeys(password);
		
		return this;
	}
	
	public MainPage submitForm() {
		WebElement btnLogar = getDriver().findElement(By.id("submit_button"));
		btnLogar.click();
		
		return new MainPage();
	}

}
