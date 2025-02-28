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

import java.time.Duration;
import java.util.List;
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
        driver.manage().window().maximize();
        //Closing the Pop-Up
        WebElement closePopup = wait.until(ExpectedConditions.elementToBeClickable(By.id("onesignal-slidedown-cancel-button")));
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
    public void InspectionReport() throws InterruptedException {
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
        WebElement viewSampleAd = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div/a[3]")));
        viewSampleAd.click();
    }

    @Test(priority = 6)
    public void searchBikes() throws InterruptedException {
        WebElement navigatetoBikes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/ul/li[3]/a")));
        navigatetoBikes.click();
        System.out.println("Current URL: " + driver.getCurrentUrl());
        //WebElement usedBikes = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Find Used Bikes")));
        //usedBikes.click();
        String x = "More Search Option";
        String buttonText = "Searc";

        //Assertion for Search Options
        softassert.assertEquals(driver.findElement(By.id("more_option")).getText(), x);

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


    @AfterClass
    public void afterClassOperations() throws InterruptedException {
        softassert.assertAll("[]");
        //Thread.sleep(3000);
        driver.close();

    }
}
  