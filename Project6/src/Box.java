import java.io.*;

/** 
 * Creates Box object
 * @author Colin Scanlon
 */
public class Box {
    /** value to compare within **/
    public static final double DELTA = 0.01;
    
    /** value of the box **/
    private double value;
    
    /** If the box is open or not **/
    private boolean isOpen; 
    
    /**
     * Constructor for box object
     * @param value box value 
     * @throw IllegalArgumentException if value is less than 0.01
     */
    public Box(double value) {
        if (value < DELTA) {
            throw new IllegalArgumentException("Invalid value");
        }
        this.value = value;
        this.isOpen = false;
    }
    
    /**
     * gets the value of a box
     * @return value of the box
     */
    public double getValue() {
        return this.value;
    }
    
    /**
     * Returns if the box is open or not
     * @return is the box is open or not
     */
    public boolean isOpen() {
        return this.isOpen;
    }
     
     /**
      * Opens the box
      *
      */
    public void open() {
        this.isOpen = true;
    }
    
    /**
     * Compares two boxes to see if they are equal
     * @param o object to be compared to
     * @return if the two objects are equal
     */
    public boolean equals(Object o) {
        if (o instanceof Box) {
            Box object = (Box) o;
            if (Math.abs(this.value - object.value) < DELTA && 
                this.isOpen == object.isOpen) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns the box in string form
     * @return string of the box
     */
    public String toString() {
        return "Open: " + this.isOpen + " Value: " + this.value;
    }
}