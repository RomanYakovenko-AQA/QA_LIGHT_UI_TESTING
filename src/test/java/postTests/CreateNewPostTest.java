package postTests;

import baseBase.BaseTest;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    @Test
    public void TR001_createNewPost(){
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreatPostPage()
                .enterTitleIntoInputTitle("Title of the post 06022025")
                .enterTextIntoInputBody("Post Body 06022025")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
        ;
    }

    }

