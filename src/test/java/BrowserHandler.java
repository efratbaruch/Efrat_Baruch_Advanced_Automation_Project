import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

class BrowserHandler {

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
                mUtilityMethods.reportAndTakeScreenshot(Status.PASS, test, "Google Maps website has opened successfully.", "/google_maps_upload_success", driver);
            }else {
                mUtilityMethods.reportAndTakeScreenshot(Status.FATAL, test, "Was unable to open Google Maps website.", "/google_maps_did_not_open", driver);
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
                mUtilityMethods.reportAndTakeScreenshot(Status.PASS, test,"Search with coordinates was performed successfully.", "/search_with_coordinates_successful", driver);
            }else {
                mUtilityMethods.reportAndTakeScreenshot(Status.FATAL, test, "Was unable perform search using coordinates.", "/search_with_coordinates_unsuccessful", driver);
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
                mUtilityMethods.reportAndTakeScreenshot(Status.PASS, test, "Address was validated successfully.", "/address_was_validated_successfuly", driver);
            }else{
                mUtilityMethods.reportAndTakeScreenshot(Status.FAIL, test, "Address validation failed.", "/address_was_not_validated", driver);
            }
        }
    }


}
