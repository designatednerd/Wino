package com.designatednerd.wino.ui;

import android.content.Context;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;

import com.designatednerd.wino.R;
import com.designatednerd.wino.SharedPreferencesHelper;
import com.designatednerd.wino.activity.WineTastingActivity;
import com.designatednerd.wino.model.WineTasting;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.designatednerd.wino.ui.EspressoHelpers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class WineTastingDetailUITests {

    @Rule
    public final ActivityTestRule<WineTastingActivity> wineTastingActivity =
            new IntentsTestRule<>(WineTastingActivity.class);

    /******************
     * TEST LIFECYCLE *
     ******************/

    @Before
    public void beforeEachTest() {
        SharedPreferencesHelper
                .getInstance(getContext())
                .nukeAllSharedPreferences();
    }

    @After
    public void afterEachTest() {
        SharedPreferencesHelper
                .getInstance(getContext())
                .nukeAllSharedPreferences();
    }

    private Context getContext() {
        return wineTastingActivity.getActivity();
    }

    /*****************
     * ACTUAL TESTS! *
     *****************/

    @Test
//    public void savingDataInTheDetailShowsUpInTheRecyclerView() {
    public void savingDataInTheDetailIsPersistedToUserDefaults() {
        onView(withId(R.id.add_tasting_button))
                .perform(click());

        String testWineName = "Test wine name";
        String testVarietal = "Cabernet Sauvignon";
        String testVineyard = "Rodney Strong";

        enterTextIntoViewWithID(testVineyard, R.id.tasting_detail_vineyard_name_edittext);
        enterTextIntoViewWithID(testVarietal, R.id.tasting_detail_varietal_edittext);
        enterTextIntoViewWithID(testWineName, R.id.tasting_detail_wine_name_edittext);

        //Select a 3 star rating
        tapViewWithID(R.id.tasting_detail_rating_spinner);
        tapViewWithText("Three");

        //Save the tasting
        tapViewWithText(R.string.save_tasting_title);

        //Back out to the main
//        pressBack();


        String expectedWineName = "Test wine name (Cabernet Sauvignon, Rodney Strong)";

        //Check that input is saving
        List<WineTasting> savedTastings = SharedPreferencesHelper.getInstance(getContext()).getCurrentTastings();

        assertNotNull(savedTastings);
        assertEquals(savedTastings.size(), 1);

        WineTasting savedTasting = savedTastings.get(0);

        assertEquals(savedTasting.vineyardName, testVineyard);
        assertEquals(savedTasting.wineName, testWineName);
        assertEquals(savedTasting.wineVarietal, testVarietal);
        assertEquals(savedTasting.getFullWineName(), expectedWineName);

        //TODO: Fix this so it actually works
//        onRecyclerItemView(R.id.row_tasting,
//                R.id.row_tasting_wine_name_textview,
//                )
//                .check(withText("Test Wine Name (Rodney Strong, Cabernet Sauvignon)"));
    }

}
