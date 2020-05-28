package com.example.beanleaf;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.TravelMode;

import org.joda.time.DateTime;

import java.util.concurrent.TimeUnit;

public class GeoApiHelper extends ContextWrapper {

    public GeoApiHelper(Context base) {
        super(base);
    }

    private GeoApiContext getGeoContext() {
        GeoApiContext geoApiContext = new GeoApiContext();
        return geoApiContext.setQueryRateLimit(3)
                .setApiKey(getString(R.string.api_key))
                .setConnectTimeout(2, TimeUnit.SECONDS)
                .setReadTimeout(2, TimeUnit.SECONDS)
                .setWriteTimeout(2, TimeUnit.SECONDS);
    }

    public DirectionsResult directionsApiRequest(LatLng currentLocation, Marker marker, TravelMode travelMode, DateTime now) {
        try {
            return DirectionsApi.newRequest(getGeoContext())
                    .mode(travelMode)
                    .origin(new com.google.maps.model.LatLng(currentLocation.latitude, currentLocation.longitude))
                    .destination(new com.google.maps.model.LatLng(marker.getPosition().latitude, marker.getPosition().longitude))
                    .departureTime(now)
                    .await();
        } catch (Exception e) {
            //IOException or APIException
            Log.d("DirectionsApiException", e.getMessage());
            return null;
        }
    }

    public GeocodingResult geocodingApiRequest(String address) {
        try {
            return GeocodingApi.newRequest(getGeoContext())
                    .address(address)
                    .await()[0];
        } catch (Exception e) {
            //IOException or APIException
            Log.d("GeocodingApiException", e.getMessage());
            return null;
        }
    }
}
