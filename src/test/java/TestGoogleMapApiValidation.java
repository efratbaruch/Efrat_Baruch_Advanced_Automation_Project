import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Connection;

class TestGoogleMapApiValidation {

    private DatabaseHandler mDatabaseHandler = new DatabaseHandler();
    private GooglePlacesAPIHandler mGooglePlacesAPIHandler = new GooglePlacesAPIHandler();
    private BrowserHandler mBrowserHandler = new BrowserHandler();
    private Connection connection;
    private WebDriver driver;
    private WebDriverWait wait;
    private ExtentTest test;

    TestGoogleMapApiValidation(Connection connection, WebDriver driver, WebDriverWait wait, ExtentTest test){
        this.connection = connection;
        this.driver = driver;
        this.wait = wait;
        this.test = test;
    }

    // This method runs and arranges the logic of the test.
    void runTest (){
        test.log(Status.INFO, "testRun method has begun.");
        ApiTestParameters apiTestParameters = mDatabaseHandler.pullTestParametersFromRemoteDatabase(connection, Constants.TEST_ID, test);
        String parsedResponse = mGooglePlacesAPIHandler.sendApiRequestAndParseResponseJson(apiTestParameters, test);
        String coordinates = mGooglePlacesAPIHandler.extractCoordinatesFromResponseUsingGson(parsedResponse, test);
        mBrowserHandler.openGoogleMaps(driver, wait, test);
        mBrowserHandler.inputCoordinatesToSearch(driver, wait, coordinates, test);
        mBrowserHandler.validateAddress(driver, test);
        test.log(Status.INFO, "testRun method has ended.");

    }

    // This method handles the logic of the report to the database.
    void reportTestResult(boolean result){
        test.log(Status.INFO, "reportTestResult method has begun.");
        if (result){
            mDatabaseHandler.sendTestResultsToDatabase(connection, Constants.TEST_ID, Constants.TEST_NAME, Constants.RESULT_SUCCESS, test);
        }else{
            mDatabaseHandler.sendTestResultsToDatabase(connection, Constants.TEST_ID, Constants.TEST_NAME, Constants.RESULT_FAIL, test);
        }
        test.log(Status.INFO, "reportTestResult method has ended.");
    }


}
