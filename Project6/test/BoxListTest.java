import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * Tests BoxList class
 * @author Suzanne Balik
 * @author Colin Scanlon
 */
public class BoxListTest extends TestCase {

    /** Delta for double tests */
    private static final double DELTA = 0.01;
    
    /** BoxList 1 for testing */
    private BoxList boxList1;
    
    /** Array for BoxList 1 **/
    private double[] monetaryAmounts1 =  {1.0, 5.0, 10.0, 25.0};

    /** BoxList 2 for testing **/
    private BoxList boxList2;
    
    /** Array for boxList 2 **/
    private double[] monetaryAmounts2 = {0.1, 1.0, 10.0, 100.0, 1000.0, 10000.0};

    /**
     * Create BoxLists for testing
     */
    @Before
    public void setUp() {
        boxList1 = new BoxList(monetaryAmounts1);
        boxList2 = new BoxList(monetaryAmounts2);
    }


    @Test
    public void testConstructorPreConditions() {
 
        try {
            new BoxList(null);
            fail();
        } catch (NullPointerException e) {
            assertEquals("Correct NullPointerException message", "Null monetary amounts",
                    e.getMessage());
        }

        try {
            double[] noElements = {};
            new BoxList(noElements);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Correct IllegalArgumentException message", "Invalid monetary amounts",
                    e.getMessage());
        }      

