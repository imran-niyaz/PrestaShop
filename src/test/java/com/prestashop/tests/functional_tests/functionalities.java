package com.prestashop.tests.functional_tests;

import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class functionalities extends TestBase {


    @Test
    public void Registration() throws InterruptedException {

        //1.Go to website
        search("http://automationpractice.com/index.php");

       // 2.Click on sign in button
        driver.findElement(By.className("login")).click();

        //3.click on typing box on top of Create an account button
        //and 4.Input your email address
        driver.findElement(By.id("email_create")).sendKeys("imranbabur1@gmail.com");
        Thread.sleep(3000);

        //5.Click on create an account button
        driver.findElement(By.id("SubmitCreate")).click();
        Thread.sleep(3000);

        //6.Fill all the required fields
        driver.findElement(By.xpath("//label//div[@class='radio'and @id='uniform-id_gender1']")).click();
        Thread.sleep(3000);

        driver.findElement(By.id("customer_firstname")).sendKeys("imran");

        driver.findElement(By.id("customer_lastname")).sendKeys("babur");

        driver.findElement(By.id("email")).sendKeys(faker.name().fullName()+"@gmail.com");

        driver.findElement(By.id("passwd")).sendKeys("123456789can");

        driver.findElement(By.id("address1")).sendKeys(faker.address().streetAddress());

        driver.findElement(By.id("city")).sendKeys("Fairfax");

        driver.findElement(By.id("postcode")).sendKeys("200030");

        String states = driver.findElement(By.id("id_state")).getAttribute("value");
        System.out.println(states);
        Select selectState = new Select(driver.findElement(By.id("id_state")));


        Select select = new Select(driver.findElement(By.id("id_country")));

        select.getOptions().get(1).click();

        driver.findElement(By.id("phone")).sendKeys(faker.phoneNumber().phoneNumber());

        //7.click on Register button
        driver.findElement(By.id("submitAccount")).click();

        driver.quit();


    }


    @Test
    public void Login_PositiveTest (){

        search("http://automationpractice.com/index.php");

        driver.findElement(By.className("login")).click();

        driver.findElement(By.id("email")).sendKeys("imranbabur111@gmail.com");

        driver.findElement(By.id("passwd")).sendKeys("123456789can");

        driver.findElement(By.xpath("//button[@id='SubmitLogin']//span")).click();

        driver.quit();
    }

    @Test
    public void Login_NegativeTest(){

        search("http://automationpractice.com/index.php");

        driver.findElement(By.className("login")).click();

        driver.findElement(By.id("email")).sendKeys("imran@gmail.com");

        driver.findElement(By.id("passwd")).sendKeys("123abc");

        driver.findElement(By.xpath("//button[@id='SubmitLogin']//span")).click();

        driver.quit();

    }

    @Test
    public void Search(){

        search("http://automationpractice.com/index.php");

        driver.findElement(By.id("search_query_top")).sendKeys("Printed Dress"+ Keys.ENTER);

        driver.quit();

    }


    @Test
    public void productInformation() throws InterruptedException {

        search("http://automationpractice.com/index.php");
        Thread.sleep(3000);

        actions.sendKeys(Keys.PAGE_DOWN).perform();
        WebElement clinOnProduct = driver.findElement(By.xpath("(//h5[@itemprop='name']//a[@title='Blouse'])[1]"));
        clinOnProduct.click();

        WebElement productInfo = driver.findElement(By.xpath("(//section[@class='page-product-box'])[2]"));
        Thread.sleep(2000);

        productInfo.click();
        Thread.sleep(2000);

        Assert.assertTrue(productInfo.isDisplayed());
        Thread.sleep(2000);

        driver.quit();

    }


     @Test
    public void AddToCart() throws InterruptedException {

         search("http://automationpractice.com/index.php");

         Thread.sleep(3000);

         WebElement More = driver.findElement(By.xpath("(//a[@class='product-name'])[2]"));
         More.click();
         Thread.sleep(3000);

         WebElement addtoCArt = driver.findElement(By.xpath("//span[contains(text(),'Add to cart')]"));
         addtoCArt.click();
         Thread.sleep(3000);

         driver.quit();

     }

     @Test
    public void CheckOuts() throws InterruptedException {

         search("http://automationpractice.com/index.php");

         Thread.sleep(3000);

         WebElement More = driver.findElement(By.xpath("(//a[@class='product-name'])[2]"));
         More.click();
         Thread.sleep(3000);

         WebElement addtoCArt = driver.findElement(By.xpath("//span[contains(text(),'Add to cart')]"));
         addtoCArt.click();
         Thread.sleep(3000);
         WebElement checkout = driver.findElement(By.xpath(" //a[@title='Proceed to checkout']//span"));
         checkout.click();
         Thread.sleep(3000);
         WebElement proceedToCheckOut = driver.findElement(By.xpath("//p[@class='cart_navigation clearfix']//span"));
         proceedToCheckOut.click();
        // actions.sendKeys(Keys.PAGE_DOWN).perform();
         // cart.click();

         driver.quit();


     }

     @Test
    public void contactUs() throws InterruptedException {

        driver.get("http://automationpractice.com/index.php");

         Thread.sleep(2000);
         actions.sendKeys(Keys.PAGE_DOWN).perform();
         Thread.sleep(3000);
         actions.sendKeys(Keys.PAGE_DOWN).perform();
         Thread.sleep(3000);
         actions.sendKeys(Keys.PAGE_DOWN).perform();
         Thread.sleep(3000);
         actions.sendKeys(Keys.PAGE_DOWN).perform();
         actions.sendKeys(Keys.PAGE_DOWN).perform();

         WebElement contactus = driver.findElement(By.xpath("//a[@title='Contact us']"));
         contactus.click();
         Thread.sleep(2000);

         actions.sendKeys(Keys.PAGE_DOWN).perform();

         select = new Select(driver.findElement(By.id("id_contact")));
         select.getOptions().get(1).click();
         String options = select.getOptions().get(1).getText();
         System.out.println(select.getOptions().get(1).getText());
         Thread.sleep(2000);

         WebElement email = driver.findElement(By.id("email"));
         Thread.sleep(2000);
         email.sendKeys("imranbabur@gmail.com");

         WebElement orderReference = driver.findElement(By.id("id_order"));
         orderReference.sendKeys("abc123");

         WebElement Message = driver.findElement(By.id("message"));
         Message.sendKeys("Hello");

         driver.findElement(By.xpath("//button[@id='submitMessage']//span")).click();


         driver.quit();
     }








}




