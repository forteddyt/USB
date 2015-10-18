import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Used to test all of the LinkedListTest methods.
 * @author Teddy Todorov
 * @version 9.24.14
 */
public class LinkedListTest {
    private LinkedList test;

    /**
     * Constructs LinkedList
     */
    @Before
    public void setUp() {
        test = new LinkedList();
    }

    /**
     * De-constructs the LinkedList
     */
    @After
    public void tearDown() {
        test = null;
    }

    /**
     * Tests the .size() method.
     */
    @Test
    public void testSizeZero() {
        assertSame(0, test.size());
        test.add("1");
        test.add(2);
        test.add("3");
        assertEquals(3, test.size());
    }

    /**
     * Tests the .add( Object ) method.
     */
    @Test
    public void testAddOne() { 
        test.add("Hello!");
        assertEquals("Hello!", test.get(0));
        assertEquals(1, test.size());
        assertNull(test.get(1));
        for ( int i = 0; i < 10; i++) {
            test.add(0);
        }
        test.add(1);
        assertEquals(1, test.get(test.size() - 1));
    }

    /**
     * Tests the .add(int , Object) method.
     */
    @Test
    public void testAddTwo() { 
        test.add(0, "World");
        assertEquals("World", test.get(0));
        test.add(-1, "Haha Wont Work!");
        test.add(-1, 0101010);
        test.add(test.size() * 2 , "Jokes on you! Neither will this!");
        test.add(1);
        test.add(42);
        test.add("Teddy");
        test.add(0, 0);
        assertEquals(0, test.get(0));
        
        assertEquals("World", test.get(1));
        test.add(3, "MiddleOccurance");
        assertEquals("World", test.get(1));

        assertEquals(1, test.get(2));
        assertEquals("MiddleOccurance", test.get(3));
        assertEquals(42, test.get(4));
        assertEquals("Teddy", test.get(5));
        test.add("Todorov");
        assertEquals("Todorov", test.get(6));
        int crst = 4;
        for ( int i = test.size(); i < crst * 42; i++) {
            test.add(i, "Junk");
        }
        test.add(test.size(), 42);
        assertEquals(test.get(test.size() - 1), 42);
    }

    /**
     * Tests the .remove( int ) method - kinda.
     */
    @Test
    public void testRemoveIndex() {
        LinkedList x = new LinkedList();
        assertEquals(false, test.remove("bob"));
        assertNull(test.remove(0));
        assertArrayEquals(test.toArray(), x.toArray());
        test.add(0);
        assertEquals(0, test.remove(0));
        test.add(0);
        test.add("World!");
        test.add(42);
        test.add("Teddy!");
        test.remove(0);
        assertNull(test.remove(-1));
        assertNull(test.remove(10000000));
        test.remove(test.size() * 42);
        assertEquals("Teddy!", test.remove(2));
        assertEquals(true, test.remove("World!"));
        assertEquals(false, test.remove("NotHere!"));
        x.add(42);
        assertArrayEquals(test.toArray(), x.toArray());
    }
    
    /**
     * moos
     */
    @Test
    public void testRemoveIndex3Yay() {
        test.add("a");
        assertEquals("a", test.remove(0));
        test.add("a");
        test.add("b");
        test.add("c");
        assertEquals("b", test.remove(1));
        test.set(1, "b");
        test.add("c");
        test.add("d");
        assertEquals("c", test.remove(2));
        test.add(3, "c");
        assertNull(test.remove(4));
        assertEquals(false, test.remove("e"));
        assertEquals(true, test.remove("c"));
    }
    
    /**
     * Tests the .remove( Object ) method - kinda.
     */
    @Test
    public void testRemoveIndex2CuzImLazy() {
        LinkedList x = new LinkedList();
        assertArrayEquals(test.toArray(), x.toArray());
        test.add(0);
        test.add("World!");
        test.add(42);
        test.add("Teddy!");
        assertEquals(0, test.remove(0));
        assertNull(test.remove(-1));
        assertNull(test.remove(10000000));
        assertNull(test.remove(test.size() * 42));
        assertEquals("Teddy!", test.remove(test.indexOf("Teddy!")));
        assertEquals("World!", test.remove(test.indexOf("World!")));
        assertEquals(false, test.remove("NotHere!"));
        x.add(42);
        assertArrayEquals(test.toArray(), x.toArray());
    }

