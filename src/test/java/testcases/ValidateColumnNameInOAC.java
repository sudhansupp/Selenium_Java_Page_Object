package testcases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import coreUtilities.testutils.ApiHelper;
import coreUtilities.utils.FileOperations;
import pages.LoginPage;
import pages.OACDashBoardPage;
import pages.StartupPage;
import testBase.AppTestBase;

public class ValidateColumnNameInOAC extends AppTestBase {
	
	Map<String, String> configData;
	Map<String, String> loginCredentials;
	
	StartupPage startupPage;
	
	@Parameters({"browser", "environment","isDBTestcase"})
	@BeforeClass(alwaysRun = true)
	public void initBrowser(String browser, String environment, String isDBTestcase) throws Exception {
		if(!isDBTestcase.equals("true")) {
		
		configData = new FileOperations().readJson(config_filePath, environment);
		configData.put("url", configData.get("url").replaceAll("[\\\\]", ""));
		configData.put("browser", browser);
		
		boolean isValidUrl = new ApiHelper().isValidUrl(configData.get("url"));
		Assert.assertTrue(isValidUrl, configData.get("url")+" might be down at this moment. Please try after sometime.");
		initialize(configData);
		startupPage = new StartupPage(driver);}
		else {
			//todo: write the db connectivity code here
			//
		}
	}
	
	@Test(priority = 1, groups = {"sudipta"})
	public void navigateToOACDashBoardTest() throws Exception {
		softAssert = new SoftAssert();
		
		String loginDataFilePath = testDataFilePath+"Login.json";
		String expectedDataFilePath = testDataFilePath+"expected_data.json";
		Map<String, String> expectedData = new FileOperations().readJson(expectedDataFilePath, "OAC_Dashboard");
		System.out.println(" loginDataFilePath is "+loginDataFilePath);
		Map<String, String> loginData = new FileOperations().readJson(loginDataFilePath, "credentials");
		
		LoginPage loginPage = startupPage.navigateToLoginPage();
		//ProductPage productPage = loginPage.login_procee_to_OAC(loginData);
		OACDashBoardPage oacDashboard=loginPage.login_proceed_to_OAC_Using_Sikuli(loginData);
		String confirmationMessage=oacDashboard.getPageTitle();
		System.out.println("confirmationMessage is "+confirmationMessage);
		System.out.println("expected data is "+expectedData.get("pageTitle"));
		softAssert.assertEquals(confirmationMessage, expectedData.get("pageTitle"), 
				"Actual confirmation message is not matching with the expected confirmation message");
		oacDashboard.selectAnLevel("Operator Review & Claims");
		oacDashboard.selectAnEnterprise("PepsiCo US");
		boolean levelCheck=oacDashboard.verifyPresenceOfLevelUnderAnEnterprise("LSS-XVL");
		softAssert.assertEquals(levelCheck, true, 
				"Failed while checking the presence of the level LSS-XVL in under Product Hirechy for the enterpise PepsiCo US. Please check manually");
		
		softAssert.assertAll();
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		System.out.println("before closing the browser");
		browserTearDown();
	}
	
	@AfterMethod
	public void retryIfTestFails() throws Exception {
		startupPage.navigateToUrl(configData.get("url"));  
		
	}
	
	
	
}
