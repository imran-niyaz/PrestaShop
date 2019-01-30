package com.prestashop.utilities;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;

public class TestBase {

   public static WebDriver driver;
   SoftAssert softAssert;
  protected Faker faker;
  protected   Select select;

  protected Actions actions;



    @BeforeMethod
    public  void beforeEachtest(){

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        actions = new Actions(driver);

        softAssert = new SoftAssert();


//        ChromeDriverManager.getInstance(CHROME).setup();
//        ChromeOptions chromeOptions = new ChromeOptions();
//
//        chromeOptions.addArguments("--headless");
//        driver = new ChromeDriver(chromeOptions);


    }


    public static void search(String url){
        driver.get("https://www.Google.com");
        WebElement search = driver.findElement(By.name("q"));

        //  Go to http://automationpractice.com/index.php
        search.sendKeys(url + Keys.ENTER);
        WebElement clickLink = driver.findElement(By.xpath("//*[@class='LC20lb']"));
        clickLink.click();
    }


    // this is headless driver method it checks the website whithout going to website
    public static void setup() {

        ChromeDriverManager.getInstance(CHROME).setup();
        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
    }

}
