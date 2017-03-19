package com.smarttravel.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.smarttravel.R;
import com.smarttravel.utils.AppConstants;
import com.smarttravel.utils.RoutesListCSVFileReader;

import java.util.ArrayList;
import java.util.List;

public class BusLocationActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private AutoCompleteTextView fromAutoCompleteTextView;
    private AutoCompleteTextView toAutoCompleteTextView;
    private Button searchButton;

    private List<String> allLocationRoutesList;
    private List<String> fromLocationRoutesList;
    private List<String> toLocationRotesList;
    private String fromLocation;
    private String toLocation;

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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, fromLocationRoutesList);
        fromAutoCompleteTextView.setAdapter(adapter);
        toAutoCompleteTextView.setAdapter(adapter);
        fromAutoCompleteTextView.setOnItemClickListener(this);
        toAutoCompleteTextView.setOnItemClickListener(this);
        searchButton.setOnClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (view == fromAutoCompleteTextView) {
            fromLocation = fromLocationRoutesList.get(position);
            toLocationRotesList.clear();
            toLocationRotesList.addAll(allLocationRoutesList);
            if (!TextUtils.isEmpty(toLocation)) {
                toLocationRotesList.remove(fromLocation);
            }
        } else if (view == toAutoCompleteTextView) {
            toLocation = toLocationRotesList.get(position);
            fromLocationRoutesList.clear();
            fromLocationRoutesList.addAll(allLocationRoutesList);
            if (!TextUtils.isEmpty(toLocation)) {
                fromLocationRoutesList.remove(toLocation);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (!TextUtils.isEmpty(fromLocation) &&
                !TextUtils.isEmpty(toLocation)) {
            Intent intent = new Intent(this, BusDetailsActivity.class);
            intent.putExtra(AppConstants.FROM_LOCATION, fromLocation);
            intent.putExtra(AppConstants.TO_LOCATION, fromLocation);
            startActivity(intent);
        }
    }

}
