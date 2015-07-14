package com.designatednerd.wino.model;

import android.content.Context;

import com.designatednerd.wino.R;

import java.util.Date;

/**
 * Created by ellen on 7/13/15.
 */
public class WineTasting {

    public enum WineType {
        UNKNOWN,
        RED,
        WHITE,
        ROSE;

        public String getDisplayName(Context aContext) {
            switch (this) {
                case RED:
                    return aContext.getString(R.string.red);
                case WHITE:
                    return aContext.getString(R.string.white);
                case ROSE:
                    return aContext.getString(R.string.rose);
                default:
                    return aContext.getString(R.string.not_set);
            }
        }
    }

    /*************
     * VARIABLES *
     *************/

    public String vineyardName;
    public String wineName;
    public WineType wineType;
    public String wineVarietal;
    public int rating;
    public Date tastingDate;

    /***************
     * CONSTRUCTOR *
     ***************/

    public WineTasting() {
        //Default to using the current date.
        tastingDate = new Date();
    }


    /***************
     * CONVENIENCE *
     ***************/

    public String getFullWineName() {
        return wineName + "(" + wineVarietal + ", " + vineyardName + ")";
    }

    public String getRatingString() {
        if (rating == 0) {
            int thumbsDownEmoji = 0x1F44E;
            return new String(Character.toChars(thumbsDownEmoji));
        } else {
            int loops = rating;
            String ratingString = "";
            int starEmoji = 0x1F31F;
            while (loops > 0) {
                ratingString = ratingString + Character.toChars(starEmoji);
            }

            return ratingString;
        }
    }
}