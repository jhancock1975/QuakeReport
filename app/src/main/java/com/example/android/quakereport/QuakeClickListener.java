package com.example.android.quakereport;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by jhancock2010 on 1/29/18.
 * We do not like anonymous inner classes.  We find them
 * hard to read.
 */

class QuakeClickListener implements android.widget.AdapterView.OnItemClickListener {

    private static final String LOG_TAG = QuakeClickListener.class.getName();
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d(LOG_TAG, "onItemClick::item clicked");
    }
}
