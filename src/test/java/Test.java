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
		
		//Create two dummy players 
		Player p1 = new Player("Ricky", 80, 100); //WLR 0.80
		Player p2 = new Player("Krissy", 85, 100); //WLR 0.85
		Player p3 = new Player("Topkek", 85, 100); //WLR 0.85
		Player p4 = new Player("Toplel", 80, 100); // WLR 0.80
		Player p5 = new Player("Tophue", 85, 100); //WLR 0.85

		//Add the first three to the matchmaking system
//		mmi.enterMatchmaking(players.get(0));
//		mmi.enterMatchmaking(players.get(1));
//		mmi.enterMatchmaking(players.get(2));
		mmi.enterMatchmaking(p1);
		mmi.enterMatchmaking(p2);
		mmi.enterMatchmaking(p3);
		mmi.enterMatchmaking(p4);
		mmi.enterMatchmaking(p5);
		
		Queue<Player> queue = mmi.getQueue();

		//Show all players that are in the queue currently
//		Iterator<Player> itr = queue.iterator();
//		Player p;
//		while(itr.hasNext()){
//			p = itr.next();
//			System.out.print(p);
//			System.out.printf(" WLR: %1.2f\n", p.getWLR());
//		}
		
		//Attempt to match up 5 players; p1 -> p5 should be matched
		Match m = mmi.findMatch(5);
		HashSet<Player> team1 = (HashSet<Player>) m.getTeam1();
		
		System.out.println("***TEAM 1:***");
		for(Player player: team1){
			System.out.println(player);
		}
		
	}

}
