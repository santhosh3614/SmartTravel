package com.transpo.model;

import java.util.List;

/**
 * Created by Santhosh on 3/26/2017.
 */

public class MmtsTrainInfo {

    private String trainNo;
    private List<MmtsTrainStopInfo> mmtsTrainStopInfoList;

    public String getTrainNo() {
        return trainNo;
    }

    public List<MmtsTrainStopInfo> getMmtsTrainStopInfoList() {
        return mmtsTrainStopInfoList;
    }

    @Override
    public String toString() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public void setMmtsTrainStopInfoList(List<MmtsTrainStopInfo> mmtsTrainStopInfoList) {
        this.mmtsTrainStopInfoList = mmtsTrainStopInfoList;
    }
}
