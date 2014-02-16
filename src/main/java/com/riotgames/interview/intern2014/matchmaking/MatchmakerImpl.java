package com.riotgames.interview.intern2014.matchmaking;

import java.util.LinkedList;
import java.util.Queue;

/**
 * The matchmaking implementation that you will write.
 */
public class MatchmakerImpl implements Matchmaker {
	
	private Queue<Player> matchmakingQueue;
	
	public MatchmakerImpl(){
		this.matchmakingQueue = new LinkedList<Player>();
	}

    public Match findMatch(int playersPerTeam) {
        // TODO be sure to implement this :D
        return null;
    }

    public void enterMatchmaking(Player player) {
        // TODO fully finish this
    	//Add the player into the Matchmaking Queue
    	this.matchmakingQueue.offer(player);
    }
    
    public Queue<Player> getQueue(){
    	return this.matchmakingQueue;
    }

}
