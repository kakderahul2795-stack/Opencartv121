package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDDT extends BaseClass {
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="Datadriven")
	public void verify_loginDDT(String email, String pwd, String exp) throws InterruptedException  {
		
		logger.info("**** Starting TC_003_LoginTest ****");	
		
		try
		{ 
			//homepage
			HomePage hp=new HomePage(driver);
			hp.clickAccount();
			hp.clicklogin();
			
			//login
			LoginPage lp=new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(pwd);
			lp.clickLogin();
			
			//MyAccount
			MyAccountPage macc=new MyAccountPage(driver);
			boolean targetPage=macc.isMyAccountPageExists();
			
			if(exp.equalsIgnoreCase("Valid"))
			{
				if(targetPage==true)
				{
					macc.clickLogout();
					Assert.assertTrue(true);
				}
				else
				{
					Assert.assertTrue(false);
				}
			}
			
			if(exp.equalsIgnoreCase("Invalid"))
	 		{
				if(targetPage==true)
				{
					macc.clickLogout();
					Assert.assertTrue(false);
				}
				else
				{
					Assert.assertTrue(true);
				}
			}
			
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		driver.get(p.getProperty("appURL2"));
		
		Thread.sleep(3000);
		logger.info("**** Finished TC_003_LoginTest ****");
	}
}