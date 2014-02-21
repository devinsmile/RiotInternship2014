import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.riotgames.interview.intern2014.matchmaking.Player;

public class PlayerTest {

	@Before
	public void setUp(){
		
	}
	
	@Test
	public void testCreatePlayer() {
		//Set some test variables.
		String name = "Test";
		long wins = 100;
		long losses = 150;
		
		//Create + initialize a Player with hard-coded values.
		Player p = new Player(name, wins, losses);
		
		//Make sure that our player actually got these values.
		assertEquals(name, p.getName());
		assertEquals(wins, p.getWins());
		assertEquals(losses, p.getLosses());
	}
	
	@Test
	public void testWLRCalculation(){
		//Create + Initialize a Player with hard-coded values.
		String name = "Test";
		long wins = 75;
		long losses = 75;
		double expectedWLR = 0.5;
		Player p = new Player(name, wins, losses);
		
		//Make sure that our WLR is approximately 0.5 (doubles are not perfect).
		assertEquals(expectedWLR, p.getWLR(), 0.01);
	}
	
	@Test
	public void testPlayerCompatibility(){
		//Hard-coded values for players 1 and 2
		String name1 = "Player1";
		long wins1 = 100;
		long losses1 = 150;
		Player p1 = new Player(name1, wins1, losses1);
		
		String name2 = "Player2";
		long wins2 = 100;
		long losses2 = 150;
		Player p2 = new Player(name2, wins2, losses2);
		
		//Checks if the players are compatible with each other.
		boolean isCompatible = 
				p1.isCompatibleWith(p2, 
						0,
						0,
						0,
						3);
		
		assertTrue(isCompatible);
		
	}
	
	@Test
	public void testTeamCompatibility(){
		//Create an array of 10 Players.
		Player[] players = new Player[5];
		
		//Add 5 players who we know are compatible to the array.
		players[0] = new Player("Test1", 100, 150);
		players[1] = new Player("Test2", 100, 150);
		players[2] = new Player("Test3", 100, 150);
		players[3] = new Player("Test4", 100, 150);
		players[4] = new Player("Test5", 100, 150);

		//Create our team hashset and add the players to it.
		Set<Player> team = new HashSet<Player>();
		team.add(players[0]);
		team.add(players[1]);
		team.add(players[2]);
		team.add(players[3]);
		team.add(players[4]);
		
		//Create a new player that we know is compatible.
		Player compatible = new Player("Compatible", 100, 150);
		
		//Make sure our new player is compatible with the team.
		boolean isCompatible = compatible.isCompatibleWithTeam(team, 0, 0, 0, 3);
		
		assertTrue(isCompatible);
	}

}
