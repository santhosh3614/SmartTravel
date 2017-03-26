package com.transpo.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.transpo.R;
import com.transpo.model.BusDetailInfo;
import com.transpo.utils.AppConstants;
import com.transpo.utils.BusDetailsCSVFileReader;

import java.util.ArrayList;
import java.util.List;

public class BusListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private String fromLocation;
    private String toLocation;
    private BusDetailsCSVFileReader busDetailsCSVFileReader;
    private List<BusDetailInfo> read;
    private ArrayList<BusDetailInfo> availableRoutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_list);
        Intent intent = getIntent();
        if (intent != null) {
            fromLocation = intent.getStringExtra(AppConstants.FROM_LOCATION);
            toLocation = intent.getStringExtra(AppConstants.TO_LOCATION);
        }
        listView = (ListView) findViewById(R.id.listView);
        busDetailsCSVFileReader = new BusDetailsCSVFileReader(this, getResources().openRawResource(R.raw.bus_1));
        read = busDetailsCSVFileReader.read();
        availableRoutes = new ArrayList();
        for (int i = 0; i < read.size(); i++) {
            BusDetailInfo busDetailInfo = read.get(i);
            if (busDetailInfo.getAllRoutesStations().contains(fromLocation) &&
                    busDetailInfo.getAllRoutesStations().contains(toLocation)) {
                availableRoutes.add(busDetailInfo);
            }
        }
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.select_dialog_item, availableRoutes));
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, BusDetailsActivity.class);
        intent.putExtra(BusDetailsActivity.BUS_DETAIL, availableRoutes.get(position));
        startActivity(intent);
    }

}
