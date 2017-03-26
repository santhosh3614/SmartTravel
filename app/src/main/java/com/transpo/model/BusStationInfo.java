package com.transpo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Santhosh on 3/23/2017.
 */

public class BusStationInfo implements Parcelable{

    private String stationName;
    private LatLng latLng;

    public BusStationInfo(String stationName, LatLng latLng) {
        this.stationName = stationName;
        this.latLng = latLng;
    }

    protected BusStationInfo(Parcel in) {
        stationName = in.readString();
        latLng = in.readParcelable(LatLng.class.getClassLoader());
    }

    public static final Creator<BusStationInfo> CREATOR = new Creator<BusStationInfo>() {
        @Override
        public BusStationInfo createFromParcel(Parcel in) {
            return new BusStationInfo(in);
        }

        @Override
        public BusStationInfo[] newArray(int size) {
            return new BusStationInfo[size];
        }
    };

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(stationName);
        dest.writeParcelable(latLng, flags);
    }
}
