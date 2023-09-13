package pages;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class LoginPage extends StartupPage {
	
	//Web elements
	By usernameField = By.name("email");
	By passwordField = By.name("password");
	By loginButton = By.name("submit");
	By yesButton = By.xpath("//input[@type=\"submit\"]");

	public String sikuli_image_filePath = System.getProperty("user.dir")+"/src/main/resources/sikuli_images/";
	
	//Getting the page name
	String pageName = this.getClass().getSimpleName();
			

	public LoginPage(WebDriver driver) {
		super(driver);
		commonEvents.waitTillElementLocated(usernameField, 120)
					.waitTillElementVisible(usernameField, 30);
	}
	
	private void enterUsername(String username) {
		commonEvents.waitTillElementLocated(usernameField, 30)
					.waitTillElementVisible(usernameField, 30)
					.sendKeys(usernameField, username);
	}
	
	private void enterPassword(String password) {
		commonEvents.waitTillElementLocated(passwordField, 30)
					.waitTillElementVisible(passwordField, 30)
					.sendKeys(passwordField, password);
	}
	
	private void clickLogin() {
		commonEvents.waitTillElementLocated(loginButton, 30)
					.waitTillElementVisible(loginButton, 30)
					.click(loginButton);
	}
	
	public OACDashBoardPage login_procee_to_OAC(Map<String, String> map) {
		try {
			enterUsername(map.get("Username"));
			//enterPassword(map.get("Password"));
			clickLogin();
			return new OACDashBoardPage(driver);
		}catch(Exception e) {
			throw e;
		}
	}
	
	public OACDashBoardPage login_proceed_to_OAC_Using_Sikuli(Map<String, String> map) throws Exception {
		try {
			enterUsername(map.get("Username"));
			//enterPassword(map.get("Password"));
			clickLogin();
			Thread.sleep(10000);
			System.out.println("before inputting the password"+sikuli_image_filePath+"password_input.png");
			
			commonEvents.inputMicrosoftPasswordUsingSikuli(sikuli_image_filePath+"password_input.png", "BiswaPnku1989@");
			commonEvents.clickOnAnElementIfItIsPresent(yesButton);
			return new OACDashBoardPage(driver);
		}catch(Exception e) {
			throw e;
		}
	}

}
