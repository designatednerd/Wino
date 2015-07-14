package com.designatednerd.wino.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.designatednerd.wino.R;
import com.designatednerd.wino.fragment.WineTastingListFragment;

/**
 * The main activity
 */
public class WineTastingActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_tasting_frame);

        //Get in to the first fragment
        WineTastingListFragment listFragment = new WineTastingListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.wine_tasting_frame, listFragment, "LIST")
                .commit();
    }
}
