package com.example.beanleaf;

import android.app.Instrumentation;
import android.location.Location;
import android.view.View;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class MapsActivityTest {
    @Rule
    public ActivityTestRule<MapsActivity> rule  = new ActivityTestRule<>(MapsActivity.class);

    @Test
    public void test_onCreate() {
        MapsActivity mapsActivity = rule.getActivity();

        //check that map is visible, demonstrating successful onCreate.
        View mapView = mapsActivity.findViewById(R.id.map);
        assertEquals(View.VISIBLE, mapView.getVisibility());
        mapsActivity.finish();
    }

    @Test
    public void test_HomeMenu() {
        MapsActivity mapsActivity = rule.getActivity();
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(NavigationActivity.class.getName(), null, false);
        View homeButton = mapsActivity.findViewById(R.id.home_button);

        mapsActivity.HomeMenu(homeButton);

        //Since we aren't logged in for this test, NavigationActivity should launch.

        //check that the navigation activity is launched.
        NavigationActivity nextActivity = (NavigationActivity) InstrumentationRegistry.getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(nextActivity);
        mapsActivity.finish();
    }

    @Test
    public void test_onMapReady() {
        final MapsActivity mapsActivity = rule.getActivity();
        mapsActivity.runOnUiThread(new Runnable() {
            public void run() {
                //Create mock restaurants data.
                ArrayList<Object> restaurants = new ArrayList<>();
                LatLng r1Location = new LatLng(1.0, 10.0);
                Restaurant r1 = new Restaurant(1,
                        "Test Restaurant 1",
                        null,
                        null,
                        1,
                        null,
                        null,
                        r1Location.latitude,
                        r1Location.longitude);
                LatLng r2Location = new LatLng(2.0, 20.0);
                Restaurant r2 = new Restaurant(2,
                        "Test Restaurant 2",
                        null,
                        null,
                        1,
                        null,
                        null,
                        r2Location.latitude,
                        r2Location.longitude);
                restaurants.add(r1);
                restaurants.add(r2);
                mapsActivity.setRestaurants(restaurants);

                mapsActivity.onMapReady(mapsActivity.getMap());

                //Check that the markers are correctly set on the map.
                Map<String, Marker> markers = mapsActivity.getMarkersByRestaurant();
                assertEquals(2, markers.size());
                String r1MarkerName = "Test Restaurant 1".toLowerCase() + ", ";
                String r2MarkerName = "Test Restaurant 2".toLowerCase() + ", ";
                try {
                    assertEquals(r1Location, markers.get(r1MarkerName).getPosition());
                    assertEquals(r2.getName(), markers.get(r2MarkerName).getTitle());
                } catch (Exception e) {
                    //if markers.get fails, handle the exception by failing the test.
                    fail();
                }
            }
        });
        mapsActivity.finish();
    }

    @Test
    public void test_onLocationChanged() {
        MapsActivity mapsActivity = rule.getActivity();

        //create dummy test locations
        Location location1 = new Location("irrelevant test provider name");
        location1.setLatitude(10.0);
        location1.setLongitude(10.0);
        Location location2 = new Location("irrelevant test provider name");
        location2.setLatitude(2.0);
        location2.setLongitude(20.0);

        //simulate location change
        mapsActivity.onLocationChanged(location1);

        //check location coordinates
        LatLng returnLocation1 = mapsActivity.getCurrentLocation();
        assertEquals(location1.getLatitude(), returnLocation1.latitude, 0.01);
        assertEquals(location1.getLongitude(), returnLocation1.longitude, 0.01);

        //simulate location change
        mapsActivity.onLocationChanged(location2);

        //check location coordinates
        LatLng returnLocation2 = mapsActivity.getCurrentLocation();
        assertEquals(location2.getLatitude(), returnLocation2.latitude, 0.01);
        assertEquals(location2.getLongitude(), returnLocation2.longitude, 0.01);
    }

    @Test
    public void test_onCameraIdle() {
        final MapsActivity mapsActivity = rule.getActivity();
        mapsActivity.runOnUiThread(new Runnable() {
            public void run() {
                //Create mock restaurants data.
                ArrayList<Object> restaurants = new ArrayList<>();
                LatLng r1Location = new LatLng(1.0, 10.0);
                Restaurant r1 = new Restaurant(1,
                        "Test Restaurant 1",
                        null,
                        null,
                        1,
                        null,
                        null,
                        r1Location.latitude,
                        r1Location.longitude);
                LatLng r2Location = new LatLng(2.0, 20.0);
                Restaurant r2 = new Restaurant(2,
                        "Test Restaurant 2",
                        null,
                        null,
                        1,
                        null,
                        null,
                        r2Location.latitude,
                        r2Location.longitude);
                restaurants.add(r1);
                restaurants.add(r2);
                mapsActivity.setRestaurants(restaurants);

                mapsActivity.onCameraIdle();

                //Check that the markers are correctly set on the map.
                Map<String, Marker> markers = mapsActivity.getMarkersByRestaurant();
                assertEquals(2, markers.size());
                String r1MarkerName = "Test Restaurant 1".toLowerCase() + ", ";
                String r2MarkerName = "Test Restaurant 2".toLowerCase() + ", ";
                try {
                    assertEquals(r1Location, markers.get(r1MarkerName).getPosition());
                    assertEquals(r2.getName(), markers.get(r2MarkerName).getTitle());
                } catch (Exception e) {
                    //if markers.get fails, handle the exception by failing the test.
                    fail();
                }
            }
        });
        mapsActivity.finish();
    }

    @Test
    public void test_onStatusChanged() {
        //Unimplemented overridden method.
        //No test code is required.
    }

    @Test
    public void test_onProviderDisabled() {
        //Unimplemented overridden method.
        //No test code is required.
    }

    @Test
    public void test_onProviderEnabled() {
        //Unimplemented overridden method.
        //No test code is required.
    }

    @Test
    public void test_onMarkerClick() {
        final MapsActivity mapsActivity = rule.getActivity();
        mapsActivity.runOnUiThread(new Runnable() {
            public void run() {
                LatLng exampleMarker = new LatLng(37.3810, -122.1240);
                GoogleMap map = mapsActivity.getMap();

                mapsActivity.onMarkerClick(map.addMarker(new MarkerOptions().position(exampleMarker).title("On Marker Click Test")));

                //check correct marker is selected
                assertEquals("On Marker Click Test", mapsActivity.getSelectedMarkerTitle());
                //check destination latlng is correct
                assertEquals(37.3810, mapsActivity.getDestination().getLatitude(), 0.01);
                assertEquals(-122.1240, mapsActivity.getDestination().getLongitude(), 0.01);
            }
        });
        mapsActivity.finish();
    }

    @Test
    public void test_route() {
        final MapsActivity mapsActivity = rule.getActivity();
        mapsActivity.runOnUiThread(new Runnable() {
            public void run() {
                LatLng exampleMarker = new LatLng(37.3810, -122.1240);
                mapsActivity.addAndSelectMarker(new MarkerOptions().position(exampleMarker).title("Test Route Marker"));

                mapsActivity.route();

                //check isRouting boolean is true.
                assertTrue(mapsActivity.getIsRouting());
                //check routing view is visible.
                View etaView = mapsActivity.findViewById(R.id.etaView);
                assertEquals(View.VISIBLE, etaView.getVisibility());
                //validate that the selected marker matches our test marker.
                assertEquals("Test Route Marker", mapsActivity.getSelectedMarkerTitle());
            }
        });
        mapsActivity.finish();
    }

    @Test
    public void test_showRoutingDisplay() {
        final MapsActivity mapsActivity = rule.getActivity();
        mapsActivity.runOnUiThread(new Runnable() {
            public void run() {
                LatLng exampleMarker = new LatLng(37.3810, -122.1240);
                mapsActivity.addAndSelectMarker(new MarkerOptions().position(exampleMarker).title("Show Routing Display Test"));

                mapsActivity.showRoutingDisplay();

                //check that the etaView is visible.
                View etaView = mapsActivity.findViewById(R.id.etaView);
                assertEquals(View.VISIBLE, etaView.getVisibility());
                //validate that the selected marker matches our test marker.
                assertEquals("Show Routing Display Test", mapsActivity.getSelectedMarkerTitle());
            }
        });
        mapsActivity.finish();
    }

    @Test
    public void test_cancelRoute() {
        MapsActivity mapsActivity = rule.getActivity();

        mapsActivity.cancelRoute();

        //check that we stopped routing.
        assertFalse(mapsActivity.getIsRouting());
        //check that the routing etaView is no longer visible.
        View etaView = mapsActivity.findViewById(R.id.etaView);
        assertEquals(View.INVISIBLE, etaView.getVisibility());
        mapsActivity.finish();
    }

    @Test
    public void test_cancelButtonClicked() {
        MapsActivity mapsActivity = rule.getActivity();

        //simulate cancel button clicked
        onView(withId(R.id.cancel_button2));

        //check that we stopped routing.
        assertFalse(mapsActivity.getIsRouting());
        //check that the routing etaView is no longer visible.
        View etaView = mapsActivity.findViewById(R.id.etaView);
        assertEquals(View.INVISIBLE, etaView.getVisibility());
        mapsActivity.finish();
    }
}