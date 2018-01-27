package com.example.android.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

    private static DateFormat listItemDateFormat = new SimpleDateFormat("MMM dd, yyyy");

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.quake_list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        QuakeListItem quakeListItem = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.quake_list_magnitude);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        magnitudeTextView.setText(quakeListItem.getMagnitude()+"");

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView locationTextView = (TextView) listItemView.findViewById(R.id.quake_list_lication);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        locationTextView.setText(quakeListItem.getLocation());

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.quake_list_date);
        dateTextView.setText(listItemDateFormat.format(quakeListItem.getDate()));

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