    /**
     * Tests .remove( int ) again!
     */
    @Test
    public void testRemoveIndexAgain() {
        test.add("a");
        test.add("b");
        test.add("c");
        test.add("d");
        test.add("e");
        test.add("f");
        test.add("g");
        assertEquals("d", test.remove(3));
    }
    
    /**
     * Tests the .get() method.
     */
    @Test
    public void testGet() {
        assertNull(test.get(0));
        assertNull(test.get(-1));
        assertNull(test.get(100000));
        test.add(42);
        assertEquals(42, test.get(0));
        assertNull(test.get(-1));
        assertNull(test.get(100000));
        test.add("work!");
        assertEquals("work!", test.get(1));
        assertEquals(42, test.get(0));
        assertNull(test.get(2));
    }

    /**
     * Tests the .set( Object ) method.
     */
    
    @Test
    public void testSet() {
        assertNull(test.get(0));
        test.add("a");
        test.add(2);
        test.add("c");
        test.add(3);
        assertEquals(test.set(0, "good"), "a");
        assertNull(test.set(-1, "good"));
        assertNull(test.set(100000, "bad"));
        assertNull(test.set(test.size() , 42));
        assertNull(test.set((int)(Math.sqrt(16.0)), 42));
        assertEquals("good", test.set((int)(Math.sqrt(-16.0)), 42));
        test.set(3, "hello!");
        test.set(-1, "hehe, won't work!");
        test.set(test.size() * 42, "neither will this!");
        assertSame("hello!", test.get(3));
        test.set(0, "notpoop");
        assertSame("notpoop", test.get(0));
        test.set(test.size() - 1, "woohoo!");
        assertSame("woohoo!", test.get(test.size() - 1));
        assertNull(test.set(-1, "nup"));
    }

    /**
     * moo
     */
    @Test
    public void testIndexOf() {
        test.add("a");
        test.add(2);
        test.add("c");
        test.add(4);
        test.add("e");
        assertEquals(1, test.indexOf(2));
        assertEquals(-1, test.indexOf("jk"));
        assertEquals(0, test.indexOf("a"));
    }

    /**
     * moo
     */
    @Test
    public void testLastIndexOf() {
        test.add("Teddy");
        test.add(752);
        test.add(752);
        test.add(52);
        test.add("Teddy");
        assertEquals(-1, test.lastIndexOf("notThere"));
        assertEquals(4, test.lastIndexOf("Teddy"));
        assertEquals(3, test.lastIndexOf(52));
        assertEquals(2, test.lastIndexOf(752));
    }

    /**
     * moo
     */
    @Test
    public void testContains() {
        test.add("Teddy");
        test.add("Me");
        test.add(0101);
        test.add("Them");
        test.add(1010);
        assertFalse(test.contains("Us"));
        assertTrue(test.contains("Me"));
        assertTrue(test.contains(1010));
    }

    /**
     * moo
     */
    @Test
    public void testClear() {
        LinkedList temp = new LinkedList();
        assertArrayEquals(temp.toArray(), test.toArray());
        test.add("Insig");
        test.add("Nificant");
        test.add(1337);
        test.clear();
        assertArrayEquals(temp.toArray(), test.toArray());
    }

    /**
     * moo
     */
    @Test
    public void testIsEmpty() {
        assertTrue(test.isEmpty());
        test.add("dies!");
        assertFalse(test.isEmpty());
    }

    /**
     * moo
     */
    @Test
    public void testToArray() {
        assertNull(test.toArray());
        test.add("poop");
        test.remove("poop");
        assertNull(test.toArray());
        Object[] temp = {"It", "Works!"};
        test.add("It");
        test.add("Works!");
        assertArrayEquals(temp, test.toArray());
    }

    /**
     * moo
     */
    
    @Test
    public void testToString() {
        assertEquals("[]", test.toString());
        String temp = "[Just, Testing, This, Thing, Out]";
        test.add("Just");
        test.add("Testing");
        test.add("This");
        test.add("Thing");
        test.add("Out");
        assertEquals("[Just, Testing, This, Thing, Out]", test.toString());
        String sT = test.toString();
        assertEquals(temp, sT);
    }
    
}