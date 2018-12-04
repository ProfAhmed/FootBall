package com.pro.ahmed.football.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.pro.ahmed.football.data.models.Team;
import com.pro.ahmed.football.data.repositories.Repository;

public class TeamViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<Team> teamLiveData;
    private MutableLiveData<String> mTeam_id = new MutableLiveData<>();

    public TeamViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);

        teamLiveData = Transformations.switchMap(mTeam_id, id -> {
            return repository.getTeam(id);
        });

    }

    public void setTeam_id(String team_id) {
        mTeam_id.setValue(team_id);
    }

    public LiveData<Team> getTeam() {
        return teamLiveData;
    }

}
