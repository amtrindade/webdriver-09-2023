package com.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LocatorsTest {
	
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/home/atrindade/Dev/drivers/chrome/chromedriver");		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		driver.get("http://antoniotrindade.com.br/treinoautomacao/localizandovalorestable.html");		
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	@Test
	public void testLocatorCssSelector() {
		//Utilizando locator cssSelector
		WebElement tfReserva = driver.findElement(By.cssSelector("[name=txtName]"));
		tfReserva.sendKeys("Reservado");
		
		assertEquals("Reservado", tfReserva.getAttribute("value"));				
	}
	
	@Test
	public void testLocatorXpath() {
		//Utilizando locator xpath
		WebElement tfReserva = driver.findElement(By.xpath("//input[@id='txt01']"));
		tfReserva.sendKeys("Reservado");
		
		assertEquals("Reservado", tfReserva.getAttribute("value"));				
	}
	
	@Test
	public void testLocatorXpathContainsText() throws InterruptedException {
		String name = "Antônio";
		
		WebElement chkBox = driver.findElement(By
				.xpath("//td[contains(text(),'"+ name +"')]/../td/input"));
		
		chkBox.click();
		
		assertTrue(chkBox.isSelected());
		
		Thread.sleep(3000);		
	}
	
	@Test
	public void testLocatorsPreceding() throws InterruptedException {
		WebElement nomeColuna = driver.findElement(By
				.xpath("//td[contains(text(),'mail2')]/preceding-sibling::td[1]"));
		
		String nome = nomeColuna.getText();
		
		WebElement tfReserva = driver.findElement(By.cssSelector("[name=txtName]"));
		tfReserva.sendKeys(nome);
		
		assertEquals("Fulano da Silva", tfReserva.getAttribute("value"));
		Thread.sleep(3000);
	}

}
