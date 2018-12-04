package com.pro.ahmed.football.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pro.ahmed.football.R;
import com.pro.ahmed.football.data.models.Fixture;
import com.pro.ahmed.football.data.models.Standing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;

public class FixtureAdapter extends RecyclerView.Adapter<FixtureAdapter.FixtureViewHolder> {

    List<Fixture> mFixtures;
    Context mContext;
    private ArrayList<Fixture> fixturesFilter;

    public FixtureAdapter(Context context) {
        mContext = context;
        this.fixturesFilter = new ArrayList<>();
    }

    @NonNull
    @Override
    public FixtureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new FixtureViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custome_fixture, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull FixtureViewHolder holder, int position) {

        int p = getItemViewType(position);

        Fixture fixture = mFixtures.get(position);
        if (p == 2) {
            holder.tvFixtureDate.setVisibility(View.VISIBLE);
            // holder.fixtureView.setVisibility(View.GONE);
            initData(holder, fixture);

        } else {
            holder.tvFixtureDate.setVisibility(View.GONE);
            /* String eventDate = fixture.getEventDate();
            String time = eventDate.substring(eventDate.indexOf('T') + 1, eventDate.indexOf('+') - 3);
            holder.tvFixtureTime.setText(time);
            holder.tvHome.setText(fixture.getHomeTeam());
            holder.tvFinalScore.setText(fixture.getFinalScore());
            holder.tvAway.setText(fixture.getAwayTeam());
            Glide.with(mContext).load(fixture.getHomeTeamLogo()).into(holder.ivHome);
            Glide.with(mContext).load(fixture.getAwayTeamLogo()).into(holder.ivAway);*/
            initData(holder, fixture);
        }
    }

    @Override
    public int getItemViewType(int position) {
        String currentDate = mFixtures.get(position).getEventDate();
        String prevDate;
        if (position == 0) {
            prevDate = null;
        } else {
            prevDate = mFixtures.get(position - 1).getEventDate();
        }
        String subCurrentDate = currentDate.substring(0, currentDate.indexOf('T'));
        String subPrevDate = null;
        if (prevDate != null) {
            subPrevDate = prevDate.substring(0, prevDate.indexOf('T'));
        }
        if (!subCurrentDate.equals(subPrevDate)) {
            return 2;
        } else {
            return super.getItemViewType(position);
        }
    }

    @Override
    public int getItemCount() {
        if (mFixtures != null) {
            return mFixtures.size();
        } else {
            return 0;
        }
    }

    private void initData(FixtureViewHolder holder, Fixture fixture) {

        String date = fixture.getEventDate().substring(0, fixture.getEventDate().indexOf('T'));
        Date dateOb = null;
        try {
            dateOb = new SimpleDateFormat("yyyy-M-d").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

// Then get the day of week from the Date based on specific locale.
        String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(dateOb);
        holder.tvFixtureDate.setText(date + "    " + dayOfWeek);
        String eventDate = fixture.getEventDate();
        String time = eventDate.substring(eventDate.indexOf('T') + 1, eventDate.indexOf('+') - 3);
        holder.tvFixtureTime.setText(time);
        holder.tvHome.setText(fixture.getHomeTeam());
        if (!fixture.getFinalScore().equals("null")) {
            holder.tvFinalScore.setText(fixture.getFinalScore());
        } else {
            holder.tvFinalScore.setText("");
        }
        holder.tvAway.setText(fixture.getAwayTeam());
        Glide.with(mContext).load(fixture.getHomeTeamLogo()).into(holder.ivHome);
        Glide.with(mContext).load(fixture.getAwayTeamLogo()).into(holder.ivAway);
        Log.v("FixtureItem", fixture.toString());
    }

    public void setFixtures(List<Fixture> fixtures) {
        mFixtures = fixtures;
        this.fixturesFilter.addAll(fixtures);
        notifyDataSetChanged();
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        if (mFixtures != null) {
            mFixtures.clear();
        }
        if (charText.length() == 0 && mFixtures != null) {
            mFixtures.addAll(fixturesFilter);
        } else {
            for (Fixture fixture : fixturesFilter) {
                if (fixture.getHomeTeam().toLowerCase(Locale.getDefault()).contains(charText) || fixture.getAwayTeam().toLowerCase(Locale.getDefault()).contains(charText)) {
                    mFixtures.add(fixture);
                }
            }
        }
        notifyDataSetChanged();
    }

    class FixtureViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvFixtureTime)
        TextView tvFixtureTime;
        @BindView(R.id.tvHome)
        TextView tvHome;
        @BindView(R.id.tvAway)
        TextView tvAway;
        @BindView(R.id.tvFinalScore)
        TextView tvFinalScore;
        @Nullable
        @BindView(R.id.tvFixtureDate)
        TextView tvFixtureDate;
        @BindView(R.id.ivHomeTeam)
        ImageView ivHome;
        @BindView(R.id.ivAwayTeam)
        ImageView ivAway;
        @BindView(R.id.fixtureView)
        LinearLayout fixtureView;

        public FixtureViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
