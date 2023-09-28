package com.test;

import static org.junit.Assert.assertTrue;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegularExpressionCNPJTest {
	
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/home/atrindade/Dev/drivers/chrome/chromedriver");		
		driver = new ChromeDriver();	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://geradordecnpj.org/");			
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	@Test
	public void testValidaMascaraCNPJcomPontuacao() throws InterruptedException {
		
		//Espera iframe carregar com WebDriver wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions
			.frameToBeAvailableAndSwitchToIt(
				driver.findElement(By.xpath("//*[@id='tool-area']"))));
		
		//entra no iframe
		driver.switchTo().frame("tool-area");
				
		WebElement chk = driver.findElement(By.xpath("//input[@id='pontuacao']/.."));
		chk.click();
		
		WebElement btnGerar = driver.findElement(By.xpath("//button[.='Gerar']"));
		btnGerar.click();
		
		WebElement tfCnpj = driver.findElement(By.id("cnpj"));
		String textCnpj = tfCnpj.getAttribute("value");
		
		System.out.println(textCnpj);
		
		assertTrue(textCnpj
				.matches("^[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}\\/000[1-2]{1}-[0-9]{2}$"));				
	}


}
