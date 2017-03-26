package com.transpo.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.transpo.R;
import com.transpo.model.BusDetailInfo;
import com.transpo.utils.AppConstants;

import java.util.List;

/**
 */
public class SummaryFragment extends Fragment {

    private String fromLocation;
    private String toLocation;
    private BusDetailInfo busDetailInfo;
    private TextView busDetailTV;
    private TextView timingTV;
    private TextView routesTV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        fromLocation = args.getString(AppConstants.FROM_LOCATION);
        toLocation = args.getString(AppConstants.TO_LOCATION);
        busDetailInfo = args.getParcelable(BusDetailsActivity.BUS_DETAIL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_summary, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        busDetailTV = (TextView) view.findViewById(R.id.busDetailTV);
        timingTV = (TextView) view.findViewById(R.id.timingTV);
        routesTV = (TextView) view.findViewById(R.id.routesTV);
        initViews(busDetailInfo);
    }

    public void initViews(BusDetailInfo busDetailInfo) {
        busDetailTV.setText("This bus services from " + busDetailInfo.getSource() + " to " + busDetailInfo.getDestination() +
                ".It covers a distance of " + busDetailInfo.getTotalDistance() + " kms from source to destination.The bus takes " +
                busDetailInfo.getTimeConsume() + "mins to complete the journey.");
        timingTV.setText(busDetailInfo.getAllTimings());
        List<String> allRoutesStations = busDetailInfo.getAllRoutesStations();
        StringBuilder routes = new StringBuilder();
        for (int i = 0; i < allRoutesStations.size(); i++) {
            routes.append((i + 1) + ". " + allRoutesStations.get(i));
            routes.append("\n");
        }
        routesTV.setText(routes);
    }

}
