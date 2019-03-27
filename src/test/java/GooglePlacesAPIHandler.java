import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

class GooglePlacesAPIHandler {

    // This method sends an api request and returned the api response parsed to string
    String sendApiRequestAndParseResponseJson (ApiTestParameters apiTestParameters, ExtentTest test) {

        test.log(Status.INFO, "Sending API request process has begun.");
        String parsedResponse = "";
        boolean responseReceived = false;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(apiTestParameters.getUrl() + apiTestParameters.getSearchParameters())
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
    String extractCoordinatesFromResponse(String parsedResponse, ExtentTest test){
        test.log(Status.INFO, "Extracting coordinates from response process has begun.");
        String lat = "";
        String lng = "";
        boolean extractSuccess = false;
        try {
            JSONObject mainJsonObject = new JSONObject(parsedResponse);
            JSONArray secondJsonObject = mainJsonObject.getJSONArray(Constants.JSON_DIRECTORY_1);
            JSONObject thirdJsonObject = secondJsonObject.getJSONObject(Constants.JSON_DIRECTORY_2);
            JSONObject fourthJsonObject = thirdJsonObject.getJSONObject(Constants.JSON_DIRECTORY_3);
            JSONObject finalJsonObject = fourthJsonObject.getJSONObject(Constants.JSON_DIRECTORY_4);

            lat = Double.toString(finalJsonObject.getDouble(Constants.JSON_KEY_LAT));
            lng = Double.toString(finalJsonObject.getDouble(Constants.JSON_KEY_LNG));

            extractSuccess = true;
        }
        finally {
            if (extractSuccess){
                test.log(Status.PASS, "Coordinates were extracted from api response successfully.");
            }
            else {
                test.log(Status.FATAL, "Was unable to extract coordinates from Api response.");
            }
        }
        return lat + ", " + lng;
    }


    String extractCoordinatesFromResponseUsingGson (String parsedResponse, ExtentTest test){
        test.log(Status.INFO, "Extracting coordinates from response using Gson process has begun.");
        String lat = "";
        String lng = "";
        boolean extractSuccess = false;
        try {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(parsedResponse, JsonObject.class);
            JsonArray jsonObject1 = jsonObject.getAsJsonArray("candidates");
            JsonObject jsonObject2 = jsonObject1.get(0).getAsJsonObject();
            JsonObject jsonObject3 = jsonObject2.getAsJsonObject("geometry");
            JsonObject jsonObject4 = jsonObject3.getAsJsonObject("location");

            lat = jsonObject4.get("lat").getAsString();
            lng = jsonObject4.get("lng").getAsString();

            extractSuccess = true;
        }
        finally {
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
