package com.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.core.BaseTest;
import com.core.GlobalProperty;
import com.page.LoginPage;
import com.page.MainPage;

public class LoginTest extends BaseTest{
	
	private LoginPage loginPage;
	private MainPage mainPage;
	
	@Before
	public void setUp() {
		loginPage = new LoginPage();
		loginPage.open();
	}
	
	@Test
	public void testLoginValido() {

		loginPage.inputEnvironment("trindade");
		loginPage.inputUserName("aluno01");
		loginPage.inputPassword(GlobalProperty.getProperty("webdriver.test.password"));
		
		mainPage = loginPage.submitFormValid();
		
		mainPage.openDetailsAvatar();
		
		assertEquals("Aluno 01 (aluno01)", mainPage.getUserLogged());		
	}
	
	@Test
	public void testLoginInvalido() {
		loginPage.inputEnvironment("ambienteinvalido");
		loginPage.inputUserName("aluno01");
		loginPage.inputPassword("xpto");
		
		loginPage.submitFormInvalid();
				
		assertEquals("ERRO\nLOGIN INVÁLIDO.", loginPage.getMessageError());
	}
	
	@Test
	public void testTentativasErroSenha() throws InterruptedException {
		loginPage.inputEnvironment("trindade");
		loginPage.inputUserName("aluno01");
		loginPage.inputPassword("xpto");
		
		loginPage.submitFormInvalid();
				
		assertEquals("ERRO\nLOGIN INVÁLIDO. VOCÊ PODE TENTAR MAIS 4 VEZES.", loginPage.getMessageError());
					
		loginPage.inputEnvironment("trindade");
		loginPage.inputUserName("aluno01");
		loginPage.inputPassword(GlobalProperty.getProperty("webdriver.test.password"));
		
		mainPage = loginPage.submitFormValid();				
		
	}
}
