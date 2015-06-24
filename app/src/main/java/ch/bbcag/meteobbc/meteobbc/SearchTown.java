package ch.bbcag.meteobbc.meteobbc;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.SearchView;

/**
 * Created by zbuchs on 19.06.2015.
 */
public class SearchTown extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        SearchView searchTown = (SearchView) findViewById(R.id.searchView);
        searchTown.add

        SharedPreferences townList = getSharedPreferences("townList",0);
        SharedPreferences.Editor editor = townList.edit();

        editor.putBoolean(searchTown.toString(),true);

    }

    @Override
    protected void onStop(){
        super.onStop();
    }

}
