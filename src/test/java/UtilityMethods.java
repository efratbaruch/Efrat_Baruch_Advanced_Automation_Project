import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

class UtilityMethods {

    // The method implements the internalTakeScreenshot method into the extent report in a protected way.
    void reportAndTakeScreenshot(Status status, ExtentTest test, String logMessage, WebDriver driver){
        try {
            test.log(status, logMessage, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("."+Constants.PATHWAY_TO_TEST_FILES + "/google_maps_upload_success", driver)).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // The method takes screen shots.
    private String takeScreenShot(String ImagesPath, WebDriver driver) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(ImagesPath+".png");
        try {
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ImagesPath+".png";
    }

}
