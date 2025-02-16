package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;



abstract class ParentPage extends CommonActionsWithElements {

    protected Logger logger = Logger.getLogger(getClass());
    String baseUrl = "https://aqa-complexapp.onrender.com";

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    abstract String getRelativeUrl();

    // method for checking static URL
    protected void checkUrl() {
        Assert.assertEquals("URL is not expected",
                baseUrl + getRelativeUrl(),
                webDriver.getCurrentUrl());
    }

    //метод по перевірці чи відкрита потрібна сторінка по патерну
    //https://aqa-complexapp.onrender.com/post/67b20bc079a879004993bc52
    // regex for 67b20bc079a879004993bc52
    // [a-zA-Z0-9]{24}
    // https://aqa-complexapp.onrender.com/post/[a-zA-Z0-9]{24}

    protected void checkUrlWithPattern() {
        Assert.assertTrue("URL is not expected \n" +
                        "Expected URL should be: " + baseUrl + getRelativeUrl() +
                        "\n Actual URL: " + webDriver.getCurrentUrl(),
                webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl()));
    }


}
