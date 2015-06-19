package ch.bbcag.meteobbc.meteobbc;

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
import java.util.Iterator;

public class JsonLoadingTask extends AsyncTask<String, Void, Temperature> {





    public static final String LOG_TAG = JsonLoadingTask.class.getCanonicalName();
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric&lang=de";
    private WeatherArrayAdapter adapter;
    private ConnectivityManager connectivityManager;


    public JsonLoadingTask(WeatherArrayAdapter adapter, ConnectivityManager connectivityManager) {
        this.adapter = adapter;
        this.connectivityManager = connectivityManager;
    }

    @Override
    protected Temperature doInBackground(String... params) {
        Temperature result = null;

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

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return null != networkInfo && networkInfo.isConnected();
    }

    private Temperature parseData(InputStream inputStream) throws IOException, JSONException {


        String input = readInput(inputStream);
        JSONObject data = new JSONObject(input);
        Temperature temperature = new Temperature();

        String city = data.getString("name");
        temperature.setStadtName(city);

        Iterator<String> keyIterator = data.keys();
        while (keyIterator.hasNext()) {

            try
            {
                String key = keyIterator.next();

                JSONObject temperatureData = data.getJSONObject(key);

                if (key.equals("main")) {

                    temperature.setTemperature(temperatureData.getDouble("temp"));
                }
            }
            catch (Exception ex)
            {
                Log.v("Task", ex.toString());
            }

        }

        return temperature;
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
    protected void onPostExecute(Temperature result) {

        adapter.add(new CityWeather(result.getStadtName(), Double.toString(result.getTemperature())));
        adapter.notifyDataSetChanged();

    }
}
