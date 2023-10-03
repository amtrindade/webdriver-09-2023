package com.page;

import static com.core.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MainPage {
	
	public MainPage openDetailsAvatar() {
		WebElement divAvatar = getDriver().findElement(By.xpath("//div[@class='topbar-widget profile-widget']"));
		divAvatar.click();		
		return this;
	}
	
	public String getUserLogged() {
		WebElement divUserLogged = getDriver().findElement(By.xpath("//span[@class='text-login']/.."));
		return divUserLogged.getText();		
	}

	
}
