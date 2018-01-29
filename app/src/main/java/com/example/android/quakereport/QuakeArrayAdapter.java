package com.example.android.quakereport;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by jhancock2010 on 1/27/18.
 */

class QuakeArrayAdapter extends ArrayAdapter<QuakeListItem> {

    public QuakeArrayAdapter(Activity context, List<QuakeListItem> earthquakes) {
        super(context, 0, earthquakes);
    }

    private static DateFormat listItemDateFormat = new SimpleDateFormat("MMM dd, yyyy ");
    private static DateFormat listItemTimeFormat = new SimpleDateFormat("HH:mm:ss a");
    public static final String LOG_TAG = QuakeArrayAdapter.class.getName();

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.quake_list_item, parent, false);
        }


        QuakeListItem quakeListItem = getItem(position);

        // Find the TextViews and set their text
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.quake_list_magnitude);

        magnitudeTextView.setText(quakeListItem.getMagnitude() + "");


        TextView locationDirectionTextView =
                (TextView) listItemView.findViewById(R.id.quake_list_location_direction);
        TextView locationPlaceTextView =
                (TextView) listItemView.findViewById(R.id.quake_list_location_place_name);


        try {
            String[] locationSplit = quakeListItem.getLocation()
                    .split(getContext().getString(R.string.location_seperator));

            for (String s: locationSplit){
                Log.d(LOG_TAG, "QuakeArrayAdapter::getView location" +
                        "split element = " + s + ".");

            }
            Log.d(LOG_TAG, "***");


            locationDirectionTextView.setText(locationSplit[0]
                    + getContext().getString(R.string.location_seperator));
            locationPlaceTextView.setText(locationSplit[1]);

        } catch (ArrayIndexOutOfBoundsException e) {
            locationDirectionTextView.setText(quakeListItem.getLocation());
        }

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.quake_list_date);
        dateTextView.setText(listItemDateFormat.format(quakeListItem.getDate()));

        TextView timeTextView = (TextView) listItemView.findViewById(R.id.quake_list_time);
        timeTextView.setText(listItemTimeFormat.format(quakeListItem.getDate()));

        // Return the whole list item layout
        // so that it can be shown in the ListView
        return listItemView;
    }
}