        try {
            double[] oneElement = {2.3};
            new BoxList(oneElement);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Correct IllegalArgumentException message", "Invalid monetary amounts",
                    e.getMessage());
        }      
    }

    @Test
    public void testGetValuePreConditions() {
        try {
            boxList1.getValue(-1);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Correct IllegalArgumentException message", "Invalid index",
                    e.getMessage());
        }

        try {
            boxList1.getValue(4);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Correct IllegalArgumentException message", "Invalid index",
                    e.getMessage());
        }
        try {
            boxList1.getValue(5);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Correct IllegalArgumentException message", "Invalid index",
                    e.getMessage());
        }

    }

    @Test
    public void testIsOpenPreConditions() {
        try {
            boxList1.isOpen(-1);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Correct IllegalArgumentException message", "Invalid index",
                    e.getMessage());
        }

        try {
            boxList1.isOpen(4);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Correct IllegalArgumentException message", "Invalid index",
                    e.getMessage());
        }
        try {
            boxList1.isOpen(5);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Correct IllegalArgumentException message", "Invalid index",
                    e.getMessage());
        }

    }

    @Test
    public void testOpenPreConditions() {
        try {
            boxList1.open(-1);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Correct IllegalArgumentException message", "Invalid index",
                    e.getMessage());
        }

        try {
            boxList1.open(4);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Correct IllegalArgumentException message", "Invalid index",
                    e.getMessage());
        }
        try {
            boxList1.open(5);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Correct IllegalArgumentException message", "Invalid index",
                    e.getMessage());
        }

    }

    @Test
    public void testShufflePreConditions() {
        try {
            boxList1.shuffle(-1);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Correct IllegalArgumentException message", "Invalid number of swaps",
                         e.getMessage());
        }

    }
   
   

    @Test
    public void testGetValue1() {
        assertEquals("Get Value ", 1.0, boxList1.getValue(0), DELTA);
        assertEquals("Get Value ", 5.0, boxList1.getValue(1), DELTA);
        assertEquals("Get Value ", 10.0, boxList1.getValue(2), DELTA);
        assertEquals("Get Value ", 25.0, boxList1.getValue(3), DELTA);
    }
    
    @Test
    public void testGetValue2() {
        assertEquals("Get Value ", 0.1, boxList2.getValue(0), DELTA);
        assertEquals("Get Value ", 1.0, boxList2.getValue(1), DELTA);
        assertEquals("Get Value ", 10.0, boxList2.getValue(2), DELTA);
        assertEquals("Get Value ", 100.0, boxList2.getValue(3), DELTA);
        assertEquals("Get Value ", 1000.0, boxList2.getValue(4), DELTA);
        assertEquals("Get Value ", 10000.0, boxList2.getValue(5), DELTA);

    }
    
    @Test
    public void testIsOpen1() {
        for (int i = 0; i < 4; i++) {
            assertFalse("Is Open ", boxList1.isOpen(i));
        }
    }
    
    @Test
    public void testIsOpen2() {
        for (int i = 0; i < 6; i++) {
            assertFalse("Is Open ", boxList2.isOpen(i));
        }
    }

    @Test
    public void testOpen1() {
        boxList1.open(0);
        boxList1.open(2);
        assertTrue("Open", boxList1.isOpen(0));
        assertFalse("Open", boxList1.isOpen(1));
        assertTrue("Open", boxList1.isOpen(2));
        assertFalse("Open", boxList1.isOpen(3));
    }
    
    @Test
    public void testOpen2() {
        boxList2.open(1);
        boxList2.open(4);
        assertTrue("Open", boxList2.isOpen(1));
        assertFalse("Open", boxList2.isOpen(0));
        assertTrue("Open", boxList2.isOpen(4));
        assertFalse("Open", boxList2.isOpen(3));
    }
    
    @Test
    public void testAverageValueOfUnopenedBoxes1() {
        assertEquals("Average Value Of Unopened Boxes", 10.25, 
                     boxList1.averageValueOfUnopenedBoxes(), DELTA);
        boxList1.open(0);
        boxList1.open(2);
        assertEquals("Average Value Of Unopened Boxes", 15.0, 
                     boxList1.averageValueOfUnopenedBoxes(), DELTA);
        boxList1.open(1);
        boxList1.open(3);
        assertEquals("Average Value Of Unopened Boxes", 0, 
                     boxList1.averageValueOfUnopenedBoxes(), DELTA);
        
    }
    
    @Test
    public void testAverageValueOfUnopenedBoxes2() {
        assertEquals("Average Value Of Unopened Boxes", 1851.85, 
                     boxList2.averageValueOfUnopenedBoxes(), DELTA);
        boxList2.open(4);
        boxList2.open(5);
        assertEquals("Average Value Of Unopened Boxes", 27.775, 
                     boxList2.averageValueOfUnopenedBoxes(), DELTA);
        boxList2.open(1);
        boxList2.open(3);
        boxList2.open(0);
        boxList2.open(2);
        assertEquals("Average Value Of Unopened Boxes", 0, 
                     boxList2.averageValueOfUnopenedBoxes(), DELTA);
        
    }

    @Test
    public void testEquals1() {
        assertTrue("boxList1 equals with same instance", boxList1.equals(boxList1));
        BoxList same = new BoxList(monetaryAmounts1);
        assertTrue("boxList1 equals with different instances", 
                   boxList1.equals(same));
        same.open(2);
        assertFalse("boxList1 with same box list with opened box", boxList1.equals(same));
        double[] differentAmounts = {2.0, 3.5, 4.5, 8.0};
        assertFalse("boxList1 with different values", 
                    boxList1.equals(new BoxList(differentAmounts)));
        double[] differentLength = {1.0, 5.0, 10.0};
        assertFalse("boxList1 with different length", 
                    boxList1.equals(new BoxList(differentLength)));
        assertFalse("boxList1 compared to null object", boxList1.equals(null));
        assertFalse("boxList1 compared to String", boxList1.equals("box list 1"));
    }
    
    @Test
    public void testEquals2() {
        assertTrue("boxList2 equals with same instance", boxList2.equals(boxList2));
        BoxList same = new BoxList(monetaryAmounts2);
        assertTrue("boxList2 equals with different instances", 
                   boxList2.equals(same));
        same.open(1);
        assertFalse("boxList2 with same box list with opened box", boxList2.equals(same));
        double[] differentAmounts = {1.0, 2.0, 3.0, 4.0};
        assertFalse("boxList2 with different values", 
                    boxList1.equals(new BoxList(differentAmounts)));
        double[] differentLength = {1.0, 5.0, 10.0, 1.0, 1.0, 1.0, 1.0};
        assertFalse("boxList2 with different length", 
                    boxList2.equals(new BoxList(differentLength)));
        assertFalse("boxList2 compared to null object", boxList2.equals(null));
        assertFalse("boxList2 compared to String", boxList2.equals("box list 1"));
        assertFalse("boxList2 compared to boxList1", boxList2.equals(boxList1));

    }

    @Test
    public void testToString1() {
        String expected = "0: Open: false Value: 1.0\n" +
                          "1: Open: false Value: 5.0\n" +
                          "2: Open: false Value: 10.0\n" +
                          "3: Open: false Value: 25.0\n";
        assertEquals("toString  after constructed", expected, boxList1.toString());
        boxList1.open(1);
        boxList1.open(2);
        expected = "0: Open: false Value: 1.0\n" +
                   "1: Open: true Value: 5.0\n" +
                   "2: Open: true Value: 10.0\n" +
                   "3: Open: false Value: 25.0\n";
        assertEquals("toString  after opening two boxes", expected, boxList1.toString());
    }
    
    @Test
    public void testToString2() {
        String expected = "0: Open: false Value: 0.1\n" +
                          "1: Open: false Value: 1.0\n" +
                          "2: Open: false Value: 10.0\n" +
                          "3: Open: false Value: 100.0\n" +
                          "4: Open: false Value: 1000.0\n" +
                          "5: Open: false Value: 10000.0\n";
        assertEquals("toString  after constructed", expected, boxList2.toString());
        boxList2.open(0);
        boxList2.open(4);
        boxList2.open(5);
        expected = "0: Open: true Value: 0.1\n" +
                   "1: Open: false Value: 1.0\n" +
                   "2: Open: false Value: 10.0\n" +
                   "3: Open: false Value: 100.0\n" +
                   "4: Open: true Value: 1000.0\n" +
                   "5: Open: true Value: 10000.0\n";
        assertEquals("toString  after opening three boxes", expected, boxList2.toString());
    }
}
