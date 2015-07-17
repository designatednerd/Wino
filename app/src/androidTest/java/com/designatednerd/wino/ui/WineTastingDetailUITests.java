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

    /******************
     * HELPER METHODS *
     ******************/

    private Context getContext() {
        return wineTastingActivity.getActivity();
    }

    private void goToDetailsPage() {
        //Tap the add button on the main page.
        onView(withId(R.id.add_tasting_button))
                .perform(click());
    }

    private void enterAndSaveWineInfo(String aWineName,
                                      String aVineyardName,
                                      String aVarietal,
                                      int aRating) {
        enterTextIntoViewWithID(aVineyardName, R.id.tasting_detail_vineyard_name_edittext);
        enterTextIntoViewWithID(aVarietal, R.id.tasting_detail_varietal_edittext);
        enterTextIntoViewWithID(aWineName, R.id.tasting_detail_wine_name_edittext);

        //NOTE: This would do the same thing with stock edit texts, but doesn't work with
        //TextInputLayout due to this bug: https://code.google.com/p/android/issues/detail?id=178182
//        enterTextIntoViewWithHint(aVineyardName, R.string.vineyard_name);
//        enterTextIntoViewWithHint(aVarietal, R.string.wine_varietal);
//        enterTextIntoViewWithHint(aWineName, R.string.wine_name);

        tapViewWithID(R.id.tasting_detail_rating_spinner);

        String[] ratings = getContext().getResources().getStringArray(R.array.ratings_array);
        String desiredRating = ratings[aRating];
        tapViewWithText(desiredRating);

        //Save the tasting
        tapViewWithText(R.string.save_tasting_title);
    }

    /*****************
     * ACTUAL TESTS! *
     *****************/

    @Test
    public void savingDataInTheDetailIsPersistedToUserDefaults() {
        goToDetailsPage();

        //Enter the text into the
        String testWineName = "Test wine name";
        String testVarietal = "Cabernet Sauvignon";
        String testVineyard = "Rodney Strong";
        int testRating = 3;

        enterAndSaveWineInfo(testWineName,
                testVineyard,
                testVarietal,
                testRating);

        String expectedWineName = "Test wine name (Cabernet Sauvignon, Rodney Strong)";

        //Check that input is saving
        List<WineTasting> savedTastings = SharedPreferencesHelper.getInstance(getContext()).getCurrentTastings();

        assertNotNull(savedTastings);
        assertEquals(savedTastings.size(), 1);

        WineTasting savedTasting = savedTastings.get(0);

        assertEquals(savedTasting.vineyardName, testVineyard);
        assertEquals(savedTasting.wineName, testWineName);
        assertEquals(savedTasting.wineVarietal, testVarietal);
        assertEquals(savedTasting.rating, testRating);
        assertEquals(savedTasting.getFullWineName(), expectedWineName);

        //back out to the main view for the next test.
        pressBack();
    }
        //TODO: Fix this so it actually works
//        onRecyclerItemView(R.id.row_tasting,
//                R.id.row_tasting_wine_name_textview,
//                )
//                .check(withText("Test Wine Name (Rodney Strong, Cabernet Sauvignon)"));
    }

}
