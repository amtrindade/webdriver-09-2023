package com.test;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CalculadoraTest {

	private WebDriver driver;
	private WebElement tfValor1;
	private WebElement tfValor2;
	private WebElement tfTotal;
	private Integer val1;
	private Integer val2;
	

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/home/atrindade/Dev/drivers/chrome/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://antoniotrindade.com.br/treinoautomacao/desafiosoma.html");
		//Mapeia elementos
		mapeiaElementos();
		
		//Geração de números Inteiros randomicos
		val1 = ThreadLocalRandom.current().nextInt(1, 99);
		val2 = ThreadLocalRandom.current().nextInt(1, 99);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	private void mapeiaElementos() {
		tfValor1 = driver.findElement(By.id("number1"));
		tfValor2 = driver.findElement(By.id("number2"));
		tfTotal = driver.findElement(By.id("total"));
	}


	@Test
	public void testSoma() throws InterruptedException {		
		Integer valTotal = val1 + val2;
		
		WebElement btnSomar = driver.findElement(By.id("somar"));

		tfValor1.sendKeys(String.valueOf(val1));
		tfValor2.sendKeys(String.valueOf(val2));

		btnSomar.click();

		Thread.sleep(3000);

		assertEquals(String.valueOf(valTotal), tfTotal.getAttribute("value"));
	}

	@Test
	public void testSubtracao() {
		Integer valTotal = val1 - val2;
		
		WebElement btnSubtracao = driver.findElement(By.id("subtrair"));
		
		tfValor1.sendKeys(String.valueOf(val1));
		tfValor2.sendKeys(String.valueOf(val2));
		
		btnSubtracao.click();
		
		assertEquals(String.valueOf(valTotal), tfTotal.getAttribute("value"));		
	}
	
	@Test
	public void testMultiplicacao() {		
		Integer valTotal = val1 * val2;
		
		WebElement btnMultiplicacao = driver.findElement(By.id("multiplicar"));
		
		tfValor1.sendKeys(String.valueOf(val1));
		tfValor2.sendKeys(String.valueOf(val2));
		
		btnMultiplicacao.click();
		
		assertEquals(String.valueOf(valTotal), tfTotal.getAttribute("value"));
	}
}
