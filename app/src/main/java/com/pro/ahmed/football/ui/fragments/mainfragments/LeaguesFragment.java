package com.pro.ahmed.football.ui.fragments.mainfragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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

import com.pro.ahmed.football.LeagueInfoActivity;
import com.pro.ahmed.football.MainActivity;
import com.pro.ahmed.football.R;
import com.pro.ahmed.football.events.Events;
import com.pro.ahmed.football.ui.adapters.LeaguesAdapter;
import com.pro.ahmed.football.viewmodel.LeagueViewModel;


import org.greenrobot.eventbus.EventBus;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LeaguesFragment extends Fragment {
    @BindView(R.id.rvLeagues)
    RecyclerView rvLeagues;

    private LeagueViewModel viewModel;

    public LeaguesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_leagues, container, false);
        ButterKnife.bind(this, view);
        setRetainInstance(true);// to prevent fragment recreation

        viewModel = ViewModelProviders.of(getActivity()).get(LeagueViewModel.class);
        final LeaguesAdapter adapter = new LeaguesAdapter(getActivity());
        rvLeagues.setAdapter(adapter);
        rvLeagues.setLayoutManager(new LinearLayoutManager(getActivity()));
        viewModel.getLeague().observe(getActivity(), leagues -> {
            if (leagues.get(0).getError() != null) {
                Log.v("MainError", leagues.get(0).getError());
            } else {
                adapter.setLeagues(leagues);
            }
        });

        // search in leagues
        MainActivity.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.etSearch.setCursorVisible(true);
                if (MainActivity.etSearch.getText().length() == 0) {
                    MainActivity.etSearch.setCursorVisible(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = MainActivity.etSearch.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }
        });

        adapter.setOnItemClickListener(league -> {
            // Toast.makeText(getActivity(), league.getName(), Toast.LENGTH_SHORT).show();

            // We are broadcasting the message here to listen to the subscriber.
            Events event = new Events(league);
            EventBus.getDefault().postSticky(event);
            startActivity(new Intent(getActivity(), LeagueInfoActivity.class));
        });
        return view;
    }

}
