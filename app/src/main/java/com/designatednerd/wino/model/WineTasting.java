package com.designatednerd.wino.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.designatednerd.wino.R;

import java.util.Date;

public class WineTasting implements Parcelable {

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

        public String parcelableName() {
            switch (this) {
                case RED:
                    return "red";
                case WHITE:
                    return "white";
                case ROSE:
                    return "rose";
                default:
                    return "unknown";
            }
        }

        public static WineType fromString(String aString) {
            if (aString != null) {
                if (aString.equalsIgnoreCase(RED.parcelableName())) {
                    return RED;
                } else if (aString.equalsIgnoreCase(WHITE.parcelableName())) {
                    return WHITE;
                } else if (aString.equalsIgnoreCase(ROSE.parcelableName())) {
                    return ROSE;
                }
            }

            //Fall-through case
            return UNKNOWN;
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
        wineType = WineType.UNKNOWN;
    }

    /************
     * EQUALITY *
     ************/
    @Override
    public boolean equals(Object aAnother) {
        if (aAnother instanceof WineTasting) {
            WineTasting other = (WineTasting)aAnother;
            if (wineName.equals(other.wineName)
                    && vineyardName.equals(other.vineyardName)
                    && wineVarietal.equals(other.wineVarietal)
                    && tastingDate.equals(other.tastingDate)
                    && wineType.equals(other.wineType)) {
                //If all of these are the same, the wines are equal
                return true;
            }
        }

        //Fall-through
        return false;
    }

    /********************
     * PARCELABLE STUFF *
     ********************/

    protected WineTasting(Parcel in) {
        vineyardName = in.readString();
        wineName = in.readString();
        String wineTypeName = in.readString();
        wineType = WineType.fromString(wineTypeName);
        wineVarietal = in.readString();
        rating = in.readInt();
        long tmpTastingDate = in.readLong();
        tastingDate = tmpTastingDate != -1 ? new Date(tmpTastingDate) : null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(vineyardName);
        dest.writeString(wineName);
        dest.writeString(wineType.parcelableName());
        dest.writeString(wineVarietal);
        dest.writeInt(rating);
        dest.writeLong(tastingDate != null ? tastingDate.getTime() : -1L);
    }

    public static final Parcelable.Creator<WineTasting> CREATOR = new Parcelable.Creator<WineTasting>() {
        @Override
        public WineTasting createFromParcel(Parcel in) {
            return new WineTasting(in);
        }

        @Override
        public WineTasting[] newArray(int size) {
            return new WineTasting[size];
        }
    };


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
                ratingString = ratingString + new String(Character.toChars(starEmoji));
            }

            return ratingString;
        }
    }
}