package com.test;

import static com.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.core.BaseTest;

public class ActionNavigateTest extends BaseTest {
		
	@Before
	public void setUp() throws Exception {
		getDriver().get("http://antoniotrindade.com.br/treinoautomacao");
	}
	
	@Test
	public void simulateNavigationActionCache() throws InterruptedException {
		
		assertEquals("Treino Automação de Testes", getDriver().getTitle());
		
		Thread.sleep(1000);
		WebElement linkCalculadora = getDriver().findElement(By.linkText("Calculadora"));
		linkCalculadora.click();
		
		assertEquals("Desafio Automação Cálculos", getDriver().getTitle());
		
		Thread.sleep(1000);
		WebElement linkTables = getDriver().findElement(By.linkText("Localizar Table"));
		linkTables.click();
		
		assertEquals("Trabalhando com tables", getDriver().getTitle());			
		
		Thread.sleep(1000);
		
		getDriver().navigate().back();
		assertEquals("Desafio Automação Cálculos", getDriver().getTitle());
		
		Thread.sleep(1000);
		
		getDriver().navigate().back();
		assertEquals("Treino Automação de Testes", getDriver().getTitle());
		
		Thread.sleep(1000);
		
		getDriver().navigate().forward();
		assertEquals("Desafio Automação Cálculos", getDriver().getTitle());
		
		Thread.sleep(1000);
		getDriver().navigate().forward();
		assertEquals("Trabalhando com tables", getDriver().getTitle());
		
	}


}
