package com.riotgames.interview.intern2014.matchmaking;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * The matchmaking implementation that you will write.
 */
public class MatchmakerImpl implements Matchmaker {

	private Queue<Player> matchmakingQueue;

	public MatchmakerImpl(){
		this.matchmakingQueue = new LinkedList<Player>();
	}

	public Match findMatch(int playersPerTeam) {
		//Set up our two teams as HashSets
		Set<Player> team1 = new HashSet<Player>();
		Set<Player> team2 = new HashSet<Player>();
		
		//Set up our variables
		int playersInTeam1 = 0; //Our team1 counter
		int playersInTeam2 = 0; //Our team2 counter
		
		double tolerance = 0.05; //our tolerance for matchmaking
		
		Iterator<Player> itr = this.matchmakingQueue.iterator();
		Player p1 = null, p2 = null;
		
		while(itr.hasNext()){
			p1 = itr.next();
			
			if(itr.hasNext())
				p2 = itr.next();
			
			if(p1 != null && p2 != null){
				if(p1.isCompatibleWith(p2, tolerance)){
					team1.add(p1); 
					team1.add(p2);
				}
			}
		}
		
		return new Match(team1, team2);
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
