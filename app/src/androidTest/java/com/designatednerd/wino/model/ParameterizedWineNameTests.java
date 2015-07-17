package com.designatednerd.wino.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * An example of a parameterized test which runs the same test function over and over with
 * different data.
 */

@RunWith(Parameterized.class)
public class ParameterizedWineNameTests {

    /*******************
     * PARAMETER SETUP *
     *******************/

    /**
     * Create an iterable of object arrays that can be handed over to the test
     * as parameters to the constructor.
     *
     * For example, here we have a constructor that takes 4 items: The wine name,
     * the vineyard name, the varietal name, and the expected output string. Each
     * of these arrays of strings will be passed in to the constructor in that order.
     */
    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Wine", "Vineyard", "Varietal", "Wine (Varietal, Vineyard)"}, //All info
                {"Wine", "Vineyard", null, "Wine (Vineyard)"}, //Missing varietal
                {"Wine", null, "Varietal", "Wine (Varietal)"}, //Missing vineyard
                {"Wine", null, null, "Wine"}, //Wine Only
                {null, null, null, ""} //No info at all
        });
    }

    /*************
     * VARIABLES *
     *************/

    private String mWineName;
    private String mVineyardName;
    private String mVarietalName;
    private String mExpectedFormattedOutput;

    /***************
     * CONSTRUCTOR *
     ***************/

    public ParameterizedWineNameTests(String aWineName,
                                      String aVineyardName,
                                      String aVarietalName,
                                      String aExpectedFormattedOutput) {

        mWineName = aWineName;
        mVineyardName = aVineyardName;
        mVarietalName = aVarietalName;
        mExpectedFormattedOutput = aExpectedFormattedOutput;
    }


    /***********************
     * PARAMETERIZED TESTS *
     ***********************/

    @Test
    public void testFormattingWineName() {
        String formatted = WineTasting.wineNameFromInfo(mWineName, mVineyardName, mVarietalName);
        assertEquals(mExpectedFormattedOutput, formatted);
    }
}
