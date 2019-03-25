import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class testTrial {

    @Test
    public void test(){
        System.setProperty("webdriver.chrome.driver", "/Users/efratbaruch/Desktop/course/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.get("https://www.google.com/maps");
        driver.findElement(Constants.SEARCH_TEXT_BOX).sendKeys("Rock of cashel");
        driver.findElement(Constants.SEARCH_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("section-hero-header-description")));
        String address = driver.findElement(By.xpath("//*[@id=\"pane\"]/div/div[1]/div/div/div[7]/div/div[1]/span[3]/span[3]")).getText();
        System.out.println(address);
        driver.close();
    }

}
