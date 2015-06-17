package ch.bbcag.meteobbc.meteobbc;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JsonLoadingTask loadingTask = new JsonLoadingTask(this);
        loadingTask.execute("Zürich");
    }

    public void displayLoadingDataFailedError() {
        Toast.makeText(this, "Errör", Toast.LENGTH_SHORT).show();
    }

    public void setData(List<Temperature> result) {

        StringBuilder sb = new StringBuilder();
        for (Temperature temperature : result) {
            sb.append(temperature.toString());
            sb.append("\n\n");
        }

        ListView dataView = (ListView) findViewById(R.id.data);
        dataView.setText(sb.toString());

    }
}