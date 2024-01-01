package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class Search extends Base {
	
public Search() {
		
		super();
}
	WebDriver driver;
	
	
	@BeforeMethod
	public void setup() {
		
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
		
	}
	
	
	@Test(priority=1)
	public void verifySearchWithValidProduct() {
		
		HomePage HomePage = new HomePage(driver);
		HomePage.enterProductNameIntoSearchBoxField(dataProp.getProperty("validProduct"));
		HomePage.clickOnSearchButton();
		SearchPage searchPage = new SearchPage(driver);
		
		Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(),"Valid product HP is not displayed in search result");

		
	}
	
	
	@Test(priority=2)
	public void verifySearchWithInvalidProduct() {
		
		
		HomePage HomePage = new HomePage(driver);
		HomePage.enterProductNameIntoSearchBoxField(dataProp.getProperty("invalidProduct"));
		HomePage.clickOnSearchButton();
		SearchPage searchPage = new SearchPage(driver);
		String actualSearchMessage = searchPage.retrieveNoProductMessageText();
		Assert.assertEquals(actualSearchMessage,(dataProp.getProperty("NoProductTextInSearchResults")),"No product is displayed in search box ");
		
	}
	
	@Test(priority=3)
	public void verifySearchWithoutAnyProduct() {
		
		HomePage HomePage = new HomePage(driver);
		HomePage.clickOnSearchButton();
		
		SearchPage searchPage = new SearchPage(driver);
		String actualSearchMessage = searchPage.retrieveNoProductMessageText();
		Assert.assertEquals(actualSearchMessage,(dataProp.getProperty("NoProductTextInSearchResults")),"No product is displayed in search box ");
		
		
	}
	
}





