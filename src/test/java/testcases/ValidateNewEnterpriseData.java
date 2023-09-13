package testcases;

import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class ValidateNewEnterpriseData {
	
	@Parameters({"schema", "environment", "username", "password"})
	@BeforeClass(alwaysRun = true)
	public void initDBConnection(String browser, String environment) throws Exception {
		//todo
	}
	
	@Test(priority = 1, groups = {"sanity"})
	public void validateEnterpriseDataForANewEnterprise(String enterPriseName) throws Exception {
		//todo
	}
	
	@AfterClass(alwaysRun = true)
	public void closeDBCOnnection() {
		//todo the DB close connection
	}
	

}
