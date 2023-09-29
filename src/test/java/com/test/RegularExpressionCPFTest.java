package com.test;

import static com.core.DriverFactory.getDriver;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.core.BaseTest;

public class RegularExpressionCPFTest extends BaseTest{
		
	private WebElement tfCpf;
	private WebElement chkMascara;
	private WebElement btnGerar;

	@Before
	public void setUp() throws Exception {
		getDriver().get("https://www.geradordecpf.org/");
		
		tfCpf = getDriver().findElement(By.id("numero"));
		chkMascara = getDriver().findElement(By.id("cbPontos"));		
		btnGerar = getDriver().findElement(By.id("btn-gerar-cpf"));
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
