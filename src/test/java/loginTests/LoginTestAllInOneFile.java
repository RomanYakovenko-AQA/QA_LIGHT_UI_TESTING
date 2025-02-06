package loginTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class  LoginTestAllInOneFile {

    private WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

    @Before
    public void setup(){
        logger.info("Test started");
        WebDriverManager.chromedriver().setup();
        webDriver =new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        logger.info("Browser was opened");



    }
    @After
    public void tearDown(){
        webDriver.quit();
        logger.info("Browser was closed");
        logger.info("Test finished");
        logger.info("------------");

    }
    @Test
    public void validLogin(){
        webDriver.get("https://aqa-complexapp.onrender.com/");
        logger.info("Site was opened");

        WebElement inputUserNameInLoginForm =
                webDriver.findElement(By.xpath("//input[@placeholder ='Username']"));
                inputUserNameInLoginForm.clear();
                inputUserNameInLoginForm.sendKeys("qaauto");
                logger.info("qaauto was inputted into input Login");

        WebElement inputPasswordInLoginForm =
                webDriver.findElement(By.xpath("//input[@placeholder ='Password']"));
                inputPasswordInLoginForm.clear();
                inputPasswordInLoginForm.sendKeys("123456qwerty");
                logger.info("Password was inputted into input Password");

        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Button Sign In Was clicked");

        Assert.assertTrue("Button Sign Out is not visible",isButtonSignOutVisible());
    }

    private boolean isButtonSignOutVisible() {
        try {


            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info(state + " is element 'Sign Out' displayed");
            return state;
        } catch (Exception e){
            logger.info("'Sign Out' is not present on page");
            return false;
        }
    }

    @Test
    public void invalidLogin(){
        webDriver.get("https://aqa-complexapp.onrender.com/");
        logger.info("Site was opened");

        WebElement inputUserNameInLoginForm =
                webDriver.findElement(By.xpath("//input[@placeholder ='Username']"));
        inputUserNameInLoginForm.clear();
        inputUserNameInLoginForm.sendKeys("1qaauto");
        logger.info("1qaauto was inputted into input Login");

        WebElement inputPasswordInLoginForm =
                webDriver.findElement(By.xpath("//input[@placeholder ='Password']"));
        inputPasswordInLoginForm.clear();
        inputPasswordInLoginForm.sendKeys("123456qwerty");
        logger.info("Password was inputted into input Password");

        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Button Sign In Was clicked");

        Assert.assertTrue("Button Sign In is not visible",isButtonSignInVisible());
        Assert.assertTrue("Alert is visible ",isAlertVisible());
        Assert.assertFalse("Element Button Sign Out is not visible",isButtonSignOutVisible());
    }

    private boolean isButtonSignInVisible() {
        try {


            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign In']")).isDisplayed();
            logger.info(state + " Button Sign In is displayed");
            return state;
        } catch (Exception e){
            logger.info("Element Button Sign In is not present on page");
            return false;
        }
    }
    private boolean isAlertVisible() {
        try {


            boolean state = webDriver.findElement(By.xpath("//div[text()='Invalid username/password.']")).isDisplayed();
            logger.info(state + "Alert 'Invalid username/password.' is element displayed");
            return state;
        } catch (Exception e){
            logger.info("Alert 'Invalid username/password.' is not present on page");
            return false;
        }
    }

}
