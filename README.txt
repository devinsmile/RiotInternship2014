Hello Riot!

This is my attempt at a Matchmaking system. Firstly, I wrote this program in two separate "parts", for lack of better words.

Part 1: src/test/java/Test.java - This is a test class with a main method. This main method reads in the Players from the SampleData.java data set, and attempts to match them all into a game. For my example, I used 5v5 games. However, the function is fully capable of working with any size team that you would like. I also made sure to comment every aspect of all of the code, making it hopefully very easy to follow my logic and mindset while creating this code.

Part 2: src/test/java/MatchmakerTestSuite.nava - This is a JUnit test suite. I  have not previously worked with JUnit, however I figured I would do some research into it as I know it is a common unit testing framework used in Java. I wrote some test cases that test the functionality of the Player and MatchmakerImpl classes to make sure that they fully work to specification. 

How my Matchmaker works, is that it polls Player objects out of the queue, and checks to see if they are compatible with both teams. When I say "are compatible", I mean using the three factors that I made in the Player class to check for compatibility; number of wins, win/loss ratio, and number of games played total. I felt that those were three good factors to use to make sure players are matched accurately and fairly. 

The matchmaker then sets some initial tolerances for each of those factors, and attempts to find the best player in the queue to fit into either team. If a player is not found, the tolerances become less strict. This implementation seems to be what most games use, if I had to guess. The longer you are in the queue, essentially, the less strict (and unfortunately less quality) the match will be in terms of player skill level. However, the very good side to this implementation, is that every player WILL be put into a game regardless. This is because of a case that I check for, in which there are still players that are somehow unmatched even when the tolerances are very high. 

I've calculated that this program makes approximately 80% of the matches fair. When I say fair, I mean that neither team has more than a 60% chance of winning. This matchmaking system can also matchmake 2,000 players nearly instantaneously. 
If you have any questions or comments regarding my code, I'd really appreciate your insight! I can be reached at mutschlechne@wisc.edu, or 920-574-1375. I hope that I've provided enough information here to be of great use for you guys. I haven't used Maven before, however I tried my best to research the paradigm and set up my project appropriately so that it would work well for you. 

Thank you very much for your consideration, and for taking the time to read my code and evaluate my performance. Regardless of the outcome, I'm extremely grateful for this opportunity. Have a wonderful day! :)  
