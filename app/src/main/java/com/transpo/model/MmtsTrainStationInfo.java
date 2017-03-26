package com.transpo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Santhosh on 3/26/2017.
 */
public class MmtsTrainStationInfo implements Parcelable{

    private String stationName;
    private LatLng mLatLng;

    public MmtsTrainStationInfo(String stationName, LatLng mLatLng) {
        this.stationName = stationName;
        this.mLatLng = mLatLng;
    }

    protected MmtsTrainStationInfo(Parcel in) {
        stationName = in.readString();
        mLatLng = in.readParcelable(LatLng.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(stationName);
        dest.writeParcelable(mLatLng, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MmtsTrainStationInfo> CREATOR = new Creator<MmtsTrainStationInfo>() {
        @Override
        public MmtsTrainStationInfo createFromParcel(Parcel in) {
            return new MmtsTrainStationInfo(in);
        }

        @Override
        public MmtsTrainStationInfo[] newArray(int size) {
            return new MmtsTrainStationInfo[size];
        }
    };

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public LatLng getmLatLng() {
        return mLatLng;
    }

    public void setmLatLng(LatLng mLatLng) {
        this.mLatLng = mLatLng;
    }

    @Override
    public String toString() {
        return stationName;
    }
}
