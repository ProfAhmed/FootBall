package com.pro.ahmed.football.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.pro.ahmed.football.data.models.League;
import com.pro.ahmed.football.data.repositories.Repository;

import java.util.ArrayList;

public class LeagueViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<ArrayList<League>> leagueLiveData;

    public LeagueViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);

        leagueLiveData = repository.getLeague();
    }

    public LiveData<ArrayList<League>> getLeague() {
        return leagueLiveData;
    }

}
