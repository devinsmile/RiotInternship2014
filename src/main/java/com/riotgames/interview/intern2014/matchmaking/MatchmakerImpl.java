package com.riotgames.interview.intern2014.matchmaking;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * This is my Matchmaking system.
 * This system works as follows:
 * 1. Attempt to find a team of people with relatively close stats.
 * 2. As the attempts fail, the tolerances get less strict. 
 * 3. If the players are still waiting in queue for a long time and unmatched,
 * 		the players will be matched in somewhat unfair games, as all players do get matched eventually.
 * 4. If the match is somehow not possible to set up (uneven number of players left in queue, etc), this will return null.
 * 
 * @author Riot Games, Riccardo Mutschlechner
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
		long totalGameTolerance = 50; //initial/current total game tolerance
		long playerScoreTolerance = 100; //initial/current player score tolerance
		int numFactors = 3; //The number of quality factors for matchmaking.
		
		int attempts = 100; //If we try 100 times and cannot find a team, quit

		/* Get players for Teams */ 
		Iterator<Player> itr = this.matchmakingQueue.iterator();
		Player p1 = null;
		while(itr.hasNext() && attempts > 0){
			p1 = itr.next();

			//If p1 is a good match for both teams:
			if(p1.isCompatibleWithTeam(team1, tolerance, totalGameTolerance, playerScoreTolerance, numFactors)
					&& p1.isCompatibleWithTeam(team2, tolerance, totalGameTolerance, playerScoreTolerance, numFactors)){

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
				playerScoreTolerance += 0.01; //Bump up the playerScore tolerance.
				attempts--; //We should only try 1000 times before we give up.
			}
			
			//If we're in the last few attempts to make a game, we should
			//Reduce the number of quality factors we check, to make sure that
			//Every player gets into a game, regardless of fairness at this point.
			if(attempts < 4){
				if(numFactors > 0) 
					numFactors--;
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
