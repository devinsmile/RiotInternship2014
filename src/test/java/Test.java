import java.util.List;

import com.riotgames.interview.intern2014.matchmaking.*;

public class Test {
	public static void main(String[] args){
		List<Player> players = SampleData.getPlayers();
		for(Player p : players){
			System.out.println(p);
		}
	}
	
}
