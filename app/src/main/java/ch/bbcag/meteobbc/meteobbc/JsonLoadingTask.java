package ch.bbcag.meteobbc.meteobbc;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by nwicks on 31.03.2015.
 */
public class JsonLoadingTask extends AsyncTask<String, Void, List<Temperature>> {


    public static final  String LOG_TAG = JsonLoadingTask.class.getCanonicalName();

    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s";

    private MainActivity activity;

    public JsonLoadingTask(MainActivity activity){
        this.activity = activity;
    }


    @Override
    protected List<Temperature> doInBackground(String... params) {
        List<Temperature> result = null;

        String ort = params[0];
        if (isNetworkConnectionAvailable()) {
            try {
                URL url = new URL(String.format(API_URL, ort));

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.connect();

                int responseCode = connection.getResponseCode();
                if (HttpURLConnection.HTTP_OK == responseCode) {
                    result = parseData(connection.getInputStream());

                } else {
                    Log.e(LOG_TAG, String.format("An error occurred while loading the data in the background. HTTP status: %d", responseCode));
                }

                connection.disconnect();

            } catch (Exception e) {
                Log.e(LOG_TAG, "An error occurred while loading the data in the background", e);
            }
        }

        return result;
    }

    private boolean isNetworkConnectionAvailable() {
        ConnectivityManager connectivityService = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityService.getActiveNetworkInfo();

        return null != networkInfo && networkInfo.isConnected();
    }

    private List<Temperature> parseData(InputStream inputStream) throws IOException, JSONException {

        List<Temperature> result = new ArrayList<>();

        String input = readInput(inputStream);
        JSONObject data = new JSONObject(input);

        Iterator<String> keyIterator = data.keys();
        while(keyIterator.hasNext()){

            String key = keyIterator.next();

            if(key.equals("main")){
                JSONObject temperatureData = data.getJSONObject(key);

                Temperature temperature = new Temperature();
                temperature.setTemperature(temperatureData.getInt("temp"));

                result.add(temperature);
            }
        }

        return result;
    }
    private String readInput(InputStream inputStream) throws IOException {
        StringBuilder resultBuilder = new StringBuilder();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

        String line;
        while (null != (line = bufferedReader.readLine())) {
            resultBuilder.append(line);
        }

        return resultBuilder.toString();
    }



    @Override
    protected void onPostExecute(List<Temperature> result) {
        if (null == result) {
            activity.displayLoadingDataFailedError();

        } else {
            activity.setData(result);
        }

    }
}
