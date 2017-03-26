package com.transpo.utils;

import com.google.android.gms.maps.model.LatLng;
import com.transpo.model.MmtsTrainStationInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Santhosh on 3/26/2017.
 */
public class MmtsTrainStationListCSVFileReader {

    private InputStream inputStream;

    public MmtsTrainStationListCSVFileReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public List<MmtsTrainStationInfo> read() {
        List<MmtsTrainStationInfo> resultList = new ArrayList();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");
                double latitude = Double.parseDouble(row[1]);
                double longitude = Double.parseDouble(row[2]);
                LatLng mLatLng = new LatLng(latitude, longitude);
                MmtsTrainStationInfo mmtsTrainStationInfo
                        = new MmtsTrainStationInfo(row[0], mLatLng);
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
