package com.pro.ahmed.football.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.pro.ahmed.football.data.models.Fixture;
import com.pro.ahmed.football.data.models.RequestType;
import com.pro.ahmed.football.data.repositories.Repository;

import java.util.ArrayList;

public class FixtureViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<ArrayList<Fixture>> fixturesLeagueLiveData;
    private MutableLiveData<RequestType> mFixture_REQUEST_TYPE = new MutableLiveData<>();

    public FixtureViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        fixturesLeagueLiveData = Transformations.switchMap(mFixture_REQUEST_TYPE, requestType -> {
            return repository.getFixtureLeagueId(requestType);
        });

    }

    public void setFixture_id(RequestType requestType) {
        mFixture_REQUEST_TYPE.setValue(requestType);
    }

    public LiveData<ArrayList<Fixture>> getFixturesLeagueIdLiveData() {
        return fixturesLeagueLiveData;
    }
}
