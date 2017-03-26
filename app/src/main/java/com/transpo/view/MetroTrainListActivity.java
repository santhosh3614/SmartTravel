package com.transpo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.transpo.R;
import com.transpo.model.MmtsTrainInfo;
import com.transpo.model.MmtsTrainStationInfo;
import com.transpo.model.MmtsTrainStopInfo;
import com.transpo.utils.AppConstants;
import com.transpo.utils.MmtsTrainStopListCSVFileReader;

import java.util.ArrayList;
import java.util.List;

public class MetroTrainListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private MmtsTrainStationInfo fromLocation;
    private MmtsTrainStationInfo toLocation;
    private ListView listView;
    private MmtsTrainStopListCSVFileReader mmtsCSVReader;
    private List<MmtsTrainInfo> read;
    private ArrayList<MmtsTrainInfo> availableRoutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metro_train_list);
        Intent intent = getIntent();
        if (intent != null) {
            fromLocation = intent.getParcelableExtra(AppConstants.FROM_LOCATION);
            toLocation = intent.getParcelableExtra(AppConstants.TO_LOCATION);
        }

        listView = (ListView) findViewById(R.id.listView);
        mmtsCSVReader = new MmtsTrainStopListCSVFileReader(getResources().openRawResource(R.raw.mmts_details));
        read = mmtsCSVReader.read();
        availableRoutes = new ArrayList();
        for (int i = 0; i < read.size(); i++) {
            MmtsTrainInfo mmtsTrainInfo = read.get(i);
            List<MmtsTrainStopInfo> mmtsTrainStopInfoList = mmtsTrainInfo.getMmtsTrainStopInfoList();
            boolean isFromStationAvailable = false;
            boolean isToStationAvailable = false;
            for (MmtsTrainStopInfo mmtsTrainStopInfo : mmtsTrainStopInfoList) {
                if (!isFromStationAvailable) {
                    isFromStationAvailable = mmtsTrainStopInfo.getStopName().equals(fromLocation.getStationName());
                }
                if (isFromStationAvailable) {
                    isToStationAvailable = mmtsTrainStopInfo.getStopName().equals(toLocation.getStationName());
                    if (isToStationAvailable) {
                        break;
                    }
                }
            }
            if (isFromStationAvailable && isToStationAvailable) {
                availableRoutes.add(mmtsTrainInfo);
            }
        }
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.select_dialog_item, availableRoutes));
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MmtsTrainInfo mmtsTrainInfo = availableRoutes.get(position);
        ListView listView = new ListView(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(listView);
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.select_dialog_item, mmtsTrainInfo.getMmtsTrainStopInfoList()));
        builder.setTitle("Train :" + mmtsTrainInfo.getTrainNo());
        builder.show();
    }
}
