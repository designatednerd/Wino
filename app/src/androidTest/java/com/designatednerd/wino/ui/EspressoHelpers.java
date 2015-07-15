package com.designatednerd.wino.ui;

import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
//import static android.support.test.espresso.contrib.RecyclerViewActions.*;
//
//import static android.support.test.espresso.Espresso.onView;
//import static android.support.test.espresso.action.ViewActions.*;
//import static android.support.test.espresso.assertion.ViewAssertions.matches;
//import static android.support.test.espresso.contrib.DrawerActions.openDrawer;
//import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
//import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.CoreMatchers.allOf;

public class EspressoHelpers {

    public static void enterTextIntoViewWithID(String aTextToEnter, @IdRes int aViewID) {
        onView(withId(aViewID)).perform(typeText(aTextToEnter));
    }

    public static void scrollToViewWithID(@IdRes int aViewIDRes) {
        onView(withId(aViewIDRes)).perform(scrollTo());
    }

    public static void tapViewWithText(String aText) {
        onView(withText(aText)).perform(click());
    }

    public static void tapViewWithText(@StringRes int aTextResID) {
        onView(withText(aTextResID)).perform(click());
    }

    public static void tapViewWithID(@IdRes int aViewResID) {
        onView(withId(aViewResID)).perform(click());
    }

    //TODO: FIX

//    public static Matcher<View> withRecyclerView(@IdRes int viewId) {
//        return allOf(isAssignableFrom(RecyclerView.class), withId(viewId));
//    }
//
//
//    //Modified from https://gist.github.com/tommyd3mdi/2622caecc1b2d498cd1a
//    public static ViewInteraction checkRecyclerViewHasItemWithTitle(@IdRes int aParentRecyclerViewID,
//                                                                    @IdRes int aRecyclerRowLayoutID,
//                                                     @IdRes int aRecyclerViewTextViewID,
//                                                    String title) {
//
//        onView(allOf(withParent(withRecyclerView(aParentRecyclerViewID)), //The view has a parent which is the recyclerview
//                withChild(allOf(withId(aRecyclerRowLayoutID),
//                        withChild())));
//
//        Matcher<View> matcher = withChild(allOf(withId(identifyingView), identifyingMatcher)))
//        withRecyclerView(aParentRecyclerViewID)
//        Matcher<View> on =
//
//                allOf(withParent(withId(aRecyclerRowID)), //Parent of the view is the full recyclerview
//                withId(aRecyclerViewTextViewID), //Has the given id and
//                withChild(allOf(withId(aRecyclerViewTextViewID), aChildIdentifyingMatcher))); //which has a child with the text view ID and matching the matcher.
//
//        return onView(outerSubview);
//    }
}
