package ch.bbcag.meteobbc.meteobbc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class WeatherArrayAdapter extends ArrayAdapter<CityWeather> {

    LayoutInflater mInflater;
    private List<CityWeather> mItems;

    public WeatherArrayAdapter(Context context, List<CityWeather> items, LayoutInflater inflater) {
        super(context, -1, items);
        this.mItems = items;
        this.mInflater = inflater;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {

        convertView = mInflater.inflate(R.layout.city_item, null);
        final CityWeather weather = (CityWeather) mItems.get(pos);

        String date = DateFormat.getDateInstance().format((new Date()));


        ((TextView) convertView.findViewById(R.id.ortFeld)).setText(weather.getOrt());
        ((TextView) convertView.findViewById(R.id.datumFeld)).setText(date);
        ((TextView) convertView.findViewById(R.id.tempFeld)).setText(weather.getTemp());

        return convertView;
    }
}
