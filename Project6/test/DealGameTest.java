import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * Tests DealGame class
 * @author Colin Scanlon
 */
public class DealGameTest extends TestCase {
    /** Delta for double tests */
    private static final double DELTA = 0.01;
    /** DealGame object for tests */
    private DealGame game;
    
    /**
     * Set up for testing
     * @throws FileNotFoundException if PrintStream doesn't exist
     */
    @Before 
    public void setUp() throws FileNotFoundException {
        game = new DealGame(true);
        PrintStream out = new PrintStream(new File(DealGame.HIGH_SCORE_FILE));
        out.print("0.0");
        out.close();
    }

    @Test
    public void testGetBoxesRemainingToOpenThisRound() {
        String id = "Six boxes to open in the first round";

        int exp = 6;
        int act = game.getBoxesRemainingToOpenThisRound();
        assertEquals(id, exp, act);
        
        // Select the player's box and show that there are still 6 boxes
        // to open
        id = "Select the player's box, 6 boxes to open in first round";
        game.selectBox(0);
        exp = 6;
        act = game.getBoxesRemainingToOpenThisRound();
        assertEquals(id, exp, act);

        // Select the first of the six boxes and check that the count is decreased
        id = "Select first box, 5 boxes left to open in first round";
        game.selectBox(1);
        exp = 5;
        act = game.getBoxesRemainingToOpenThisRound();
        assertEquals(id, exp, act);

        // Select remaining boxes and ensure that the count fully decreases
        id = "Select 5 more boxes, 0 boxes left to open in first round";
        game.selectBox(2);
        game.selectBox(3);
        game.selectBox(4);
        game.selectBox(5);
        game.selectBox(6);
        exp = 0;
        act = game.getBoxesRemainingToOpenThisRound();
        assertEquals(id, exp, act);

        // Check end of round
        id = "End of round";
        assertEquals(id, exp, act);

        // Move to next round
        game.startNextRound();

        // Checking boxes in second round
        id = "Boxes to open in the second round";
        exp = 5;
        act = game.getBoxesRemainingToOpenThisRound();
        assertEquals(id, exp, act);
        
        // Check opening boxes in second round
        id = "Selecting first two boxes to be opened in second round";
        game.selectBox(7);
        game.selectBox(8);
        exp = 3;
        act = game.getBoxesRemainingToOpenThisRound();
        assertEquals(id, exp, act);
    }

    @Test
    public void testGetBoxesOpenedThisRound() {
        // Test that there are no open boxes at the start of the game
        String id = "No Boxes open at start of game";
        int exp = 0;
        int act = game.getBoxesOpenedThisRound();
        assertEquals(id, exp, act);

        // Test after player selects their box
        id = "Opening player box";
        game.selectBox(0);
        exp = 0;
        act = game.getBoxesOpenedThisRound();
        assertEquals(id, exp, act);
        
        // Test after opening two boxes in the round
        id = "Opening two boxes";
        game.selectBox(1);
        game.selectBox(2);
        exp = 2;
        act = game.getBoxesOpenedThisRound();
        assertEquals(id, exp, act);
        
        // Opening the rest of the round boxes
        id = "Selecting the rest of the first round boxes";
        game.selectBox(3);
        game.selectBox(4);
        game.selectBox(5);
        game.selectBox(6);
        exp = 6;
        act = game.getBoxesOpenedThisRound();
        assertEquals(id, exp, act);
        
        //  Test after new round 
        id = "Going to a new round";
        game.startNextRound();
        exp = 0;
        act = game.getBoxesOpenedThisRound();
        assertEquals(id, exp, act);
        
        // Opening boxes in second round
        id = "Opening two boxes in round two";
        game.selectBox(7);
        game.selectBox(8);
        exp = 2;
        act = game.getBoxesOpenedThisRound();
        assertEquals(id, exp, act);

    }

