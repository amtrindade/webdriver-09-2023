package com.test;

import static com.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.core.BaseTest;

public class DragAndDropTest extends BaseTest{
	

	@Before
	public void setUp() throws Exception {
		getDriver().get("https://jqueryui.com/resources/demos/droppable/default.html");
		getDriver().manage().window().maximize();
	}

	@Test
	public void testValidaDragAndDrop() throws InterruptedException {
		WebElement origin = getDriver().findElement(By.id("draggable"));
		WebElement target = getDriver().findElement(By.id("droppable"));
		
		assertEquals("Drop here", target.getText());
		
		Thread.sleep(3000);
		
		//Action
		new Actions(getDriver()).dragAndDrop(origin, target).perform();
		
		Thread.sleep(3000);
		
		assertEquals("Dropped!", target.getText());		
	}

}
