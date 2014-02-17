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
		double tolerance = 0.00; //our initial/current tolerance for matchmaking
		int totalGameTolerance = 50; //initial/current total game tolerance
		int playerScoreTolerance = 100; //initial/current player score tolerance
		
		int attempts = 500; //If we try 1000 times and cannot find a team, quit

		/* Get players for Teams */ 
		Iterator<Player> itr = this.matchmakingQueue.iterator();
		Player p1 = null;
		while(itr.hasNext() && attempts > 0){
			p1 = itr.next();

			//If p1 is a good match for both teams:
			if(p1.isCompatibleWithTeam(team1, tolerance, totalGameTolerance, playerScoreTolerance)
					&& p1.isCompatibleWithTeam(team2, tolerance, totalGameTolerance, playerScoreTolerance)){

				//If team 1 isn't full yet,
				if( team1.size() < playersPerTeam ){
					//Add p1 to team 1!
					team1.add(p1);

					//Remove p1 from the Matchmaking queue.
					itr.remove();
				}

				//If team 2 isn't full yet,
				else if( team2.size() < playersPerTeam){
					//Add p1 to team 2!
					team2.add(p1);

					//Remove p1 from matchmaking queue.
					itr.remove();
				}
			}

			//If our teams are both full:
			if(team1.size() == playersPerTeam && team2.size() == playersPerTeam){
				//Teams found! Can stop searching.
				return new Match(team1, team2);
			}

			/*
			 * If we still haven't found our teams yet, but we've
			 * reached the end of the queue, start over from the 
			 * beginning but loosen the tolerances ever-so slightly.
			 */
			if(!itr.hasNext() && this.matchmakingQueue.size() > 0){
				itr = this.matchmakingQueue.iterator();
				tolerance += 0.01; //Bump up the WLR tolerance
				totalGameTolerance += 10; //Bump up the total games played tolerance
				playerScoreTolerance += 10; //Bump up the playerScore tolerance.
				attempts--; //We should only try 100 times before we give up.
			}
		}

		//We attempted to find a team too many times, and failed.
		//Return null, signifying there is no good team match.
		return null;
	}

	public void enterMatchmaking(Player player) {
		//Add the player into the Matchmaking Queue
		this.matchmakingQueue.offer(player);
	}

	public Queue<Player> getQueue(){
		return this.matchmakingQueue;
	}

}
