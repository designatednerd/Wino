package com.designatednerd.wino.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class WineTastingTests {

    @Test
    public void checkingEqualityNotAffectedByRating() {
        String vineyardName = "Test Vineyard";
        String wineName = "Test Wine";
        String varietal = "Shiraz";
        WineTasting.WineType type = WineTasting.WineType.RED;

        WineTasting zeroStarWine = new WineTasting();
        zeroStarWine.vineyardName = vineyardName;
        zeroStarWine.wineName = wineName;
        zeroStarWine.wineVarietal = varietal;
        zeroStarWine.wineType = type;
        zeroStarWine.rating = 0;

        WineTasting fiveStarWine = new WineTasting();
        fiveStarWine.vineyardName = vineyardName;
        fiveStarWine.wineName = wineName;
        fiveStarWine.wineVarietal = varietal;
        fiveStarWine.wineType = type;
        fiveStarWine.rating = 5;

        assertEquals(zeroStarWine, fiveStarWine);
    }
}
