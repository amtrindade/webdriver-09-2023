package com.page;

import static com.core.DriverFactory.getDriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TaskPage {

	public TaskPage clearFilter() {
		WebElement btnClear = getDriver().findElement(By.id("scheduleList_doClear"));
		btnClear.click();
		return this;
	}
	
	public TaskPage inputFinalDate(String date) {
		WebElement dtFinal = getDriver().findElement(By.id("schedule_finalDate"));
		dtFinal.sendKeys(date);
		return this;		
	}
	
	public TaskPage inputByGenericFilter(String localDescription) {
		WebElement tfSearch = getDriver().findElement(By.id("genericFilter"));
		tfSearch.sendKeys(localDescription);
				
		return this;
	}
	
	public TaskPage searchTask() {
		WebElement btnSearch = getDriver().findElement(By.id("scheduleList_doSearch"));
		btnSearch.click();
		
		return this;
	}
	
	public Boolean isExistLocalInTable(String local) {
		List<WebElement> listLocal = getDriver().findElements(By.xpath("//a[contains(text(),'"+local+"')]"));		
		return listLocal.size() > 0;
	}
	
	public TaskEditPage editTaskByLocal(String local) {
		WebElement btnEdit = getDriver().findElement(By.xpath("//a[contains(text(),'"+local+"')]/../../td[@class='TableActions']/a"));
		btnEdit.click();
		
		return new TaskEditPage();
	}
	
	
	
	
}
