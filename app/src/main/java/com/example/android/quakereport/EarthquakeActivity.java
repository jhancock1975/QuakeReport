/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<QuakeListItem>>{

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    public final static String baseUrl = "https://earthquake.usgs.gov";
    private static final String path = "/fdsnws/event/1/";
    private static final String query
            = "query?format=geojson&eventtype=earthquake&orderby=time&limit=10";
    private QuakeArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(LOG_TAG, "onCreate::starting up");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create an empty list for initial display
        List<QuakeListItem> earthquakes = new ArrayList<QuakeListItem>();

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        earthquakeListView.setOnItemClickListener(new QuakeClickListener());

        //set empty view to display until we finish downloading
        TextView empty=(TextView)findViewById(R.id.empty);
        earthquakeListView.setEmptyView(empty);


        // Create a new {@link ArrayAdapter} of earthquakes
        adapter = new QuakeArrayAdapter(this, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

        //get the actual list of earthquake data in the background
        //using async task loader
        getLoaderManager().initLoader(EarthquakeLoader.EARTHQUAKE_LOADER_ID, null,
                this);
    }

    @Override
    public Loader<List<QuakeListItem>> onCreateLoader(int i, Bundle bundle) {
        Log.d(EarthquakeActivity.LOG_TAG, "oncreateLoader::begin");
        return new EarthquakeLoader(this, baseUrl+path+query);
    }

    @Override
    public void onLoadFinished(Loader<List<QuakeListItem>> loader,
                               List<QuakeListItem> quakeListItems) {
        Log.d(EarthquakeActivity.LOG_TAG, "onLoadFinished begin");
        adapter.setEarthquakes(quakeListItems);
    }

    @Override
    public void onLoaderReset(Loader<List<QuakeListItem>> loader) {
        Log.d(EarthquakeActivity.LOG_TAG, "onLoaderRest begin");
        adapter.setEarthquakes(QueryUtils.getInitialList());
    }
}
