package com.transpo.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.transpo.R;
import com.transpo.utils.AppConstants;
import com.transpo.utils.RoutesListCSVFileReader;

import java.util.ArrayList;
import java.util.List;

public class BusLocationActivity extends AppCompatActivity implements View.OnClickListener {

    private AutoCompleteTextView fromAutoCompleteTextView;
    private AutoCompleteTextView toAutoCompleteTextView;
    private Button searchButton;

    private List<String> allLocationRoutesList;
    private List<String> fromLocationRoutesList;
    private List<String> toLocationRotesList;
    private String fromLocation;
    private String toLocation;
    private ArrayAdapter<String> frmLocRouteAdapter;
    private ArrayAdapter<String> toLocRouteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_location);
        fromAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.fromAutoCompTV);
        toAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.toAutoCompTV);
        searchButton = (Button) findViewById(R.id.searchBtn);

        fromAutoCompleteTextView.setThreshold(1);
        toAutoCompleteTextView.setThreshold(1);
        RoutesListCSVFileReader routesListCSVFileReader = new RoutesListCSVFileReader(getResources().openRawResource(R.raw.buslist));
        allLocationRoutesList = routesListCSVFileReader.read();
        fromLocationRoutesList = new ArrayList(allLocationRoutesList);
        toLocationRotesList = new ArrayList(allLocationRoutesList);
        frmLocRouteAdapter = new ArrayAdapter(this, android.R.layout.select_dialog_item, fromLocationRoutesList);
        fromAutoCompleteTextView.setAdapter(frmLocRouteAdapter);
        toLocRouteAdapter = new ArrayAdapter(this, android.R.layout.select_dialog_item, toLocationRotesList);
        toAutoCompleteTextView.setAdapter(toLocRouteAdapter);
        fromAutoCompleteTextView.setOnItemClickListener(getFromPositionItemClick());
        toAutoCompleteTextView.setOnItemClickListener(getToPositionItemClick());
        searchButton.setOnClickListener(this);
    }

    private AdapterView.OnItemClickListener getToPositionItemClick() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toLocation = toLocRouteAdapter.getItem(position);
                fromLocationRoutesList.clear();
                fromLocationRoutesList.addAll(allLocationRoutesList);
                if (!TextUtils.isEmpty(toLocation)) {
                    fromLocationRoutesList.remove(toLocation);
                }
            }
        };
    }

    private AdapterView.OnItemClickListener getFromPositionItemClick() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fromLocation = frmLocRouteAdapter.getItem(position);
                toLocationRotesList.clear();
                toLocationRotesList.addAll(allLocationRoutesList);
                if (!TextUtils.isEmpty(toLocation)) {
                    toLocationRotesList.remove(fromLocation);
                }
            }
        };
    }

    @Override
    public void onClick(View v) {
        if (!TextUtils.isEmpty(fromLocation) &&
                !TextUtils.isEmpty(toLocation)) {
            Intent intent = new Intent(this, BusListActivity.class);
            intent.putExtra(AppConstants.FROM_LOCATION, fromLocation);
            intent.putExtra(AppConstants.TO_LOCATION, toLocation);
            startActivity(intent);
        }
    }

}
