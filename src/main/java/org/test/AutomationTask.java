package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AutomationTask {

    @Test
    public void setup(){
        WebDriver driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();

        String baseUrl = "https://www.google.com";
        driver.get(baseUrl);

//        driver.findElement
//                        (By.xpath("//textarea[@title='Search']"))
//                .sendKeys("Open AI");

        WebElement searchBar = driver.findElement(By.xpath("//textarea[@title='Search']"));
        searchBar.sendKeys("Open AI");
        searchBar.submit();

        String newURL = driver.getCurrentUrl();

//        if (!newURL.equals(baseUrl)){
//            System.out.println("Verified");
//        }
        Assert.assertNotEquals(newURL, baseUrl,"Verified the base URL");
        System.out.println("Verified");
    }

    @Test
    public void searchWithPageObjectModel(){
        WebDriver driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();

        String baseUrl = "https://www.google.com";
        driver.get(baseUrl);

        org.example.GoogleHomePage googleHomePage = new org.example.GoogleHomePage(driver);
        googleHomePage.search();
        googleHomePage.submit();


    }
}
