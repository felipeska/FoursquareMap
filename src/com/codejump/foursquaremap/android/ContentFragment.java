package com.codejump.foursquaremap.android;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.actionbarsherlock.app.SherlockFragment;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelSlideListener;

public class ContentFragment extends SherlockFragment {

	private static final String TAG = ContentFragment.class.getSimpleName();
	private SlidingUpPanelLayout mUpPanelLayout;
	private Handler mHandler;

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
		Log.i(TAG, "created");
		addMap();
		mUpPanelLayout = (SlidingUpPanelLayout) getView().findViewById(
				R.id.sliding_layout);
		mUpPanelLayout.setShadowDrawable(getResources().getDrawable(
				R.drawable.above_shadow));
		mUpPanelLayout.setCoveredFadeColor(getResources().getColor(
				android.R.color.transparent));
		mUpPanelLayout.setAnchorPoint(0.04f);
		mUpPanelLayout.setPanelSlideListener(new PanelSlideListener() {
			@Override
			public void onPanelSlide(View panel, float slideOffset) {
				Log.i(TAG, "onPanelSlide, offset " + slideOffset);
			}

			@Override
			public void onPanelExpanded(View panel) {
				Log.i(TAG, "onPanelExpanded");

			}

			@Override
			public void onPanelCollapsed(View panel) {
				Log.i(TAG, "onPanelCollapsed");

			}

			@Override
			public void onPanelAnchored(View panel) {
				Log.i(TAG, "onPanelAnchored");
			}
		});

		mHandler = new Handler();
		mHandler.postDelayed(new SlidingUpPaneHandler(), 2000);
	}

	private void addMap() {
		Fragment mFragmentMap = new MapFragment();
		FragmentManager fragmentManager = getActivity()
				.getSupportFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.map, mFragmentMap)
				.commit();
	}

	class SlidingUpPaneHandler implements Runnable {
		public void run() {
			mUpPanelLayout.expandPane(0);
		}
	}
}
