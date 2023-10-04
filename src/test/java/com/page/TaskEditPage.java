package com.page;

import static com.core.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TaskEditPage {
	
	public TaskPage save() {
		WebElement btnSave = getDriver().findElement(By.id("formSchedule_doSave"));
		btnSave.click();
		
		return new TaskPage();				
	}

}
