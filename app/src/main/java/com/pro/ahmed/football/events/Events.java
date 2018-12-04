package com.pro.ahmed.football.events;

import com.pro.ahmed.football.data.models.League;

public class Events {
    private League mLeague;

    public Events(League league) {
        mLeague = league;
    }

    public League getLeague() {
        return mLeague;
    }
}
