package com.codejump.foursquaremap.android;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import android.os.Bundle;

public class MapActivity extends SherlockFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
    }
}
