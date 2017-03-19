package com.smarttravel.view;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.smarttravel.R;
import com.smarttravel.model.BusDetailInfo;
import com.smarttravel.utils.AppConstants;
import com.smarttravel.utils.BusDetailsCSVFileReader;

import java.util.List;

public class BusDetailsActivity extends AppCompatActivity {

    private static final String BUS_DETAIL = "bus_detail";
    private ViewPager mViewPager;
    private String fromLocation;
    private String toLocation;
    private BusDetailsCSVFileReader busDetailsCSVFileReader;
    private List<BusDetailInfo> read;
    private Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_details);
        Intent intent = getIntent();
        if (intent != null) {
            fromLocation = intent.getStringExtra(AppConstants.FROM_LOCATION);
            toLocation = intent.getStringExtra(AppConstants.TO_LOCATION);
        }
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mSpinner = (Spinner) findViewById(R.id.spinner);
        busDetailsCSVFileReader = new BusDetailsCSVFileReader(getResources().openRawResource(R.raw.bus_1));
        read = busDetailsCSVFileReader.read();
        mSpinner.setAdapter(new ArrayAdapter(this, android.R.layout.select_dialog_item, read));
        BusDetailsPagerAdapter busDetailsPagerAdapter = new BusDetailsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(busDetailsPagerAdapter);
    }

    public class BusDetailsPagerAdapter extends FragmentStatePagerAdapter {

        public BusDetailsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if (position == 0) {
                fragment = new SummaryFragment();
            } else if (position == 1) {
                fragment = new BusRouteMapFragment();
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
            return 2;
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
