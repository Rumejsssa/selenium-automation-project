package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestData;

public class LoginTests extends BaseTest {

    @Test(groups = "smoke")
    public void testValidLogin() {

        login(TestData.STANDARD_USER, TestData.PASSWORD);

        Assert.assertEquals(driver.getTitle(), "Swag Labs");
    }

    @Test(groups = "regression")
    public void testInvalidLogin() {

        login(TestData.INVALID_USER, TestData.WRONG_PASSWORD);

        boolean errorDisplayed =
                driver.findElement(By.cssSelector("h3[data-test='error']")).isDisplayed();

        Assert.assertTrue(errorDisplayed);
    }

    @Test(groups = "regression")
    public void testLockedUser() {

        login(TestData.LOCKED_USER, TestData.PASSWORD);

        String error =
                driver.findElement(By.cssSelector("h3[data-test='error']")).getText();

        Assert.assertTrue(error.contains("locked out"));
    }

    @Test(groups = "regression")
    public void testEmptyLogin() {

        driver.findElement(By.id("login-button")).click();

        boolean error =
                driver.findElement(By.cssSelector("h3[data-test='error']")).isDisplayed();

        Assert.assertTrue(error);
    }
    @Test(groups = "regression")
    public void testProductsPageAfterLogin() {

        login(TestData.STANDARD_USER, TestData.PASSWORD);

        boolean productsVisible =
                driver.findElements(By.className("inventory_list")).size() > 0;

        Assert.assertTrue(productsVisible);
    }
}