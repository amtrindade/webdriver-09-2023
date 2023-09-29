package com.test;

import static com.core.DriverFactory.getDriver;
import static org.junit.Assert.assertTrue;

import java.time.Duration;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.core.BaseTest;

public class RegularExpressionCNPJTest extends BaseTest{
	
	@Before
	public void setUp() throws Exception {
		getDriver().get("https://geradordecnpj.org/");			
	}	
	
	@Test
	public void testValidaMascaraCNPJcomPontuacao() throws InterruptedException {
		
		//Espera iframe carregar com WebDriver wait
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
		wait.until(ExpectedConditions
			.frameToBeAvailableAndSwitchToIt(
				getDriver().findElement(By.xpath("//*[@id='tool-area']"))));
		
		//entra no iframe
		getDriver().switchTo().frame("tool-area");
				
		WebElement chk = getDriver().findElement(By.xpath("//input[@id='pontuacao']/.."));
		chk.click();
		
		WebElement btnGerar = getDriver().findElement(By.xpath("//button[.='Gerar']"));
		btnGerar.click();
		
		WebElement tfCnpj = getDriver().findElement(By.id("cnpj"));
		String textCnpj = tfCnpj.getAttribute("value");
		
		System.out.println(textCnpj);
		
		assertTrue(textCnpj
				.matches("^[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}\\/000[1-2]{1}-[0-9]{2}$"));				
	}


}
