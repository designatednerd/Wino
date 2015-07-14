package com.designatednerd.wino.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.designatednerd.wino.R;
import com.designatednerd.wino.fragment.WineTastingDetailFragment;
import com.designatednerd.wino.model.WineTasting;

public class WineTastingDetailActivity extends AppCompatActivity {

    public static final String TASTING_TO_DISPLAY_EXTRA = "com.designatednerd.wino.tastingtodisplay";
    public static final String IS_EDITING_EXTRA = "com.designatednerd.wino.isediting";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_tasting_frame);

        boolean isEditing = getIntent().getBooleanExtra(IS_EDITING_EXTRA, false);
        WineTasting tasting = getIntent().getParcelableExtra(TASTING_TO_DISPLAY_EXTRA);

        //Show the detail
        WineTastingDetailFragment detailFragment = new WineTastingDetailFragment();
        detailFragment.setTasting(tasting);
        detailFragment.setIsEditing(isEditing);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.wine_tasting_frame, detailFragment, "DETAIL")
                .commit();
    }
}
