import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Site_Automation {

    //Global Methods
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


    @Test(priority = 1)
    public void SiteRedirection() {
        System.out.println("Opening the Pakwheels Website");
        driver.get("https://www.pakwheels.com/");
        driver.manage().window().maximize();
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

        //Select chooseCity = new Select(city);
        //chooseCity.selectByIndex(3);

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
        Set<String> allwindowHandles = driver.getWindowHandles();
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
        driver.findElement(By.id("mobile-number-submit-btn")).click();
    }
}
