import static org.junit.Assert.*;

import java.util.List;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

import com.riotgames.interview.intern2014.matchmaking.MatchmakerImpl;
import com.riotgames.interview.intern2014.matchmaking.Player;
import com.riotgames.interview.intern2014.matchmaking.SampleData;


public class MatchmakingTestSuite {
	protected List<Player> players;
	protected MatchmakerImpl mmi;
	protected Queue<Player> queue;
	
	@Before
	public void setUp(){
		//This test should add all players from the DataSet to the Matchmaking queue.
		//Initialize our MatchmakerImplementation
		mmi = new MatchmakerImpl();
		
		//Get our sample list of players
		players = SampleData.getPlayers();

		//Enter all of our Players into the queue.
		for(Player p : players){
			mmi.enterMatchmaking(p);
		}
		
		//Retrieve the current Queue.
		queue = mmi.getQueue();
	}

	@Test
	public void testPlayersAddedToQueue() {
		assertTrue(queue.size() == players.size());
	}
	
	@Test
	public void testMatchmaking(){
		//Make sure that we actually get everyone matched, even if
		//conditions are not ideal.
		while(!queue.isEmpty()){
			assertNotNull(mmi.findMatch(5));
		}
	}

}
