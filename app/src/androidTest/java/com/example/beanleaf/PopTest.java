package com.example.beanleaf;

import android.app.Instrumentation;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class PopTest {

    @Rule
    public ActivityTestRule<Pop> activityRule
            = new ActivityTestRule<>(Pop.class);
    @Test
    public void onCreate() {
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(viewMenu2.class.getName(), null, false);

        onView(withId(R.id.button))
                .perform(click());

        viewMenu2 nextActivity = (viewMenu2) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);

        assertNotNull(nextActivity);
        nextActivity.finish();
    }
}