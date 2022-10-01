package com.jquery;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jQuery.dataTable.HomePageObject;
import pageObjects.jQuery.dataTable.PageGeneratorManager;

public class Level_10_DataTable_DataGrid extends BaseTest {

	private WebDriver driver;
	HomePageObject homePage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
	}

//	@Test
//	public void Table_01_Paging() {
//		homePage.openPagingByPageNumber("10");
//		homePage.sleepInSecond(3);
//		// Verify something
//		Assert.assertTrue(homePage.isPageNumberActived("10"));
//	}

//	@Test
//	public void Table_02_Enter_To_Header() {
//		homePage.refreshCurrentPage(driver);
//		homePage.enterToHeaderTextboxByLabel("Country", "Argentina");
//		homePage.enterToHeaderTextboxByLabel("Females", "338282");
//		homePage.enterToHeaderTextboxByLabel("Males", "348238");
//		homePage.enterToHeaderTextboxByLabel("Total", "687522");
//		homePage.sleepInSecond(3);
//
//		homePage.enterToHeaderTextboxByLabel("Country", "Angola");
//		homePage.enterToHeaderTextboxByLabel("Females", "276880");
//		homePage.enterToHeaderTextboxByLabel("Males", "276472");
//		homePage.enterToHeaderTextboxByLabel("Total", "553353");
//		homePage.sleepInSecond(3);
//	}

//	@Test
//	public void Table_03_Customer_Info() {
//		homePage.getValuesEachRowAtAllPage();
//	}

	@Test
	public void Table_04_Enter_To_Textbox_At_Any_Row() {
		homePage.enterToTextboxAtRowNumberByColumnName("Album", "1", "Michael 97");
		homePage.enterToTextboxAtRowNumberByColumnName("Artist", "1", "Michael Jackson");
		homePage.enterToTextboxAtRowNumberByColumnName("Year", "1", "1997");
		homePage.enterToTextboxAtRowNumberByColumnName("Price", "1", "15");

		homePage.selectDropdownByColumnNameAtRowNumber("Origin", "1", "Japan");

		homePage.clickToLoadButton();

		homePage.checkToCheckboxByColumnNameAtRowNumber("With Poster?", "3");
		homePage.checkToCheckboxByColumnNameAtRowNumber("With Poster?", "5");
		homePage.uncheckToCheckboxByColumnNameAtRowNumber("With Poster?", "1");
		homePage.uncheckToCheckboxByColumnNameAtRowNumber("With Poster?", "2");
		homePage.uncheckToCheckboxByColumnNameAtRowNumber("With Poster?", "4");
	}

	public int generateFakeNumber() {
		Random rand = new Random();
		return (int) rand.nextInt(9999);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
