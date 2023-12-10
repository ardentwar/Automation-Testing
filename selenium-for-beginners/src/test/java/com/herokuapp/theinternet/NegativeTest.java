package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NegativeTest {
	
	@Parameters({"username","password","expectedMessage"})
	@Test(priority=1, groups = { "NegativeTest", "smoketest" })
	public void negativeLoginTest(String username,String password,String expectedErrorMessage) {
			System.out.println("Starting negativeLogin Test with" + username + "and" +password);
			

			// Create driver
			//System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			//WebDriver driver = new ChromeDriver();
			System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe" ); 
			WebDriver driver = new FirefoxDriver();
			System.setProperty("webdriver.firefox.marionette","src/main/resources/geckodriver.exe");

			// maximise browser window
			driver.manage().window().maximize();

			// open test page
			String url = "https://the-internet.herokuapp.com/login";
			driver.get(url);
			System.out.println("The page is opened");

			WebElement usernameElement = driver.findElement(By.id("username"));
			usernameElement.sendKeys(username);

			WebElement passwordElement = driver.findElement(By.name("password"));
			passwordElement.sendKeys(password);

			WebElement logInButton = driver.findElement(By.tagName("button"));
			logInButton.click();
			
			//Verifications
			WebElement errorMessage = driver.findElement(By.id("flash"));
			String actualErrorMessage = errorMessage.getText();
			Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),"Actual error message does not contains expected.\nActual:"+ actualErrorMessage+"\nExpected:"+ expectedErrorMessage+"");
	
			driver.quit();		
	}
	/*
	 * @Test(priority=2 ,groups = { "NegativeTest"}) public void
	 * IncorrectPasswordTest() {
	 * System.out.println("Starting IncorrectPassword Test");
	 * 
	 * 
	 * // Create driver //System.setProperty("webdriver.chrome.driver",
	 * "src/main/resources/chromedriver.exe"); //WebDriver driver = new
	 * ChromeDriver(); System.setProperty("webdriver.gecko.driver",
	 * "src/main/resources/geckodriver.exe" ); WebDriver driver = new
	 * FirefoxDriver(); System.setProperty("webdriver.firefox.marionette",
	 * "src/main/resources/geckodriver.exe");
	 * 
	 * // maximise browser window driver.manage().window().maximize();
	 * 
	 * // open test page String url = "https://the-internet.herokuapp.com/login";
	 * driver.get(url); System.out.println("The page is opened");
	 * 
	 * WebElement username = driver.findElement(By.id("username"));
	 * username.sendKeys("tomsmith");
	 * 
	 * WebElement password = driver.findElement(By.name("password"));
	 * password.sendKeys("incorrect password");
	 * 
	 * WebElement logInButton = driver.findElement(By.tagName("button"));
	 * logInButton.click();
	 * 
	 * //Verifications WebElement errorMessage = driver.findElement(By.id("flash"));
	 * String expectedErrorMessage = "Your password is invalid!"; String
	 * actualErrorMessage = errorMessage.getText();
	 * Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage)
	 * ,"Actual error message does not contains expected.\nActual:"+
	 * actualErrorMessage+"\nExpected:"+ expectedErrorMessage+"");
	 * 
	 * driver.quit();
	 * 
	 * }
	 */
	
}

