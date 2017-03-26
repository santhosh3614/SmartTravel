package com.transpo.utils;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.transpo.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Santhosh on 3/23/2017.
 */

public class LatLongFileReader {

    private final Context mContext;

    public LatLongFileReader(Context mContext) {
        this.mContext = mContext;
    }

    public LatLng read(String stationName) {
        InputStream inputStream = mContext.getResources().openRawResource(R.raw.latlon);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            double latitude = 0, longitude = 0;
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");
                if (row[0].toLowerCase().equals(stationName.toLowerCase())) {
                    for (int i = 0; i < row.length; i++) {
                        switch (i) {
                            case 1:
                                latitude = Double.parseDouble(row[i]);
                                break;
                            case 2:
                                longitude = Double.parseDouble(row[i]);
                                break;
                        }
                    }
                    return new LatLng(latitude, longitude);
                }
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
        return null;
    }
}
