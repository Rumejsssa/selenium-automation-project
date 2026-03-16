package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTests extends BaseTest {

    @Test
    public void testCompleteCheckout(){

        login("standard_user","secret_sauce");

        addToCart("add-to-cart-sauce-labs-backpack");

        driver.findElement(By.className("shopping_cart_link")).click();

        driver.findElement(By.id("checkout")).click();

        driver.findElement(By.id("first-name")).sendKeys("Test");
        driver.findElement(By.id("last-name")).sendKeys("User");
        driver.findElement(By.id("postal-code")).sendKeys("1000");

        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();

        String message =
                driver.findElement(By.className("complete-header")).getText();

        Assert.assertEquals(message,"Thank you for your order!");

    }

    @Test
    public void testCheckoutEmptyForm(){

        login("standard_user","secret_sauce");

        addToCart("add-to-cart-sauce-labs-backpack");

        driver.findElement(By.className("shopping_cart_link")).click();

        driver.findElement(By.id("checkout")).click();

        driver.findElement(By.id("continue")).click();

        boolean error =
                driver.findElement(By.cssSelector("h3[data-test='error']")).isDisplayed();

        Assert.assertTrue(error);

    }

}