package com.pro.ahmed.football.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.pro.ahmed.football.data.models.Standing;
import com.pro.ahmed.football.data.repositories.Repository;

import java.util.ArrayList;

public class StandingsViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<ArrayList<Standing>> standingsLiveData;
    private MutableLiveData<String> mLeague_id = new MutableLiveData<>();

    public StandingsViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application);

        standingsLiveData = Transformations.switchMap(mLeague_id, id -> {
            return repository.getStandings(id);
        });
    }

    public void setLeague_id(String league_id) {
        mLeague_id.setValue(league_id);
    }

    public LiveData<ArrayList<Standing>> getStandings() {
        return standingsLiveData;
    }

}
