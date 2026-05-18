package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

    public AccountRegistrationPage(WebDriver driver) {
        super(driver);
    }

    // TutorialsNinja locators

    @FindBy(id="input-firstname")
    WebElement txtFirstname;

    @FindBy(id="input-lastname")
    WebElement txtLastname;

    @FindBy(id="input-email")
    WebElement txtEmail;

    @FindBy(id="input-telephone")
    WebElement txtTelephone;

    @FindBy(id="input-password")
    WebElement txtPassword;

    @FindBy(id="input-confirm")
    WebElement txtConfirmPassword;

    @FindBy(xpath="//input[@name='agree']")
    WebElement chkPolicy;

    @FindBy(xpath="//input[@value='Continue']")
    WebElement btnContinue;

    @FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement msgConfirmation;


    public void setFirstName(String fname) {
        txtFirstname.sendKeys(fname);
    }

    public void setLastName(String lname) {
        txtLastname.sendKeys(lname);
    }

    public void setEmail(String email) {
        txtEmail.sendKeys(email);
    }

    public void setTelephone(String tel) {
        txtTelephone.sendKeys(tel);
    }

    public void setPassword(String pwd) {
        txtPassword.sendKeys(pwd);
    }

    public void setConfirmPassword(String pwd) {
        txtConfirmPassword.sendKeys(pwd);
    }

    public void setPrivacyPolicy() {
        chkPolicy.click();
    }

    public void clickContinue() {
        btnContinue.click();
    }

    public String getConfirmationMsg() {
        try {
            return msgConfirmation.getText();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}