package com.codejump.foursquaremap.android;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.codejump.foursquaremap.android.R.anim;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class MapActivity extends SherlockFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		addMap();
	}

	@Override
	protected void onResume() {
		addContent();
		super.onResume();
	}

	private void addContent() {
		Fragment mFragmentContainer = new ContentFragment();
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction()
				.setCustomAnimations(anim.slide_in_from_bottom, 0)
				.replace(R.id.container, mFragmentContainer).commit();
	}

	private void addMap() {
		Fragment mFragmentMap = new MapFragment();
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.map, mFragmentMap)
				.commit();
	}
}
