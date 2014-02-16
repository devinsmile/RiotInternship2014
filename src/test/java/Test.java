import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

import com.riotgames.interview.intern2014.matchmaking.*;

public class Test {
	public static void main(String[] args){
		//Get our sample list of players
		List<Player> players = SampleData.getPlayers();
		
		//Initialize our MatchmakerImplementation
		MatchmakerImpl mmi = new MatchmakerImpl();

		//Enter all of our Players into the queue.
		for(Player p : players){
			mmi.enterMatchmaking(p);
		}
	
		//Retrieve the current Queue.
		Queue<Player> queue = mmi.getQueue();
		
		//Attempt to match up 5v5 players until the queue is empty:
		
		//Get our first match as a demo, to visualize the algorithm.
		Match m = mmi.findMatch(5);
		HashSet<Player> team1 = (HashSet<Player>) m.getTeam1();
		HashSet<Player> team2 = (HashSet<Player>) m.getTeam2();
		
		//Create our list of matches:
		LinkedList<Match> matches = new LinkedList<Match>();
		
		//Add our first match to the matches list:
		matches.add(m);
		
		
		Iterator<Player> itr = null;
		
		while(!queue.isEmpty()){
			itr = queue.iterator();
			while(itr.hasNext()){
				matches.add(mmi.findMatch(5));
				System.out.println("Match found!");
			}
		}
		
		//Team score = sum of total games played / sum of WLRs
		double team1WLR = 0;
		double team1GamesPlayed = 0;
		
		double team2WLR = 0;
		double team2GamesPlayed = 0;
		
		System.out.println("********** SAMPLE MATCHUP: **********");
		System.out.println("***TEAM 1:***");
		for(Player player: team1){
			System.out.print(player);
			System.out.printf(" Total: %d, WLR: %f\n",
					player.getWins() + player.getLosses(), player.getWLR());
			 team1WLR += player.getWLR();
			 team1GamesPlayed += player.getWins() + player.getLosses();
		}
		
		System.out.println("Team 1 'score': " + team1GamesPlayed / team1WLR);
		System.out.println();
		
		System.out.println("***TEAM 2:***");
		for(Player player: team2){
			System.out.print(player);
			System.out.printf(" Total: %d, WLR: %f\n",
					player.getWins() + player.getLosses(), player.getWLR()); 
			 team2WLR += player.getWLR();
			 team2GamesPlayed += player.getWins() + player.getLosses();
		}
		
		System.out.println("Team 2 'score': " + team2GamesPlayed / team2WLR);
	}

}
