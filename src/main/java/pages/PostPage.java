package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage {

    @FindBy(xpath = "//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;

    @FindBy(xpath = "//p[contains(text(), 'Is this post unique?')]")
    private WebElement isThisPostUnique;

    private String locatorForTextThisPostWasWritten = "//*[contains(text(), '%s')]";







    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
        //TODO checkUrl
        //TODO check some element
        return this;
    }
    /*
    * Method for checking is success message displayed
    * doesn't check text in the message
     */

    public PostPage checkIsSuccessMessageDisplayed() {
        Assert.assertTrue("Success message is not displayed", isElementVisible(successMessage));
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectedMessageText) {
        String actualText = successMessage.getText();
        Assert.assertEquals("Text in message", expectedMessageText, actualText);
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {

        clickOnElement(buttonDeletePost);
        return new MyProfilePage(webDriver);
    }


    public PostPage checkIsThisPostUnique(String expectedState) {
        String actualState = isThisPostUnique.getText();
        Assert.assertEquals("State of checkbox", expectedState, actualState);   //yes or no
        return this;
    }

    public PostPage checkTextThisPostWasWrittenIsVisible(String expectedText) {
       Assert.assertTrue(expectedText + " is not visible",
               isElementVisible(String.format(locatorForTextThisPostWasWritten, expectedText)));
        return this;
    }
}
