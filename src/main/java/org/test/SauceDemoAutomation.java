package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class SauceDemoAutomation {

    WebDriver driver = new ChromeDriver();

    @Test
    public void setup(){

        WebDriverManager.chromedriver().setup();

        String baseUrl = "https://www.saucedemo.com";
        driver.get(baseUrl);

        WebElement username = driver.findElement(By.id("user-name"));
        username.sendKeys("standard_user");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");

        WebElement login = driver.findElement(By.id("login-button"));
        login.submit();

        String givenUrl = "https://www.saucedemo.com/inventory.html";
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(givenUrl, currentUrl);
        System.out.println(currentUrl + "is similar to " + givenUrl);

        WebElement item01 = driver.findElement(By.id("item_4_title_link"));
        item01.click();

        WebElement addToCart = driver.findElement(By.xpath("//button[@id='add-to-cart']"));
        addToCart.click();

        WebElement shoppingCart = driver.findElement(By.id("shopping_cart_container"));
        shoppingCart.click();

        givenUrl = "https://www.saucedemo.com/cart.html";
        currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(givenUrl, currentUrl);
        System.out.println(currentUrl + "is similar to " + givenUrl);

        String item01Name = driver.findElement(By.id("item_4_title_link")).getText();
        Assert.assertEquals(item01Name,"Sauce Labs Backpack");
        System.out.println(item01Name + " is similar to " +"Sauce Labs Backpack");

        String priceString = driver.findElement(By.xpath("//div[@data-test='inventory-item-price']")).getText();
        System.out.println(priceString);
        Assert.assertEquals(priceString,"$29.99");

        double priceDouble = Double.parseDouble(priceString.replace("$",""));
        System.out.println(priceDouble);
        Assert.assertEquals(priceDouble,29.99);

        WebElement removeBtn = driver.findElement(By.id("remove-sauce-labs-backpack"));
        removeBtn.click();

        WebElement removedCart = driver.findElement(By.className("removed_cart_item"));
        Assert.assertEquals(removedCart.getText(), "");

        // 11-Clicking on continue shopping
        WebElement continueShoppingButton = driver.findElement(By.id("continue-shopping"));
        continueShoppingButton.click();

        // 12-Adding any 2 products to the cart (Locator Strategy: name)
        WebElement product2 = driver.findElement(By.name("add-to-cart-sauce-labs-backpack"));
        product2.click();
        WebElement product3 = driver.findElement(By.name("add-to-cart-sauce-labs-onesie"));
        product3.click();

        // 13-Verifying the product name and price is similar to the products you selected
        WebElement cart2 = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a"));
        cart2.click();

        WebElement product4 = driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div"));
        Assert.assertEquals(product4.getText(), "Sauce Labs Backpack");
        WebElement product5 = driver.findElement(By.xpath("//*[@id=\"item_2_title_link\"]/div"));
        Assert.assertEquals(product5.getText(), "Sauce Labs Onesie");
        WebElement price4 = driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]/div[2]/div[2]/div"));
        Assert.assertEquals(price4.getText(), "$29.99");
        WebElement price5 = driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[4]/div[2]/div[2]/div"));
        Assert.assertEquals(price5.getText(), "$7.99");

        // 14-Clicking on checkout
        WebElement checkoutButton = driver.findElement(By.name("checkout"));
        checkoutButton.click();

        // 15-Filling the necessary fields by using suitable element locators
        WebElement firstName = driver.findElement(By.id("first-name"));
        firstName.sendKeys("Hansi");
        WebElement lastName = driver.findElement(By.id("last-name"));
        lastName.sendKeys("Wijayatilake");
        WebElement zipCode = driver.findElement(By.id("postal-code"));
        zipCode.sendKeys("00500");

        // 16-Clicking Continue
        WebElement continueButton = driver.findElement(By.xpath("//*[@id=\"continue\"]"));
        continueButton.click();

        // 17- Verifying the Item Total is similar to the total of the products you selected
        WebElement total = driver.findElement(By.xpath("//div[@data-test='total-label']"));
        Assert.assertEquals(total.getText(), "Total: $41.02");

        // 18-Clicking on finish
        WebElement finishButton = driver.findElement(By.id("finish"));
        finishButton.click();

        // 19-Verifying the Thank You for your order text is visible
        WebElement tyText = driver.findElement(By.xpath("//h2[@data-test='complete-header']"));
        String test = tyText.getText();
        Assert.assertEquals(test, "Thank you for your order!");

        // 20-Verify the URL https://www.saucedemo.com/checkout-complete.html
        String currentUrl2 = driver.getCurrentUrl();
        String expectedUrl1 = "https://www.saucedemo.com/checkout-complete.html";
        Assert.assertEquals(expectedUrl1, currentUrl2);


    }

    @AfterMethod
    public  void exitBrowser(){
        // if (driver!=null){
        driver.quit();
        // }
    }
}
