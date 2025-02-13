package postTests;

import baseBase.BaseTest;
import org.junit.After;
import org.junit.Test;
import utils.Utils;

public class CreateNewPostTest extends BaseTest {
    //GUID = 1b1b1b1b-1b1b-1b1b-1b1b-1b1b1b1b1b1b
    private final String POST_TITLE = "TR001 CreateNewPostTitle " + Utils.getDateAndTimeFormatted();

    @Test
    public void TR001_createNewPost() {

        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTitleIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Post Body 06022025")
                .clickOnCheckBoxIsThisPostUnique("check") //  set check or uncheck
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkIsThisPostUnique("Is this post unique? : yes") // yes or no
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE, 1)
        ;
    }

    @After
    public void deletePosts() {
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(POST_TITLE)

        ;
    }


}