    @Test
    public void testGetPlayerBoxValue() {
        // Test that the player's box value is 0.01 since the
        // player's box index is initialized to 0
        String id = "Player box value before selecting box";
        double exp = 0.01;
        double act = game.getPlayerBoxValue();
        assertEquals(id, exp, act);
        
        // Getting player's box value after picking box
        id = "Picking player's box";
        game.selectBox(1);
        exp = 1.00;
        act = game.getPlayerBoxValue();
        assertEquals(id, exp, act);
        
        // Checking after picking next box
        id = "Picking first box";
        game.selectBox(2);
        exp = 1.00;
        act = game.getPlayerBoxValue();
        assertEquals(id, exp, act);
        
        // Checking after next round 
        game.selectBox(0);
        game.selectBox(3);
        game.selectBox(4);
        game.selectBox(5);
        game.selectBox(6);
        game.startNextRound();
        id = "Getting player value in second round";
        exp = 1.00;
        act = game.getPlayerBoxValue();
        assertEquals(id, exp, act);
        

    }

    @Test
    public void testIsBoxOpen() {

        // Test that all boxes are closed at the start of the
        // game
        String id = "All boxes closed initially: ";
        String desc = "game.isBoxOpen ";
        for (int i = 0; i < DealGame.NUM_BOXES; i++) {
            assertFalse(id + desc + i, game.isBoxOpen(i));
        }
        
        // Test for first box opened 
        id = "Open first box";
        game.selectBox(7);
        game.selectBox(8);
        boolean open = game.isBoxOpen(8);
        assertTrue(id, open);
        
        // Test for player box
        id = "Player box";
        open = game.isBoxOpen(7);
        assertFalse(id, open);

    }

    @Test
    public void testGetCurrentOffer() {
        // Test initial current offer - this doesn't match
        // what is actually done in game play since the offer
        // isn't made until the end of the 1st round. That's
        // ok. White box tests focus on the method's functionality,
        // not the method's functionality as part of the overall
        // game.
        String id = "Initial current offer";
        double exp = 13147.75;
        double act = game.getCurrentOffer();
        assertEquals(id, exp, act, DELTA);
        
        // Setting player box and opening first box
        id = "Offer after first open box";
        game.selectBox(0);
        game.selectBox(1);
        exp = 13673.66;
        act = game.getCurrentOffer();
        assertEquals(id, exp, act, DELTA);
    }

    @Test
    public void testGetHighScore() {
        // Start with no high score - do before starting game
        String id = "Default high score";
        // Test that the high score is 0.0
        double exp = 0.0;
        double act = game.getHighScore();
        assertEquals(id, exp, act, DELTA);
        
        // New high score saved
        id = "New high score";
        double value = 10.0;
        assertTrue(id, game.isNewHighScore(value));
        exp = value;
        act = game.getHighScore();
        assertEquals(id, exp, act, DELTA);
    }

    @Test
    public void testGetValueInBox() {
        // Check that the value in box 0 is 0.01
        String id = "Value in box 0";
        double exp = 0.01;
        double act = game.getValueInBox(0);
        assertEquals(id, exp, act, DELTA);

        // Values after player picks a box
        id = "Value in player box";
        game.selectBox(0);
        exp = 0.01;
        act = game.getValueInBox(0);
        assertEquals(id, exp, act, DELTA);
        
        // Value after picking several boxes
        id = "Value after first boxes open";
        game.selectBox(2);
        game.selectBox(3);
        exp = 1.00;
        act = game.getValueInBox(1);
        assertEquals(id, exp, act, DELTA);
        
    }

    @Test
    public void testIsNewHighScore() {
        // Start with no high score - do before starting game
        String id = "New high score";

        // Test that the new score was recorded
        assertTrue(id, game.isNewHighScore(10.0));
        double exp = 10.0;
        double act = game.getHighScore();
        assertEquals(id, exp, act, DELTA);
        
        // Adding an even higher score
        id = "Higher high score";
        assertTrue(id, game.isNewHighScore(100.0));
        exp = 100.0;
        act = game.getHighScore();
        assertEquals(id, exp, act, DELTA);
        
        // Testing a lower score
        id = "Lower score";
        assertFalse(id, game.isNewHighScore(5.0));
        
        // Testing that the high score was not changed
        exp = 100.0;
        act = game.getHighScore();
        assertEquals(id, exp, act, DELTA);
    }
}
