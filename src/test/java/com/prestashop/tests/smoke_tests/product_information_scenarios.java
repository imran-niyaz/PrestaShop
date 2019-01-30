package com.prestashop.tests.smoke_tests;


import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class product_information_scenarios extends TestBase {


   @Test(priority = 1)
   public void Product_information_price(){

       //Product information-price:

       driver.get("https://www.Google.com");
       WebElement search = driver.findElement(By.name("q"));


       //1.Go to http://automationpractice.com/index.php
       search.sendKeys("http://automationpractice.com/index.php"+ Keys.ENTER);
       WebElement clickLink = driver.findElement(By.xpath("//*[@class='LC20lb']"));
       clickLink.click();


       //2.Click on any product
       WebElement HomePageName = driver.findElement(By.xpath("(//*[@title='Printed Dress'])[3]"));
       String HomePageName1 = HomePageName.getText();

       WebElement HomePagePrice = driver.findElement(By.xpath("(//span[@class='price product-price'])[6]"));
       String HomePagePrice1 = HomePagePrice.getText();

       driver.findElement(By.xpath("(//*[@title='Printed Dress'])[3]")).click();

       WebElement nextPageName = driver.findElement(By.xpath("//h1[text()='Printed Dress']"));
       String nextPageName2 = nextPageName.getText();

       WebElement nextPagePrice = driver.findElement(By.id("our_price_display"));
       String nextPagePrice2 = nextPagePrice.getText();

       //3.Verify that same name and price displayed as on the home page

       Assert.assertEquals(HomePageName1,nextPageName2);
                           // expected        actual
       Assert.assertEquals(HomePagePrice1,nextPagePrice2);

       driver.quit();

   }

   @Test(priority = 2)
    public void Product_information_details(){
       //4.that default quantity is 1

       driver.get("https://www.Google.com");
       WebElement search = driver.findElement(By.name("q"));

       //  Go to http://automationpractice.com/index.php
       search.sendKeys("http://automationpractice.com/index.php"+ Keys.ENTER);
       WebElement clickLink = driver.findElement(By.xpath("//*[@class='LC20lb']"));
       clickLink.click();

       driver.findElement(By.xpath("(//*[@title='Printed Dress'])[3]")).click();

       String defaultQuantity = driver.findElement(By.id("quantity_wanted")).getAttribute("value");

       Assert.assertEquals(defaultQuantity,"1");

       //5.Verify that default size is S
       Select dropDownBox_Size = new Select(driver.findElement(By.xpath("//select[@name='group_1']")));

       String defaultSize = dropDownBox_Size.getOptions().get(0).getText();
       Assert.assertEquals(defaultSize,"S");

       //6.Verify that size options are S, M, L

       List <String> sizeList = new ArrayList<String>();
       sizeList.add("S");
       sizeList.add("M");
       sizeList.add("L");

       for(int i=0 ; i < sizeList.size(); i++ ){
           Assert.assertEquals(dropDownBox_Size.getOptions().get(i).getText(),sizeList.get(i));
      }


       driver.quit();
   }


    @Test(priority = 3)
    public void Product_information_Add_to_cart() throws InterruptedException {

       search("http://automationpractice.com/index.php");

       WebElement item = driver.findElement(By.xpath("(//*[@title='Printed Dress'])[3]"));
        item.click();

        //7. Click on Add to cart
        WebElement addtoCart = driver.findElement(By.xpath("//span[text()='Add to cart']"));
        addtoCart.click();

        //8. Verify confirmation message “Product successfully added to your shopping cart”
        WebElement confirmationMessageElement = driver.findElement(By.xpath("//div[@class='layer_cart_product col-xs-12 col-md-6']//h2"));
        Thread.sleep(2000);
        String confirmationMessage2  = confirmationMessageElement.getText();
        String expectedCon_Message = "Product successfully added to your shopping cart";
        System.out.println(confirmationMessage2);

        Assert.assertEquals(confirmationMessage2,expectedCon_Message);

        //9. Verify that default quantity is 1

        WebElement confirmQty = driver.findElement(By.id("layer_cart_product_quantity"));
        Thread.sleep(2000);
        String confirmQty2 = confirmQty.getText();
        System.out.println(confirmQty2);

        Assert.assertEquals(confirmQty2,"1");



        //10.Verify that defaultsize is S
       WebElement confirmSize = driver.findElement(By.id("layer_cart_product_attributes"));
        String confirmSize2 = confirmSize.getText();
        System.out.println(confirmSize2);
        Assert.assertTrue(confirmSize2.contains("S"));



        //Verify that same name and price displayed as on the home page


        driver.quit();

    }

    @Test(priority = 4)
    public void Login_my_account() throws InterruptedException {

        search("http://automationpractice.com/index.php");

        //1.Go to http://automationpractice.com/index.php
        WebElement signIn = driver.findElement(By.className("login"));
        //2.Click Signin link
        signIn.click();

        //3.Login using valid username and password
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("imranbabur111@gmail.com");



        WebElement password = driver.findElement(By.id("passwd"));
        password.sendKeys("12345678imran");

        WebElement signInButton = driver.findElement(By.xpath("//button[@id='SubmitLogin']//span"));
        signInButton.click();


        //4.Verify that titlecontainsMy account
        System.out.println(driver.getTitle());
        Assert.assertTrue(driver.getTitle().contains("My account"));


        //5.Verify that account holder full name is displayed next to the Sign out link
        WebElement accountHolder = driver.findElement(By.xpath("//span[contains(text(),'imran niyaz')]"));
        String accountHolder2 = accountHolder.getText();
        Assert.assertTrue(accountHolder.isDisplayed());


                       //Login: My personal information
        //6.Click on My personal information button
        WebElement MyPersonalInformation = driver.findElement(By.xpath("//span[contains(text(),'My personal information')]"));
        MyPersonalInformation.click();

        //7.Verify title contains Identity
        System.out.println(driver.getTitle());
        Assert.assertTrue(driver.getTitle().contains("Identity"));

        //8.Verify thatfirst name and last name matches the full name on top
        String firstName = driver.findElement(By.id("firstname")).getAttribute("value");

       String lastName = driver.findElement(By.id("lastname")).getAttribute("value");

        Assert.assertEquals(firstName.concat(" "+lastName),accountHolder2);

        //9.Click on Save button
        WebElement SaveButton = driver.findElement(By.xpath("//button[@name='submitIdentity']//span"));
        SaveButton.click();

        //10.Verify error message “The password you entered is incorrect.”
        WebElement errorMessage = driver.findElement(By.xpath(" //li[contains(text(),'The password you entered is incorrect.')]"));
        String errorMessage2 = errorMessage.getText();

        Assert.assertTrue(errorMessage2.contains("The password you entered is incorrect."));

        //11.Click on Back to your account
        WebElement backToAccount = driver.findElement(By.xpath(" //ul[@class='footer_links clearfix']//li[1]//a[1]//span[1]"));
        backToAccount.click();

        //12.Verify that title contains My account
        Assert.assertTrue(driver.getTitle().contains("My account"));

                   //Login My Address
        //13.Clickon My addresses
        WebElement MyAddress = driver.findElement(By.xpath("//span[contains(text(),'My addresses')]"));
        MyAddress.click();

        //14.Clickon Add a new address
        WebElement clickNewAddress = driver.findElement(By.xpath("//a[@title='Add an address']//span"));
        clickNewAddress.click();

        //15.Verify thatfirst name and last name matches the full name on top
        String firstName3 = driver.findElement(By.id("firstname")).getAttribute("value");

        String lastName3 = driver.findElement(By.id("lastname")).getAttribute("value");

        Assert.assertEquals(firstName3.concat(" "+lastName3),accountHolder2);

        //16.Delete the first name
        WebElement delete1stName = driver.findElement(By.id("firstname"));
        delete1stName.clear();

        //17.Click save
        WebElement clickSave = driver.findElement(By.xpath("//button[@id='submitAddress']//span"));
        clickSave.click();

        //18.Verify error message “firstname is required.”
        WebElement errorMessage3 = driver.findElement(By.xpath("//ol//li[1]"));
        String errorMessage003 = errorMessage3.getText();

        Assert.assertTrue(errorMessage003.contains("firstname is required."));

        Thread.sleep(2000);
        driver.quit();

    }








}
