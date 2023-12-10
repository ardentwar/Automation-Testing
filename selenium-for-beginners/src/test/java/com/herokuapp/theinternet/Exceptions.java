package com.herokuapp.theinternet;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Exceptions {
	
	private WebDriver driver;
	@BeforeMethod(alwaysRun = true)
	private void setUp()
	{
		
			// Create driver
			System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe" ); 
			driver = new FirefoxDriver();
			System.setProperty("webdriver.firefox.marionette","src/main/resources/geckodriver.exe");

			
				
		
	
		// Maximize browser window
		driver.manage().window().maximize();
		
	}
	@Test
	public void noSuchElementExceptionsTest() {
		
		driver.get("https://practicetestautomation.com/practice-test-exceptions/");
		
		WebElement addButtonElement = driver.findElement(By.id("add_btn"));
		addButtonElement.click();
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(8));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input")));
		
				
		WebElement row2Input = driver.findElement(By.xpath("//div[@id='row2']/input"));
		Assert.assertTrue(row2Input.isDisplayed(),"Row2 is not displayed");
	
	}
	@Test
	public void elementNotInteractableExceptionTest() {
		
driver.get("https://practicetestautomation.com/practice-test-exceptions/");
		
		WebElement addButtonElement = driver.findElement(By.id("add_btn"));
		addButtonElement.click();
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(8));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input")));
		
				
		WebElement row2Input = driver.findElement(By.xpath("//div[@id='row2']/input"));
		Assert.assertTrue(row2Input.isDisplayed(),"Row2 is not displayed");
		
		row2Input.sendKeys("Sushi");
		
		WebElement saveButton = driver.findElement(By.xpath("//div[@id='row2']/button[@name='Save']"));
		saveButton.click();
		
		WebElement confirmationMessage = driver.findElement(By.id("confirmation"));
		String messageText = confirmationMessage.getText();
		Assert.assertEquals(messageText ,"Row 2 was saved", "Confirmation message text is not equal");
		
		
		
	}
	
	
	


	@Test
	public void invalidElementStateExceptionTest() {
		
		//open url
		driver.get("https://practicetestautomation.com/practice-test-exceptions/");
		
		//clear Input field
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(8));
		WebElement row1Input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row1']/input")));
		WebElement editButton = driver.findElement(By.id("edit_btn"));
		editButton.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(row1Input));
		
		row1Input.clear();
		
		//type text into input field
		row1Input.sendKeys("Sushi");
		
		WebElement saveButton = driver.findElement(By.id("save_btn"));
		saveButton.click();
		
		//verify text change
		String value = row1Input.getAttribute("value");
		Assert.assertEquals(value, "Sushi", "The value is not as expected");
		
		WebElement confirmationMessage = driver.findElement(By.id("confirmation"));
		String messageText = confirmationMessage.getText();
		Assert.assertEquals(messageText ,"Row 1 was saved", "Confirmation message text is not equal");
		
}
	@Test
	public void staleElementReferenceExceptionTest() {
		
		
		//Open page
		driver.get("https://practicetestautomation.com/practice-test-exceptions/");
		
		//Find the instructions text element
		WebElement instructionText = driver.findElement(By.id("instructions"));
		
		//Push add button
		WebElement addButtonElement = driver.findElement(By.id("add_btn"));
		addButtonElement.click();
		
		//Verify instruction text element is no longer displayed
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
		Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("instructions"))),"Instructions are still displayed");
		
		
	}
	
	@Test
	public void timeoutExceptionTest() {
		
		//Open page
		driver.get("https://practicetestautomation.com/practice-test-exceptions/");
		
		//Click Add button
		WebElement addButtonElement = driver.findElement(By.id("add_btn"));
		addButtonElement.click();
		
		//Wait for 3 seconds for the second input field to be displayed
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement row2Input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input")));
		
		
		
		//Verify second input field is displayed
		Assert.assertTrue(row2Input.isDisplayed(),"Row2 is not displayed");
		
		
		
		
	}
	
	
	
	@AfterMethod(alwaysRun = true)
	private void tearDown() {
		driver.quit();
	}
}
