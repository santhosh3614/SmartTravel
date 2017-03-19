package com.smarttravel.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Santhosh on 3/19/2017.
 */

public class BusDetailInfo implements Parcelable {

    private String busName;
    private String source;
    private String destination;
    private String totalDistance;
    private String timeConsume;
    private String timePeak;
    private String x;
    private String allTimings;
    private String allReverseTimings;
    private String j;
    private String k;
    private String busType;
    private List<String> allRoutesStations = new ArrayList();

    public BusDetailInfo() {
    }

    protected BusDetailInfo(Parcel in) {
        busName = in.readString();
        source = in.readString();
        destination = in.readString();
        totalDistance = in.readString();
        timeConsume = in.readString();
        timePeak = in.readString();
        x = in.readString();
        allTimings = in.readString();
        allReverseTimings = in.readString();
        j = in.readString();
        k = in.readString();
        busType = in.readString();
        allRoutesStations = in.createStringArrayList();
    }

    public static final Creator<BusDetailInfo> CREATOR = new Creator<BusDetailInfo>() {
        @Override
        public BusDetailInfo createFromParcel(Parcel in) {
            return new BusDetailInfo(in);
        }

        @Override
        public BusDetailInfo[] newArray(int size) {
            return new BusDetailInfo[size];
        }
    };

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(String totalDistance) {
        this.totalDistance = totalDistance;
    }

    public String getTimeConsume() {
        return timeConsume;
    }

    public void setTimeConsume(String timeConsume) {
        this.timeConsume = timeConsume;
    }

    public String getTimePeak() {
        return timePeak;
    }

    public void setTimePeak(String timePeak) {
        this.timePeak = timePeak;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getAllTimings() {
        return allTimings;
    }

    public void setAllTimings(String allTimings) {
        this.allTimings = allTimings;
    }

    public String getAllReverseTimings() {
        return allReverseTimings;
    }

    public void setAllReverseTimings(String allReverseTimings) {
        this.allReverseTimings = allReverseTimings;
    }

    public String getJ() {
        return j;
    }

    public void setJ(String j) {
        this.j = j;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public List<String> getAllRoutesStations() {
        return allRoutesStations;
    }

    public void setAllRoutesStations(List<String> allRoutesStations) {
        this.allRoutesStations = allRoutesStations;
    }

    public void addRouteStation(String route) {
        this.allRoutesStations.add(route);
    }

    @Override
    public String toString() {
        return busName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(busName);
        dest.writeString(source);
        dest.writeString(destination);
        dest.writeString(totalDistance);
        dest.writeString(timeConsume);
        dest.writeString(timePeak);
        dest.writeString(x);
        dest.writeString(allTimings);
        dest.writeString(allReverseTimings);
        dest.writeString(j);
        dest.writeString(k);
        dest.writeString(busType);
        dest.writeStringList(allRoutesStations);
    }
}
