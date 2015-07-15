package com.designatednerd.wino.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.designatednerd.wino.R;
import com.designatednerd.wino.SharedPreferencesHelper;
import com.designatednerd.wino.dialog.DatePickerDialogFragment;
import com.designatednerd.wino.model.TastingDateFormatter;
import com.designatednerd.wino.model.WineTasting;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A fragment representing a single Wine Tasting.
 */
public class WineTastingDetailFragment extends Fragment implements DatePickerDialogFragment.DateSelectedListener {

    /*************
     * VARIABLES *
     *************/

    private WineTasting mTasting;
    private boolean mIsEditing;

    @Bind(R.id.tasting_detail_vineyard_name_edittext)   EditText mVineyardNameEditText;
    @Bind(R.id.tasting_detail_wine_name_edittext)       EditText mWineNameEditText;
    @Bind(R.id.tasting_detail_varietal_edittext)        EditText mVarietalEditText;
    @Bind(R.id.tasting_detail_tasting_date_button)      Button mTastingDateButton;
    @Bind(R.id.tasting_detail_save_button)              Button mSaveButton;
    @Bind(R.id.tasting_detail_rating_spinner)           Spinner mRatingSpinner;

    /******************
     * VIEW LIFECYCLE *
     ******************/

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_wine_tasting_detail, container, false);
        ButterKnife.bind(this, rootView);

        //Setup spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.ratings_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mRatingSpinner.setAdapter(adapter);

        //Configure for editability
        setIsEditing(mIsEditing);
        configureForCurrentTasting();

        return rootView;
    }

    /******************
     * PUBLIC SETTERS *
     ******************/

    public void setIsEditing(boolean aIsEditing) {
        mIsEditing = aIsEditing;

        if (butterknifeHasFired()) {
            mVineyardNameEditText.setEnabled(mIsEditing);
            mWineNameEditText.setEnabled(mIsEditing);
            mVarietalEditText.setEnabled(mIsEditing);
            mTastingDateButton.setEnabled(mIsEditing);
            mRatingSpinner.setEnabled(mIsEditing);

            if (mIsEditing) {
                mSaveButton.setVisibility(View.VISIBLE);
            } else {
                mSaveButton.setVisibility(View.GONE);
            }
        }
    }

    public void setTasting(WineTasting aTasting) {
        mTasting = aTasting;
        configureForCurrentTasting();
    }

    /*************************
     * DISPLAY CONFIGURATION *
     *************************/

    private boolean butterknifeHasFired() {
        //If butterknife hasn't fired yet, crashes galore since all bound
        //variables will be null.
        return (mVineyardNameEditText != null);
    }

    private void configureForCurrentTasting() {
        if (mTasting != null && butterknifeHasFired()) {
            mVineyardNameEditText.setText(mTasting.vineyardName);
            mWineNameEditText.setText(mTasting.wineName);
            mVarietalEditText.setText(mTasting.wineVarietal);
            showTastingDisplayDate();
            mRatingSpinner.setSelection(mTasting.rating);
        }
    }

    private void showTastingDisplayDate() {
        String tastingDate = TastingDateFormatter.shortFormattedDate(mTasting.tastingDate, getActivity());
        mTastingDateButton.setText(tastingDate);
    }

    /**********************
     * ON CLICK LISTENERS *
     **********************/

    @OnClick(R.id.tasting_detail_save_button)
    public void saveTasting() {
        mTasting.vineyardName = mVineyardNameEditText.getText().toString();
        mTasting.wineName = mWineNameEditText.getText().toString();
        mTasting.wineVarietal = mVarietalEditText.getText().toString();
        mTasting.rating = mRatingSpinner.getSelectedItemPosition();

        SharedPreferencesHelper preferencesHelper = SharedPreferencesHelper.getInstance(getActivity());
        List<WineTasting> currentTastings = preferencesHelper.getCurrentTastings();
        if (currentTastings == null) {
            currentTastings = new ArrayList<>();
        }
        currentTastings.add(mTasting);
        preferencesHelper.setCurrentTastings(currentTastings);
    }


    @OnClick(R.id.tasting_detail_tasting_date_button)
    public void showDatePicker() {
        DatePickerDialogFragment datePicker = new DatePickerDialogFragment();
        datePicker.setDate(mTasting.tastingDate);
        datePicker.setListener(this);
        datePicker.show(getFragmentManager(), "DATE");
    }

    /**************************
     * DATE SELECTED LISTENER *
     **************************/

    @Override
    public void selectedDate(Date aDate) {
        mTasting.tastingDate = aDate;
        showTastingDisplayDate();
    }
}
