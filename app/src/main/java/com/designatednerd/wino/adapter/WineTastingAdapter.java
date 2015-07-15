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
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WineTastingAdapter extends RecyclerView.Adapter<WineTastingAdapter.WineTastingViewHolder> {

    /**************
     * INTERFACES *
     **************/

    public interface TastingSelectedListener {
        /**
         * Called whenever a tasting is selected.
         * @param aTasting The tasting which was selected.
         */
        void selectedTasting(WineTasting aTasting);
    }

    /*************
     * VARIABLES *
     *************/

    private List<WineTasting>       mTastings;
    private TastingSelectedListener mListener;

    /**
     * Designated constructor.
     * @param aTastings An array of existing tastings, or null
     * @param aListener A TastingSelectedListener object.
     */
    public WineTastingAdapter(List<WineTasting> aTastings,
                              TastingSelectedListener aListener) {
        mListener = aListener;
        updateTastings(aTastings);
    }

    /**
     * Adds a tasting object to the list.
     * @param aTasting The tasting object to add.
     */
    public void addTasting(WineTasting aTasting) {
        mTastings.add(aTasting);
        notifyDataSetChanged();
    }

    public void updateTastings(List<WineTasting> aTastings) {
        if (aTastings != null) {
            mTastings = aTastings;
        } else {
            mTastings = new ArrayList<>();
        }
        notifyDataSetChanged();
    }

    /************************
     * SUPERCLASS OVERRIDES *
     ************************/

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
        if (mTastings != null) {
            return mTastings.size();
        } else {
            return 0;
        }
    }

    /***************
     * VIEW HOLDER *
     ***************/

    class WineTastingViewHolder extends RecyclerView.ViewHolder {

        /*************
         * VARIABLES *
         *************/

        private WineTasting mTasting;

        @Bind(R.id.row_tasting_wine_name_textview) TextView mWineNameTextView;
        @Bind(R.id.row_tasting_wine_rating_textview) TextView mWineRatingTextView;
        @Bind(R.id.row_tasting_date_textview) TextView mTastingDateTextView;

        /***************
         * CONSTRUCTOR *
         ***************/

        public WineTastingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        /*****************
         * CONFIGURATION *
         *****************/

        public void configureForTasting(WineTasting aTasting) {
            mTasting = aTasting;
            mWineNameTextView.setText(mTasting.getFullWineName());
            mWineRatingTextView.setText(mTasting.getRatingString());
            String formattedDate = TastingDateFormatter.shortFormattedDate(mTasting.tastingDate,
                    itemView.getContext());
            mTastingDateTextView.setText(formattedDate);
        }

        /********************
         * ONCLICK LISTENER *
         ********************/

        @OnClick(R.id.row_tasting)
        public void rowTapped() {
            if (mListener != null) {
                mListener.selectedTasting(mTasting);
            } else {
                throw new RuntimeException("Hey you might want a listener for this!");
            }
        }
    }
}
