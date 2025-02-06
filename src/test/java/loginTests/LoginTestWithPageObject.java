package loginTests;

import baseBase.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void TR001_validLogin(){
    pageProvider.getLoginPage().openLoginPage();
    pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto");
    pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
    pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button Sign Out is not Visible",
                pageProvider.getHomePage().isButtonSignOutVisible());
    }

    @Test
    public void TR002_invalidLogin(){
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("!qaauto");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertTrue("Allert - is not visible",
                pageProvider.getLoginPage().isAlertVisible());
    }

    @Test
    public void TR003_invalidPassword(){
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto");
        pageProvider.getLoginPage().enterTextIntoInputPassword("!123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertTrue("Allert - is not visible",
                pageProvider.getLoginPage().isAlertVisible());
    }
}
