import java.io.*;
import java.util.*;

/** 
 * Creates DealGame object
 * @author Colin Scanlon
 */
public class DealGame {
    /** Number of boxes in the game */
    public static final int NUM_BOXES = 26;
  
  
    /** Monetary values that will be used in the game */
    public static final double[] BOX_VALUES = {0.01, 1, 5, 10, 25, 50, 75, 
                                               100, 200, 300, 400, 500, 
                                               750, 1000, 5000, 10000, 
                                               25000, 50000, 75000, 
                                               100000, 200000, 300000, 
                                               400000, 500000, 750000, 
                                               1000000};
  
    /** Number of boxes to be opened in each round */
    public static final int[] BOXES_IN_ROUND = {0, 6, 5, 4, 3, 2, 
                                                1, 1, 1, 1, 1};
                                                
    /** Number of rounds in the game */
    public static final int NUM_ROUNDS = 10;
  
    /** Number of times boxes are swapped during the set up */
    public static final int BOX_SWAPS = 500;
  
    /** Name of the file that contains the high score */
    public static final String HIGH_SCORE_FILE = "highscore.txt";
    
    /** The players box index **/
    private int playerBoxIndex;
    
    /** Boolean if the player has chosen a box of not **/
    private boolean hasPlayerChosenBox;
    
    /** Round number **/
    private int round;
    
    /** Boxes that were opened this round **/
    private int boxesOpenedThisRound;
    
    /** Boxes opened total **/
    private int boxesOpenedTotal;
    
    /** Player's high score **/
    private double highScore;
    
    /** Box list object **/
    private BoxList boxList;
    
    /**
     * Constructor for GameDeal
     * @param testing boolean for true or false
     */
    public DealGame(boolean testing) {
        this.boxList = new BoxList(BOX_VALUES);
        
        if (testing == false) {
            boxList.shuffle(BOX_SWAPS);
        }
        try {
            File scoreFile = new File(this.HIGH_SCORE_FILE);
            Scanner fileScanner = new Scanner(scoreFile);
            if (fileScanner.hasNextDouble()) {
                this.highScore = fileScanner.nextDouble();
            } else {
                this.highScore = 0.0;
            }
        } catch (FileNotFoundException e) {
            this.highScore = 0.0;
        }
        this.round = 1;
        this.boxesOpenedThisRound = 0;
        this.boxesOpenedTotal = 0;
    
    }
    
    /**
     * Returns if the player has chosen a box or not
     * @return if the player has chosen a box or not
     */
    public boolean hasPlayerChosenBox() {
        return this.hasPlayerChosenBox;
    }
    
    /**
     * Selects a specific box
     * @param index box to be selected
     */
    public void selectBox(int index) {
        if (this.hasPlayerChosenBox == false) {
            this.playerBoxIndex = index;
            this.hasPlayerChosenBox = true;
        } else {
            this.boxList.open(index);
            this.boxesOpenedThisRound ++;
            this.boxesOpenedTotal ++;
        }
    }
    
    /**
     * Gets the boxes remaining to be opened this round
     * @return box int to be opened
     */
    public int getBoxesRemainingToOpenThisRound() {
        int num = BOXES_IN_ROUND[this.round] - this.boxesOpenedThisRound;
        return num;
    }
    
    /** 
     * Starts the next round of the game
     *
     */
    public void startNextRound() {
        this.round ++;
        this.boxesOpenedThisRound = 0;
    }
    
    /**
     * returns if it is the end of the round or not
     * @return if it is the end of the round
     */
    public boolean isEndOfRound() {
        boolean tru = true;
        if (BOXES_IN_ROUND[this.round] == this.boxesOpenedThisRound) {
            return tru;
        } else {
            return false;
        }
    }
    
    /**
     * returns the round number
     * @return round number
     */
    public int getRound() {
        return this.round;
    }
    
    /**
     * gets the player's box value
     * @return player's box value
     */
    public double getPlayerBoxValue(){
        return this.boxList.getValue(playerBoxIndex);
    }
    
    /**
     * Determines if a box is open or not
     * @param index of the box to be checked
     * @return if a box is open or not
     */
    public boolean isBoxOpen(int index) {
        return this.boxList.isOpen(index);
    } 
    
    /**
     * Gets the current offer
     * @return double of the current offer
     */
    public double getCurrentOffer() {
        return this.boxList.averageValueOfUnopenedBoxes() * round / NUM_ROUNDS;
    }
    
    /**
     * gets the value of a given box
     * @param index of box to get the value of 
     * @return value of the specific box
     */
    public double getValueInBox(int index) {
        return this.boxList.getValue(index);
    }
    
    /**
     * Gets the number of boxes opened this round
     * @return number of boxes opened 
     */
    public int getBoxesOpenedThisRound() {
        return this.boxesOpenedThisRound;
    }
    
    /**
     * Gets the high score of the game
     * @return high score of the game
     */
    public double getHighScore() {
        return this.highScore;
    }
    
    /**
     * returns if the player's score is a new highscore
     * @param value new score to be compared with high score
     * @return if this is a new highscore
     */
    public boolean isNewHighScore(double value) {
        if (value > this.highScore) {
            try {
                PrintStream out = new PrintStream(HIGH_SCORE_FILE);
                out.print(value);
                this.highScore = value;
                return true;                    
            } catch (FileNotFoundException e){
                System.out.println("Printing high score file error");
            }
        }
        return false;
    }
    
}