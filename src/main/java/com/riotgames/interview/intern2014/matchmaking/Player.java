package com.riotgames.interview.intern2014.matchmaking;

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
     * This 
     */
    public boolean isCompatibleWith(Player other, double tolerance){
    	// this.WLR +/- tolerance of the other WLR
    	if(other.getWLR() <= this.getWLR() + tolerance 
    			&& other.getWLR() >= this.getWLR() - tolerance){ 
    		return true;
    	}
    	
    	return false;
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
    
    public String toString(){
    	return this.name + "   Wins: " + this.wins + " Losses: " + this.losses;
    }

}
