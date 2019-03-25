import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

class BrowserHandler {

    // TODO: wast's fail and whats fatal

    private UtilityMethods mUtilityMethods = new UtilityMethods();

    // This method opens Google Maps site on the browser.
    void openGoogleMaps (WebDriver driver, WebDriverWait wait, ExtentTest test){
        test.log(Status.INFO, "Open Google Maps website process has begun.");
        boolean googleMapsOpened = false;
        try {
            driver.get(Constants.GOOGLE_MAPS_URL);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Constants.SEARCH_TEXT_BOX));
            googleMapsOpened = true;
        }
        finally {
            if (googleMapsOpened){
                mUtilityMethods.reportAndTakeScreenshot(Status.PASS, test, "Google Maps website has opened successfully.", driver);
            }else {
                mUtilityMethods.reportAndTakeScreenshot(Status.FATAL, test, "Was unable to open Google Maps website.", driver);
            }
        }

    }

    // This method sends a search in Google Maps site using coordinates given.
    void inputCoordinatesToSearch (WebDriver driver, WebDriverWait wait, String coordinates, ExtentTest test){
        test.log(Status.INFO, "Input coordinates to Google Maps search process has begun.");
        boolean searchSuccess = false;
        try {
            driver.findElement(Constants.SEARCH_TEXT_BOX).sendKeys(coordinates);
            driver.findElement(Constants.SEARCH_BUTTON).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(Constants.RESULTS_HEADER));
            searchSuccess = true;
        }
        finally {
            if (searchSuccess){
                mUtilityMethods.reportAndTakeScreenshot(Status.PASS, test,"Search with coordinates was performed successfully.", driver);
            }else {
                mUtilityMethods.reportAndTakeScreenshot(Status.FATAL, test, "Was unable perform search using coordinates.", driver);
            }
        }
    }

    // This method compares the expexted address with the address acquired through the search.
    void validateAddress (WebDriver driver, ExtentTest test){
        test.log(Status.INFO, "Input coordinates to Google Maps search process has begun.");
        boolean validationSuccess = false;
        try {
            String actualAddress = driver.findElement(Constants.ACTUAL_ADDRESS).getText();
            Assert.assertEquals(Constants.EXPECTED_ADDRESS, actualAddress);
            validationSuccess = true;
        }
        finally {
            if (validationSuccess){
                mUtilityMethods.reportAndTakeScreenshot(Status.PASS, test, "Address was validated successfully.", driver);
            }else{
                mUtilityMethods.reportAndTakeScreenshot(Status.FAIL, test, "Address validation failed.", driver);
            }
        }
    }


}
