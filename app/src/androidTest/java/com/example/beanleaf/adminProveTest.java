package com.example.beanleaf;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class adminProveTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.ACCESS_FINE_LOCATION",
                    "android.permission.READ_EXTERNAL_STORAGE");

    @Test
    public void adminProveTest() {
        final DBHandler db = DBHandler.getInstance(InstrumentationRegistry.getInstrumentation().getTargetContext());
        db.sweepDatabases();
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
        appCompatEditText.perform(replaceText("m2"), closeSoftKeyboard());

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
        appCompatEditText2.perform(replaceText("e2"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.emailField), withText("e2"),
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
        appCompatEditText4.perform(replaceText("2222"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.passField), withText("2222"),
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
        appCompatEditText6.perform(replaceText("2"), closeSoftKeyboard());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.numberField), withText("2"),
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
        appCompatEditText8.perform(replaceText("r2"), closeSoftKeyboard());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.addressFiled),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                9),
                        isDisplayed()));
        appCompatEditText9.perform(replaceText("a2"), closeSoftKeyboard());

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

//        ViewInteraction appCompatButton4 = onView(
//                allOf(withId(R.id.uploadBtn), withText("Upload"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                7),
//                        isDisplayed()));
//        appCompatButton4.perform(click());

        // ViewInteraction appCompatButton5 = onView(
        //         allOf(withId(R.id.button8), withText("Submit Request"),
        //                 childAtPosition(
        //                         childAtPosition(
        //                                 withId(android.R.id.content),
        //                                 0),
        //                         12),
        //                 isDisplayed()));
        // appCompatButton5.perform(click());
        onView(withId(R.id.button8)).perform(click());

//        ViewInteraction appCompatButton6 = onView(
//                allOf(withId(R.id.merchant_singupb), withText("Sign Out"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                4),
//                        isDisplayed()));
//        appCompatButton6.perform(click());
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

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.button3), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton7.perform(click());

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

        // ViewInteraction appCompatButton8 = onView(
        //         allOf(withId(R.id.button4), withText("Sign in"),
        //                 childAtPosition(
        //                         childAtPosition(
        //                                 withId(android.R.id.content),
        //                                 0),
        //                         4),
        //                 isDisplayed()));
        // appCompatButton8.perform(click());
        onView(withId(R.id.button4)).perform(click());

        onView(withId(R.id.button6)).perform(click());

        ViewInteraction textView = onView(
                allOf(withText("r2"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.table),
                                        1),
                                0),
                        isDisplayed()));
        textView.perform(click());

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(R.id.acceptBtn), withText("Accept"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        appCompatButton9.perform(click());
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
}
