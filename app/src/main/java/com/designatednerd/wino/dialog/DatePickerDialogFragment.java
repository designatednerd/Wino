package com.designatednerd.wino.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

public class DatePickerDialogFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    public interface DateSelectedListener {
        void selectedDate(Date aDate);
    }

    private Date                    mDate;
    private DateSelectedListener    mListener;

    public void setDate(Date aDate) {
        mDate = aDate;
    }

    public void setListener(DateSelectedListener aListener) {
        mListener = aListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        if (mDate == null) {
            mDate = new Date();
        }
        calendar.setTime(mDate);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        if (mListener != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            Date dateFromCalendar = calendar.getTime();
            mListener.selectedDate(dateFromCalendar);
        } else {
            throw new RuntimeException("You're probably gonna want a listener for this.");
        }
    }
}