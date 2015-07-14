package com.designatednerd.wino.model;

import android.content.Context;

import java.text.DateFormat;
import java.util.Date;

public class TastingDateFormatter {

    public static String shortFormattedDate(Date aDate, Context aContext) {
        //Get the system short-form formatter.
        DateFormat format = android.text.format.DateFormat.getDateFormat(aContext);
        return format.format(aDate);
    }
}
