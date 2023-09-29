package com.test;

import static com.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.core.BaseTest;

public class NavigationTabsTest extends BaseTest{		

	@Before
	public void setUp() throws Exception {
		getDriver().get("http://antoniotrindade.com.br/treinoautomacao");			
	}
		
	@Test
	public void testNavigationTabs() {
		assertEquals("Treino Automação de Testes", getDriver().getTitle());
		
		WebElement linkCPF = getDriver().findElement(By.linkText("Gerador de CPF"));
		linkCPF.click();
		
		WebElement linkJQuery = getDriver().findElement(By.linkText("Drag and Drop JQuery"));
		linkJQuery.click();					
		
		ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());		
		getDriver().switchTo().window(tabs.get(2));
		
		assertEquals("Gerador de CPF", getDriver().getTitle());
		
		getDriver().switchTo().window(tabs.get(1));
		assertEquals("jQuery UI Droppable - Default functionality", getDriver().getTitle());
		
		getDriver().switchTo().window(tabs.get(0));
		assertEquals("Treino Automação de Testes", getDriver().getTitle());								
	}
}
