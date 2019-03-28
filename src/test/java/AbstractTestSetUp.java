import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public abstract class AbstractTestSetUp {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static ExtentReports extent;
    private static ExtentTest test;

    private Connection connection;

    @BeforeClass
    public static void setUpTestSuit() {

        if(Constants.BROWSER_OF_CHOISE.equals("chrome"))
        {
            System.setProperty("webdriver.chrome.driver", "/Users/efratbaruch/Desktop/course/chromedriver");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--lang=en");
            driver = new ChromeDriver(options);
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);

        extent = new ExtentReports();
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(Constants.PATHWAY_TO_TEST_FILES +"/extent.html");
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
            Class.forName(Constants.JDBC_CONNECTION_NAME);
            connection = DriverManager.getConnection(Constants.DATABASE_URL, Constants.DATABASE_USER_NAME, Constants.DATABASE_PASSWORD);
        }catch (SQLException e){
            test.log(Status.FATAL, "Was unable to connect to remote database");
        }catch (ClassNotFoundException ex){
            test.log(Status.FATAL, "Was unable to connect to remote database");
        }
    }

    // This method closes the connection to the remote database.
    private void closeConnection (){
        try {
            connection.close();
        }catch (SQLException e){
            test.log(Status.INFO, "Was unable to close connection to remote database");
        }
    }

    // These method are responsible to pass down the listed objects for use, and yet keep them encapsulated.

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