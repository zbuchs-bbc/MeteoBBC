package ch.bbcag.meteobbc.meteobbc;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView img = (ImageView) findViewById(R.id.plusblau);
        img.setImageResource(R.drawable.plusblau);

        List<String> cities = new ArrayList<String>();

        cities.add("Chur");
        cities.add("Bern");
        //cities.add("Zürich");

        ConnectivityManager connectivityService = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        ListView listView = (ListView) findViewById(R.id.listView);
        WeatherArrayAdapter adapter = new WeatherArrayAdapter(getApplicationContext(), new ArrayList<CityWeather>(), getLayoutInflater());
        listView.setAdapter(adapter);

        for(String city : cities) {
            JsonLoadingTask loadingTask = new JsonLoadingTask(adapter, connectivityService);
            loadingTask.execute(city);
        }

    }

    public void displayLoadingDataFailedError() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }

    public void setData(List<Temperature> result) {

        StringBuilder sb = new StringBuilder();
        for (Temperature temperature : result) {
            sb.append(temperature.toString());
            sb.append("\n\n");
        }

        TextView dataView = (TextView) findViewById(R.id.data);
        dataView.setText(sb.toString());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String getDate() {

        TextView textView = (TextView) findViewById(R.id.datumFeld);
        String date = DateFormat.getDateInstance().format((new Date()));
        textView.setText(date);

        return date;
    }
}