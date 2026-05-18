package pageObjects;

// baseobject class required for every pageObjects that's y. this will invoked by every page object class constructor(Re-usability)

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

// constructor --- can use for all code
public class BasePage {
	
	WebDriver driver;
	
	public BasePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	

}
