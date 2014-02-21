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
		
		//Generate 400 random players and put them into matches.
//		Player p = null;
//		for(int i = 0; i < 1200; i++){
//			p = new Player();
//			mmi.enterMatchmaking(p);
//		}

		//Retrieve the current Queue.
		Queue<Player> queue = mmi.getQueue();

		//Attempt to match up 5v5 players until the queue is empty:

		//Get our first match as a demo, to visualize the algorithm.
		Match m = mmi.findMatch(5);

		//Create our list of matches:
		LinkedList<Match> matches = new LinkedList<Match>();

		//Add our first match to the matches list:
		matches.add(m);

		//Get our Iterator to use for the queue
		Iterator<Player> itr = null;

		//Create a Match object to store future matches.
		Match mat = null; 

		int matcount = 1;

		//While our queue isn't empty, keep finding matches
		while(!queue.isEmpty()){
			itr = queue.iterator();
			while(itr.hasNext()){
				mat = mmi.findMatch(5);
				matches.add(mat);
				if(mat != null){
					printMatchup(mat);
					matcount++;
				}
				else{
					System.out.println("Unable to match the current queue of players.");
				}
			}

			System.out.println("Match count: " + matcount + " out of " + (players.size() / 10));
		}

		//System.out.println("********** SAMPLE MATCHUP: **********");
		//printMatchup(m);

	}

	public static void printMatchup(Match m){
		//Team score = sum of total games played / sum of WLRs
		HashSet<Player> team1 = (HashSet<Player>) m.getTeam1();
		HashSet<Player> team2 = (HashSet<Player>) m.getTeam2();

		//Set up variables:
		double team1WLR = 0;
		double team1GamesPlayed = 0;
		double team1Score = 0;

		double team2WLR = 0;
		double team2GamesPlayed = 0;
		double team2Score = 0;

		//Print out Team 1 players and stats
		System.out.println("***TEAM 1:***");
		for(Player player: team1){
			System.out.print(player);
			System.out.printf(" Total: %d, WLR: %f\n",
					player.getTotalPlayed(), player.getWLR());
			team1WLR += player.getWLR();
			team1GamesPlayed += player.getTotalPlayed();
		}

		team1Score =  team1GamesPlayed / team1WLR;
		System.out.println();


		//Print out Team 2 players and stats
		System.out.println("***TEAM 2:***");
		for(Player player: team2){
			System.out.print(player);
			System.out.printf(" Total: %d, WLR: %f\n",
					player.getTotalPlayed(), player.getWLR()); 
			team2WLR += player.getWLR();
			team2GamesPlayed += player.getTotalPlayed();
		}

		team2Score =  team2GamesPlayed / team2WLR;

		double team1Odds = (team1Score / ( team1Score + team2Score ) * 100);
		double team2Odds = (team2Score / ( team1Score + team2Score ) * 100);

		System.out.printf("Team 1 Odds: %2.2f%%. Team 2 Odds: %2.2f%%.\n\n", team1Odds, team2Odds);
	}

}
