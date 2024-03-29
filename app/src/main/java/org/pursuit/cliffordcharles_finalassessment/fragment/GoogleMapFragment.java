package org.pursuit.cliffordcharles_finalassessment.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.pursuit.cliffordcharles_finalassessment.R;


public class GoogleMapFragment extends Fragment implements OnMapReadyCallback {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private double lat;
    private double lon;

    SupportMapFragment mapFragment;

    public static GoogleMapFragment newInstance(String param1, String param2) {
        GoogleMapFragment fragment = new GoogleMapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public GoogleMapFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String latParam = getArguments().getString(ARG_PARAM1);
            String lonParam = getArguments().getString(ARG_PARAM2);

            lat = Double.parseDouble(latParam);
            lon = Double.parseDouble(lonParam);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment == null) {

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mapFragment = SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.map, mapFragment).commit();
            mapFragment = SupportMapFragment.newInstance();

        }
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng coordinate = new LatLng(lat, lon);
        LatLng getLocation = new LatLng(lat, lon);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(10);
        CameraUpdate center = CameraUpdateFactory.newLatLng(coordinate);
        googleMap.addMarker(new MarkerOptions().position(getLocation).title("The marker"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(getLocation));
        googleMap.moveCamera(center);
        googleMap.moveCamera(zoom);

        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);

    }

}
