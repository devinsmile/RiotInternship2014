import org.junit.runners.Suite;
import org.junit.runner.RunWith;

//Setup
@RunWith(Suite.class)

//Specify our tests that need to be run
@Suite.SuiteClasses({
	MatchmakingTest.class,
	PlayerTest.class
})

public class MatchmakerTestSuite {
}
