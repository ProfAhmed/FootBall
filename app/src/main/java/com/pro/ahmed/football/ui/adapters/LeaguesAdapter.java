package com.pro.ahmed.football.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pro.ahmed.football.R;
import com.pro.ahmed.football.data.models.League;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeaguesAdapter extends RecyclerView.Adapter<LeaguesAdapter.LeagueViewHolder> {
    private OnItemClickListener listener;
    List<League> mLeagues;
    Context mContext;
    private ArrayList<League> leaguesFilter;
    int count = 0;
    int colors[];

    public LeaguesAdapter(Context context) {
        mContext = context;
        colors = new int[]{Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.GRAY};
        this.leaguesFilter = new ArrayList<League>();
    }

    @NonNull
    @Override
    public LeagueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_leagues, parent, false);
        LeagueViewHolder viewHolder = new LeagueViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LeagueViewHolder holder, int position) {

        Log.v("LeagueCode", String.valueOf(count));
        if (mLeagues != null) {
            League league = mLeagues.get(position);
            holder.tvLeagueName.setText(league.getName());
            holder.tvSeason.setText(league.getSeason());
            Log.v("NameLL", league.getCountry());
            holder.tvCountryName.setText(league.getCountry());
            if (count < colors.length) {
                holder.vLeague.setBackgroundColor(colors[count]);
                count++;
            } else {
                count = 0;
            }
            try {
                Glide.with(mContext).load(new URL(league.getLogo())).into(holder.ivLeague);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mLeagues != null) {
            return mLeagues.size();
        } else {
            return 0;
        }
    }


    public void setLeagues(List<League> leagues) {
        mLeagues = leagues;
        this.leaguesFilter.addAll(leagues);
        notifyDataSetChanged();
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        if (mLeagues != null) {
            mLeagues.clear();
        }
        if (charText.length() == 0 && (mLeagues != null)) {
            mLeagues.addAll(leaguesFilter);
        } else {
            for (League league : leaguesFilter) {
                if (league.getCountry().toLowerCase(Locale.getDefault()).contains(charText) || league.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    mLeagues.add(league);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public class LeagueViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvLeagueName)
        TextView tvLeagueName;
        @BindView(R.id.tvSeason)
        TextView tvSeason;
        @BindView(R.id.tvCountryName)
        TextView tvCountryName;
        @BindView(R.id.vLeagues)
        View vLeague;
        @BindView(R.id.ivLeague)
        ImageView ivLeague;

        public LeagueViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(mLeagues.get(position));
                }

            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(League league);
    }

}
