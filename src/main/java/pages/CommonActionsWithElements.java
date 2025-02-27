package pages;

import com.beust.ah.A;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait webDriverWait_10, webDriverWait_15; //чекаємо 10 або 15 секунд

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);// ініціалізує елементи описані FindBy
        webDriverWait_10 = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait_15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
    }

    protected void clearAndEnterTextIntoElement(WebElement webElement, String text){
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(" '"+ text+"' " + " was inputed into element " + getElementName(webElement));
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement){
        try {
            webDriverWait_10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info("Element "+ webElement + getElementName(webElement)+ " was clicked");

        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }
    protected boolean isElementVisible(WebElement webElement){
        try {
            boolean state = webElement.isDisplayed();
            if(state){
                logger.info("Element: " + webElement + getElementName(webElement)+ " is displayed");
            }else {
                logger.info("Element: " + webElement + getElementName(webElement)+ "  is not displayed");
            }
            return state;
        }catch (Exception e){
          logger.info("Elemen:t " + webElement + getElementName(webElement)+ " is not displayed");
          return false;
        }
    }
    protected boolean isElementVisible(WebElement webElement,String elementName){
        try {
            boolean state = webElement.isDisplayed();
            if(state){
                logger.info("Element: " + webElement + elementName + " is displayed");
            }else {
                logger.info("Element: " + webElement + elementName + "  is not displayed");
            }
            return state;
        }catch (Exception e){
            logger.info("Elemen:t " + webElement + elementName + " is not displayed");
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

    // accept alert
    public void acceptAlert(){
        try {
            webDriverWait_10.until(ExpectedConditions.alertIsPresent());
            webDriver.switchTo().alert().accept();
            logger.info("Alert was accepted");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    //scroll to element
    public void scrollToElement(WebElement webElement){
        try {
            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement).build().perform();
            logger.info("Scrolled to element " + getElementName(webElement));

        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    //press Enter key using Actions class
    public void pressEnterKey(){
        try {
            Actions actions = new Actions(webDriver);
            actions.sendKeys(Keys.ENTER).build().perform();
            logger.info("Enter key was pressed");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    //execute JavaScript code - open new tab
    public void openNewTab(){
        try {
            ((JavascriptExecutor) webDriver).executeScript("window.open()");
            logger.info("New tab was opened");
        }catch (Exception e){
            printErrorAndStopTest(e);
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
            logger.info("Value " + valueForSelect + " was selected in DropDown" + getElementName(dropDown));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }
    private String getElementName(WebElement webElement){
        String elementName ="";
        try {
            return webElement.getAccessibleName();
        }catch (Exception e){
            logger.error("Can't get element name" + e);
            return elementName;
        }


}
}
 