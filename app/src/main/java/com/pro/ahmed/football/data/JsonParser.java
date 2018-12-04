package com.pro.ahmed.football.data;

import android.util.Log;

import com.pro.ahmed.football.data.models.Draws;
import com.pro.ahmed.football.data.models.Fixture;
import com.pro.ahmed.football.data.models.GoalsAgainst;
import com.pro.ahmed.football.data.models.GoalsFor;
import com.pro.ahmed.football.data.models.League;
import com.pro.ahmed.football.data.models.Loses;
import com.pro.ahmed.football.data.models.MatchesPlayed;
import com.pro.ahmed.football.data.models.Standing;
import com.pro.ahmed.football.data.models.Stats;
import com.pro.ahmed.football.data.models.Team;
import com.pro.ahmed.football.data.models.Wins;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;


public class JsonParser {

    static public ArrayList<League> parseLeagues(JSONObject jsonObject) {
        ArrayList<League> leagues = new ArrayList<>();

        try {
            JSONObject api = jsonObject.getJSONObject("api");
            Log.v("jsonObject1", jsonObject.toString());

            String jsonString = api.toString();//your json string here

            JSONObject L_Object = new JSONObject(jsonString).getJSONObject("leagues");
            Iterator<String> keys = L_Object.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                Log.v("**********", "**********");
                Log.v("category key", key);
                JSONObject innerJObject = L_Object.getJSONObject(key);

                League league = new League();
                String league_name = innerJObject.getString("name");
                String league_id = innerJObject.getString("league_id");
                String league_logo = innerJObject.getString("logo");
                String countryName = innerJObject.getString("country");
                String season = innerJObject.getString("season");
                league.setLeagueId(league_id);
                league.setName(league_name);
                league.setLogo(league_logo);
                league.setCountry(countryName);
                league.setSeason(season);
                leagues.add(league);

                Collections.sort(leagues, new Comparator<League>() {
                    @Override
                    public int compare(League o1, League o2) {
                        int league_id_1 = Integer.parseInt(o1.getLeagueId());
                        int league_id_2 = Integer.parseInt(o2.getLeagueId());

                        return league_id_1 < league_id_2 ? -1 : 1;
                    }
                });
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return leagues;
    }

    static public ArrayList<Standing> parseStandings(JSONObject jsonObject) {
        ArrayList<Standing> standings = new ArrayList<>();

        try {
            JSONObject api = jsonObject.getJSONObject("api");
            Log.v("jsonObject1", jsonObject.toString());

            String jsonString = api.toString();//your json string here

            JSONObject S_Object = new JSONObject(jsonString).getJSONObject("standings");
            Iterator<String> keys = S_Object.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                Log.v("**********", "**********");
                Log.v("category key", key);
                JSONObject innerJObject = S_Object.getJSONObject(key);

                Standing standing = new Standing();
                String league_id = innerJObject.getString("league_id");
                String team_id = innerJObject.getString("team_id");
                String teamName = innerJObject.getString("teamName");
                String win = innerJObject.getString("win");
                String draw = innerJObject.getString("draw");
                String lose = innerJObject.getString("lose");
                String goalsFor = innerJObject.getString("goalsFor");
                String goalsAgainst = innerJObject.getString("goalsAgainst");
                String goalsDiff = innerJObject.getString("goalsDiff");
                String points = innerJObject.getString("points");
                String play = innerJObject.getString("play");

                standing.setLeagueId(league_id);
                standing.setTeamId(team_id);
                standing.setTeamName(teamName);
                standing.setPlay(play);
                standing.setWin(win);
                standing.setDraw(draw);
                standing.setLose(lose);
                standing.setGoalsFor(goalsFor);
                standing.setGoalsAgainst(goalsAgainst);
                standing.setGoalsDiff(goalsDiff);
                standing.setPoints(points);

                standings.add(standing);

                Collections.sort(standings, new Comparator<Standing>() {
                    @Override
                    public int compare(Standing o1, Standing o2) {
                        int point_1 = Integer.parseInt(o1.getPoints());
                        int point_2 = Integer.parseInt(o2.getPoints());

                        return point_1 > point_2 ? -1 : 1;
                    }
                });
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < standings.size(); i++) {
            standings.get(i).setArrangement(String.valueOf(i + 1));
        }
        return standings;
    }

    static public Team parsTeam(JSONObject jsonObject) {
        Team team = new Team();

        try {
            JSONObject api = jsonObject.getJSONObject("api");
            String jsonString = api.toString();//your json string here

            JSONObject T_Object = new JSONObject(jsonString).getJSONObject("teams");
            Iterator<String> keys = T_Object.keys();
            String key = keys.next();
            JSONObject innerObj = T_Object.getJSONObject(key);
            String team_id = innerObj.getString("team_id");
            String teamName = innerObj.getString("name");
            String teamLogo = innerObj.getString("logo");
            team.setTeamId(team_id);
            team.setName(teamName);
            team.setLogo(teamLogo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return team;
    }

    static public ArrayList<Fixture> parsFixtures(JSONObject jsonObject) {

        ArrayList<Fixture> fixtures = new ArrayList<>();

        try {
            JSONObject api = jsonObject.getJSONObject("api");
            Log.v("jsonObjectFixture", jsonObject.toString());

            String jsonString = api.toString();//your json string here

            JSONObject F_Object = new JSONObject(jsonString).getJSONObject("fixtures");
            Iterator<String> keys = F_Object.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                JSONObject innerJObject = F_Object.getJSONObject(key);

                String fixture_id = innerJObject.getString("fixture_id");
                String event_timestamp = innerJObject.getString("event_timestamp");
                String event_date = innerJObject.getString("event_date");
                String league_id = innerJObject.getString("league_id");
                String round = innerJObject.getString("round");
                String homeTeam_id = innerJObject.getString("homeTeam_id");
                String awayTeam_id = innerJObject.getString("awayTeam_id");
                String homeTeam = innerJObject.getString("homeTeam");
                String awayTeam = innerJObject.getString("awayTeam");
                String status = innerJObject.getString("status");
                String statusShort = innerJObject.getString("statusShort");
                String goalsHomeTeam = innerJObject.getString("goalsHomeTeam");
                String goalsAwayTeam = innerJObject.getString("goalsAwayTeam");
                String halftime_score = innerJObject.getString("halftime_score");
                String final_score = innerJObject.getString("final_score");
                String penalty = innerJObject.getString("penalty");
                String elapsed = innerJObject.getString("elapsed");
                String firstHalfStart = innerJObject.getString("firstHalfStart");
                String secondHalfStart = innerJObject.getString("secondHalfStart");

                Fixture fixture = new Fixture(fixture_id, event_timestamp, event_date, league_id, round, homeTeam_id,
                        awayTeam_id, homeTeam, awayTeam, status, statusShort, goalsHomeTeam, goalsAwayTeam, halftime_score,
                        final_score, penalty, elapsed, firstHalfStart, secondHalfStart);

                fixtures.add(fixture);

                Collections.sort(fixtures, new Comparator<Fixture>() {
                    @Override
                    public int compare(Fixture o1, Fixture o2) {
                        int fixture_id_1 = Integer.parseInt(o1.getFixtureId());
                        int fixture_id_2 = Integer.parseInt(o2.getFixtureId());

                        return fixture_id_1 < fixture_id_2 ? -1 : 1;
                    }
                });
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return fixtures;
    }

    static public Stats parsStats(JSONObject jsonObject) {
        Stats stats = null;
        String home, away, total;
        try {
            JSONObject statsOb = jsonObject.getJSONObject("api").getJSONObject("stats");
            JSONObject matchesPlayedOb = statsOb.getJSONObject("matchs").getJSONObject("matchsPlayed");
            JSONObject winsOb = statsOb.getJSONObject("matchs").getJSONObject("wins");
            JSONObject drawsOb = statsOb.getJSONObject("matchs").getJSONObject("draws");
            JSONObject losesOb = statsOb.getJSONObject("matchs").getJSONObject("loses");
            JSONObject goalsForOb = statsOb.getJSONObject("goals").getJSONObject("goalsFor");
            JSONObject goalsAgainstOb = statsOb.getJSONObject("goals").getJSONObject("goalsAgainst");

            home = matchesPlayedOb.getString("home");
            away = matchesPlayedOb.getString("away");
            total = matchesPlayedOb.getString("total");
            MatchesPlayed matchesPlayed = new MatchesPlayed(home, away, total);

            home = winsOb.getString("home");
            away = winsOb.getString("away");
            total = winsOb.getString("total");
            Wins wins = new Wins(home, away, total);

            home = drawsOb.getString("home");
            away = drawsOb.getString("away");
            total = drawsOb.getString("total");
            Draws draws = new Draws(home, away, total);

            home = losesOb.getString("home");
            away = losesOb.getString("away");
            total = losesOb.getString("total");
            Loses loses = new Loses(home, away, total);

            home = goalsForOb.getString("home");
            away = goalsForOb.getString("away");
            total = goalsForOb.getString("total");
            GoalsFor goalsFor = new GoalsFor(home, away, total);

            home = goalsAgainstOb.getString("home");
            away = goalsAgainstOb.getString("away");
            total = goalsAgainstOb.getString("total");
            GoalsAgainst goalsAgainst = new GoalsAgainst(home, away, total);
            stats = new Stats(matchesPlayed, wins, draws, loses, goalsFor, goalsAgainst);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return stats;
    }
}
