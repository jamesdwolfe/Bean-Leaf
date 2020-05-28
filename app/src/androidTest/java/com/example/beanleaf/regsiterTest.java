package com.example.beanleaf;

import android.app.Instrumentation;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.PendingIntent.getActivity;
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

public class regsiterTest {

    @Rule
    public ActivityTestRule<regsiter> activityRule
            = new ActivityTestRule<>(regsiter.class);

    //Test toast messages.
    String toastStringName = "Please enter a name";
    String toastStringEmail = "Please enter an email";
    String toastStringPass = "Please enter a password";
    String toastStringPhone = "Please enter a phone number";


//    @Test
//    public void onCreate() {
//    }

    @Test
    public void noInput() {

        //When no info is typed.
        onView(withId(R.id.registerBtn))
                .perform(click());

        onView(withText(toastStringName)).inRoot(withDecorView(not(activityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));

        //When only name is input.


    }

    @Test
    public void onlyName(){
        onView(withId(R.id.nameField)).perform(setTextInTextView("Sample Name"));

        onView(withId(R.id.registerBtn))
                .perform(click());

        onView(withText(toastStringEmail)).inRoot(withDecorView(not(activityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    @Test
    public void onlyNameEmail(){
        onView(withId(R.id.nameField)).perform(setTextInTextView("Sample Name"));
        onView(withId(R.id.emailField)).perform(setTextInTextView("Sample Email"));

        onView(withId(R.id.registerBtn))
                .perform(click());

        onView(withText(toastStringPass)).inRoot(withDecorView(not(activityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    @Test
    public void onlyNoPhone(){
        onView(withId(R.id.nameField)).perform(setTextInTextView("Sample Name"));
        onView(withId(R.id.emailField)).perform(setTextInTextView("Sample Email"));
        onView(withId(R.id.passField)).perform(setTextInTextView("Sample Password"));

        onView(withId(R.id.registerBtn))
                .perform(click());

        onView(withText(toastStringPhone)).inRoot(withDecorView(not(activityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

//    @Test
//    public void ifClickMerchant(){
//        final DBHandler db = DBHandler.getInstance(InstrumentationRegistry.getInstrumentation().getTargetContext());
//        db.sweepDatabases();
//        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(MapsActivity.class.getName(), null, false);
//
//        onView(withId(R.id.nameField)).perform(setTextInTextView("Sample Name"));
//        onView(withId(R.id.emailField)).perform(setTextInTextView("Sample Email"));
//        onView(withId(R.id.passField)).perform(setTextInTextView("Sample Password"));
//        onView(withId(R.id.numberField)).perform(setTextInTextView("8888888888"));
//
//        onView(withId(R.id.radioBtn1)).perform(click());
//        onView(withId(R.id.maleBtn)).perform(click());
//
//        onView(withId(R.id.registerBtn))
//                .perform(click());
//
//        assertEquals(db.checkIfUserExists(false, "Sample Email"), true);
//
//
//        onView(withId(R.id.button1)).perform(click());
//        onView(withId(R.id.merchant_singupb)).perform(click());
//        onView(withId(R.id.button1)).perform(click());
//        onView(withId(R.id.button2)).perform(click());
//
////        Merchant lastMerchant = db.getMerchantByName("Sample Name");
////        while(lastMerchant != null){
////            db.deleteMerchant(lastMerchant.getID());
////            lastMerchant = db.getMerchantByName("Sample Name");
////        }
//
//
//        onView(withId(R.id.nameField)).perform(setTextInTextView("Sample Name2"));
//        onView(withId(R.id.emailField)).perform(setTextInTextView("Sample Email2"));
//        onView(withId(R.id.passField)).perform(setTextInTextView("Sample Password2"));
//        onView(withId(R.id.numberField)).perform(setTextInTextView("8888888888"));
//
//        onView(withId(R.id.radioBtn1)).perform(click());
//        onView(withId(R.id.femlateBtn)).perform(click());
//
//        onView(withId(R.id.registerBtn))
//                .perform(click());
//
//        assertEquals(db.checkIfUserExists(false, "Sample Email2"), true);
//        MapsActivity nextActivity = (MapsActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 100000);
//
//        assertNotNull(nextActivity);
//        nextActivity.finish();
//        db.sweepDatabases();
//    }
//
//    @Test
//    public void ifClickCustomer(){
//
//        final DBHandler db = DBHandler.getInstance(InstrumentationRegistry.getInstrumentation().getTargetContext());
//        db.sweepDatabases();
//        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(MapsActivity.class.getName(), null, false);
//
//        onView(withId(R.id.nameField)).perform(setTextInTextView("Sample Name3"));
//        onView(withId(R.id.emailField)).perform(setTextInTextView("Sample Email3"));
//        onView(withId(R.id.passField)).perform(setTextInTextView("Sample Password3"));
//        onView(withId(R.id.numberField)).perform(setTextInTextView("8888888888"));
//
//        onView(withId(R.id.radioBtn2)).perform(click());
//        onView(withId(R.id.maleBtn)).perform(click());
//
//        onView(withId(R.id.registerBtn))
//                .perform(click());
//
//        assertEquals(db.checkIfUserExists(true, "Sample Email3"), true);
//
//        onView(withId(R.id.button1)).perform(click());
//        onView(withId(R.id.button7)).perform(click());
//        onView(withId(R.id.button1)).perform(click());
//        onView(withId(R.id.button2)).perform(click());
//
////        lastCustomer = db.getCustomerByName("Sample Name2");
////        db.deleteMerchant(lastCustomer.getID());
//
//        onView(withId(R.id.nameField)).perform(setTextInTextView("Sample Name4"));
//        onView(withId(R.id.emailField)).perform(setTextInTextView("Sample Email4"));
//        onView(withId(R.id.passField)).perform(setTextInTextView("Sample Password4"));
//        onView(withId(R.id.numberField)).perform(setTextInTextView("8888888888"));
//
//        onView(withId(R.id.radioBtn2)).perform(click());
//        onView(withId(R.id.femlateBtn)).perform(click());
//
//        onView(withId(R.id.registerBtn))
//                .perform(click());
//
//        assertEquals(db.checkIfUserExists(false, "Sample Email4"), true);
//        MapsActivity nextActivity = (MapsActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 100000);
//
//        assertNotNull(nextActivity);
//        nextActivity.finish();
//        db.sweepDatabases();
//    }


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