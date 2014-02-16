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
		
		//Create ten dummy players - five for each team
		//Team 1:
//		Player p1 = new Player("Ricky", 80, 100); //WLR 0.80
//		Player p2 = new Player("Krissy", 85, 100); //WLR 0.85
//		Player p3 = new Player("Topkek", 85, 100); //WLR 0.85
//		Player p4 = new Player("Toplel", 80, 100); // WLR 0.80
//		Player p5 = new Player("Tophue", 85, 100); //WLR 0.80
//		
//		//Team 2:
//		Player p6 = new Player("Troll1", 50, 1000); //WLR 0.05
//		Player p7 = new Player("Troll2", 50, 1000); //WLR 0.05
//		Player p8 = new Player("Troll3", 50, 1000); //WLR 0.05
//		Player p9 = new Player("Troll4", 50, 1000); //WLR 0.05
//		Player p10 = new Player("Troll5", 50, 1000); //WLR 0.05

		//Add all players to matchmaking queue
		//Team 1:
//		mmi.enterMatchmaking(p1);
//		mmi.enterMatchmaking(p2);
//		mmi.enterMatchmaking(p3);
//		mmi.enterMatchmaking(p4);
//		mmi.enterMatchmaking(p5);
//		
//		//Team 2:
//		mmi.enterMatchmaking(p6);
//		mmi.enterMatchmaking(p7);
//		mmi.enterMatchmaking(p8);
//		mmi.enterMatchmaking(p9);
//		mmi.enterMatchmaking(p10);
		for(Player p : players){
			mmi.enterMatchmaking(p);
		}
		
//		Queue<Player> queue = mmi.getQueue();

		//Show all players that are in the queue currently
//		Iterator<Player> itr = queue.iterator();
//		Player p;
//		while(itr.hasNext()){
//			p = itr.next();
//			System.out.print(p);
//			System.out.printf(" WLR: %1.2f\n", p.getWLR());
//		}
		
		//Attempt to match up 5v5 players
		Match m = mmi.findMatch(5);
		HashSet<Player> team1 = (HashSet<Player>) m.getTeam1();
		HashSet<Player> team2 = (HashSet<Player>) m.getTeam2();
		
		
		//TODO: Team "Scores" to see if two teams are compatible
		//Team score = sum of total games played / sum of WLRs
		double team1WLR = 0;
		double team1GamesPlayed = 0;
		
		double team2WLR = 0;
		double team2GamesPlayed = 0;
		
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
