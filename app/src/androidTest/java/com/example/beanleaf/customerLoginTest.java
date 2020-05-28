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
public class customerLoginTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.ACCESS_FINE_LOCATION");

    @Test
    public void customerLoginTest() {
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
        //         allOf(withId(R.id.radioBtn2), withText("Customer"),
        //                 childAtPosition(
        //                         allOf(withId(R.id.radioGroup),
        //                                 childAtPosition(
        //                                         withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
        //                                         10)),
        //                         1),
        //                 isDisplayed()));
        // appCompatRadioButton.perform(click());
        onView(withId(R.id.radioBtn2)).perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.nameField),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("c1"), closeSoftKeyboard());

        ViewInteraction appCompatRadioButton2 = onView(
                allOf(withId(R.id.femlateBtn), withText("Female"),
                        childAtPosition(
                                allOf(withId(R.id.radioGroup3),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                12)),
                                1),
                        isDisplayed()));
        appCompatRadioButton2.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.emailField),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("ce1"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.emailField), withText("ce1"),
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
//                allOf(withId(R.id.button7), withText("Sign out"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                3),
//                        isDisplayed()));
//        appCompatButton3.perform(click());

        onView(withId(R.id.button7)).perform(click());

        ViewInteraction button3 = onView(
                allOf(withId(R.id.home_button), withText("Home"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        0),
                                1),
                        isDisplayed()));
        button3.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.button3), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton4.perform(click());

        // ViewInteraction appCompatRadioButton3 = onView(
        //         allOf(withId(R.id.radioButton2), withText("Customer"),
        //                 childAtPosition(
        //                         allOf(withId(R.id.radioGroup2),
        //                                 childAtPosition(
        //                                         withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
        //                                         0)),
        //                         1),
        //                 isDisplayed()));
        // appCompatRadioButton3.perform(click());
        onView(withId(R.id.radioButton2)).perform(click());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.emailField),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText8.perform(replaceText("c1"), closeSoftKeyboard());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.PasswordField),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText9.perform(replaceText("1111"), closeSoftKeyboard());

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.PasswordField), withText("1111"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText10.perform(pressImeActionButton());

        // ViewInteraction appCompatButton5 = onView(
        //         allOf(withId(R.id.button4), withText("Sign in"),
        //                 childAtPosition(
        //                         childAtPosition(
        //                                 withId(android.R.id.content),
        //                                 0),
        //                         4),
        //                 isDisplayed()));
        // appCompatButton5.perform(click());
        onView(withId(R.id.button4)).perform(click());
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
