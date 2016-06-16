package com.designatednerd.wino.activity;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import com.designatednerd.wino.R;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.allOf;

/**
 * This test was recorded with Android Studio 2.2 Canary Preview 3.
 *
 * It...doesn't so much actually run, but it gives a good idea of what
 * kind of information you can record.
 *
 * Assuming this will be improved in later versions of 2.2. 
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class WineTastingActivityTest {

    @Rule
    public ActivityTestRule<WineTastingActivity> mActivityTestRule = new ActivityTestRule<>(WineTastingActivity.class);

    @Test
    public void wineTastingActivityTest() {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.add_tasting_button), isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction editText = onView(
                allOf(withId(R.id.tasting_detail_wine_name_edittext),
                        withParent(allOf(withText("Wine Name"),
                                withParent(withId(R.id.winetasting_detail_layout)))),
                        isDisplayed()));
        editText.check(matches(isDisplayed()));

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.tasting_detail_vineyard_name_edittext), isDisplayed()));
        appCompatEditText.perform(replaceText("wine"));

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.tasting_detail_wine_name_edittext), isDisplayed()));
        appCompatEditText2.perform(replaceText("it is s"));

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.tasting_detail_varietal_edittext), isDisplayed()));
        appCompatEditText3.perform(replaceText("merlot"));

        appCompatEditText3.perform(pressImeActionButton());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.tasting_detail_rating_spinner),
                        withParent(withId(R.id.winetasting_detail_layout)),
                        isDisplayed()));
        appCompatSpinner.perform(click());

        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.tasting_detail_rating_spinner),
                        withParent(withId(R.id.winetasting_detail_layout)),
                        isDisplayed()));
        appCompatSpinner2.perform(click());

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(android.R.id.text1), withText("Four"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.tasting_detail_save_button), withText("Save Tasting"),
                        withParent(withId(R.id.winetasting_detail_layout)),
                        isDisplayed()));
        appCompatButton.perform(click());

        pressBack();

        ViewInteraction textView = onView(
                allOf(withId(R.id.row_tasting_wine_name_textview), withText("it is s (merlot, wine)"), isDisplayed()));
        textView.check(matches(withText("it is s (merlot, wine)")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.row_tasting_date_textview), withText("6/15/16"),
                        withParent(allOf(withId(R.id.row_tasting),
                                withParent(withId(R.id.wine_tasting_recyclerview)))),
                        isDisplayed()));
        textView2.check(matches(withText("6/15/16")));

    }
}
