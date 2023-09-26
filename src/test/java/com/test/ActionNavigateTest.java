package com.test;

import static org.junit.Assert.assertEquals;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ActionNavigateTest {
	
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/home/atrindade/Dev/drivers/chrome/chromedriver");		
		driver = new ChromeDriver();	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("http://antoniotrindade.com.br/treinoautomacao");			
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	@Test
	public void simulateNavigationActionCache() throws InterruptedException {
		
		assertEquals("Treino Automação de Testes", driver.getTitle());
		
		Thread.sleep(1000);
		WebElement linkCalculadora = driver.findElement(By.linkText("Calculadora"));
		linkCalculadora.click();
		
		assertEquals("Desafio Automação Cálculos", driver.getTitle());
		
		Thread.sleep(1000);
		WebElement linkTables = driver.findElement(By.linkText("Localizar Table"));
		linkTables.click();
		
		assertEquals("Trabalhando com tables", driver.getTitle());			
		
		Thread.sleep(1000);
		
		driver.navigate().back();
		assertEquals("Desafio Automação Cálculos", driver.getTitle());
		
		Thread.sleep(1000);
		
		driver.navigate().back();
		assertEquals("Treino Automação de Testes", driver.getTitle());
		
		Thread.sleep(1000);
		
		driver.navigate().forward();
		assertEquals("Desafio Automação Cálculos", driver.getTitle());
		
		Thread.sleep(1000);
		driver.navigate().forward();
		assertEquals("Trabalhando com tables", driver.getTitle());
		
	}


}
