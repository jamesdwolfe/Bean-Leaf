package com.example.beanleaf;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.maps.model.TravelMode;

import java.util.Map;

public class RoutingSheetFragment extends BottomSheetDialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.routing_sheet_fragment, container, false);
        final MapsActivity activity = (MapsActivity) getActivity();

        //restaurant title
        TextView title = v.findViewById(R.id.markerTitle);
        title.setText(activity.getSelectedMarkerTitle());

        //drive button
        Button driveButton = v.findViewById(R.id.drive_button);
        driveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.setTravelMode(TravelMode.DRIVING);
                activity.route();
                dismiss();
            }
        });

        //walk button
        Button walkButton = v.findViewById(R.id.walk_button);
        walkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.setTravelMode(TravelMode.WALKING);
                activity.route();
                dismiss();
            }
        });
        return v;
    }
}