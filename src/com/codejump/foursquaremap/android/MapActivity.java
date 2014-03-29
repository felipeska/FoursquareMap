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
		addContent();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	void addContent() {
		Fragment mFragmentContainer = new ContentFragment();
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction()
				.setCustomAnimations(anim.slide_in_from_bottom, 0)
				.replace(R.id.container, mFragmentContainer).commit();
	}
}
