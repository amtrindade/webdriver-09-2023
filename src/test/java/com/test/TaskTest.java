package com.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.core.BaseTest;
import com.core.GlobalProperty;
import com.page.LoginPage;
import com.page.MainPage;
import com.page.TaskEditPage;
import com.page.TaskPage;

public class TaskTest extends BaseTest{
	
	private LoginPage loginPage;
	private MainPage mainPage;
	private TaskPage taskPage;
	private TaskEditPage taskEditPage;
	
	@Before
	public void setUp() {
		loginPage = new LoginPage();
		loginPage.open();
		loginPage.inputEnvironment("trindade");
		loginPage.inputUserName("aluno01");
		loginPage.inputPassword(GlobalProperty.getProperty("webdriver.test.password"));		
		mainPage = loginPage.submitFormValid();	
		taskPage = mainPage.clickMenuTask();
	}
	
	@Test
	public void testSearchExistTaskWithLocal() throws InterruptedException {
		String localDescription = "Haus Burger";
		
		taskPage.clearFilter();		
		taskPage.inputByGenericFilter(localDescription);
		taskPage.searchTask();
		
		assertTrue(taskPage.isExistLocalInTable(localDescription));		
	}
	
	@Test
	public void testSearchLocalWhithoutText() throws InterruptedException {
		String localDescription = "Haus Burger";
		taskPage.clearFilter();		
		taskPage.searchTask();		
		assertTrue(taskPage.isExistLocalInTable(localDescription));				
	}
	
	@Test
	public void testSearchInvalidLocal() {
		String localDescription = "Fruteira do tio João";
		
		taskPage.clearFilter();		
		taskPage.inputByGenericFilter(localDescription);
		taskPage.searchTask();
		
		assertFalse(taskPage.isExistLocalInTable(localDescription));
	}
	
	@Test
	public void testEditTask() throws InterruptedException {
		String localDescription = "Haus Burger";
		taskPage.clearFilter();		
		taskPage.searchTask();
		
		taskEditPage = taskPage.editTaskByLocal(localDescription);
					
		taskPage = taskEditPage.save();					
		
		taskPage.clearFilter();		
		taskPage.searchTask();
		assertTrue(taskPage.isExistLocalInTable(localDescription));				
	}
}
