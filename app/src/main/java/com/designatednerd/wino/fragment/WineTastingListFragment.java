package com.designatednerd.wino.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.designatednerd.wino.R;
import com.designatednerd.wino.activity.WineTastingActivity;
import com.designatednerd.wino.activity.WineTastingDetailActivity;
import com.designatednerd.wino.adapter.WineTastingAdapter;
import com.designatednerd.wino.adapter.WineTastingAdapter.TastingSelectedListener;
import com.designatednerd.wino.model.WineTasting;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A list fragment representing a list of WineTastings.
 */
public class WineTastingListFragment extends Fragment implements TastingSelectedListener {

    /*************
     * VARIABLES *
     *************/

    private WineTastingAdapter mAdapter;

    @Bind(R.id.wine_tasting_recyclerview) RecyclerView mRecyclerView;

    /**********************
     * FRAGMENT LIFECYCLE *
     **********************/

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View main = inflater.inflate(R.layout.fragment_wine_tasting_list, container, false);

        //Fire off butterknife to populate bound variables
        ButterKnife.bind(this, main);

        //Add layout manager to recyclerview
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

        //Setup adapter
        mAdapter = new WineTastingAdapter(null, this);
        mRecyclerView.setAdapter(mAdapter);
        return main;
    }

    /*********************
     * FRAGMENT FUNTIMES *
     *********************/

    private void showTastingDetail(WineTasting aTasting, boolean aIsEditable) {
        Intent tastingDetailIntent = new Intent(getActivity(), WineTastingDetailActivity.class);
        tastingDetailIntent.putExtra(WineTastingDetailActivity.IS_EDITING_EXTRA, aIsEditable);
        tastingDetailIntent.putExtra(WineTastingDetailActivity.TASTING_TO_DISPLAY_EXTRA, aTasting);
        startActivity(tastingDetailIntent);
    }

    /*********************
     * ON CLICK LISTENER *
     *********************/

    @OnClick(R.id.add_tasting_button)
    public void addTasting() {
        WineTasting tasting = new WineTasting();
        mAdapter.addTasting(tasting);
        showTastingDetail(tasting, true);
    }

    /*****************************
     * TASTING SELECTED LISTENER *
     *****************************/

    public void selectedTasting(WineTasting aTasting) {
        showTastingDetail(aTasting, false);
    }
}
