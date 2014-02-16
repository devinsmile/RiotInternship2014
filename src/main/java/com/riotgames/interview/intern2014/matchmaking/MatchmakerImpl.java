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
		double tolerance = 0.05; //our initial/current tolerance for matchmaking
		int totalGameTolerance = 100; //initial/current total game tolerance
		
		int attempts = 1000; //If we try 1000 times and cannot find a team, quit
		
		/* Get players for Teams */ 
		Iterator<Player> itr = this.matchmakingQueue.iterator();
		Player p1 = null;
		while(itr.hasNext() && attempts > 0){
			p1 = itr.next();
			if(p1.isCompatibleWithTeam(team1, tolerance, totalGameTolerance)
					&& team1.size() < 5){
				team1.add(p1); //Add player to team1
				itr.remove(); //Remove player from queue.
			}

			else if(p1.isCompatibleWithTeam(team2, tolerance, totalGameTolerance)
					&& team2.size() < playersPerTeam){
				team2.add(p1); //Add player to team2
				itr.remove(); //Remove player from queue.
			}
			
			if(team1.size() == playersPerTeam && team2.size() == playersPerTeam){
				break; //Teams found! Can stop searching.
			}
			
			attempts--; //We should only try 1000 times before we give up.
			
			//If we still haven't found a match, yet reached the end of
			// the queue, loosen our tolerance up a bit!
			if(!itr.hasNext() && this.matchmakingQueue.size() > 0){
				itr = this.matchmakingQueue.iterator();
				tolerance += 0.20;
				totalGameTolerance += 200;
			}
		}

		if(attempts != 0 || (team1.size() == playersPerTeam && team2.size() == playersPerTeam))
			return new Match(team1, team2);
		else
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
