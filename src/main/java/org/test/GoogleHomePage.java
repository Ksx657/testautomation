package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoogleHomePage {

    private WebDriver driver;

    public GoogleHomePage(WebDriver driver) {
        this.driver = driver;
    }

    private By searchBar = By.xpath("//*[@id=\"APjFqb\"]");

    public void search(){
        driver.findElement(searchBar).sendKeys("OpenAI");
    }

    public void submit(){
        driver.findElement(searchBar).submit();
    }
}
