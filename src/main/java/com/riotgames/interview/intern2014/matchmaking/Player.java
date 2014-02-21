package com.riotgames.interview.intern2014.matchmaking;

import java.util.Set;

/**
 * <p>
 * Representation of a player.
 * </p>
 * <p>
 * As indicated in the challenge description, feel free to augment the Player
 * class in any way that you feel will improve your final matchmaking solution.
 * <strong>Do NOT remove the name, wins, or losses fields.</strong> Also note
 * that if you make any of these changes, you are responsible for updating the
 * {@link SampleData} such that it provides a useful data set to exercise your
 * solution.
 * </p>
 * 
 * @author Riot Games, Riccardo Mutschlechner
 */
public class Player implements Comparable<Player>{

	private final String name;
	private final long wins;
	private final long losses;
	private final double score; //This is computed at runtime
	
	/*
	 * This is a constructor for a randomly generated player, used to create a random dataset.
	 */
	public Player(){
		this.name = "Randomly Generated";
		this.wins = (long)(Math.random() * 500);
		this.losses = (long)(Math.random() * 500);
		this.score = this.getWLR();
	}

	public Player(String name, long wins, long losses) {
		this.name = name;
		this.wins = Math.abs(wins);
		this.losses = Math.abs(losses);

		//Score = (Total Games Played / WLR)
		this.score =  this.getWLR();
	}

	public String getName() {
		return name;
	}

	public long getWins() {
		return wins;
	}

	public long getLosses() {
		return losses;
	}

	/**
	 * Computes the total number of games played.
	 * @return The total number of games this player has played.
	 */
	public long getTotalPlayed(){
		//If we have overflow, something bad happened.
		if(this.wins + this.losses >= 0)
			return this.wins + this.losses;
		else
			return Long.MAX_VALUE;
	}

	/**
	 * Computes the arbitrary "score" of a player, used to 
	 * weigh the player in this matchmaking system.
	 * @return The arbitrary computed score/weight of this player.
	 */
	public double getScore(){
		return this.score;
	}

	/**
	 * Computes the Win/Loss ratio of this player.
	 * @return Win/Loss Ratio as a double.
	 */
	public double getWLR(){
		//Make sure we aren't dividing by 0.
		if(this.getLosses() != 0)
			return (double)this.wins / (double)this.getTotalPlayed();
		else
			return (double)this.wins;
	}

	/**
	 * This uses two factors of compatibility to determine whether
	 * or not two players are potentially compatible with each other
	 * to be on a team. 
	 * 
	 * Factor 1: Win/Loss Ratio +/- a tolerance
	 * Factor 2: Total games played +/- another tolerance
	 * Factor 3: Making sure that the player score difference is within a third tolerance.
	 * 
	 * @param other	Another player to check compatibility with.
	 * @param ratioTolerance This is the maximum allowed tolerance of Win/Loss ratio between two players.
	 * @param totalGameTolerance This is the maximum allowed tolerance of total games played between two players.
	 * @param playerScoreTolerance This is the maximum allowed tolerance of player score between two players.
	 * @param numFactors This is the number of compatibility factors we wish to check against. 3 is the most strict but best quality, 0 is the worst quality but will match any players.
	 * @return Whether or not the two players are compatible.
	 */
	public boolean isCompatibleWith(Player other, double ratioTolerance, long totalGameTolerance, 
			long playerScoreTolerance, int numFactors){
		boolean factor1 = true;
		boolean factor2 = true;
		boolean factor3 = true;

		if(numFactors >= 1){
			//Check our first quality factor
			factor1 = qualityFactor1(this, other, ratioTolerance);
		}

		if(numFactors >= 2){
			factor2 = qualityFactor2(this, other, totalGameTolerance);
		}

		if(numFactors >= 3){
			factor3 = qualityFactor3(this, other, playerScoreTolerance);
		}

		return factor1 && factor2 && factor3;
	}

	/**
	 * This method checks compatibility of this player against an entire team, using the 
	 * individual comparison method above.
	 * 
	 * Factor 1: Win/Loss Ratio +/- a tolerance
	 * Factor 2: Total games played +/- another tolerance
	 * Factor 3: Making sure that the player score difference is within a third tolerance.
	 * 
	 * @param team A team to check compatibility with.
	 * @param ratioTolerance This is the maximum allowed tolerance of Win/Loss ratio between two players.
	 * @param totalGameTolerance This is the maximum allowed tolerance of total games played between two players.
	 * @param playerScoreTolerance This is the maximum allowed tolerance of player score between two players.
	 * @param numFactors This is the number of compatibility factors we wish to check against. 3 is the most strict but best quality, 0 is the worst quality but will match any players.
	 * @return Whether or not the two players are compatible.
	 */

	public boolean isCompatibleWithTeam(Set<Player> team, double ratioTolerance, long totalGameTolerance,
			long playerScoreTolerance, int numFactors){
		boolean isCompatible = true;
		for(Player p : team){
			if(!this.isCompatibleWith(p, ratioTolerance, totalGameTolerance, playerScoreTolerance, numFactors)){
				isCompatible = false;
			}
		}

		return isCompatible;
	}

	/**
	 * Factor 1 of the matchmaking system. 
	 * Checks Win/Loss Ratio +/- a tolerance
	 * 
	 * @param p1 The first player in the comparison.
	 * @param p2 The second player in the comparison
	 * @param ratioTolerance The tolerance of Win/Loss ratio allowed.
	 */
	public boolean qualityFactor1(Player p1, Player p2, double ratioTolerance){
		//First factor:
		// this.WLR +/- tolerance of the other WLR
		if(Math.abs(p1.getWLR() - p2.getWLR()) <= ratioTolerance){ 
			return true;
		}

		else{
			return false;
		}
	}

	/**
	 * Factor 2 of the matchmaking system. 
	 * Total games played +/- another tolerance
	 * 
	 * @param p1 The first player in the comparison.
	 * @param p2 The second player in the comparison
	 * @param totalGameTolerance The tolerance of Total Games played allowed.
	 */
	public boolean qualityFactor2(Player p1, Player p2, double totalGameTolerance){
		//Second factor:
		//If total games played are within totalGameTolerance of each other
		if( Math.abs((p1.getTotalPlayed()) 
				- (p2.getTotalPlayed() )) <= totalGameTolerance ){
			return true;
		}

		else{
			return false;
		}
	}
	
	/**
	 * Factor 3 of the matchmaking system. 
	 * Total games played +/- another tolerance
	 * 
	 * @param p1 The first player in the comparison.
	 * @param p2 The second player in the comparison
	 * @param playerScoreTolerance The tolerance of Player Score difference allowed.
	 */
	public boolean qualityFactor3(Player p1, Player p2, double playerScoreTolerance){
		//Third factor:
		//If the difference in the players' scores is too great
		return Math.abs(p1.score - p2.score) <= playerScoreTolerance;
	}
	

	/*
	 * Allows us to arbitrarily compare two players based on Win/Loss Ratio (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Player other){
		if(this.getWLR() < other.getWLR()){
			return -1;
		}

		else if(this.getWLR() > other.getWLR()){
			return 1;
		}

		else{
			return 0;
		}
	}


	/*
	 * Allows us to easily visualize a Player by printing it directly
	 * to stdout. (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return this.name + "   Wins: " + this.wins + " Losses: " + this.losses + " Score: " + this.score;
	}

}
