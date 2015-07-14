package com.designatednerd.wino.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.designatednerd.wino.R;
import com.designatednerd.wino.model.TastingDateFormatter;
import com.designatednerd.wino.model.WineTasting;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class WineTastingAdapter extends RecyclerView.Adapter<WineTastingAdapter.WineTastingViewHolder> {

    private List<WineTasting> mTastings;

    public WineTastingAdapter(List<WineTasting> aTastings) {
        if (aTastings != null) {
            mTastings = aTastings;
        } else {
            mTastings = new ArrayList<>();
        }
    }

    public void addTasting(WineTasting aTasting) {
        mTastings.add(aTasting);
        notifyDataSetChanged();
    }

    @Override
    public WineTastingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View inflated = inflater.inflate(R.layout.row_wine_tasting, parent, false);
        return new WineTastingViewHolder(inflated);
    }

    @Override
    public void onBindViewHolder(WineTastingViewHolder holder, int position) {
        WineTasting tasting = mTastings.get(position);
        holder.configureForTasting(tasting);
    }

    @Override
    public int getItemCount() {
        return mTastings.size();
    }

    class WineTastingViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.row_tasting_wine_name_textview) TextView mWineNameTextView;
        @Bind(R.id.row_tasting_wine_rating_textview) TextView mWineRatingTextView;
        @Bind(R.id.row_tasting_date_textview) TextView mTastingDateTextView;


        public WineTastingViewHolder(View itemView) {
            super(itemView);
        }

        public void configureForTasting(WineTasting aTasting) {
            mWineNameTextView.setText(aTasting.getFullWineName());
            mWineRatingTextView.setText(aTasting.getRatingString());
            String formattedDate = TastingDateFormatter.shortFormattedDate(aTasting.tastingDate,
                    itemView.getContext());
            mTastingDateTextView.setText(formattedDate);
        }
    }
}
