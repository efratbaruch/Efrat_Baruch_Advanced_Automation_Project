import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class AbstractBasicTest {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static ExtentReports extent;
    private static ExtentTest test;

    private Connection connection;

    @BeforeClass
    public static void setUpTestSuit() {
        System.setProperty("webdriver.chrome.driver", "/Users/efratbaruch/Desktop/course/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);

        extent = new ExtentReports();
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("."+Constants.PATHWAY_TO_TEST_FILES +"/extent.html");
        extent.attachReporter(htmlReporter);
        test = extent.createTest("Test reports", "Reports of Advanced Automation Course Project");
    }

    @Before
    public void setUpTest(){
        connectToDatabase();
    }

    @After
    public void closeTest(){
        closeConnection();
    }

    @AfterClass
    public static void closeTestSuit() {
        driver.quit();
        extent.flush();
    }


    // This method connects to a remote database
    private void connectToDatabase(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(Constants.DATABASE_URL, Constants.DATABASE_USER_NAME, Constants.DATABASE_PASSWORD);
        }catch (SQLException e){
            // TODO: insert a report and a failure action
        }catch (ClassNotFoundException ex){
            // TODO: insert a report and a failure action
        }
    }

    // This method closes the connection to the remote database.
    private void closeConnection (){
        try {
            connection.close();
        }catch (SQLException e){
            // TODO: insert a report and a failure action
        }
    }

    // TODO: add description to these and think if they are necessary or maybe I shouldn't make them private
    WebDriver getDriver(){
        return driver;
    }

    Connection getConnection(){
        return connection;
    }

    WebDriverWait getWait(){
        return wait;
    }

    ExtentTest getTest(){
        return test;
    }

}