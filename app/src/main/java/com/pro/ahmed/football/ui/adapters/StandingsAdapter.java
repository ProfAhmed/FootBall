package com.pro.ahmed.football.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pro.ahmed.football.R;
import com.pro.ahmed.football.data.models.League;
import com.pro.ahmed.football.data.models.Standing;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StandingsAdapter extends RecyclerView.Adapter<StandingsAdapter.StandingsViewHolder> {

    private StandingsAdapter.OnItemClickListener listener;
    List<Standing> mStandings;
    Context mContext;
    private ArrayList<Standing> standingsFilter;

    public StandingsAdapter(Context context) {
        mContext = context;
        this.standingsFilter = new ArrayList<Standing>();
    }

    @NonNull
    @Override
    public StandingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custome_standings, parent, false);
        StandingsViewHolder viewHolder = new StandingsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StandingsViewHolder holder, int position) {
        Standing standing = mStandings.get(position);
        holder.tvArrangement.setText(standing.getArrangement());
        holder.tvTeamName.setText(standing.getTeamName());
        holder.tvPlay.setText(standing.getPlay());
        holder.tvWin.setText(standing.getWin());
        holder.tvDraw.setText(standing.getDraw());
        holder.tvLose.setText(standing.getLose());
        holder.tvForAndOn.setText(standing.getGoalsFor() + "/" + standing.getGoalsAgainst());
        holder.tvDiff.setText(standing.getGoalsDiff());
        holder.tvPoints.setText(standing.getPoints());
        Glide.with(mContext).load(standing.getTeamLogo()).into(holder.ivTeamLogo);
    }

    @Override
    public int getItemCount() {
        if (mStandings != null) {
            return mStandings.size();
        } else {
            return 0;
        }
    }

    public void setStandings(List<Standing> standings) {
        mStandings = standings;
        this.standingsFilter.addAll(standings);
        notifyDataSetChanged();
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        if (mStandings != null) {
            mStandings.clear();
        }
        if (charText.length() == 0 && mStandings != null) {
            mStandings.addAll(standingsFilter);
        } else {
            for (Standing standing : standingsFilter) {
                if (standing.getTeamName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    mStandings.add(standing);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(StandingsAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public class StandingsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvArrangement)
        TextView tvArrangement;
        @BindView(R.id.tvTeamName)
        TextView tvTeamName;
        @BindView(R.id.tvPlay)
        TextView tvPlay;
        @BindView(R.id.tvWin)
        TextView tvWin;
        @BindView(R.id.tvDraw)
        TextView tvDraw;
        @BindView(R.id.tvLose)
        TextView tvLose;
        @BindView(R.id.tvForAndOn)
        TextView tvForAndOn;
        @BindView(R.id.tvDiff)
        TextView tvDiff;
        @BindView(R.id.tvPoints)
        TextView tvPoints;
        @BindView(R.id.ivTeamLogo)
        ImageView ivTeamLogo;

        public StandingsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(mStandings.get(position));
                }

            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Standing standing);
    }
}
