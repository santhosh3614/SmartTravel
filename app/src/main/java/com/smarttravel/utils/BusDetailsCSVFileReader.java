package com.smarttravel.utils;

import com.smarttravel.model.BusDetailInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Santhosh on 3/19/2017.
 */

public class BusDetailsCSVFileReader {

    InputStream inputStream;

    public BusDetailsCSVFileReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public List<BusDetailInfo> read() {
        List<BusDetailInfo> resultList = new ArrayList();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");
                BusDetailInfo mBusDetailInfo = new BusDetailInfo();
                for (int i = 0; i < row.length; i++) {
                    switch (i) {
                        case 0:
                            mBusDetailInfo.setBusName(row[i]);
                            break;
                        case 1:
                            mBusDetailInfo.setSource(row[i]);
                            break;
                        case 2:
                            mBusDetailInfo.setDestination(row[i]);
                            break;
                        case 3:
                            mBusDetailInfo.setTotalDistance(row[i]);
                            break;
                        case 4:
                            mBusDetailInfo.setTimeConsume(row[i]);
                            break;
                        case 5:
                            mBusDetailInfo.setTimePeak(row[i]);
                            break;
                        case 6:
                            mBusDetailInfo.setX(row[i]);
                            break;
                        case 7:
                            mBusDetailInfo.setAllTimings(row[i]);
                            break;
                        case 8:
                            mBusDetailInfo.setAllReverseTimings(row[i]);
                            break;
                        case 9:
                            mBusDetailInfo.setJ(row[i]);
                            break;
                        case 10:
                            mBusDetailInfo.setK(row[i]);
                            break;
                        default:
                            mBusDetailInfo.addRouteStation(row[i]);
                            break;
                    }
                }
                resultList.add(mBusDetailInfo);
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
