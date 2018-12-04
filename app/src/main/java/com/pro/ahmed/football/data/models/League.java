package com.pro.ahmed.football.data.models;

public class League {


    private String leagueId;

    private String name;

    private String country;

    private String season;

    private String seasonStart;

    private String seasonEnd;

    private String logo;

    private String error = null;

    private Boolean standings;


    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getSeasonStart() {
        return seasonStart;
    }

    public void setSeasonStart(String seasonStart) {
        this.seasonStart = seasonStart;
    }

    public String getSeasonEnd() {
        return seasonEnd;
    }

    public void setSeasonEnd(String seasonEnd) {
        this.seasonEnd = seasonEnd;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Boolean getStandings() {
        return standings;
    }

    public void setStandings(Boolean standings) {
        this.standings = standings;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "League{" +
                "leagueId='" + leagueId + '\'' +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}