package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestData;
import org.openqa.selenium.By;

public class CartTests extends BaseTest {

    @Test
    public void addSingleItemTest(){

        login(TestData.STANDARD_USER,TestData.PASSWORD);

        addToCart("add-to-cart-sauce-labs-backpack");

        String badge = driver.findElement(By.className("shopping_cart_badge")).getText();

        Assert.assertEquals(badge,"1");

    }

    @Test
    public void addMultipleItemsTest(){

        login(TestData.STANDARD_USER,TestData.PASSWORD);

        addToCart("add-to-cart-sauce-labs-backpack");
        addToCart("add-to-cart-sauce-labs-bike-light");
        addToCart("add-to-cart-sauce-labs-bolt-t-shirt");

        String badge = driver.findElement(By.className("shopping_cart_badge")).getText();

        Assert.assertEquals(badge,"3");

    }

    @Test
    public void removeItemTest(){

        login(TestData.STANDARD_USER,TestData.PASSWORD);

        addToCart("add-to-cart-sauce-labs-backpack");

        driver.findElement(By.id("remove-sauce-labs-backpack")).click();

        boolean exists = !driver.findElements(By.className("shopping_cart_badge")).isEmpty();

        Assert.assertFalse(exists);

    }

}