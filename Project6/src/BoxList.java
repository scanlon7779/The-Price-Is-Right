import java.io.*;
import java.util.*;

/** 
 * Creates BoxList object
 * @author Colin Scanlon
 */
public class BoxList {
    
    /** boxes in the game **/
    private Box[] boxes;
    
    /**
     * Constructor for BoxList
     * @param monetaryAmounts double array for the boxes
     * @throw NullPointerException if input array is null
     * @throw IllegalArgumentException if array is less than two units
     */
    public BoxList(double[] monetaryAmounts) {
        if (monetaryAmounts == null) {
            throw new NullPointerException("Null monetary amounts");
        }
        if (monetaryAmounts.length < 2) {
            throw new IllegalArgumentException("Invalid monetary amounts");
        }
        boxes = new Box[monetaryAmounts.length];
        for(int i = 0; i < monetaryAmounts.length; i++) {
            this.boxes[i] = new Box(monetaryAmounts[i]);
        }
        
    }
    
    /**
     * opens a given box 
     * @param index box to be opened
     * @throw IllegalArgumentException if index is less than 0 or greater than the length
     */
    public void open(int index) {
        if(index < 0 || index >= this.boxes.length) {
            throw new IllegalArgumentException("Invalid index");
        }
        this.boxes[index].open();
    }
    
    /**
     * gets the value of a given box
     * @param index int of the box 
     * @return value of the chosen box
     * @throw IllegalArgumentException if index is less than 0 or greater than the length
     */
    public double getValue(int index) {
        if(index < 0 || index >= this.boxes.length) {
            throw new IllegalArgumentException("Invalid index");
        }
        return this.boxes[index].getValue();
    }
    
    /**
     * returns if a specific box is open 
     * @param index box to check is open 
     * @return if the box is open or not
     * @throw IllegalArgumentException if index is less than 0 or greater than the length
     */
    public boolean isOpen(int index) {
        if(index < 0 || index >= this.boxes.length) {
            throw new IllegalArgumentException("Invalid index");
        }
        return this.boxes[index].isOpen();
    }
    
    /**
     * Gets the average value of all unopened boxes
     * @return average of all unopened boxes
     */
    public double averageValueOfUnopenedBoxes() {
        double totalValue = 0;
        int count = 0;
        for(int i = 0; i < this.boxes.length; i++) {
            if(this.boxes[i].isOpen() == false) {
                totalValue += this.boxes[i].getValue();
                count ++;
            }
        }
        if (count == 0) {
            return totalValue;
        }
        return totalValue / count;
    }
    
    /**
     * Shuffles all boxes in the game 
     * @param numberOfSwaps how manu times the boxes will be switched
     * @throw IllegalArgumentException if the number of swaps is less than 0 
     */
    public void shuffle(int numberOfSwaps) {
        if (numberOfSwaps < 0) {
            throw new IllegalArgumentException("Invalid number of swaps");
        }
        Random r = new Random();
        for(int i = 0; i < numberOfSwaps; i++) {
            int random1 = r.nextInt(this.boxes.length);
            int random2 = r.nextInt(this.boxes.length);
            Box hold = this.boxes[random1];
            this.boxes[random1] = this.boxes[random2];
            this.boxes[random2] = hold;
        }
    }
    
    /**
     * Checks if the BoxList and another object are equal
     * @param o object to be compared
     * @return if the objects are equal or not
     */
    public boolean equals(Object o) {
        if (o instanceof BoxList) {
            BoxList object = (BoxList) o;
            if (this.boxes.length == object.boxes.length) {
                for(int i = 0; i < this.boxes.length; i++) {
                    if(this.boxes[i].equals(object.boxes[i]) == false) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns a string 
     * @return a string of all the array boxes
     */
    public String toString() {
        String returnString = "";
        for (int i = 0; i < this.boxes.length; i++) {
            returnString += i + ": Open: " + this.boxes[i].isOpen() + " Value: " + 
                            this.boxes[i].getValue() + "\n";
        }
        return returnString;
    }
}