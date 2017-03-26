package com.transpo.view;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.maps.model.LatLng;
import com.transpo.R;
import com.transpo.model.BusDetailInfo;
import com.transpo.utils.AppConstants;
import com.transpo.utils.BusDetailsCSVFileReader;
import com.transpo.utils.LatLongFileReader;

import java.io.InputStream;
import java.util.List;

public class BusDetailsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String BUS_DETAIL = "bus_detail";
    private ViewPager mViewPager;
    private String fromLocation;
    private String toLocation;
    private BusDetailsCSVFileReader busDetailsCSVFileReader;
    private List<BusDetailInfo> read;
    private Spinner mSpinner;
    private BusDetailsPagerAdapter busDetailsPagerAdapter;
    private BusDetailInfo busDetailInfo = null;
    private InputStream latlongStream;
    private LatLongFileReader latLongFileReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_details);
        Intent intent = getIntent();
        if (intent != null) {
            fromLocation = intent.getStringExtra(AppConstants.FROM_LOCATION);
            toLocation = intent.getStringExtra(AppConstants.TO_LOCATION);
            busDetailInfo = intent.getParcelableExtra(BUS_DETAIL);
        }
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mSpinner = (Spinner) findViewById(R.id.spinner);
        busDetailsCSVFileReader = new BusDetailsCSVFileReader(this, getResources().openRawResource(R.raw.bus_1));
        read = busDetailsCSVFileReader.read();
        if (busDetailInfo != null) {
            findViewById(R.id.topContainer).setVisibility(View.GONE);
        }
        latLongFileReader = new LatLongFileReader(this);
        mSpinner.setAdapter(new ArrayAdapter(this, android.R.layout.select_dialog_item, read));
        mSpinner.setOnItemSelectedListener(this);
        busDetailsPagerAdapter = new BusDetailsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(busDetailsPagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (busDetailInfo != null) {
            getMenuInflater().inflate(R.menu.bus_menu, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.map_menu) {
            String source = busDetailInfo.getSource();
            String destination = busDetailInfo.getDestination();
            LatLng mSrcLatLng = latLongFileReader.read(source);
            LatLng mDestLatLng = latLongFileReader.read(destination);
            openInMap(mSrcLatLng, mDestLatLng);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ((SummaryFragment) busDetailsPagerAdapter.fragments[0])
                .initViews(read.get(position));
    }

    private void openInMap(LatLng srcmLatLng, LatLng dstmLatLng) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr=" + srcmLatLng.latitude + ","
                        + srcmLatLng.longitude + "&daddr=" + dstmLatLng.latitude + "," + dstmLatLng.longitude));
        startActivity(intent);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private class BusDetailsPagerAdapter extends FragmentStatePagerAdapter {

        private Fragment[] fragments = new Fragment[2];

        public BusDetailsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if (position == 0) {
                fragment = new SummaryFragment();
                fragments[0] = fragment;
            } else if (position == 1) {
                fragment = new BusRouteMapFragment();
                fragments[1] = fragment;
            }
            Bundle args = new Bundle();
            args.putString(AppConstants.FROM_LOCATION, fromLocation);
            args.putString(AppConstants.TO_LOCATION, fromLocation);
            args.putParcelable(BUS_DETAIL, read.get(0));
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "Summary";
            } else if (position == 1) {
                return "Map";
            }
            return null;
        }
    }

}
