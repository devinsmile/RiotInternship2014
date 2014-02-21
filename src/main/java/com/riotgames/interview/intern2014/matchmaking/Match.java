package com.riotgames.interview.intern2014.matchmaking;

import java.util.Set;

/**
 * This class contains a Match. 
 * A match has two Set<Player> of teams: team1, and team 2. 
 * 
 * @author Riot Games, Riccardo Mutschlechner
 */
public class Match {

    private final Set<Player> team1;
    private final Set<Player> team2;

    public Match(Set<Player> team1, Set<Player> team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    public Set<Player> getTeam1() {
        return team1;
    }

    public Set<Player> getTeam2() {
        return team2;
    }

}
