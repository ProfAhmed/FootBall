package com.pro.ahmed.football.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.pro.ahmed.football.data.models.Stats;
import com.pro.ahmed.football.data.repositories.Repository;


public class StatisticsViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<Stats> statsLiveData;
    private MutableLiveData<String> mStats_id = new MutableLiveData<>();


    public StatisticsViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application);

        statsLiveData = Transformations.switchMap(mStats_id, id -> {
            return repository.getStatistics(id);
        });
    }

    public void setStats_id(String stats_id) {
        mStats_id.setValue(stats_id);
    }

    public LiveData<Stats> getStats() {
        return statsLiveData;
    }
}
