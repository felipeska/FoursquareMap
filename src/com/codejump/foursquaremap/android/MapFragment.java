package com.codejump.foursquaremap.android;

import java.util.List;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.actionbarsherlock.app.SherlockFragment;
import com.cyrilmottier.polaris2.maps.CameraUpdate;
import com.cyrilmottier.polaris2.maps.CameraUpdateFactory;
import com.cyrilmottier.polaris2.maps.GoogleMap;
import com.cyrilmottier.polaris2.maps.GoogleMap.CancelableCallback;
import com.cyrilmottier.polaris2.maps.GoogleMap.OnCameraChangeListener;
import com.cyrilmottier.polaris2.maps.SupportMapFragment;
import com.cyrilmottier.polaris2.maps.UiSettings;
import com.cyrilmottier.polaris2.maps.model.CameraPosition;
import com.cyrilmottier.polaris2.maps.model.LatLng;

public class MapFragment extends SherlockFragment implements
		OnCameraChangeListener {

	private GoogleMap mMap;
	private SupportMapFragment mMapFragment;
	private UiSettings mUiSettings;
	private final static float DEFAULT_ZOOM = 17.0f;
	private CameraPosition mCameraPosition;

	public MapFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.map, container, false);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		FragmentManager fm = getChildFragmentManager();
		mMapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);

		if (mMapFragment == null) {
			mMapFragment = SupportMapFragment.newInstance();
			fm.beginTransaction().replace(R.id.mapSpace, mMapFragment).commit();
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		setUpMapIfNeeded();
	}

	private void setUpMap() {
		mMap.setMyLocationEnabled(true);
		mUiSettings = mMap.getUiSettings();
		mUiSettings.setAllGesturesEnabled(true);
		mUiSettings.setCompassEnabled(false);
		mUiSettings.setMyLocationButtonEnabled(false);
		mUiSettings.setZoomControlsEnabled(true);
		mMap.setOnCameraChangeListener(this);
		locateMe();
	}

	private void setUpMapIfNeeded() {
		if (mMap == null) {
			mMap = mMapFragment.getPolarisMap();
			if (mMap != null) {
				setUpMap();
			}
		}
	}

	private void locateMe() {
		LatLng mLoc = getlocation();
		if (mLoc != null) {
			mCameraPosition = buildCamera(mLoc, DEFAULT_ZOOM);
			changeCamera(CameraUpdateFactory.newCameraPosition(mCameraPosition));
		}
	}

	private CameraPosition buildCamera(LatLng mLocation, float zoom) {
		return new CameraPosition.Builder()
				.target(new LatLng(mLocation.latitude, mLocation.longitude))
				.zoom(zoom).bearing(45.0f).tilt(0).build();
	}

	private void changeCamera(CameraUpdate update) {
		changeCamera(update, null);
	}

	private void changeCamera(CameraUpdate update, CancelableCallback callback) {
		mMap.animateCamera(update, callback);
		CameraUpdateFactory.scrollBy(1000, 100);
	}

	@Override
	public void onCameraChange(CameraPosition position) {
		System.out.println("se mueve la camara:");
	}

	private LatLng getlocation() throws SecurityException,
			IllegalArgumentException {
		LatLng actualLocation = null;
		LocationManager lm = (LocationManager) getActivity().getSystemService(
				Context.LOCATION_SERVICE);
		List<String> providers = lm.getProviders(true);

		Location l = null;
		for (int i = 0; i < providers.size(); i++) {
			l = lm.getLastKnownLocation(providers.get(i));
			if (l != null)
				break;
		}
		if (l != null) {
			actualLocation = new LatLng(l.getLatitude(), l.getLongitude());
		}
		return actualLocation;
	}

}
