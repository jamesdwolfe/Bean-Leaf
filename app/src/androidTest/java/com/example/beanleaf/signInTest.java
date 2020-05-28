package com.example.beanleaf;

import android.app.Instrumentation;
import android.view.View;
import android.widget.TextView;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

public class signInTest {

    @Rule
    public ActivityTestRule<signIn> activityRule
            = new ActivityTestRule<>(signIn.class);

    String toastStringName = "Invalid Information, please try again!";


    //    @Test
//    public void onCreate() {
//    }

    @Test
    public void noInput() {
        onView(withId(R.id.button4))
                .perform(click());

        onView(withText(toastStringName)).inRoot(withDecorView(not(activityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    @Test
    public void noPassword(){
        final DBHandler db = DBHandler.getInstance(InstrumentationRegistry.getInstrumentation().getTargetContext());
        db.sweepDatabases();
        ArrayList<Order> r = new ArrayList<Order>();

        db.insertCustomer("c1", "Male", "SampleEmail", "1111", "8888888888",0,0,0, new ArrayList<Order>());
        db.insertMerchant("m1", "Female", "SampleEmail2", "2222", "9999999999", 0,0, 0, r, null);
        assertEquals(db.checkIfUserExists(true, "SampleEmail"), true);
        assertEquals(db.checkIfUserExists(false, "SampleEmail2"), true);

        onView(withId(R.id.radioButton2)).perform(click());
        onView(withId(R.id.textView3)).perform(setTextInTextView("c1"));

        onView(withId(R.id.button4))
                .perform(click());

        onView(withText(toastStringName)).inRoot(withDecorView(not(activityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));

    }

    @Test
    public void noUsername(){
        final DBHandler db = DBHandler.getInstance(InstrumentationRegistry.getInstrumentation().getTargetContext());
        db.sweepDatabases();
        ArrayList<Order> r = new ArrayList<Order>();

        db.insertCustomer("c1", "Male", "SampleEmail", "1111", "8888888888",0,0,0, new ArrayList<Order>());
        db.insertMerchant("m1", "Female", "SampleEmail2", "2222", "9999999999", 0,0, 0, r, null);
        assertEquals(db.checkIfUserExists(true, "SampleEmail"), true);
        assertEquals(db.checkIfUserExists(false, "SampleEmail2"), true);

        onView(withId(R.id.radioButton)).perform(click());
        onView(withId(R.id.textView5)).perform(setTextInTextView("2222"));

        onView(withId(R.id.button4))
                .perform(click());

        onView(withText(toastStringName)).inRoot(withDecorView(not(activityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    @Test
    public void customerLogin(){
        final DBHandler db = DBHandler.getInstance(InstrumentationRegistry.getInstrumentation().getTargetContext());
        db.sweepDatabases();
        ArrayList<Order> r = new ArrayList<Order>();
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(MapsActivity.class.getName(), null, false);

        db.insertCustomer("c1", "Male", "SampleEmail", "1111", "8888888888",0,0,0, new ArrayList<Order>());
        db.insertMerchant("m1", "Female", "SampleEmail2", "2222", "9999999999", 0,0, 0, r, null);
        assertEquals(db.checkIfUserExists(true, "SampleEmail"), true);
        assertEquals(db.checkIfUserExists(false, "SampleEmail2"), true);

        onView(withId(R.id.radioButton2)).perform(click());
        onView(withId(R.id.emailField)).perform(setTextInTextView("c1"));
        onView(withId(R.id.PasswordField)).perform(setTextInTextView("1111"));

        onView(withId(R.id.button4))
                .perform(click());

        MapsActivity nextActivity = (MapsActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);

        assertNotNull(nextActivity);
        nextActivity.finish();
        db.sweepDatabases();
    }

    @Test
    public void merchantLogin(){
        final DBHandler db = DBHandler.getInstance(InstrumentationRegistry.getInstrumentation().getTargetContext());
        db.sweepDatabases();
        ArrayList<Order> r = new ArrayList<Order>();
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(MapsActivity.class.getName(), null, false);

        db.insertCustomer("c1", "Male", "SampleEmail", "1111", "8888888888",0,0,0, new ArrayList<Order>());
        db.insertMerchant("m1", "Female", "SampleEmail2", "2222", "9999999999", 0,0, 0, r, null);
        assertEquals(db.checkIfUserExists(true, "SampleEmail"), true);
        assertEquals(db.checkIfUserExists(false, "SampleEmail2"), true);

        onView(withId(R.id.radioButton)).perform(click());
        onView(withId(R.id.emailField)).perform(setTextInTextView("m1"));
        onView(withId(R.id.PasswordField)).perform(setTextInTextView("2222"));

        onView(withId(R.id.button4))
                .perform(click());

        MapsActivity nextActivity = (MapsActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);

        assertNotNull(nextActivity);
        nextActivity.finish();
        db.sweepDatabases();
    }

    @Test
    public void adminLogin(){
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(administrator.class.getName(), null, false);

        onView(withId(R.id.emailField)).perform(setTextInTextView("Root1234"));
        onView(withId(R.id.PasswordField)).perform(setTextInTextView("Root1234"));

        onView(withId(R.id.button4))
                .perform(click());

        administrator nextActivity = (administrator) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);

        assertNotNull(nextActivity);
        nextActivity.finish();
    }


    public static ViewAction setTextInTextView(final String value){
        return new ViewAction() {
            @SuppressWarnings("unchecked")
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isDisplayed(), isAssignableFrom(TextView.class));
            }

            @Override
            public void perform(UiController uiController, View view) {

                ((TextView) view).setText(value);
            }

            @Override
            public String getDescription() {
                return "replace text";
            }
        };
    }
}