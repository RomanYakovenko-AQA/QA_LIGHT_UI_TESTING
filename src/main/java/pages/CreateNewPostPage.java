package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewPostPage extends ParentPage {
    @FindBy(xpath = "//*[@id='post-title']")
    private WebElement inputTitle;

    @FindBy(xpath = "//*[@id='post-body']")
    private WebElement textAreaBody;

    @FindBy(xpath = "//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(xpath = "//input [@name='uniquePost']")
    private WebElement checkBoxIsThisPostUnique;

    @FindBy(xpath = "//select")
    private WebElement dropDownAccess;

    public CreateNewPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreateNewPostPage checkIsRedirectToCreatePostPage() {
        //TODO checkUrl
        //TODO check some element
        return this;
    }

    public CreateNewPostPage enterTitleIntoInputTitle(String postTitle) {
        clearAndEnterTextIntoElement(inputTitle, postTitle);
        return this;
    }

    public CreateNewPostPage enterTextIntoInputBody(String postBody) {
        clearAndEnterTextIntoElement(textAreaBody, postBody);
        return this;
    }

    public PostPage clickOnButtonSaveNewPost() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

    //set checkbox true or false on this page
    public CreateNewPostPage setStateInCheckBoxIsThisPostUnique(String neededState) {
        setCheckBoxToNeededState(checkBoxIsThisPostUnique, neededState);
        return this;
    }


    public CreateNewPostPage selectTextInDropDownAccessByVisibleText(String textForSelect) {
        selectTextInDropDownByVisibleText(dropDownAccess, textForSelect);
        return this;
    }

    public CreateNewPostPage selectValueInDropDownAccess(String valueForSelect) {

        selectValueInDropDown(dropDownAccess, valueForSelect);
        return this;
    }


}


