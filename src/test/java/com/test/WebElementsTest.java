package com.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class WebElementsTest {
	
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/home/atrindade/Dev/drivers/chrome/chromedriver");		
		//Alterar o path do driver de acordo com o SO
		//System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver.exe");
		driver = new ChromeDriver();		
				
		driver.get("http://antoniotrindade.com.br/treinoautomacao/elementsweb.html");			
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void testValidaNome() throws InterruptedException {
		
		Thread.sleep(3000);
				
		//Mapeia o componente de texto		
		WebElement tfName = driver.findElement(By.name("txtbox1"));
		
		//Faz a interação de envio de texto
		tfName.sendKeys("Antônio");
				
		Thread.sleep(3000);
		
		//Valida o resultado
		assertEquals("Antônio", tfName.getAttribute("value"));				
	}
	
	@Test
	public void testValidaTextFieldsHabilitados() {
		//Mapear os componentes
		WebElement tfEnable = driver.findElement(By.name("txtbox1"));
		WebElement tfDisable = driver.findElement(By.name("txtbox2"));
						
		//Validação		
		assertTrue(tfEnable.isEnabled());
		assertFalse(tfDisable.isEnabled());					
	}
	
	@Test
	public void testValidaRadioButton() throws InterruptedException {
		List<WebElement> radioList = driver.findElements(By.name("radioGroup1"));
		
		assertEquals(4, radioList.size());
		
		for (WebElement radio: radioList) {		
			//System.out.println(radio.getAttribute("value"));
			
			if (radio.getAttribute("value").equals("Radio 3")) {
				radio.click();
				break;
			}			
		}
		Thread.sleep(3000);
		
		assertTrue(radioList.get(2).isSelected());
		
		assertFalse(radioList.get(0).isSelected());
		assertFalse(radioList.get(1).isSelected());
		assertFalse(radioList.get(3).isSelected());		
	}
}
