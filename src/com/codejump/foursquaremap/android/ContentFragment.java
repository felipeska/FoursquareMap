package com.codejump.foursquaremap.android;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.actionbarsherlock.app.SherlockFragment;
import com.codejump.foursquaremap.android.R.anim;

public class ContentFragment extends SherlockFragment {

	public ContentFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.container, container, false);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		getView().findViewById(R.id.header).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						removeContent();
					}
				});
	}

	private void removeContent() {
		FragmentManager fragmentManager = getActivity()
				.getSupportFragmentManager();
		fragmentManager.beginTransaction()
				.setCustomAnimations(0, anim.slide_out_to_bottom)
				.remove(ContentFragment.this).addToBackStack(null).commit();
	}
}
