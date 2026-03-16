package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test(groups = "smoke")
    public void testValidLogin() {

        login("standard_user","secret_sauce");

        Assert.assertEquals(driver.getTitle(),"Swag Labs");

    }

    @Test(groups = "regression")
    public void testInvalidLogin() {

        login("invalid_user","wrong_password");

        boolean errorDisplayed =
                driver.findElement(By.cssSelector("h3[data-test='error']")).isDisplayed();

        Assert.assertTrue(errorDisplayed);

    }

    @Test(groups = "regression")
    public void testLockedUser() {

        login("locked_out_user","secret_sauce");

        String error =
                driver.findElement(By.cssSelector("h3[data-test='error']")).getText();

        Assert.assertTrue(error.contains("locked out"));

    }

}