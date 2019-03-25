import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;

class GooglePlacesAPIHandler {

    // TODO: Decide on adress and edit then delete these requests.
    // https://maps.googleapis.com/maps/api/place/findplacefromtext/json?inputtype=textquery&language=en&fields=geometry/location&key=AIzaSyCu2vjNMuQDCQSB67-2zsJRXkvQYgFSW64
    // &input=Rock%20of%20Cashel

    // This method sends and api request and returned the api response parsed to string
    String sendApiRequestAndParseResponseJson (TestParameters testParameters, ExtentTest test) {

        test.log(Status.INFO, "Sending API request process has begun.");
        String parsedResponse = "";
        boolean responseReceived = false;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(testParameters.getUrl() + testParameters.getSearchParameters())
                .build();

        try {
            Response response = client.newCall(request).execute();
            parsedResponse = response.body().string();
            responseReceived = true;
        }
        catch (IOException e) {
            e.printStackTrace();
            test.log(Status.FATAL, "Was unable to complete Api request process.");
        }
        finally {
            if (responseReceived){
                test.log(Status.PASS, "Api response was received successfully.");
            }
        }
        return parsedResponse;
    }


    // This method receives string response and extracts the coordinates from it
    String extractCoordinatesFromStringResponse (String parsedResponse, ExtentTest test){
        test.log(Status.INFO, "Extracting coordinates from response process has begun.");
        String lat = "";
        String lng = "";
        boolean extractSuccess = false;

         try {
             JSONObject mainJsonObject = new JSONObject(parsedResponse);
             JSONObject resultsJson = mainJsonObject.getJSONObject(Constants.JSON_DIRECTORY);
             lat = resultsJson.getString(Constants.JSON_KEY_LAT);
             lng = resultsJson.getString(Constants.JSON_KEY_LNG);
             extractSuccess = true;
         }finally {
             if (extractSuccess){
                 test.log(Status.PASS, "Coordinates were extracted from api response successfully.");
             }
             else {
                 test.log(Status.FATAL, "Was unable to extract coordinates from Api response.");
             }
         }
        return lat + ", " + lng;
    }

}
