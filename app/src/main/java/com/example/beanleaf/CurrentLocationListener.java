package com.example.beanleaf;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public class CurrentLocationListener implements LocationListener {
    public double myLatitute;
    public double myLongitude;

    @Override
    public void onLocationChanged(Location location) {
        myLatitute = location.getLatitude();
        myLongitude = location.getLongitude();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {}

    @Override
    public void onProviderEnabled(String s) {}

    @Override
    public void onProviderDisabled(String s) {}
}
