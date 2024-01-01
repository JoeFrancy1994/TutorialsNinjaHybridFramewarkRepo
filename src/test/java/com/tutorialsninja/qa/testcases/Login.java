package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class Login extends Base {
	
	public Login() {
	
	super();
	
	}  
	
	
	WebDriver driver;
	
	@BeforeMethod
	public void setup(){
		
		
		
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver); 
		homePage.clickOnMyAccount();
		homePage.selectLoginOption();
		
		
		
		
	}
	
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}
	
	@Test(priority=1,dataProvider="SuplyTestData")
	public void verifyLoginWithValidCredentials(String email, String password) {
		
	    LoginPage LoginPage = new LoginPage(driver);
	    LoginPage.enterEmailAddress(email);
		LoginPage.enterPassword(password);
		LoginPage.clickOnLoginButton();
		
		AccountPage AccountPage = new AccountPage(driver);
		Assert.assertTrue(AccountPage.getDisplayStatusOfEditYourAccountInformationOption(),"Edit your account information option is not displayed");
		
		
		
	}
	
	@DataProvider(name="validCredentialsSupplier")
	public Object[][] supplyTestData() {
		
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
		
		
	}
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials() {
	
		LoginPage LoginPage = new LoginPage(driver);
	    LoginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
	LoginPage.enterPassword(dataProp.getProperty("invalidPassword"));
	LoginPage.clickOnLoginButton();
	
	
	String actualWarningMessage = LoginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
	String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
	Assert.assertTrue(actualWarningMessage.contains(actualWarningMessage),"Expected Warning Message is not displayed");
	
	
	}
	
	@Test(priority=3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		
		LoginPage LoginPage = new LoginPage(driver);
		LoginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		LoginPage.enterPassword(prop.getProperty("validPassword"));
		LoginPage.clickOnLoginButton();
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		String actualWarningMessage = LoginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(actualWarningMessage),"Expected Warning Message is not displayed");
		
		
		}
		
		@Test(priority=4)
		public void verifyLoginWithValidEmailAndInvalidPassword() {
			
			LoginPage LoginPage = new LoginPage(driver);
			LoginPage.enterEmailAddress(prop.getProperty("validEmail"));
			LoginPage.enterPassword(dataProp.getProperty("invalidPassword"));
			LoginPage.clickOnLoginButton();
			
			
			String actualWarningMessage = LoginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
			String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
			Assert.assertTrue(actualWarningMessage.contains(actualWarningMessage),"Expected Warning Message is not displayed");	
			
		
	}
		
		@Test(priority=5)
		public void verifyLoginWithoutEmailAndPassword() {
			
		   
			LoginPage LoginPage = new LoginPage(driver);
			LoginPage.clickOnLoginButton();
			
			
			String actualWarningMessage = LoginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
			String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
			Assert.assertTrue(actualWarningMessage.contains(actualWarningMessage),"Expected Warning Message is not displayed");	
			
			
		}
	
			
	
		
	}

	
	
	
	
   
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

