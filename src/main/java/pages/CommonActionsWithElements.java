package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

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


    //Is element visible (String locator)
    protected boolean isElementVisible(String locator){
        try {
            return isElementVisible(webDriver.findElement(By.xpath(locator)));
        }catch (Exception e){
            logger.info("Element " + locator +" is not displayed");
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


    protected void selectTextInDropDownByVisibleText(WebElement dropDown, String textForSelect) {
        try {
            Select optionsFromDropDown = new Select(dropDown);
            optionsFromDropDown.selectByVisibleText(textForSelect);
            logger.info("Text " + textForSelect + " was selected in DropDown");
    } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void selectValueInDropDown(WebElement dropDown, String valueForSelect) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(valueForSelect);
            logger.info("Value " + valueForSelect + " was selected in DropDown");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }


}
 