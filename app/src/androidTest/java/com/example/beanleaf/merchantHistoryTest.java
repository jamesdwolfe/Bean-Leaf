package com.example.beanleaf;


import android.app.Instrumentation;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiSelector;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class merchantHistoryTest {

    @Rule
    public ActivityTestRule<MapsActivity> mActivityTestRule = new ActivityTestRule<>(MapsActivity.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.ACCESS_FINE_LOCATION");

    @Test
    public void merchantHistoryTest() {
        final DBHandler db = DBHandler.getInstance(InstrumentationRegistry.getInstrumentation().getTargetContext());
        db.sweepDatabases();
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(viewMenu2.class.getName(), null, false);


        ViewInteraction button = onView(
                allOf(withId(R.id.home_button), withText("Home"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        0),
                                1),
                        isDisplayed()));
        button.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.button2), withText("Sign Up"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton.perform(click());

        // ViewInteraction appCompatRadioButton = onView(
        //         allOf(withId(R.id.radioBtn1), withText("Merchant"),
        //                 childAtPosition(
        //                         allOf(withId(R.id.radioGroup),
        //                                 childAtPosition(
        //                                         withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
        //                                         10)),
        //                         0),
        //                 isDisplayed()));
        // appCompatRadioButton.perform(click());
        onView(withId(R.id.radioBtn1)).perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.nameField),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("m1"), closeSoftKeyboard());

        // ViewInteraction appCompatRadioButton2 = onView(
        //         allOf(withId(R.id.maleBtn), withText("Male"),
        //                 childAtPosition(
        //                         allOf(withId(R.id.radioGroup3),
        //                                 childAtPosition(
        //                                         withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
        //                                         12)),
        //                         0),
        //                 isDisplayed()));
        // appCompatRadioButton2.perform(click());
        onView(withId(R.id.maleBtn)).perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.emailField),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("em1"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.emailField), withText("em1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        appCompatEditText3.perform(pressImeActionButton());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.passField),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                9),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("1111"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.passField), withText("1111"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                9),
                        isDisplayed()));
        appCompatEditText5.perform(pressImeActionButton());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.numberField),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("1"), closeSoftKeyboard());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.numberField), withText("1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        appCompatEditText7.perform(pressImeActionButton());

        // ViewInteraction appCompatButton2 = onView(
        //         allOf(withId(R.id.registerBtn), withText("Regsiter"),
        //                 childAtPosition(
        //                         childAtPosition(
        //                                 withId(android.R.id.content),
        //                                 0),
        //                         5),
        //                 isDisplayed()));
        // appCompatButton2.perform(click());
        onView(withId(R.id.registerBtn)).perform(click());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.home_button), withText("Home"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        0),
                                1),
                        isDisplayed()));
        button2.perform(click());

//        ViewInteraction appCompatButton3 = onView(
//                allOf(withId(R.id.button12), withText("Add A Restaurant"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                3),
//                        isDisplayed()));
//        appCompatButton3.perform(click());

        onView(withId(R.id.button12)).perform(click());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.nameField),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        appCompatEditText8.perform(replaceText("r1"), closeSoftKeyboard());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.addressFiled),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                9),
                        isDisplayed()));
        appCompatEditText9.perform(replaceText("1600 Amphitheatre pkwy"), closeSoftKeyboard());

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.zipField),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                13),
                        isDisplayed()));
        appCompatEditText10.perform(replaceText("94043"), closeSoftKeyboard());

        ViewInteraction appCompatEditText11 = onView(
                allOf(withId(R.id.stateField),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                10),
                        isDisplayed()));
        appCompatEditText11.perform(replaceText("CA"), closeSoftKeyboard());

        ViewInteraction appCompatEditText12 = onView(
                allOf(withId(R.id.stateField), withText("CA"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                10),
                        isDisplayed()));
        appCompatEditText12.perform(pressImeActionButton());

        // ViewInteraction appCompatButton4 = onView(
        //         allOf(withId(R.id.button8), withText("Submit Request"),
        //                 childAtPosition(
        //                         childAtPosition(
        //                                 withId(android.R.id.content),
        //                                 0),
        //                         12),
        //                 isDisplayed()));
        // appCompatButton4.perform(click());
        onView(withId(R.id.button8)).perform(click());

