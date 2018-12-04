package com.pro.ahmed.football.data.models;


public class Fixture {

    private String fixtureId;
    private String eventTimestamp;
    private String eventDate;
    private String leagueId;
    private String round;
    private String homeTeamId;
    private String awayTeamId;
    private String homeTeam;
    private String awayTeam;
    private String homeTeamLogo;
    private String awayTeamLogo;
    private String status;
    private String statusShort;
    private String goalsHomeTeam;
    private String goalsAwayTeam;
    private String halftimeScore;
    private String finalScore;
    private String penalty;
    private String elapsed;
    private String firstHalfStart;
    private String secondHalfStart;

    public Fixture() {
    }

    public Fixture(String fixtureId, String eventTimestamp, String eventDate, String leagueId, String round, String homeTeamId, String awayTeamId, String homeTeam, String awayTeam, String status, String statusShort, String goalsHomeTeam, String goalsAwayTeam, String halftimeScore, String finalScore, String penalty, String elapsed, String firstHalfStart, String secondHalfStart) {
        this.fixtureId = fixtureId;
        this.eventTimestamp = eventTimestamp;
        this.eventDate = eventDate;
        this.leagueId = leagueId;
        this.round = round;
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.status = status;
        this.statusShort = statusShort;
        this.goalsHomeTeam = goalsHomeTeam;
        this.goalsAwayTeam = goalsAwayTeam;
        this.halftimeScore = halftimeScore;
        this.finalScore = finalScore;
        this.penalty = penalty;
        this.elapsed = elapsed;
        this.firstHalfStart = firstHalfStart;
        this.secondHalfStart = secondHalfStart;
    }

    public String getFixtureId() {
        return fixtureId;
    }

    public void setFixtureId(String fixtureId) {
        this.fixtureId = fixtureId;
    }

    public String getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(String eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(String homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public String getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(String awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getHomeTeamLogo() {
        return homeTeamLogo;
    }

    public void setHomeTeamLogo(String homeTeamLogo) {
        this.homeTeamLogo = homeTeamLogo;
    }

    public String getAwayTeamLogo() {
        return awayTeamLogo;
    }

    public void setAwayTeamLogo(String awayTeamLogo) {
        this.awayTeamLogo = awayTeamLogo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusShort() {
        return statusShort;
    }

    public void setStatusShort(String statusShort) {
        this.statusShort = statusShort;
    }

    public String getGoalsHomeTeam() {
        return goalsHomeTeam;
    }

    public void setGoalsHomeTeam(String goalsHomeTeam) {
        this.goalsHomeTeam = goalsHomeTeam;
    }

    public String getGoalsAwayTeam() {
        return goalsAwayTeam;
    }

    public void setGoalsAwayTeam(String goalsAwayTeam) {
        this.goalsAwayTeam = goalsAwayTeam;
    }

    public String getHalftimeScore() {
        return halftimeScore;
    }

    public void setHalftimeScore(String halftimeScore) {
        this.halftimeScore = halftimeScore;
    }

    public String getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(String finalScore) {
        this.finalScore = finalScore;
    }

    public String getPenalty() {
        return penalty;
    }

    public void setPenalty(String penalty) {
        this.penalty = penalty;
    }

    public String getElapsed() {
        return elapsed;
    }

    public void setElapsed(String elapsed) {
        this.elapsed = elapsed;
    }

    public String getFirstHalfStart() {
        return firstHalfStart;
    }

    public void setFirstHalfStart(String firstHalfStart) {
        this.firstHalfStart = firstHalfStart;
    }

    public String getSecondHalfStart() {
        return secondHalfStart;
    }

    public void setSecondHalfStart(String secondHalfStart) {
        this.secondHalfStart = secondHalfStart;
    }

    @Override
    public String toString() {
        return "Fixture{" +
                "fixtureId='" + fixtureId + '\'' +
                ", eventTimestamp='" + eventTimestamp + '\'' +
                ", eventDate='" + eventDate + '\'' +
                ", leagueId='" + leagueId + '\'' +
                ", round='" + round + '\'' +
                ", homeTeamId='" + homeTeamId + '\'' +
                ", awayTeamId='" + awayTeamId + '\'' +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", homeTeamLogo='" + homeTeamLogo + '\'' +
                ", awayTeamLogo='" + awayTeamLogo + '\'' +
                ", status='" + status + '\'' +
                ", statusShort='" + statusShort + '\'' +
                ", goalsHomeTeam='" + goalsHomeTeam + '\'' +
                ", goalsAwayTeam='" + goalsAwayTeam + '\'' +
                ", halftimeScore='" + halftimeScore + '\'' +
                ", finalScore='" + finalScore + '\'' +
                ", penalty='" + penalty + '\'' +
                ", elapsed='" + elapsed + '\'' +
                ", firstHalfStart='" + firstHalfStart + '\'' +
                ", secondHalfStart='" + secondHalfStart + '\'' +
                '}';
    }
}