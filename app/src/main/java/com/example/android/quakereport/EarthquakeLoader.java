package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;


import java.util.List;



/**
 * Created by jhancock2010 on 2/6/18.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<QuakeListItem>> {
    public EarthquakeLoader(@NonNull Context context) {
        super(context);
    }

    private String urlQuery;

    @Nullable
    @Override
    public List<QuakeListItem> loadInBackground() {
        Log.d(EarthquakeActivity.LOG_TAG, "doInBackground::begin");
        return QueryUtils.extractEarthquakes(this.urlQuery);
    }

    public String getUrlQuery() {
        return urlQuery;
    }

    public void setUrlQuery(String urlQuery) {
        this.urlQuery = urlQuery;
    }
}