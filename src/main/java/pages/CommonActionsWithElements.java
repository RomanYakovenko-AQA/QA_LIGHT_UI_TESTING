package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this); // ініціалізує елементи описані FindBy
    }

    protected void clearAndEnterTextIntoElement(WebElement webElement, String text){
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(" '"+ text+"' " + " was inputed into element ");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement){
        try {
            webElement.click();
            logger.info("Element "+ webElement + " was clicked");

        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }
    protected boolean isElementVisible(WebElement webElement){
        try {
            boolean state = webElement.isDisplayed();
            if(state){
                logger.info("Element " + webElement + " is displayed");
            }else {
                logger.info("Element " + webElement + "  is not displayed");
            }
            return state;
        }catch (Exception e){
          logger.info("Element " + webElement +" is not displayed");
          return false;
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

    // set checkbox state
    protected void setCheckBoxToNeededState(WebElement element, String neededState){
        if ("check".equals(neededState) || "uncheck".equals(neededState)){
            if (element.isSelected() && "check".equals(neededState)){
                logger.info("Checkbox is already checked");
            } else if (element.isSelected() && "uncheck".equals(neededState)){
                clickOnElement(element);
            } else if (!element.isSelected() && "check".equals(neededState)){
                clickOnElement(element);
            } else {
                logger.info("Checkbox is already unchecked");
            }
        } else {
            logger.error("State should be 'check' or 'uncheck'");
            Assert.fail("State should be 'check' or 'uncheck'");
        }
    }




}
 