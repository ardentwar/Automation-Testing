package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest {
	
	private WebDriver driver;
	@Parameters({"browser"})
	@BeforeMethod(alwaysRun = true)
	private void setUp(String browser)
	{
		switch(browser) {
		case "Chrome" : 
			// Create driver
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			break ;
			
		case "firefox" : {
			// Create driver
			System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe" ); 
			driver = new FirefoxDriver();
			System.setProperty("webdriver.firefox.marionette","src/main/resources/geckodriver.exe");

			break ;
				
		}
		default:
			System.out.println("when dont know which to start" +browser+ "Starting Firefox instead");
			System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe" ); 
			driver = new FirefoxDriver();
			System.setProperty("webdriver.firefox.marionette","src/main/resources/geckodriver.exe");

		
		
		
		
		
		}
		
		
		
		
		
		
		
		// maximise browser window
		driver.manage().window().maximize();
		
	}
	
	@Test(priority=1, groups = { "positiveTest", "smoketest" })
	public void positiveloginTest() {
		System.out.println("Starting Login Test");
		

		

		// open test page
		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("The page is opened");

		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");

		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("SuperSecretPassword!");

		WebElement logInButton = driver.findElement(By.tagName("button"));
		logInButton.click();

		// verifications:
		// new url
		String expectedUrl = "https://the-internet.herokuapp.com/secure";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not as same as expected");

		// logout button is visible
		WebElement logOutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
		Assert.assertTrue(logOutButton.isDisplayed(), "log out button is not visible");

		// Successful login message
		WebElement successMessage = driver.findElement(By.cssSelector("#flash"));
		String expectedMessage = "You logged into a secure area!";
		String actualMessage = successMessage.getText();
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Actual message does not contains expected message\nActual Message: "+actualMessage+ "\nExpected Message: "+expectedMessage);

		

	}


		
		@Parameters({"username","password","expectedMessage"})
		@Test(priority=2, groups = { "NegativeTest", "smoketest" })
		public void negativeLoginTest(String username,String password,String expectedErrorMessage) {
				System.out.println("Starting negativeLogin Test with" + username + "and" +password);
				

				// Create driver
				//System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
				//WebDriver driver = new ChromeDriver();
			

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
		
					
		}
		@AfterMethod(alwaysRun = true)
		private void tearDown() {
			driver.quit();
}
		
}
