package com.test;

import static com.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.core.BaseTest;

public class CalculadoraTest extends BaseTest{
	
	private WebElement tfValor1;
	private WebElement tfValor2;
	private WebElement tfTotal;
	private Integer val1;
	private Integer val2;
	

	@Before
	public void setUp() throws Exception {
		getDriver().get("http://antoniotrindade.com.br/treinoautomacao/desafiosoma.html");
		//Mapeia elementos
		mapeiaElementos();
		
		//Geração de números Inteiros randomicos
		val1 = ThreadLocalRandom.current().nextInt(1, 99);
		val2 = ThreadLocalRandom.current().nextInt(1, 99);
	}

	
	private void mapeiaElementos() {
		tfValor1 = getDriver().findElement(By.id("number1"));
		tfValor2 = getDriver().findElement(By.id("number2"));
		tfTotal = getDriver().findElement(By.id("total"));
	}


	@Test
	public void testSoma() throws InterruptedException {		
		Integer valTotal = val1 + val2;
		
		WebElement btnSomar = getDriver().findElement(By.id("somar"));

		tfValor1.sendKeys(String.valueOf(val1));
		tfValor2.sendKeys(String.valueOf(val2));

		btnSomar.click();
		
		System.out.println(valTotal);

		// Espera explícita pelo valor do locator
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		wait.until(ExpectedConditions
				.textToBePresentInElementValue(tfTotal, String.valueOf(valTotal)));

		assertEquals(String.valueOf(valTotal), tfTotal.getAttribute("value"));
	}

	@Test
	public void testSubtracao() {
		Integer valTotal = val1 - val2;
		
		WebElement btnSubtracao = getDriver().findElement(By.id("subtrair"));
		
		tfValor1.sendKeys(String.valueOf(val1));
		tfValor2.sendKeys(String.valueOf(val2));
		
		btnSubtracao.click();
		
		assertEquals(String.valueOf(valTotal), tfTotal.getAttribute("value"));		
	}
	
	@Test
	public void testMultiplicacao() {		
		Integer valTotal = val1 * val2;
		
		WebElement btnMultiplicacao = getDriver().findElement(By.id("multiplicar"));
		
		tfValor1.sendKeys(String.valueOf(val1));
		tfValor2.sendKeys(String.valueOf(val2));
		
		btnMultiplicacao.click();
		
		assertEquals(String.valueOf(valTotal), tfTotal.getAttribute("value"));
	}
}
