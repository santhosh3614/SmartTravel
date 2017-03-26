package com.transpo.utils;

import com.transpo.model.MmtsTrainInfo;
import com.transpo.model.MmtsTrainStopInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Santhosh on 3/26/2017.
 */

public class MmtsTrainStopListCSVFileReader {

    private InputStream inputStream;

    public MmtsTrainStopListCSVFileReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public List<MmtsTrainInfo> read() {
        List<MmtsTrainInfo> resultList = new ArrayList();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");
                MmtsTrainInfo mmtsTrainStationInfo = new MmtsTrainInfo();
                List<MmtsTrainStopInfo> mmtsTrainStopInfoList = new ArrayList();
                mmtsTrainStationInfo.setMmtsTrainStopInfoList(mmtsTrainStopInfoList);
                MmtsTrainStopInfo mmtsTrainStopInfo;
                for (int i = 0; i < row.length; i++) {
                    switch (i) {
                        case 0:
                            mmtsTrainStationInfo.setTrainNo(row[i]);
                            break;
                        default:
                            mmtsTrainStopInfo = new MmtsTrainStopInfo();
                            mmtsTrainStopInfo.setStopName(row[i]);
                            mmtsTrainStopInfo.setTime(row[i + 1]);
                            mmtsTrainStopInfoList.add(mmtsTrainStopInfo);
                            break;
                    }
                    if (i != 0) {
                        i = i + 1;
                    }
                }
                resultList.add(mmtsTrainStationInfo);
            }
        } catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: " + ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: " + e);
            }
        }
        return resultList;
    }


}
