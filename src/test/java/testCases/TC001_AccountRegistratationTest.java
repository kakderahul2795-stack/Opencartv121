package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistratationTest extends BaseClass {
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
	    logger.info("***** Starting TC001_AccountRegistratationTest ****");
	    
	    try
	    {
	        HomePage hp = new HomePage(driver);
	        hp.clickAccount();
	        logger.info("clicked on Myaccount link");
	        
	        hp.clickRegister();
	        logger.info("clicked on Register link");

	        AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
	        
	        logger.info("Providing customer details");
	        regpage.setFirstName(randomString().toUpperCase()); 
	        regpage.setLastName(randomString().toUpperCase());
	        regpage.setEmail(randomString()+"@gmail.com"); 
	        regpage.setTelephone(randomNumber());
	                
	        String password = randomAlphaNumeric();        
	        
	        regpage.setPassword(password); 
	        regpage.setConfirmPassword(password);
	        
	        regpage.setPrivacyPolicy(); 
	        regpage.clickContinue();
	        
	        logger.info("Validating expected message...");
	        String confmsg = regpage.getConfirmationMsg();

	        if(confmsg.contains("Your Account Has Been Created!"))
	        {
	            Assert.assertTrue(true);                
	        }
	        else
	        {
	            logger.error("Test failed..");
	            Assert.assertTrue(false);
	        }    
	    }
	    catch(Exception e)
	    {
	        Assert.fail();
	    }

	    logger.info("***** Finished TC001_AccountRegistratationTest ****");
	}
}