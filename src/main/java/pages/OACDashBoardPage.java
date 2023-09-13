package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OACDashBoardPage extends StartupPage {
	
	//Web elements
		By pageTitleText = By.id("new");
		By filterDropdown = By.xpath("//a[text()='Filter']");
		By searchLevelInputBox = By.id("idSubjectAreasMenuSASearchBoxInput");
		String tabNameGeneric = "//a[text()='value']";
		By searchEnterPriseButton = By.id("SAPaneHeaderToolbar_searchSA_image");
		By searchEnterPriseInputBox = By.id("criteriaDataBrowserSASearchBoxInput");
		By expandArrowForPepsico = By.id("$Operator Review & ClaimsundefinedPepsiCo US_disclosure");
		By productHirechyExpandButton = By.xpath("//div[@id='$Operator Review & ClaimsundefinedPepsiCo US_children']//span[text()='Product Hierarchy (Pepsico)']/../../img");
		By lssXvlLevel = By.xpath("//span[text()='LSS-XVL']");
		
		//Getting the page name
		String pageName = this.getClass().getSimpleName();

		public OACDashBoardPage(WebDriver driver) {
			super(driver);
			commonEvents.waitTillElementLocated(pageTitleText, 120)
						.waitTillElementVisible(pageTitleText, 30);
		}
		
		public String getPageTitle() {
			try {
				return commonEvents.waitTillElementLocated(pageTitleText, 30)
									.waitTillElementVisible(pageTitleText, 30)
									.getText(pageTitleText);
			}catch(Exception e) {
				throw e;
			}
		}
		public void selectAnLevel(String tabName) throws Exception {
			By finalTabName = By.xpath(tabNameGeneric.replace("value", tabName.trim()));
			commonEvents.click(pageTitleText, pageName, pageName)
			            .click(filterDropdown, pageName, pageName)
			            .sendKeys(searchLevelInputBox, tabName)
			            .click(finalTabName, tabName, pageName);
			
		}
		public void selectAnEnterprise(String enterPriseName) throws Exception {

			commonEvents.click(searchEnterPriseButton, pageName, pageName)
			            .sendKeys(searchEnterPriseInputBox, enterPriseName)
			            .click(expandArrowForPepsico, enterPriseName, pageName)
			            .click(productHirechyExpandButton);
			
		}
		
		public Boolean verifyPresenceOfLevelUnderAnEnterprise(String levelName) throws Exception {

			return commonEvents.isDisplayed(lssXvlLevel);
			
		}
		

}
