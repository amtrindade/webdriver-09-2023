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

public class RegularExpressionCPFTest {
	
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/home/atrindade/Dev/drivers/chrome/chromedriver");		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		driver.get("https://www.geradordecpf.org/");		
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void testValidaCPFComMascara() throws InterruptedException {
		WebElement tfCpf = driver.findElement(By.id("numero"));
		WebElement chkMascara = driver.findElement(By.id("cbPontos"));		
		WebElement btnGerar = driver.findElement(By.id("btn-gerar-cpf"));
		
		chkMascara.click();
		btnGerar.click();
		
		String cpfGerado = tfCpf.getAttribute("value");
		
		System.out.println(cpfGerado);
		
		assertTrue(cpfGerado.matches("^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}$"));
		
		Thread.sleep(3000);
	}


}
