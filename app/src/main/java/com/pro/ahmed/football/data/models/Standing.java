
package com.pro.ahmed.football.data.models;


public class Standing {

    private String leagueId;
    private String teamId;
    private String teamName;
    private String play;
    private String win;
    private String draw;
    private String lose;
    private String goalsFor;
    private String goalsAgainst;
    private String goalsDiff;
    private String points;
    private String teamLogo;
    private String arrangement;

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public Standing withLeagueId(String leagueId) {
        this.leagueId = leagueId;
        return this;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public Standing withTeamId(String teamId) {
        this.teamId = teamId;
        return this;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Standing withTeamName(String teamName) {
        this.teamName = teamName;
        return this;
    }

    public String getPlay() {
        return play;
    }

    public void setPlay(String play) {
        this.play = play;
    }

    public Standing withPlay(String play) {
        this.play = play;
        return this;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public Standing withWin(String win) {
        this.win = win;
        return this;
    }

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }

    public Standing withDraw(String draw) {
        this.draw = draw;
        return this;
    }

    public String getLose() {
        return lose;
    }

    public void setLose(String lose) {
        this.lose = lose;
    }

    public Standing withLose(String lose) {
        this.lose = lose;
        return this;
    }

    public String getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(String goalsFor) {
        this.goalsFor = goalsFor;
    }

    public Standing withGoalsFor(String goalsFor) {
        this.goalsFor = goalsFor;
        return this;
    }

    public String getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(String goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public Standing withGoalsAgainst(String goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
        return this;
    }

    public String getGoalsDiff() {
        return goalsDiff;
    }

    public void setGoalsDiff(String goalsDiff) {
        this.goalsDiff = goalsDiff;
    }

    public Standing withGoalsDiff(String goalsDiff) {
        this.goalsDiff = goalsDiff;
        return this;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public Standing withPoints(String points) {
        this.points = points;
        return this;
    }

    public void setTeamLogo(String teamLogo) {
        this.teamLogo = teamLogo;
    }

    public String getTeamLogo() {
        return teamLogo;
    }

    public String getArrangement() {
        return arrangement;
    }

    public void setArrangement(String arrangement) {
        this.arrangement = arrangement;
    }

    @Override
    public String toString() {
        return "Standing{" +
                "leagueId='" + leagueId + '\'' +
                ", teamId='" + teamId + '\'' +
                ", teamName='" + teamName + '\'' +
                ", teamLogo='" + teamLogo + '\'' +
                ", play='" + play + '\'' +
                ", win='" + win + '\'' +
                ", draw='" + draw + '\'' +
                ", lose='" + lose + '\'' +
                ", goalsFor='" + goalsFor + '\'' +
                ", goalsAgainst='" + goalsAgainst + '\'' +
                ", goalsDiff='" + goalsDiff + '\'' +
                ", points='" + points + '\'' +
                ", arrangement='" + arrangement + '\'' +
                '}';
    }
}
