package com.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


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
	
	@Test
	public void testValidaCheckBox() throws InterruptedException {
		List<WebElement> checkList = driver.findElements(By.name("chkbox"));
		
		assertEquals(4, checkList.size());
		
		for(WebElement check: checkList) {
			//System.out.println(check.getAttribute("value"));
			if ((check.getAttribute("value").equals("Check 3")) || 
					(check.getAttribute("value").equals("Check 4"))) {
				check.click();
			}			
		}
//		checkList.get(2).click();
//		checkList.get(3).click();
		
		assertFalse(checkList.get(0).isSelected());
		assertFalse(checkList.get(1).isSelected());
		
		assertTrue(checkList.get(2).isSelected());
		assertTrue(checkList.get(3).isSelected());		
	}
	
	@Test
	public void testValidaSelectSingle() throws InterruptedException {
		WebElement elementSingle = driver.findElement(By.name("dropdownlist"));
		
		Select selectSingle = new Select(elementSingle);
		
		selectSingle.selectByIndex(1);
		Thread.sleep(2000);
		selectSingle.selectByValue("item5");
		Thread.sleep(2000);
		selectSingle.selectByVisibleText("Item 7");
		Thread.sleep(2000);
		
		assertEquals("Item 7", selectSingle.getFirstSelectedOption().getText());
				
	}
	
	@Test
	public void testValidaSelectMulti() throws InterruptedException {
		WebElement elementMulti = driver.findElement(By.name("multiselectdropdown"));
		
		Select selectMulti = new Select(elementMulti);
		
		selectMulti.selectByVisibleText("Item 10");
							
		selectMulti.selectByVisibleText("Item 5");
		selectMulti.selectByVisibleText("Item 8");
		selectMulti.selectByVisibleText("Item 9");
		
		selectMulti.deselectByIndex(9);
		
		//Busca todos os selecionados no momento
		List<WebElement> optionsSelected = selectMulti.getAllSelectedOptions();
		
		//Quantos são
		assertEquals(3, optionsSelected.size());
		
		//Quais são
		assertEquals("Item 5", optionsSelected.get(0).getText());
		assertEquals("Item 8", optionsSelected.get(1).getText());
		assertEquals("Item 9", optionsSelected.get(2).getText());	
		
		Thread.sleep(3000);
		
	}
	
	@Test
	public void testValidaIFrame() {
		driver.switchTo().frame(0);
		
		WebElement tfIframe = driver.findElement(By.id("tfiframe"));
		
		tfIframe.sendKeys("Hello iframe");
		
		assertEquals("Hello iframe", tfIframe.getAttribute("value"));
		
		WebElement button = driver.findElement(By.id("btniframe"));
		
		//TODO: implementar o clique do botão
		//button.click();		
		driver.switchTo().defaultContent();
		
	}
	
	@Test
	public void testValidaAlert() throws InterruptedException {
		WebElement btnAlert = driver.findElement(By.name("alertbtn"));
		btnAlert.click();
		
		Alert alerta = driver.switchTo().alert();	
		assertEquals("Eu sou um alerta!", alerta.getText());		
		alerta.accept();	
		
		WebElement btnConfirm = driver.findElement(By.name("confirmbtn"));
		btnConfirm.click();
		
		Alert confirme = driver.switchTo().alert();
		assertEquals("Pressione um botão!", confirme.getText());
		confirme.dismiss();
		
		WebElement btnPrompt = driver.findElement(By.id("promptBtn"));
		btnPrompt.click();		
		Alert promptAlert = driver.switchTo().alert();
		assertEquals("Digite o ano:", promptAlert.getText());
		
		promptAlert.sendKeys("2023");		
		Thread.sleep(3000);		
		promptAlert.accept();
		
		Alert promptAno = driver.switchTo().alert();		
		assertEquals("O ano é 2023?", promptAno.getText());
		Thread.sleep(3000);
	}
	
}
