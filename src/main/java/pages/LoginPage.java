package pages;

import data.TestData;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage{

    @FindBy(xpath = "//input[@placeholder ='Username']")
    private WebElement inputUserNameInLoginForm;

    @FindBy (xpath ="//input[@placeholder ='Password']")
    private WebElement inputPasswordInLoginPage;

    @FindBy (xpath ="//button[text()='Sign In']" )
    private WebElement buttonSignIn;

    @FindBy (xpath = "//div[text()='Invalid username/password.']")
    private WebElement allert;




    private Logger logger = Logger.getLogger(getClass());

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public void openLoginPage(){
        webDriver.get(baseUrl);
        logger.info("Login page was openned" + baseUrl);

    }

    public void enterTextIntoInputLogin (String login) {
//        try {
////            WebElement inputUserNameInLoginForm =
////                    webDriver.findElement(By.xpath("//input[@placeholder ='Username']"));
//            inputUserNameInLoginForm.clear();
//            inputUserNameInLoginForm.sendKeys(login);
//            logger.info( "Login: "+ " '"+ login + "' "+" was inputted into input Login");
//
//        }catch (Exception e){
//            logger.error("Can't work with element: " + e);
//            Assert.fail("Can't work with element: " + e );

        clearAndEnterTextIntoElement(inputUserNameInLoginForm, login);
    }

    public void enterTextIntoInputPassword (String password){
        clearAndEnterTextIntoElement(inputPasswordInLoginPage, password);
    }

    public void clickOnButtonSignIn(){
     clickOnElement(buttonSignIn);
    }

    public boolean isAlertVisible() {
        return isElementVisible(allert);
    }

    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }
}

