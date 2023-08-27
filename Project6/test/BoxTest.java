import org.junit.Before;
import org.junit.Test;
import java.awt.*;

import junit.framework.TestCase;

/**
 * Tests Box class
 * @author Suzanne Balik
 * @author Colin Scanlon
 */
public class BoxTest extends TestCase {

    /** Delta for double tests */
    private static final double DELTA = 0.01;

    /** Box 1 for testing */
    private Box box1 ;
    
    /** Box 2 fior testing **/
    private Box box2;


    /**
     * Create Boxes for testing
     */
    @Before
    public void setUp() {
        box1 = new Box(52.80);
        box2 = new Box(120.20);
    }




    @Test
    public void testConstructorPreConditions() {
        
        try {
            new Box(0);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Correct IllegalArgumentException message", "Invalid value",
                    e.getMessage());
        }

        try {
            new Box(-5);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Correct IllegalArgumentException message", "Invalid value",
                    e.getMessage());
        }       

    }

    @Test
    public void testGetValueBox1() {
        assertEquals("box1 value", 52.80, box1.getValue(), DELTA);
        
    }
    
    @Test
    public void testGetValueBox2() {
        assertEquals("box2 value", 120.20, box2.getValue(), DELTA);
        
    }

    @Test
    public void testIsOpenBox1() {
        assertFalse("box1 is open", box1.isOpen());
    }
    
    @Test
    public void testIsOpenBox2() {
        assertFalse("box2 is open", box2.isOpen());
    }

    @Test
    public void testOpenBox1() {
        box1.open();
        assertTrue("box1 is open", box1.isOpen());    
    }
    
    @Test
    public void testOpenBox2() {
        box2.open();
        assertTrue("box2 is open", box2.isOpen());    
    }

    @Test
    public void testEqualsBox1() {
        assertTrue("box1 equals with same instance", box1.equals(box1));
        assertTrue("box1 equals with different instances", box1.equals(new Box(52.80)));
        assertTrue("box1 with similar value", box1.equals(new Box(52.809)));
        assertFalse("box1 with somewhat different value", box1.equals(new Box(52.81)));
        Box openBox = new Box(52.80);
        openBox.open();
        assertFalse("box1 with same value but open", box1.equals(openBox));
        Box openDifferentValueBox = new Box(45.2);
        openDifferentValueBox.open();
        assertFalse("box1 with different value and open", box1.equals(openDifferentValueBox));
        assertFalse("box1 compared to null object", box1.equals(null));
        assertFalse("box1 compared to String", box1.equals("box 1"));
    }
    
    @Test
    public void testEqualsBox2() {
        assertTrue("box2 equals with same instance", box2.equals(box2));
        assertTrue("box2 equals with different instances", box2.equals(new Box(120.20)));
        assertTrue("box2 with similar value", box2.equals(new Box(120.201)));
        assertFalse("box2 with somewhat different value", box2.equals(new Box(120.0)));
        Box openBox = new Box(120.20);
        openBox.open();
        assertFalse("box2 with same value but open", box2.equals(openBox));
        Box openDifferentValueBox = new Box(82.7);
        openDifferentValueBox.open();
        assertFalse("box2 with different value and open", box2.equals(openDifferentValueBox));
        assertFalse("box2 compared to null object", box2.equals(null));
        assertFalse("box2 compared to String", box2.equals("box 2"));
        assertFalse("box2 compared to box1", box2.equals(box1));
    }

    @Test
    public void testToStringBox1() {
        assertEquals("box1 toString", "Open: false Value: 52.8", box1.toString());
    }
    
    @Test
    public void testToStringBox2() {
        assertEquals("box2 toString", "Open: false Value: 120.2", box2.toString());
    }

}
