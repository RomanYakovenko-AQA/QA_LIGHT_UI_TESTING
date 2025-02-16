package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage {

    private String postTitleLocator = "//*[text()='%s']"; //locator with parameter

   @FindBy(xpath = "//*[text()='Post successfully deleted.']")
    private WebElement successMessageDelete;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/profile/[a-zA-Z0-9]*";
    }

    private List<WebElement> postsListWithTitle(String postTitle) {
        return webDriver.findElements(By.xpath(String.format(postTitleLocator, postTitle)));
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        checkUrlWithPattern();
        //TODO check some element
        return this;
    }

    public MyProfilePage checkPostWithTitleIsPresent(String postTitle, int expectedNumberOfPosts) {
    Assert.assertEquals("Number of posts with title " + postTitle, expectedNumberOfPosts, postsListWithTitle(postTitle).size());
        return this;
    }

    public MyProfilePage deletePostsTillPresent(String postTitle) {
       List<WebElement> postsList = postsListWithTitle(postTitle);
       final int MAX_POST_COUNT = 100; //postList.size()
        int counter = 0;

       while (!postsList.isEmpty() && (counter < MAX_POST_COUNT)) {

       clickOnElement(postsList.get(0));
         new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsMessageSuccesDeletePresent();
         logger.info("Post with title " + postTitle + " was deleted");
         postsList = postsListWithTitle(postTitle);
        counter++;
       }
       if (counter >= MAX_POST_COUNT) {
           logger.warn("Number of posts with title " + postTitle + " more than " + MAX_POST_COUNT);

       }

        return this;
    }

    private MyProfilePage checkIsMessageSuccesDeletePresent() {
        //check is message success delete present
        Assert.assertTrue("Success message is not displayed", isElementVisible(successMessageDelete));
        return this;
    }
}
