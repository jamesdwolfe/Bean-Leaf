package com.example.beanleaf;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        LocationListener,
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnCameraIdleListener {

    private final float DEFAULT_ZOOM_LEVEL = 12.0f;
    private final float CLOSE_ENOUGH_DISTANCE = 500.0f; //meters

    private SupportMapFragment mapFragment;
    private GoogleMap mMap;
    public GoogleMap getMap() { return mMap; }

    private LocationManager locationManager;
    private LatLng currentLocation;
    public LatLng getCurrentLocation() { return currentLocation; }

    private Marker selectedMarker;
    public void setSelectedMarker(Marker selectedMarker) { this.selectedMarker = selectedMarker; }

    private Location destination = new Location(LocationManager.NETWORK_PROVIDER);
    public Location getDestination() { return destination; }
    private Polyline routePolyline;
    private String etaString;

    private boolean userLogedIn;
    private boolean customerLogedIn;
    private String username;

    private SearchView searchView;
    private ListView listView;
    private ArrayAdapter<String > adapter;
    private Map<String, Marker> markersByRestaurant;
    public Map<String, Marker> getMarkersByRestaurant() { return markersByRestaurant; }

    private ArrayList<Object> restaurants;
    public void setRestaurants(ArrayList<Object> restaurants) { this.restaurants = restaurants; }

    private DBHandler database;

    private boolean popupDisplayed = false;

    private TravelMode travelMode = TravelMode.DRIVING;

    public String getSelectedMarkerTitle() {
        return selectedMarker.getTitle();
    }

    public void setTravelMode(TravelMode travel) {
        travelMode = travel;
    }

    private boolean locationInitted = false;
    private boolean isRouting = false;
    public boolean getIsRouting() { return isRouting; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        database = DBHandler.getInstance(this);

        markersByRestaurant = new HashMap<>();
        restaurants = database.getRestaurants();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mapFragment.getView().setVisibility(View.GONE);

        //Get device coordinates
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void HomeMenu(View view) {
        //If customer or merchant logged in then route to their appropriate pages
        //else route to signin or signout page
        state st = (state) getApplicationContext();
        userLogedIn = st.isLoggedIn();
        customerLogedIn = st.isCustomer;
        username = st.getUsername();

        //determine activity flow
        Intent intent;
        if (userLogedIn) {
            if (customerLogedIn) {
                intent = new Intent(this, customerPage.class);
            } else {
                intent = new Intent(this, merchantPage.class);
            }
        } else {
            intent = new Intent(this, NavigationActivity.class);
        }
        startActivity(intent);


        if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);



    }


        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera. In this case,
         * we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to install
         * it inside the SupportMapFragment. This method will only be triggered once the user has
         * installed Google Play services and returned to the app.
         */

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onMapReady (GoogleMap googleMap){
            String[] permissionList = new String[1];
            permissionList[0] = android.Manifest.permission.ACCESS_FINE_LOCATION;
            mMap = googleMap;
            mMap.setOnMarkerClickListener(this);

            if (ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {



                this.requestPermissions(
                        permissionList,
                        1);

                System.out.println("Get user permission ready");
                mMap.setMyLocationEnabled(true);

            }

            mMap.setOnCameraIdleListener(this);

            for (int i = 0; i < restaurants.size(); i++) {
                if(/*restaurant is in current map view*/ true) {
                    Restaurant r = (Restaurant)restaurants.get(i);
                    LatLng ll = new LatLng(r.getLocationLat(), r.getLocationLng());
                    String name = r.getName().toLowerCase();
                    String address = "";
//                String address = r.getAddress().toLowercase();
                    Marker m = mMap.addMarker(new MarkerOptions().position(ll).title(r.getName()));
                    markersByRestaurant.put(name + ", " + address, m);
                }
            }

            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);

            searchView = (SearchView) findViewById(R.id.searchView);
            listView = (ListView) findViewById(R.id.lv1);

            List<String> list = new ArrayList<>(markersByRestaurant.keySet());
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
            listView.setAdapter(adapter);

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    String lowerCaseQuery = query.toLowerCase();
                    String[] restaurantSearchCriteria = markersByRestaurant.keySet().toArray(new String[0]);
                    boolean result = false;
                    for(int i = 0; i < markersByRestaurant.size(); i++) {
                        if (restaurantSearchCriteria[i].contains(lowerCaseQuery)) {
                            result = true;
                        } else {
                            markersByRestaurant.get(restaurantSearchCriteria[i]).setVisible(false);
                        }
                    }
                    if(!result) {
                        Toast.makeText(MapsActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                    }
                    return false;
                }

                @Override
                public boolean onQueryTextChange(final String newText) {
                    if (newText.equals("")) {
                        listView.setVisibility(View.INVISIBLE);
                    } else {
                        String lowerCaseQuery = newText.toLowerCase();
                        // filter the list
                        List<String> list = new ArrayList<>(markersByRestaurant.keySet());
                        List<String> filteredList = new ArrayList<>();
                        for (String s : list) {
                            if (s.toLowerCase().contains(lowerCaseQuery)) {
                                filteredList.add(s);
                            }
                        }
                        // replace the list in the adapter
                        adapter.clear();
                        adapter.addAll(filteredList);
                        if(filteredList.size() > 0) {
                            // calculate height
                            ListAdapter la = listView.getAdapter();
                            View mView = la.getView(0, null, listView);
                            mView.measure(
                                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                            LayoutParams params = listView.getLayoutParams();
                            int totalHeight = (mView.getMeasuredHeight() * adapter.getCount()) + (listView.getDividerHeight() * (adapter.getCount() - 1));
                            int fiveItemHeight = (mView.getMeasuredHeight() * 5) + (listView.getDividerHeight() * 4);
                            params.height = totalHeight > fiveItemHeight ? fiveItemHeight : totalHeight;
                            // set height
                            listView.setLayoutParams(params);
                            listView.setVisibility(View.VISIBLE);
                        } else {
                            listView.setVisibility(View.INVISIBLE);
                        }
                    }
                    return false;
                }
            });

            searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean hasFocus) {
                    if(hasFocus) {
                        listView.setVisibility(View.VISIBLE);
                    } else {
                        listView.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }

        @Override
        public void onLocationChanged (Location location){
            currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
            if (!isRouting) {
                if (!locationInitted) {
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, DEFAULT_ZOOM_LEVEL));
                    mapFragment.getView().setVisibility(View.VISIBLE);
                    locationInitted = true;
                }
            } else {
                if (location.distanceTo(destination) <= CLOSE_ENOUGH_DISTANCE) {
                    //stop routing and display menu
                    isRouting = false;
                    cancelRoute();
                    if (!popupDisplayed) {

                        double lat = destination.getLatitude();
                        double lon = destination.getLongitude();
                        DBHandler db = DBHandler.getInstance(this);
                        ArrayList<Object> list = db.getRestaurants();
                        Restaurant r = null;
                        for(int i = 0; i < list.size(); i++){
                            r = (Restaurant)list.get(i);
                            if(r.getLocationLat() == lat && r.getLocationLng() == lon){
                                break;
                            }
                        }
                        Intent i = new Intent(this,Pop.class);
                        i.putExtra("name", r.getName());
                        startActivity(i);
                        popupDisplayed = true;
                    }

                } else {
                    popupDisplayed = false;
                    route();
                }
            }
        }

    @Override
    public void onCameraIdle() {
        Marker[] markers = markersByRestaurant.values().toArray(new Marker[0]);
        for(int i = 0; i < markersByRestaurant.size(); i++) {
            markers[i].remove();
        }
        markersByRestaurant.clear();
        for (int i = 0; i < restaurants.size(); i++) {
            if(/*restaurant is in current map view*/ true) {
                Restaurant r = (Restaurant)restaurants.get(i);
                LatLng ll = new LatLng(r.getLocationLat(), r.getLocationLng());
                String name = r.getName().toLowerCase();
                String address = "";
//                String address = r.getAddress().toLowercase();
                Marker m = mMap.addMarker(new MarkerOptions().position(ll).title(r.getName()));
                markersByRestaurant.put(name + ", " + address, m);
            }
        }
        adapter.clear();
        List<String> list = new ArrayList<>(markersByRestaurant.keySet());
        adapter.addAll(list);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {}
    public void onProviderDisabled(String s) {}
    @Override
    public void onProviderEnabled(String s) {}

    @Override
    public boolean onMarkerClick ( final Marker marker) {
        //if we're routing somewhere, markers are not clickable.
        if (isRouting) return true;

        selectedMarker = marker;
        destination.setLatitude(marker.getPosition().latitude);
        destination.setLongitude(marker.getPosition().longitude);
        RoutingSheetFragment routingSheetFragment = new RoutingSheetFragment();
        routingSheetFragment.show(getSupportFragmentManager(), "Dialog");
        return true;
    }

    public void route () {
        if (isRouting) {
            cancelRoute();
        }
        isRouting = true;

        //get DirectionsResult object
        DateTime now = new DateTime();
        DirectionsResult result;
        try {
            GeoApiHelper geoApiHelper = new GeoApiHelper(this);
            result = geoApiHelper.directionsApiRequest(currentLocation, selectedMarker, travelMode, now);

            //use result to generate route and show ETA
            List<LatLng> decodedPath = PolyUtil.decode(result.routes[0].overviewPolyline.getEncodedPath());
            routePolyline = mMap.addPolyline(new PolylineOptions().addAll(decodedPath));

            etaString = result.routes[0].legs[0].duration.humanReadable;
            TextView etaText = findViewById(R.id.etaText);
            etaText.setText("ETA: " + etaString);

            //display ETA and cancel button
            showRoutingDisplay();
        } catch (Exception e) {
            //IOException or APIException
            Log.d("DirectionsApiException", e.getMessage());
        }
    }

    public void showRoutingDisplay() {
        View etaView = findViewById(R.id.etaView);
        TextView textView = findViewById(R.id.etaMarkerName);
        textView.setText(getSelectedMarkerTitle());
        etaView.setVisibility(View.VISIBLE);
    }

    public void cancelRoute() {
        if (routePolyline != null) routePolyline.remove();
        View etaView = findViewById(R.id.etaView);
        etaView.setVisibility(View.INVISIBLE);
    }

    public void cancelButtonClicked(View view) {
        cancelRoute();
        isRouting = false;
    }

    public void addAndSelectMarker(MarkerOptions markerOptions) {
            setSelectedMarker(mMap.addMarker(markerOptions));
    }
}
