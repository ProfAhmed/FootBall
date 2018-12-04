package com.pro.ahmed.football.data.models;

public class Stats {
    private MatchesPlayed matchesPlayed;
    private Wins wins;
    private Draws draws;
    private Loses loses;
    private GoalsFor goalsFor;
    private GoalsAgainst goalsAgainst;

    public Stats(MatchesPlayed matchesPlayed, Wins wins, Draws draws, Loses loses, GoalsFor goalsFor, GoalsAgainst goalsAgainst) {
        this.matchesPlayed = matchesPlayed;
        this.wins = wins;
        this.draws = draws;
        this.loses = loses;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
    }

    public MatchesPlayed getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(MatchesPlayed matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public Wins getWins() {
        return wins;
    }

    public void setWins(Wins wins) {
        this.wins = wins;
    }

    public Draws getDraws() {
        return draws;
    }

    public void setDraws(Draws draws) {
        this.draws = draws;
    }

    public Loses getLoses() {
        return loses;
    }

    public void setLoses(Loses loses) {
        this.loses = loses;
    }

    public GoalsFor getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(GoalsFor goalsFor) {
        this.goalsFor = goalsFor;
    }

    public GoalsAgainst getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(GoalsAgainst goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "matchesPlayed=" + matchesPlayed +
                ", wins=" + wins +
                ", draws=" + draws +
                ", loses=" + loses +
                ", goalsFor=" + goalsFor +
                ", goalsAgainst=" + goalsAgainst +
                '}';
    }
}
