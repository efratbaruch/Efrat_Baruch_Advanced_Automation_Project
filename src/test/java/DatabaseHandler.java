import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import java.sql.*;

class DatabaseHandler {

        // This method pulls the url and search parameters used for the test and returns an object containing them.
        ApiTestParameters pullTestParametersFromRemoteDatabase (Connection con, int test_id, ExtentTest test) {
            test.log(Status.INFO, "Pull test parameters process has begun.");
            String url = "";
            String searchParameters = "";

            try {
            Statement statement = con.createStatement();
            String statementToExecute = Constants.DATABASE_QUERY_TEST_PARAMETERS + test_id + "';";
            ResultSet rs = statement.executeQuery(statementToExecute);
            rs.next();
            url = rs.getString(Constants.DATABASE_COLUMN_NAME_1);
            searchParameters = rs.getString(Constants.DATABASE_COLUMN_NAME_2);
            rs.close();
            statement.close();
            test.log(Status.PASS, "Test parameters were pulled successfully.");
            }
            catch (SQLException e) {
                e.printStackTrace();
                test.log(Status.FATAL, "Was unable to pull test parameters.");
            }
            return new ApiTestParameters(url, searchParameters);
        }


        // This method inserts test result to the remote database
        void sendTestResultsToDatabase(Connection con, int test_id, String test_name, String result, ExtentTest test) {
            test.log(Status.INFO, "Sending test results to database process has begun.");
            try {
                PreparedStatement preparedStatement = con.prepareStatement(Constants.DATABASE_INSERT_RESULT);
                preparedStatement.setInt(1, test_id);
                preparedStatement.setString(2, test_name);
                preparedStatement.setString(3, result);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                test.log(Status.PASS, "Test results were sent to database successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
                test.log(Status.FAIL, "Was unable to send test results to database.");
            }
        }



}

