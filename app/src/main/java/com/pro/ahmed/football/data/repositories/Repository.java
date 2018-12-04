package com.pro.ahmed.football.data.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import android.util.Log;

import com.android.volley.VolleyError;
import com.pro.ahmed.football.constants.Constants;
import com.pro.ahmed.football.data.JsonParser;
import com.pro.ahmed.football.data.api.IResult;
import com.pro.ahmed.football.data.api.VolleyService;
import com.pro.ahmed.football.data.models.Fixture;
import com.pro.ahmed.football.data.models.League;
import com.pro.ahmed.football.data.models.RequestType;
import com.pro.ahmed.football.data.models.Standing;
import com.pro.ahmed.football.data.models.Stats;
import com.pro.ahmed.football.data.models.Team;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class Repository {
    IResult mResultCallback = null;
    VolleyService mVolleyService;
    Application mContext;
    private Executor executor = Executors.newSingleThreadExecutor();


    public Repository(Application mContext) {
        this.mContext = mContext;
    }

    public LiveData<ArrayList<League>> getLeague() {
        final MutableLiveData<ArrayList<League>> data = new MutableLiveData<>();
        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {

                ArrayList<League> leagues = JsonParser.parseLeagues(response);
                data.setValue(leagues);
            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                ArrayList<League> leagues = new ArrayList<>();
                League league = new League();
                league.setError(error.toString());
                leagues.add(league);
                data.setValue(leagues);
                Log.v("Error Response", error.toString());
            }
        };

        mVolleyService = new VolleyService(mResultCallback, mContext);
        mVolleyService.addHeader(Constants.API_KEY_NAME, Constants.API_KEY_VALUE2);
        mVolleyService.addHeader(Constants.HEADER_NAME, Constants.HEADER_VALUE);
        mVolleyService.getDataVolley(Constants.GET_CALL, Constants.ALL_LEAGUES_URI);

        return data;
    }

    public LiveData<ArrayList<Standing>> getStandings(String id) {
        final MutableLiveData<ArrayList<Standing>> data = new MutableLiveData<>();
        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {

                ArrayList<Standing> standings = JsonParser.parseStandings(response);
                for (int i = 0; i < standings.size(); i++) {
                    int finalI = i;
                    mResultCallback = new IResult() {
                        @Override
                        public void notifySuccess(String requestType, JSONObject response) {
                            Team team = JsonParser.parsTeam(response);
                            standings.get(finalI).setTeamLogo(team.getLogo());

                            if (finalI == standings.size() - 1) {
                                data.setValue(standings);
                            }
                        }

                        @Override
                        public void notifyError(String requestType, VolleyError error) {
                        }
                    };

                    mVolleyService = new VolleyService(mResultCallback, mContext);
                    mVolleyService.addHeader(Constants.API_KEY_NAME, Constants.API_KEY_VALUE2);
                    mVolleyService.addHeader(Constants.HEADER_NAME, Constants.HEADER_VALUE);
                    mVolleyService.getDataVolley(Constants.GET_CALL, Constants.TEAM_ID_URI + standings.get(i).getTeamId());
                }
            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
            }
        };

        mVolleyService = new VolleyService(mResultCallback, mContext);
        mVolleyService.addHeader(Constants.API_KEY_NAME, Constants.API_KEY_VALUE2);
        mVolleyService.addHeader(Constants.HEADER_NAME, Constants.HEADER_VALUE);
        mVolleyService.getDataVolley(Constants.GET_CALL, Constants.ALL_STANDINGS_URI + id);

        return data;
    }

    public LiveData<Team> getTeam(String team_id) {
        final MutableLiveData<Team> data = new MutableLiveData<>();
        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Team team = JsonParser.parsTeam(response);
                data.postValue(team);
            }

            @Override
            public void notifyError(String requestType, VolleyError error) {

            }
        };

        mVolleyService = new VolleyService(mResultCallback, mContext);
        mVolleyService.addHeader(Constants.API_KEY_NAME, Constants.API_KEY_VALUE2);
        mVolleyService.addHeader(Constants.HEADER_NAME, Constants.HEADER_VALUE);
        mVolleyService.getDataVolley(Constants.GET_CALL, Constants.TEAM_ID_URI + team_id);

        return data;
    }

    public LiveData<ArrayList<Fixture>> getFixtureLeagueId(RequestType requestType) { // for leagues and teams

        final MutableLiveData<ArrayList<Fixture>> data = new MutableLiveData<>();

        executor.execute(() -> {
            mResultCallback = new IResult() {
                @Override
                public void notifySuccess(String requestType, JSONObject response) {

                    ArrayList<Fixture> fixtures = JsonParser.parsFixtures(response);
                    Log.v("FixtureSuccess", fixtures.toString());
                    for (int i = 0; i < fixtures.size(); i++) {

                        int finalI = i;
                        mResultCallback = new IResult() {
                            @Override
                            public void notifySuccess(String requestType, JSONObject response) {
                                Team homeTeam = JsonParser.parsTeam(response);
                                fixtures.get(finalI).setHomeTeamLogo(homeTeam.getLogo());
                                executor.execute(() -> {
                                    mResultCallback = new IResult() {
                                        @Override
                                        public void notifySuccess(String requestType, JSONObject response) {
                                            Team awayTeam = JsonParser.parsTeam(response);
                                            fixtures.get(finalI).setAwayTeamLogo(awayTeam.getLogo());
                                            data.setValue(fixtures);
                                        }

                                        @Override
                                        public void notifyError(String requestType, VolleyError error) {

                                        }
                                    };

                                    mVolleyService = new VolleyService(mResultCallback, mContext);
                                    mVolleyService.addHeader(Constants.API_KEY_NAME, Constants.API_KEY_VALUE);
                                    mVolleyService.addHeader(Constants.HEADER_NAME, Constants.HEADER_VALUE);
                                    mVolleyService.getDataVolley(Constants.GET_CALL, Constants.TEAM_ID_URI + fixtures.get(finalI).getAwayTeamId());
                                });
                            }

                            @Override
                            public void notifyError(String requestType, VolleyError error) {
                            }
                        };

                        mVolleyService = new VolleyService(mResultCallback, mContext);
                        mVolleyService.addHeader(Constants.API_KEY_NAME, Constants.API_KEY_VALUE);
                        mVolleyService.addHeader(Constants.HEADER_NAME, Constants.HEADER_VALUE);
                        mVolleyService.getDataVolley(Constants.GET_CALL, Constants.TEAM_ID_URI + fixtures.get(finalI).getHomeTeamId());
                    }
                }

                @Override
                public void notifyError(String requestType, VolleyError error) {

                }
            };

            mVolleyService = new VolleyService(mResultCallback, mContext);
            mVolleyService.addHeader(Constants.API_KEY_NAME, Constants.API_KEY_VALUE);
            mVolleyService.addHeader(Constants.HEADER_NAME, Constants.HEADER_VALUE);
            if (requestType.getRequestType().equals(Constants.LEAGUE_TAG)) {
                mVolleyService.getDataVolley(Constants.GET_CALL, Constants.FIXTURES_LEAGUE_ID + requestType.getId());
            } else if (requestType.getRequestType().equals(Constants.TEAM_TAG)) {
                mVolleyService.getDataVolley(Constants.GET_CALL, Constants.FIXTURES_TEAM_ID + requestType.getId());
            } else if (requestType.getRequestType().equals(Constants.LIVE_TAG)) {
                mVolleyService.getDataVolley(Constants.GET_CALL, Constants.FIXTURES_LIVE);
            }
        });
        return data;
    }

    public LiveData<Stats> getStatistics(String league_team_id) {
        final MutableLiveData<Stats> data = new MutableLiveData<>();
        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Stats stats = JsonParser.parsStats(response);
                data.setValue(stats);
            }

            @Override
            public void notifyError(String requestType, VolleyError error) {

            }
        };
        mVolleyService = new VolleyService(mResultCallback, mContext);
        mVolleyService.addHeader(Constants.API_KEY_NAME, Constants.API_KEY_VALUE2);
        mVolleyService.addHeader(Constants.HEADER_NAME, Constants.HEADER_VALUE);
        mVolleyService.getDataVolley(Constants.GET_CALL, Constants.STATISTICS + league_team_id);
        return data;
    }

}
