package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class Register extends Base {
	
	public Register() {
		
		super();
	}
	WebDriver driver;
	
	
	@BeforeMethod
	public void setup() {
		
	
		
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));	
    HomePage homePage = new HomePage(driver);
    homePage.clickOnMyAccount();
	homePage.selectRegisterOption();
	
	
	}
	
	@AfterMethod
	public void tearDown() {
	
		driver.quit();
	
	}	
		
	@Test(priority=1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {
		
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterPassword(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectPrivayPolicy();;
		registerPage.clickOnContinueButton();
		AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
		String actualSuccessHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(actualSuccessHeading,dataProp.getProperty("accountSuccessfullyCreatedHeading"), "Account Success Page is not displayed");
		
		
	
	
	
	}


    @Test(priority=2)
  public void verifyRegisteringAccountWithAllFields() {
   
    	
    	
    	RegisterPage registerPage = new RegisterPage(driver);
    	registerPage.enterFirstName(dataProp.getProperty("firstName"));
    	registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivayPolicy();
		registerPage.clickOnContinueButton();
		
		
		
		AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
		String actualSuccessHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(actualSuccessHeading,dataProp.getProperty("accountSuccessfullyCreatedHeading"), "Account Success Page is not displayed");
		
		 
    	
    	
    	
   
  
  }
    
    
    
    
    @Test(priority=3)
    public void registeringAccountWithExistingEmailAddress() {
    	
    
    	RegisterPage registerPage = new RegisterPage(driver);
    	registerPage.enterFirstName(dataProp.getProperty("firstName"));
    	registerPage.enterLastName(dataProp.getProperty("lastName"));
    	registerPage.enterEmailAddress(prop.getProperty("validEmail"));
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivayPolicy();
		registerPage.clickOnContinueButton();
		
		
		String actualWarning = registerPage.retrieveDuplicateEmailAddressWarning();
		Assert.assertTrue(actualWarning.contains(prop.getProperty("duplicateEmailWarning")),"Warning message regarding duplicate email adress is not displayed");
		
		
		
    	
    		
    }  
    
    @Test(priority=4)
    public void verifyAccountWithoutFillingAnyDetils() {
    	
    	
    	RegisterPage registerPage = new RegisterPage(driver);
    	registerPage.clickOnContinueButton();
    	
		
		String actualPrivacyPolicyWarning = registerPage.retrievePrivacyPolicyWarning();
		Assert.assertTrue(actualPrivacyPolicyWarning.contains(dataProp.getProperty("privacyPolicyWarning")),"Privacy policy warning message is not displayed");
		
		String actualFirstNameWarning = registerPage.retrieveFirstNameWarning();
		Assert.assertTrue(actualFirstNameWarning.contains(dataProp.getProperty("FirstNameWarning")),"First name warning message is not displayed");
		
		String actualLastNameWarning = registerPage.retrieveLastNameWarning();
		Assert.assertTrue(actualLastNameWarning.contains(dataProp.getProperty("lastNameWarning")),"Last name warning message is not displayed");
		
		String actualEmailWarning = registerPage.retrieveActualEmailWarning();
		Assert.assertTrue(actualEmailWarning.contains(dataProp.getProperty("emailWarning")),"Email warning message is not displayed");
		
		
		String actualTelephoneWarning = registerPage.retrieveActualTelephoneWarning();
		Assert.assertTrue(actualTelephoneWarning.contains(dataProp.getProperty("telephoneWarning")),"Telephone warning message is not displayed");
		
    	
		String actualPasswordWarning = registerPage.retrieveActualPasswordWarning();
		Assert.assertTrue(actualPasswordWarning.contains(dataProp.getProperty("passwordWarning")),"Telephone warning message is not displayed");
		
		
		
    }
    
    
}









