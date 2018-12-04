package com.pro.ahmed.football.ui.fragments.leaguefragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pro.ahmed.football.LeagueInfoActivity;
import com.pro.ahmed.football.R;
import com.pro.ahmed.football.events.Events;
import com.pro.ahmed.football.ui.adapters.StandingsAdapter;
import com.pro.ahmed.football.viewmodel.StandingsViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StandingsFragment extends Fragment {
    @BindView(R.id.rvStandings)
    RecyclerView rvStandings;
    private StandingsViewModel viewModel;
    StandingsAdapter adapter;


    public StandingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_standings, container, false);
        ButterKnife.bind(this, view);
        viewModel = ViewModelProviders.of(getActivity()).get(StandingsViewModel.class);
        adapter = new StandingsAdapter(getActivity());
        rvStandings.setAdapter(adapter);
        rvStandings.setLayoutManager(new LinearLayoutManager(getActivity()));
        viewModel.getStandings().observe(getActivity(), standings -> {
            if (standings != null) {
                adapter.setStandings(standings);
            }
        });

        // search in leagues
        LeagueInfoActivity.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                LeagueInfoActivity.etSearch.setCursorVisible(true);
                if (LeagueInfoActivity.etSearch.getText().length() == 0) {
                    LeagueInfoActivity.etSearch.setCursorVisible(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = LeagueInfoActivity.etSearch.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }
        });

        adapter.setOnItemClickListener(standing -> {
            // Toast.makeText(getActivity(), standing.getTeamName(), Toast.LENGTH_SHORT).show();
        });
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageEvent(Events event) {

        viewModel.setLeague_id(event.getLeague().getLeagueId());
        Toast.makeText(getActivity(), "label " + event.getLeague().toString(),
                Toast.LENGTH_SHORT).show();
        EventBus.getDefault().removeStickyEvent(event.toString()); // don't forget to remove the sticky event if youre done with it
        adapter.setOnItemClickListener(standing -> {
            Toast.makeText(getActivity(), standing.getTeamName() + "Event", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);

    }
}
