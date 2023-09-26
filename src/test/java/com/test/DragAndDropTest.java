package com.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DragAndDropTest {
	
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/home/atrindade/Dev/drivers/chrome/chromedriver");		
		driver = new ChromeDriver();						
		driver.get("https://jqueryui.com/resources/demos/droppable/default.html");			
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	@Test
	public void testValidaDragAndDrop() throws InterruptedException {
		WebElement origin = driver.findElement(By.id("draggable"));
		WebElement target = driver.findElement(By.id("droppable"));
		
		assertEquals("Drop here", target.getText());
		
		Thread.sleep(3000);
		
		//Action
		new Actions(driver).dragAndDrop(origin, target).perform();
		
		Thread.sleep(3000);
		
		assertEquals("Dropped!", target.getText());		
	}

}
