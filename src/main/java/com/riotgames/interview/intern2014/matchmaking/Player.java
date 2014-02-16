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
 */
public class Player implements Comparable<Player>{

    private final String name;
    private final long wins;
    private final long losses;
    
    public Player(String name, long wins, long losses) {
        this.name = name;
        this.wins = wins;
        this.losses = losses;
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
    
    /*
     * Returns the Win/Loss ratio (Wins / Losses).
     * 
     */
    public double getWLR(){
    	return (double)wins / (double)losses;
    }
    
    /*
     * This uses two factors of compatibility to determine whether
     * or not two players are potentially compatible with each other
     * to be on a team. 
     * 
     * Factor 1: Win/Loss Ratio +/- a tolerance
     * Factor 2: Total games played +/- another tolerance
     */
    public boolean isCompatibleWith(Player other, double tolerance, int totalGameTolerance){
    	boolean factor1 = false;
    	boolean factor2 = false;
    	
    	//First factor:
    	// this.WLR +/- tolerance of the other WLR
    	if(other.getWLR() <= this.getWLR() + tolerance 
    			&& other.getWLR() >= this.getWLR() - tolerance){ 
    		factor1 = true;
    	}
    	
    	//Second factor:
    	//If total games played are within totalGameTolerance of each other
    	if( Math.abs((this.getWins() + this.getLosses()) 
    			- (other.getWins() + other.getLosses() )) < totalGameTolerance ){
    		factor2 = true;
    	}
    	
    	
    	return factor1 && factor2;
    }
    
    /*
     * This function determines whether or not the current player is
     * compatible with the team passed as an argument, using the
     * tolerances specified as arguments as well. 
     */
    public boolean isCompatibleWithTeam(Set<Player> team, double tolerance, int totalGameTolerance){
    	boolean isCompatible = true;
    	for(Player p : team){
    		if(!this.isCompatibleWith(p, tolerance, totalGameTolerance)){
    			isCompatible = false;
    		}
    	}
    	
    	return isCompatible;
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
    	return this.name + "   Wins: " + this.wins + " Losses: " + this.losses;
    }

}
