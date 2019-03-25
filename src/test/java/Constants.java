import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.openqa.selenium.By;

class Constants {

    static final int TEST_ID = 1;
    static final String TEST_NAME = "Google Map Api Validation Test";
    static final String RESULT_SUCCESS = "Success";
    static final String RESULT_FAIL = "Fail";

    static final String PATHWAY_TO_TEST_FILES = "~/Efrat_Baruch_Advanced_Automation_Project/src/test/test_files";


    static final String DATABASE_URL = "jdbc:mysql://https://remotemysql.com:3306/i3FcIy3eW3?user=i3FcIy3eW3";
    static final String DATABASE_USER_NAME = "i3FcIy3eW3";
    static final String DATABASE_PASSWORD = "kHxWEWx6Vv";

    static final String DATABASE_QUERY_TEST_PARAMETERS = "SELECT url, search_parameters FROM i3FcIy3eW3.test_parameters WHERE test_id = '";
    static final String DATABASE_COLUMN_NAME_1 = "url";
    static final String DATABASE_COLUMN_NAME_2 = "search_parameters";

    static final String DATABASE_INSERT_RESULT = "INSERT INTO i3FcIy3eW3.test_results (test_id, test_name, test_result) VALUES (?, ?, ?);";

    static final String JSON_DIRECTORY = "candidates/geometry/location";
    static final String JSON_KEY_LAT = "lat";
    static final String JSON_KEY_LNG = "lng";

    static final String GOOGLE_MAPS_URL = "https://www.google.com/maps";

    static final By SEARCH_TEXT_BOX = By.id("searchboxinput");
    static final By SEARCH_BUTTON = By.id("searchbox-searchbutton");
    static final By RESULTS_HEADER = By.className("section-hero-header-description");

    // TODO: find a correct locator for this element
    static final By ACTUAL_ADDRESS = By.xpath("//*[@id=\"pane\"]/div/div[1]/div/div/div[7]/div/div[1]/span[3]/span[3]");
    static final String EXPECTED_ADDRESS = "Moor, Cashel, Co. Tipperary";

}
