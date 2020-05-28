package com.example.beanleaf;

import android.app.Instrumentation;

import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class NavigationActivityTest {

    @Rule
    public ActivityTestRule<NavigationActivity> activityRule
            = new ActivityTestRule<>(NavigationActivity.class);

//    @Test
//    public void onCreate() {
//    }
//
//    @Test
//    public void onCreateOptionsMenu() {
//    }
//
//    @Test
//    public void onSupportNavigateUp() {
//    }

    @Test
    public void signUpClicked() {
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(regsiter.class.getName(), null, false);

        onView(withId(R.id.button2))
                .perform(click());

        regsiter nextActivity = (regsiter)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);

        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void signInClicked() {
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(signIn.class.getName(), null, false);

        onView(withId(R.id.button3))
                .perform(click());

        signIn nextActivity = (signIn)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);

        assertNotNull(nextActivity);
        nextActivity.finish();
    }

//    @Test
//    public void adminLoginClicked() {
//        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(addRequests.class.getName(), null, false);
//
//
//
//    }
}