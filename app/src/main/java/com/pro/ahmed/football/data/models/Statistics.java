package com.pro.ahmed.football.data.models;


public abstract class Statistics {
    private String home;
    private String away;
    private String total;

    public Statistics(String home, String away, String total) {
        this.home = home;
        this.away = away;
        this.total = total;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getAway() {
        return away;
    }

    public void setAway(String away) {
        this.away = away;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "home='" + home + '\'' +
                ", away='" + away + '\'' +
                ", total='" + total + '\'' +
                '}';
    }
}
