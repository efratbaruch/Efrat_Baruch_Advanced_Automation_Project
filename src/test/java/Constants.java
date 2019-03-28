import com.sun.xml.internal.rngom.parse.xml.SAXParseable;
import org.openqa.selenium.By;

class Constants {

    static final String BROWSER_OF_CHOISE = "chrome";
    static final String JDBC_CONNECTION_NAME = "com.mysql.cj.jdbc.Driver";

    static final int TEST_ID = 1;
    static final String TEST_NAME = "Google Map Api Validation Test";
    static final String RESULT_SUCCESS = "Success";
    static final String RESULT_FAIL = "Fail";

    static final String PATHWAY_TO_TEST_FILES = "/Users/efratbaruch/Efrat_Baruch_Advanced_Automation_Project/test_files";

    static final String DATABASE_URL = "jdbc:mysql://remotemysql.com:3306/i3FcIy3eW3?user=i3FcIy3eW3";
    static final String DATABASE_USER_NAME = "i3FcIy3eW3";
    static final String DATABASE_PASSWORD = "kHxWEWx6Vv";

    static final String DATABASE_QUERY_TEST_PARAMETERS = "SELECT url, search_parameters FROM i3FcIy3eW3.test_parameters WHERE test_id = '";
    static final String DATABASE_COLUMN_NAME_1 = "url";
    static final String DATABASE_COLUMN_NAME_2 = "search_parameters";

    static final String DATABASE_INSERT_RESULT = "INSERT INTO i3FcIy3eW3.test_results (test_id, test_name, test_result) VALUES (?, ?, ?);";

    static final String JSON_DIRECTORY_1 = "candidates";
    static final int JSON_DIRECTORY_2 = 0;
    static final String JSON_DIRECTORY_3 = "geometry";
    static final String JSON_DIRECTORY_4 = "location";

    static final String JSON_KEY_1 = "lat";
    static final String JSON_KEY_2 = "lng";

    static final String GOOGLE_MAPS_URL = "https://www.google.com/maps?hl=en";

    static final By SEARCH_TEXT_BOX = By.id("searchboxinput");
    static final By SEARCH_BUTTON = By.id("searchbox-searchbutton");
    static final By RESULTS_HEADER = By.className("section-hero-header-description");

    static final By ACTUAL_ADDRESS = By.xpath("//*[@id=\"pane\"]/div/div[1]/div/div/div[5]/div/div[1]/span[3]/span[3]");
    static final String EXPECTED_ADDRESS = "Moor, Cashel, Co. Tipperary, Ireland";

}
