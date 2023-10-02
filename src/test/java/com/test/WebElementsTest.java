package com.test;

import static com.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.core.BaseTest;


public class WebElementsTest extends BaseTest{
		
	@Before
	public void setUp() throws Exception {
		getDriver().get("http://antoniotrindade.com.br/treinoautomacao/elementsweb.html");			
	}

	@Test
	public void testValidaNome() throws InterruptedException {
		//Mapeia o componente de texto		
		WebElement tfName = getDriver().findElement(By.name("txtbox1"));
		
		//Faz a interação de envio de texto
		tfName.sendKeys("Antônio");				
		//Valida o resultado
		assertEquals("Antônio", tfName.getAttribute("value"));				
	}
	
	@Test
	public void testValidaTextFieldsHabilitados() {
		//Mapear os componentes
		WebElement tfEnable = getDriver().findElement(By.name("txtbox1"));
		WebElement tfDisable = getDriver().findElement(By.name("txtbox2"));
						
		//Validação		
		assertTrue(tfEnable.isEnabled());
		assertFalse(tfDisable.isEnabled());					
	}
	
	@Test
	public void testValidaRadioButton() throws InterruptedException {
		List<WebElement> radioList = getDriver().findElements(By.name("radioGroup1"));
		
		assertEquals(4, radioList.size());
		
		for (WebElement radio: radioList) {		
			//System.out.println(radio.getAttribute("value"));
			
			if (radio.getAttribute("value").equals("Radio 3")) {
				radio.click();
				break;
			}			
		}
		
		assertTrue(radioList.get(2).isSelected());
		
		assertFalse(radioList.get(0).isSelected());
		assertFalse(radioList.get(1).isSelected());
		assertFalse(radioList.get(3).isSelected());		
	}
	
	@Test
	public void testValidaCheckBox() throws InterruptedException {
		List<WebElement> checkList = getDriver().findElements(By.name("chkbox"));
		
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
		WebElement elementSingle = getDriver().findElement(By.name("dropdownlist"));
		
		Select selectSingle = new Select(elementSingle);
		
		selectSingle.selectByIndex(1);		
		selectSingle.selectByValue("item5");
		selectSingle.selectByVisibleText("Item 7");
		
		assertEquals("Item 7", selectSingle.getFirstSelectedOption().getText());
				
	}
	
	@Test
	public void testValidaSelectMulti() throws InterruptedException {
		WebElement elementMulti = getDriver().findElement(By.name("multiselectdropdown"));
		
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
		
	}
		
	@Test
	public void testValidaIFrame() {
		getDriver().switchTo().frame(0);
		
		WebElement tfIframe = getDriver().findElement(By.id("tfiframe"));
		
		tfIframe.sendKeys("Hello iframe");
		
		assertEquals("Hello iframe", tfIframe.getAttribute("value"));
		
		WebElement button = getDriver().findElement(By.id("btniframe"));
		button.click();
		
		Alert alert = getDriver().switchTo().alert();
		alert.accept();
		
		getDriver().switchTo().defaultContent();		
	}
	
	@Test
	public void testValidaAlert() throws InterruptedException {
		WebElement btnAlert = getDriver().findElement(By.name("alertbtn"));
		btnAlert.click();
		
		Alert alerta = getDriver().switchTo().alert();	
		assertEquals("Eu sou um alerta!", alerta.getText());		
		alerta.accept();	
		
		WebElement btnConfirm = getDriver().findElement(By.name("confirmbtn"));
		btnConfirm.click();
		
		Alert confirme = getDriver().switchTo().alert();
		assertEquals("Pressione um botão!", confirme.getText());
		confirme.dismiss();
		
		WebElement btnPrompt = getDriver().findElement(By.id("promptBtn"));
		btnPrompt.click();		
		Alert promptAlert = getDriver().switchTo().alert();
		assertEquals("Digite o ano:", promptAlert.getText());
		
		promptAlert.sendKeys("2023");			
		promptAlert.accept();
		
		Alert promptAno = getDriver().switchTo().alert();		
		assertEquals("O ano é 2023?", promptAno.getText());
		promptAno.accept();
		
		Alert promptFeito = getDriver().switchTo().alert();
		promptFeito.accept();		
	}
	
}
