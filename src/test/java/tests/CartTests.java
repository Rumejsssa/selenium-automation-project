package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestData;
import org.openqa.selenium.By;

public class CartTests extends BaseTest {

    @Test(groups = "smoke")
    public void addSingleItemTest() {

        login(TestData.STANDARD_USER, TestData.PASSWORD);

        addToCart("add-to-cart-sauce-labs-backpack");

        String badge = driver.findElement(By.className("shopping_cart_badge")).getText();

        Assert.assertEquals(badge, "1");
    }

    @Test(groups = "regression")
    public void addMultipleItemsTest() {

        login(TestData.STANDARD_USER, TestData.PASSWORD);

        addToCart("add-to-cart-sauce-labs-backpack");
        addToCart("add-to-cart-sauce-labs-bike-light");
        addToCart("add-to-cart-sauce-labs-bolt-t-shirt");

        String badge = driver.findElement(By.className("shopping_cart_badge")).getText();

        Assert.assertEquals(badge, "3");
    }

    @Test(groups = "regression")
    public void openCartTest() {

        login(TestData.STANDARD_USER, TestData.PASSWORD);

        driver.findElement(By.className("shopping_cart_link")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("cart"));
    }
    @Test(groups = "regression")
    public void testAddButtonChangesToRemove() {

        login(TestData.STANDARD_USER, TestData.PASSWORD);

        addToCart("add-to-cart-sauce-labs-backpack");

        boolean removeButton =
                driver.findElement(By.id("remove-sauce-labs-backpack")).isDisplayed();

        Assert.assertTrue(removeButton);
    }
    @Test(groups = "regression")
    public void testOpenProductDetails() {

        login(TestData.STANDARD_USER, TestData.PASSWORD);

        driver.findElements(By.className("inventory_item_name")).get(0).click();

        boolean productPage =
                driver.findElement(By.className("inventory_details_name")).isDisplayed();

        Assert.assertTrue(productPage);
    }
}