//        ViewInteraction appCompatButton5 = onView(
//                allOf(withId(R.id.merchant_singupb), withText("Sign Out"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                4),
//                        isDisplayed()));
//        appCompatButton5.perform(click());

        onView(withId(R.id.merchant_singupb)).perform(click());

        ViewInteraction button3 = onView(
                allOf(withId(R.id.home_button), withText("Home"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        0),
                                1),
                        isDisplayed()));
        button3.perform(click());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.button3), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction appCompatEditText13 = onView(
                allOf(withId(R.id.emailField),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText13.perform(replaceText("Root1234"), closeSoftKeyboard());

        ViewInteraction appCompatEditText14 = onView(
                allOf(withId(R.id.PasswordField),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText14.perform(replaceText("Root1234"), closeSoftKeyboard());

        ViewInteraction appCompatEditText15 = onView(
                allOf(withId(R.id.PasswordField), withText("Root1234"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText15.perform(pressImeActionButton());

        // ViewInteraction appCompatButton7 = onView(
        //         allOf(withId(R.id.button4), withText("Sign in"),
        //                 childAtPosition(
        //                         childAtPosition(
        //                                 withId(android.R.id.content),
        //                                 0),
        //                         4),
        //                 isDisplayed()));
        // appCompatButton7.perform(click());
        onView(withId(R.id.button4)).perform(click());

        onView(withId(R.id.button6)).perform(click());

        ViewInteraction textView = onView(
                allOf(withText("r1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.table),
                                        1),
                                0),
                        isDisplayed()));
        textView.perform(click());

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.acceptBtn), withText("Accept"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        appCompatButton8.perform(click());

        pressBack();

        pressBack();

        pressBack();

        pressBack();

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(R.id.button3), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton9.perform(click());

        // ViewInteraction appCompatRadioButton3 = onView(
        //         allOf(withId(R.id.radioButton), withText("Merchant"),
        //                 childAtPosition(
        //                         allOf(withId(R.id.radioGroup2),
        //                                 childAtPosition(
        //                                         withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
        //                                         0)),
        //                         0),
        //                 isDisplayed()));
        // appCompatRadioButton3.perform(click());
        onView(withId(R.id.radioButton)).perform(click());

        ViewInteraction appCompatEditText16 = onView(
                allOf(withId(R.id.emailField),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText16.perform(replaceText("m1"), closeSoftKeyboard());

        ViewInteraction appCompatEditText17 = onView(
                allOf(withId(R.id.PasswordField),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText17.perform(replaceText("1111"), closeSoftKeyboard());

        ViewInteraction appCompatEditText18 = onView(
                allOf(withId(R.id.PasswordField), withText("1111"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText18.perform(pressImeActionButton());

        // ViewInteraction appCompatButton10 = onView(
        //         allOf(withId(R.id.button4), withText("Sign in"),
        //                 childAtPosition(
        //                         childAtPosition(
        //                                 withId(android.R.id.content),
        //                                 0),
        //                         4),
        //                 isDisplayed()));
        // appCompatButton10.perform(click());
        onView(withId(R.id.button4)).perform(click());

        ViewInteraction button4 = onView(
                allOf(withId(R.id.home_button), withText("Home"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        0),
                                1),
                        isDisplayed()));
        button4.perform(click());

//        ViewInteraction appCompatButton11 = onView(
////                allOf(withId(R.id.button15), withText("View restaurant"),
////                        childAtPosition(
////                                childAtPosition(
////                                        withId(android.R.id.content),
////                                        0),
////                                6),
////                        isDisplayed()));
////        appCompatButton11.perform(click());

        onView(withId(R.id.button15)).perform(click());

        ViewInteraction textView2 = onView(
                allOf(withText("r1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.table),
                                        1),
                                0),
                        isDisplayed()));
        textView2.perform(click());

        ViewInteraction appCompatButton12 = onView(
                allOf(withId(R.id.button17), withText("Add Item"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatButton12.perform(click());

        ViewInteraction appCompatEditText19 = onView(
                allOf(withId(R.id.nameField),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText19.perform(replaceText("Water"), closeSoftKeyboard());

        ViewInteraction appCompatEditText20 = onView(
                allOf(withId(R.id.priceField),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatEditText20.perform(replaceText("2"), closeSoftKeyboard());

        ViewInteraction appCompatEditText21 = onView(
                allOf(withId(R.id.caffeineField),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText21.perform(replaceText("1"), closeSoftKeyboard());

        // ViewInteraction appCompatButton13 = onView(
        //         allOf(withId(R.id.button16), withText("Add the item"),
        //                 childAtPosition(
        //                         childAtPosition(
        //                                 withId(android.R.id.content),
        //                                 0),
        //                         7),
        //                 isDisplayed()));
        // appCompatButton13.perform(click());
        onView(withId(R.id.button16)).perform(click());

        pressBack();

        pressBack();

        pressBack();

        pressBack();

//        ViewInteraction appCompatButton14 = onView(
//                allOf(withId(R.id.merchant_singupb), withText("Sign Out"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                4),
//                        isDisplayed()));
//        appCompatButton14.perform(click());

        onView(withId(R.id.merchant_singupb)).perform(click());

        ViewInteraction button5 = onView(
                allOf(withId(R.id.home_button), withText("Home"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        0),
                                1),
                        isDisplayed()));
        button5.perform(click());

        ViewInteraction appCompatButton15 = onView(
                allOf(withId(R.id.button2), withText("Sign Up"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton15.perform(click());

        // ViewInteraction appCompatRadioButton4 = onView(
        //         allOf(withId(R.id.radioBtn2), withText("Customer"),
        //                 childAtPosition(
        //                         allOf(withId(R.id.radioGroup),
        //                                 childAtPosition(
        //                                         withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
        //                                         10)),
        //                         1),
        //                 isDisplayed()));
        // appCompatRadioButton4.perform(click());
        onView(withId(R.id.radioBtn2)).perform(click());

        ViewInteraction appCompatEditText22 = onView(
                allOf(withId(R.id.nameField),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText22.perform(replaceText("c1"), closeSoftKeyboard());

        // ViewInteraction appCompatRadioButton5 = onView(
        //         allOf(withId(R.id.maleBtn), withText("Male"),
        //                 childAtPosition(
        //                         allOf(withId(R.id.radioGroup3),
        //                                 childAtPosition(
        //                                         withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
        //                                         12)),
        //                         0),
        //                 isDisplayed()));
        // appCompatRadioButton5.perform(click());
        onView(withId(R.id.maleBtn)).perform(click());

        ViewInteraction appCompatEditText23 = onView(
                allOf(withId(R.id.emailField),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        appCompatEditText23.perform(replaceText("ec1"), closeSoftKeyboard());

        ViewInteraction appCompatEditText24 = onView(
                allOf(withId(R.id.emailField), withText("ec1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        appCompatEditText24.perform(pressImeActionButton());

        ViewInteraction appCompatEditText25 = onView(
                allOf(withId(R.id.passField),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                9),
                        isDisplayed()));
        appCompatEditText25.perform(replaceText("1111"), closeSoftKeyboard());

        ViewInteraction appCompatEditText26 = onView(
                allOf(withId(R.id.passField), withText("1111"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                9),
                        isDisplayed()));
        appCompatEditText26.perform(pressImeActionButton());

        ViewInteraction appCompatEditText27 = onView(
                allOf(withId(R.id.numberField),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        appCompatEditText27.perform(replaceText("1"), closeSoftKeyboard());

        ViewInteraction appCompatEditText28 = onView(
                allOf(withId(R.id.numberField), withText("1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        appCompatEditText28.perform(pressImeActionButton());

        // ViewInteraction appCompatButton16 = onView(
        //         allOf(withId(R.id.registerBtn), withText("Regsiter"),
        //                 childAtPosition(
        //                         childAtPosition(
        //                                 withId(android.R.id.content),
        //                                 0),
        //                         5),
        //                 isDisplayed()));
        // appCompatButton16.perform(click());
        onView(withId(R.id.registerBtn)).perform(click());

        try{
            UiDevice device = UiDevice.getInstance(getInstrumentation());
            UiObject marker = device.findObject(new UiSelector().descriptionContains("r1"));
            marker.click();
            marker.click();
            System.out.println("Marker clicked");
        }catch(Exception e){
            e.printStackTrace();
        }

        //waitFor(5000);
        onView(withId(R.id.drive_button)).perform(click());

        onView(withId(R.id.button)).perform(click());

        viewMenu2 nextActivity = (viewMenu2) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);

        int i = 0;
        while(nextActivity == null){
            System.out.println("Waiting");
            i = i +1;
            if(i == 2){
                onView(withId(R.id.button)).perform(click());
                break;
            }
        }
        //waitFor(5000);
        ViewInteraction textViewMenu = onView(
                allOf(withText("Water"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.table),
                                        1),
                                0),
                        isDisplayed()));
        textViewMenu.perform(click());

        onView(withId(R.id.button23)).perform(click());

        pressBack();
        pressBack();
        pressBack();

        ViewInteraction homebtn = onView(
                allOf(withId(R.id.home_button), withText("Home"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        0),
                                1),
                        isDisplayed()));
        homebtn.perform(click());

        onView(withId(R.id.button7)).perform(click());

        ViewInteraction homebtn2 = onView(
                allOf(withId(R.id.home_button), withText("Home"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        0),
                                1),
                        isDisplayed()));
        homebtn2.perform(click());

        ViewInteraction appCompatButton90 = onView(
                allOf(withId(R.id.button3), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton90.perform(click());

        // ViewInteraction appCompatRadioButton30 = onView(
        //         allOf(withId(R.id.radioButton), withText("Merchant"),
        //                 childAtPosition(
        //                         allOf(withId(R.id.radioGroup2),
        //                                 childAtPosition(
        //                                         withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
        //                                         0)),
        //                         0),
        //                 isDisplayed()));
        // appCompatRadioButton3.perform(click());
        onView(withId(R.id.radioButton)).perform(click());

        ViewInteraction appCompatEditText160 = onView(
                allOf(withId(R.id.emailField),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText160.perform(replaceText("m1"), closeSoftKeyboard());

        ViewInteraction appCompatEditText170 = onView(
                allOf(withId(R.id.PasswordField),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText170.perform(replaceText("1111"), closeSoftKeyboard());

        ViewInteraction appCompatEditText180 = onView(
                allOf(withId(R.id.PasswordField), withText("1111"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText180.perform(pressImeActionButton());

        // ViewInteraction appCompatButton100 = onView(
        //         allOf(withId(R.id.button4), withText("Sign in"),
        //                 childAtPosition(
        //                         childAtPosition(
        //                                 withId(android.R.id.content),
        //                                 0),
        //                         4),
        //                 isDisplayed()));
        // appCompatButton100.perform(click());
        onView(withId(R.id.button4)).perform(click());

        ViewInteraction button40 = onView(
                allOf(withId(R.id.home_button), withText("Home"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        0),
                                1),
                        isDisplayed()));
        button4.perform(click());

//        ViewInteraction appCompatButton110 = onView(
//                allOf(withId(R.id.button15), withText("View restaurant"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                6),
//                        isDisplayed()));
//        appCompatButton11.perform(click());
        onView(withId(R.id.button15)).perform(click());

        ViewInteraction textView20 = onView(
                allOf(withText("r1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.table),
                                        1),
                                0),
                        isDisplayed()));
        textView2.perform(click());

        //onView(withId(R.id.button20)).perform(click());

        ViewInteraction appCompatButton140 = onView(
                allOf(withId(R.id.button20), withText("View Orders"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatButton140.perform(click());

        db.sweepDatabases();
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    public static ViewAction waitFor(final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "Wait for " + millis + " milliseconds.";
            }

            @Override
            public void perform(UiController uiController, final View view) {
                uiController.loopMainThreadForAtLeast(millis);
            }
        };
    }
}
