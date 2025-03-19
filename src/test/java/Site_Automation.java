import com.beust.ah.A;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executor;

public class Site_Automation {

    //Global Methods
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    Actions act = new Actions(driver);
    SoftAssert softassert = new SoftAssert();


    @Test(priority = 1)
    public void SiteRedirection() {
        System.out.println("Opening the Pakwheels Website");
        driver.get("https://www.pakwheels.com/");
        //driver.manage().window().maximize();
        //Closing the Pop-Up
        WebElement closePopup = driver.findElement(By.id("onesignal-slidedown-cancel-button"));
        closePopup.click();
        WebElement car = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[5]/section[2]/div[2]/div/ul/li[1]/input[1]")));
        car.sendKeys("BMW");
        WebElement city = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[5]/section[2]/div[2]/div/ul/li[2]/div/a")));
        city.click();
        driver.findElement(By.xpath("/html/body/div[5]/section[2]/div[2]/div/ul/li[2]/div/div/ul/li[5]")).click();
        WebElement pricerange = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[5]/section[2]/div[2]/div/ul/li[3]/div")));
        pricerange.sendKeys("10");
        driver.findElement(By.xpath("/html/body/div[5]/section[2]/div[2]/div/ul/li[3]/div/div/div/div/div[2]/span/input")).sendKeys("30");
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("home-search-btn")));
        searchButton.click();

    }

    @Test(priority = 2)
    public void sortandSelect() throws InterruptedException {
        System.out.println("Test Method 2");
        WebElement sortBy = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[6]/section[2]/div/div[3]/div[2]/div/div[2]/div[1]/div/div[1]/span/select")));
        Select sel = new Select(sortBy);
        sel.selectByIndex(3);
        //Wait to get the first Listing
        Thread.sleep(2000);
        WebElement firstElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[6]/section[2]/div/div[3]/div[2]/div/div[2]/div[2]/ul[1]/li[1]/div"))); // Get the first div
        firstElement.click();
        String currentWindow = driver.getWindowHandle();
        System.out.println("Current Window handle: " + currentWindow);
        Set<String> allwindowHandles = driver.getWindowHandles();
        System.out.println("All Window Handles till now: " + allwindowHandles);

        for(String window:allwindowHandles){
            if(!window.equals(currentWindow)){
                driver.switchTo().window(window);
            }
        }
    }

    @Test(priority = 3)
    public void contactOptions(){
        WebElement contactoption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[6]/div/div[1]/div[2]/div[3]/div[1]/div[2]/div[1]/button[1]")));
        contactoption.click();
        WebElement contactNumber = wait.until(ExpectedConditions.elementToBeClickable(By.id("mobile-number-id")));
        contactNumber.sendKeys("7827382");
        contactNumber.clear();
        contactNumber.sendKeys("03365676796");
        WebElement phone_no = wait.until(ExpectedConditions.elementToBeClickable(By.id("mobile-number-submit-btn")));
        phone_no.click();
        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[7]/div/div/div[1]/button")));
        act.moveToElement(closeButton);
        closeButton.click();

    }

    @Test(priority = 4)
    public void scheduleInspection() throws InterruptedException {
        WebElement schedulebutton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[6]/div/div[1]/div[2]/div[1]/div/div[2]/div/a[1]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", schedulebutton);
        schedulebutton.click();
        WebElement proceedInspection = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Next")));
        proceedInspection.click();
    }

    @Test(priority = 5)
    public void InspectionReport() throws InterruptedException, AWTException {
        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,700)");
        Thread.sleep(2000);
        WebElement viewSample = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("View Sample Inspection Report")));
        viewSample.click();
        String currentWindow2 = driver.getWindowHandle();
        System.out.println("Current Window handle: " + currentWindow2);
        Set<String> allhandlesupdated = driver.getWindowHandles();
        System.out.println("All Window Handles till now: " + allhandlesupdated );

        for(String i:allhandlesupdated){
             if(!i.equals(currentWindow2)){
                 driver.switchTo().window(i);
             }
        }
        //Print Summary
        WebElement printadSummary = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div/a[2]")));
        printadSummary.click();
        Robot robot = new Robot();
        robot.delay(2000); // Wait for the print dialog to open

        // Press Enter to confirm the Save as PDF option
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(2000);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        robot.keyPress(KeyEvent.VK_LEFT);
        robot.keyRelease(KeyEvent.VK_LEFT);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        driver.switchTo().window(currentWindow2);
        driver.navigate().back();

    }

    @Test(priority = 6)
    public void searchBikes() throws InterruptedException {
        WebElement navigatetoBikes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/ul/li[3]/a")));
        navigatetoBikes.click();
        System.out.println("Current URL: " + driver.getCurrentUrl());
        //WebElement usedBikes = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Find Used Bikes")));
        //usedBikes.click();
        String x = "More Search Options";
        String buttonText = "Searc";

        //Assertion for Search Options
        WebElement fetchText = driver.findElement(By.id("more_option"));
        String a = fetchText.getText();
        if(a.equals(x)){
            Assert.assertTrue(true);
        }
        else{
            Assert.assertTrue(false); // For Failed Test Case
        }
        //softassert.assertEquals(driver.findElement(By.id("more_option")).getText(), x);
        //softassert.assertTrue(false);
        //Assertion for Button Text
        softassert.assertEquals(driver.findElement(By.xpath("/html/body/div[5]/section[2]/div[2]/div[2]/div[1]/div[3]/div[2]/button")).getText(), buttonText);;

        WebElement BikeMake = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[5]/section[2]/div[2]/div[2]/div[1]/div[1]/div/ul/li[1]/input[1]")));
        BikeMake.sendKeys("YBR");

        WebElement citySelection = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[5]/section[2]/div[2]/div[2]/div[1]/div[1]/div/ul/li[2]/div/a")));
        citySelection.click();

        driver.findElement(By.id("UsedCity_chzn_o_4")).click();

        WebElement priceRange = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[5]/section[2]/div[2]/div[2]/div[1]/div[1]/div/ul/li[3]/div")));
        priceRange.click();
        driver.findElement(By.xpath("/html/body/div[5]/section[2]/div[2]/div[2]/div[1]/div[1]/div/ul/li[3]/div/div/div/div/div[1]/span/input")).sendKeys("100");
        driver.findElement(By.xpath("/html/body/div[5]/section[2]/div[2]/div[2]/div[1]/div[1]/div/ul/li[3]/div/div/div/div/div[2]/span/input")).sendKeys("300");
        driver.findElement(By.xpath("/html/body/div[5]/section[2]/div[2]/div[2]/div[1]/div[1]/div/ul/li[3]/div/i")).click();
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[5]/section[2]/div[2]/div[2]/div[1]/div[3]/div[2]/button")));
        searchButton.click();

    }

    @Test(priority = 7)
    public void sortandSelectBikes(){

        WebElement sortBy = wait.until(ExpectedConditions.elementToBeClickable(By.id("sortby")));
        Select selectSort = new Select(sortBy);
        selectSort.selectByVisibleText("Price: High to Low");
        softassert.assertTrue(sortBy.isEnabled());
        WebElement gridView = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[6]/section[2]/div/div/div[3]/div/div[2]/div[1]/div/div[2]/div/button[2]")));
        gridView.click();
    }

    @Test(priority = 8)
    public void openDetailedBikeListing() {
        List<WebElement> list = driver.findElements(By.cssSelector(".classified-listing"));
        list.get(1).click();
        //Assertion to pass the test case
        Set<String> currentAllWindowHandles = driver.getWindowHandles();
        System.out.println("Current All Window Handles: " + currentAllWindowHandles);
        String currentWindowHandle = driver.getWindowHandle();
        for (String i : currentAllWindowHandles) {
            if (!i.equals(currentWindowHandle)) {
                driver.switchTo().window(i);
            }
        }
        softassert.assertEquals(driver.findElement(By.xpath("/html/body/div[6]/div/div[2]/div[2]/div[1]/div/h2")).getText(), "Seller details");

    }
        @Test(priority = 9)
        public void bikecontactFunctionality() {
            String assertText = "Please enter a valid mobile number";

            //Static Validation for Contact Number

            WebElement contactNo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[6]/div/div[2]/div[2]/div[3]/div[1]/div[2]/div[1]/button[1]")));
            contactNo.click();
            WebElement flagCode = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[7]/div/div/div[2]/form/div/div/div/div/div/div[2]")));
            flagCode.click();
            WebElement flagCodestatic = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[7]/div/div/div[2]/form/div/div/div/div/ul/li[1]")));
            flagCodestatic.click();
            WebElement contactNoInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("mobile-number-id")));
            contactNoInput.sendKeys("3458676796");
            WebElement continueMobile = wait.until(ExpectedConditions.elementToBeClickable(By.id("mobile-number-submit-btn")));
            continueMobile.click();
            softassert.assertEquals(driver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/small")).getText(), assertText);
            driver.findElement(By.xpath("/html/body/div[7]/div/div/div[1]/button")).click();


            for (int i = 0;i<2;i++) {
            contactNo.click();
            flagCode.click();
            Random rand = new Random();
            int randomValueflag = rand.nextInt(9) + 1;
            System.out.println("Flag Count: " +randomValueflag);

            String dynamicflagValue = "//*[@id=\"sign_in_pop_up\"]/div/div/div[2]/form/div/div/div/div/ul/li" + "[" + randomValueflag + "]";

            WebElement flagCodeSelect = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicflagValue)));

            flagCodeSelect.click();
            contactNoInput.clear();

            contactNoInput.sendKeys("345867679");


            driver.findElement(By.xpath("/html/body/div[7]/div/div/div[1]/button")).click();

        }
            System.out.println(driver.findElement(By.cssSelector("#mobile-number-submit-btn")).getCssValue("color"));

        }

        @Test(priority = 10)
        public void carInsurance() throws InterruptedException {
            WebElement More = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("More")));
            More.click();
            WebElement CarInsurance = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/ul/li[8]/ul/li/div/ul/li[3]/a")));
            CarInsurance.click();

            for(int i = 0; i<5;i++) {
                //Fields filling
                WebElement carmakeModel = wait.until(ExpectedConditions.elementToBeClickable(By.name("car_selector")));
                WebElement carPrice = wait.until(ExpectedConditions.elementToBeClickable(By.name("car_value")));
                carmakeModel.click();

                //Assertion to check if the pop-up is opened!
                softassert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/section[2]/div/div[2]/div/div/div[1]/div[1]/div[1]")).isDisplayed());

                //Elements for Model Year, Make, Model and Version
                WebElement modelYear = wait.until(ExpectedConditions.elementToBeClickable(By.id("model_year_2024")));
                modelYear.click();

                softassert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/section[2]/div/div[2]/div/div/div[1]/div[2]/div[1]")).isEnabled());


                WebElement make = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[5]/section[2]/div/div[2]/div/div/div[1]/div[2]/div[2]/ul/li[4]")));
                make.click();
                WebElement model = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"model_2\"]")));
                model.click();
                Random random = new Random();

                String[] versionIDs = {"version_3508", "version_3509", "version_3510"};
                String randomVersionid = versionIDs[random.nextInt(versionIDs.length)];

                WebElement version = wait.until(ExpectedConditions.elementToBeClickable(By.id(randomVersionid)));
                version.click();
                int[] price = {100000, 200000, 400000, 1500000, 5000000};
                int randomPrice = price[random.nextInt(price.length)];
                carPrice.sendKeys("200000");

                //carPrice.sendKeys(Integer.toString(randomPrice));
                WebElement doneButton = wait.until(ExpectedConditions.elementToBeClickable(By.name("submit_btn")));
                doneButton.click();
                Thread.sleep(2000);
            }
        }

    @AfterClass
    public void afterClassOperations() throws InterruptedException {
        softassert.assertAll("[]");
        //Thread.sleep(3000);
        //driver.quit();

    }
}
  