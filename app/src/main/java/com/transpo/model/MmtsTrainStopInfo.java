package com.transpo.model;

/**
 * Created by Santhosh on 3/26/2017.
 */

public class MmtsTrainStopInfo {

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String stopName;
    private String time;

    public MmtsTrainStopInfo(String stopName, String time) {
        this.stopName = stopName;
        this.time = time;
    }

    public MmtsTrainStopInfo() {
    }

    public String getStopName() {
        return stopName;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return stopName + " " + time;
    }
}
