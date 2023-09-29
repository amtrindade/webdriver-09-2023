package com.test;

import static com.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.core.BaseTest;

public class LocatorsTest extends BaseTest{

	@Before
	public void setUp() throws Exception {
		getDriver().get("http://antoniotrindade.com.br/treinoautomacao/localizandovalorestable.html");		
	}
	
	@Test
	public void testLocatorCssSelector() {
		//Utilizando locator cssSelector
		WebElement tfReserva = getDriver().findElement(By.cssSelector("[name=txtName]"));
		tfReserva.sendKeys("Reservado");
		
		assertEquals("Reservado", tfReserva.getAttribute("value"));				
	}
	
	@Test
	public void testLocatorXpath() {
		//Utilizando locator xpath
		WebElement tfReserva = getDriver().findElement(By.xpath("//input[@id='txt01']"));
		tfReserva.sendKeys("Reservado");
		
		assertEquals("Reservado", tfReserva.getAttribute("value"));				
	}
	
	@Test
	public void testLocatorXpathContainsText() throws InterruptedException {
		String name = "Antônio";
		
		WebElement chkBox = getDriver().findElement(By
				.xpath("//td[contains(text(),'"+ name +"')]/../td/input"));
		
		chkBox.click();
		
		assertTrue(chkBox.isSelected());		
	}
	
	@Test
	public void testLocatorsPreceding() throws InterruptedException {
		WebElement nomeColuna = getDriver().findElement(By
				.xpath("//td[contains(text(),'mail2')]/preceding-sibling::td[1]"));
		
		String nome = nomeColuna.getText();
		
		WebElement tfReserva = getDriver().findElement(By.cssSelector("[name=txtName]"));
		tfReserva.sendKeys(nome);
		
		assertEquals("Fulano da Silva", tfReserva.getAttribute("value"));
	}

}
