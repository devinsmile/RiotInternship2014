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
		Player p1 = new Player("Ricky", 75, 100); //WLR 0.75
		Player p2 = new Player("Krissy", 85, 100); //WLR 0.85

		//Add the first three to the matchmaking system
//		mmi.enterMatchmaking(players.get(0));
//		mmi.enterMatchmaking(players.get(1));
//		mmi.enterMatchmaking(players.get(2));
		mmi.enterMatchmaking(p1);
		mmi.enterMatchmaking(p2);
		
		
		//See if p1's WLR is within 10% (inclusive) of p2's WLR,
		//if so, they are compatible. If not, they are not.
		System.out.println(p1.isCompatibleWith(p2, 0.10));
		
		
		Queue<Player> queue = mmi.getQueue();

		//Show all players that are in the queue currently
		Iterator<Player> itr = queue.iterator();
		Player p;
		while(itr.hasNext()){
			p = itr.next();
			System.out.print(p);
			System.out.println(" WLR: " + p.getWLR());
		}
	}

}
