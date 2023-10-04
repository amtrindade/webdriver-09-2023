package com.core;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
	
	private static WebDriver driver;
	
	public static WebDriver getDriver() {
				
		if (driver == null) {			
			// chrome / firefox / chrome-headless / firefox-headless		
			String browser = GlobalProperty.getProperty("webdriver.test.browser");
			String path = GlobalProperty.getProperty("webdriver.test.path");
			
			if (browser.equals("chrome")) {
				
				System.setProperty("webdriver.chrome.driver", path + "chromedriver");		
				driver = new ChromeDriver();	
								
			}
			else if (browser.equals("chrome-headless")) {
				
				System.setProperty("webdriver.chrome.driver", path + "chromedriver");
				
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				options.addArguments("--windows-size=1200x960");
				
				driver = new ChromeDriver(options);
				
			}			
			else if (browser.equals("firefox")) {
				System.setProperty("webdriver.geckodriver.driver", path + "geckodriver");
				driver = new FirefoxDriver();				
				
			}
			else if (browser.equals("firefox-headless")) {
				System.setProperty("webdriver.geckodriver.driver", path + "geckodriver");
				
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless");
				options.addArguments("--windows-size=1200x960");
				
				driver = new FirefoxDriver(options);
			}
			else {
				System.out.println("Driver não está sendo reconhecido");
			}
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			driver.manage().window().maximize();
		}
		
		return driver;
	}
	
	public static void killDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
	
	
	

}
