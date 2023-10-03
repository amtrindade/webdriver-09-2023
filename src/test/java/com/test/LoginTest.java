package com.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.core.BaseTest;
import com.page.LoginPage;
import com.page.MainPage;

public class LoginTest extends BaseTest{
	
	private LoginPage loginPage;
	private MainPage mainPage;
	
	@Test
	public void testLoginValido() {
		
		loginPage = new LoginPage();
		loginPage.open();
		loginPage.inputEnvironment("trindade");
		loginPage.inputUserName("aluno01");
		loginPage.inputPassword("trocarsenha");
		
		mainPage = loginPage.submitForm();
		
		mainPage.openDetailsAvatar();
		
		assertEquals("Aluno 01 (aluno01)", mainPage.getUserLogged());		
	}
}
