import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.junit.Test;

public class APITestSuit extends AbstractBasicTest {

    private ExtentTest test = getTest();

    @Test
    public void test_id_1(){
        test.log(Status.INFO, "Test 1 - " + Constants.TEST_NAME + " - now begins.");
        boolean testSuccess = false;
        GoogleMapApiValidationTest googleMapApiValidationTest = new GoogleMapApiValidationTest(getConnection(), getDriver(), getWait(), getTest());
        try {
            googleMapApiValidationTest.runTest();
            testSuccess = true;
        }finally {
            if (testSuccess) {
                test.log(Status.PASS, "Test 1 - " + Constants.TEST_NAME + " - has passed successfully.");
            } else {
                test.log(Status.FAIL, "Test 1 - " + Constants.TEST_NAME + " - has failed.");
            }
            googleMapApiValidationTest.reportTestResult(testSuccess);
        }
    }

}
