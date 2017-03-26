package com.transpo.view;

import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.transpo.R;
import com.transpo.model.BusDetailInfo;
import com.transpo.utils.AppConstants;
import com.transpo.utils.LatLongFileReader;

import java.io.InputStream;

/**
 * Created by Santhosh on 3/19/2017.
 */
public class BusRouteMapFragment extends SupportMapFragment implements OnMapReadyCallback {

    private String fromLocation;
    private String toLocation;
    private BusDetailInfo busDetailInfo;
    private LatLongFileReader latLongFileReader;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        fromLocation = args.getString(AppConstants.FROM_LOCATION);
        toLocation = args.getString(AppConstants.TO_LOCATION);
        busDetailInfo = args.getParcelable(BusDetailsActivity.BUS_DETAIL);
        latLongFileReader = new LatLongFileReader(getContext());
        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

//        LatLng sourceLatLng = latLongFileReader.read(busDetailInfo.getSource());
//        LatLng destLatLng = latLongFileReader.read(busDetailInfo.getDestination());
//
//        googleMap.addMarker(new MarkerOptions().position(sourceLatLng)
//                .title(busDetailInfo.getSource()));
//        googleMap.addMarker(new MarkerOptions().position(destLatLng)
//                .title(busDetailInfo.getDestination()));
//
//        Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
//                .add(sourceLatLng, destLatLng));
    }
}
