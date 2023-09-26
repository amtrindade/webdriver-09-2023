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
	private WebElement tfCpf;
	private WebElement chkMascara;
	private WebElement btnGerar;
	
	

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/home/atrindade/Dev/drivers/chrome/chromedriver");		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		driver.get("https://www.geradordecpf.org/");
		
		tfCpf = driver.findElement(By.id("numero"));
		chkMascara = driver.findElement(By.id("cbPontos"));		
		btnGerar = driver.findElement(By.id("btn-gerar-cpf"));
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void testValidaCPFComMascara() throws InterruptedException {			
		chkMascara.click();
		btnGerar.click();
		
		String cpfGerado = tfCpf.getAttribute("value");
		
		System.out.println(cpfGerado);
		
		assertTrue(cpfGerado.matches("^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}$"));
		
		Thread.sleep(3000);
	}
	
	@Test 
	public void testValidaCPFSemMascara() throws InterruptedException {
		btnGerar.click();
		
		String cpfGeradoSemMascara = tfCpf.getAttribute("value");
		System.out.println(cpfGeradoSemMascara);
		
		assertTrue(cpfGeradoSemMascara.matches("^[0-9]{11}$"));		
		Thread.sleep(3000);
	}
}
