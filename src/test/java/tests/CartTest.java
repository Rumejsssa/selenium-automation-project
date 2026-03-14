package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTests extends BaseTest {

    @Test
    public void testAddSingleItem(){

        login("standard_user","secret_sauce");

        addToCart("add-to-cart-sauce-labs-backpack");

        String cartBadge =
                driver.findElement(By.className("shopping_cart_badge")).getText();

        Assert.assertEquals(cartBadge,"1");

    }

    @Test
    public void testAddMultipleItems(){

        login("standard_user","secret_sauce");
        addToCart("add-to-cart-sauce-labs-backpack");
        addToCart("add-to-cart-sauce-labs-bike-light");
        addToCart("add-to-cart-sauce-labs-bolt-t-shirt");

        String cartBadge =
                driver.findElement(By.className("shopping_cart_badge")).getText();

        Assert.assertEquals(cartBadge,"3");

    }

    @Test
    public void testRemoveFromCart(){

        login("standard_user","secret_sauce");

        addToCart("add-to-cart-sauce-labs-backpack");

        driver.findElement(By.id("remove-sauce-labs-backpack")).click();

        boolean badgeExists =
                driver.findElements(By.className("shopping_cart_badge")).size()>0;

        Assert.assertFalse(badgeExists);

    }

}