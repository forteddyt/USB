import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Used to test all of the StringArrayList methods.
 * @author Teddy Todorov
 * @version 9.18.14
 */
public class StringArrayListTest {
    private StringArrayList test;
    private StringArrayList test2;
    private StringArrayList test3;
    /**
     * Constructs an empty StringArrayList.
     */
    @Before
    public void setUp() {
        test = new StringArrayList();
        test2 = new StringArrayList(2);
        test3 = new StringArrayList(-1);
    }

    /**
     * De-constructs an empty StringArrayList.
     */
    @After
    public void tearDown() {
        test = null;
        test2 = null;
    }
    
    /**
     * moo's
     */
    @Test
    public void testStringArrayListContructorIndex() {
        StringArrayList temp = new StringArrayList(2);
        assertEquals(temp.lengthThing(), 2);
        StringArrayList temp2 = new StringArrayList(-2);
        assertEquals(temp2.lengthThing(), 10);
        assertEquals(test2.lengthThing(), 2);
        assertEquals(test3.lengthThing(), 10);
        assertNotNull( temp );
        assertNotNull( temp2 );
    }

    /**
     * Tests the .size() method.
     */
    @Test
    public void testSizeZero() {
        assertSame(0, test.size());
        test.add("1");
        test.add("2");
        test.add("3");
        assertEquals(3, test.size());
    }

    /**
     * Tests the .add( String ) method.
     */
    @Test
    public void testAddOne() { 
        test.add("Hello!");
        assertSame("Hello!", test.get(0));
        assertEquals(1, test.size());
        for ( int i = 0; i < 10; i++) {
            test.add("Junk");
        }
        test.add("NotJunk");
        assertSame("NotJunk", test.get(test.size() - 1));
    }

    /**
     * Tests the .add(int , String) method.
     */
    @Test
    public void testAddTwo() { 
        System.out.println(test.size());
        test.add(0, "World");
        test.add(-1, "Haha Wont Work!");
        test.add(test.size() * 2 , "Jokes one you! Neither will this!");
        System.out.println(test);
        test.add("This");
        test.add("Is");
        System.out.println(test);
        test.add("Teddy");
        System.out.println(test);
        test.add(0, "Hello!");
        
        System.out.println(test);
        test.add(test.size(), "Todorov");

        System.out.println(test);
        test.add(3, "MiddleOccurance");
        assertEquals("Hello!", test.get(0));
        System.out.println(test);
        assertEquals("World", test.get(1));


        System.out.println(test);

        assertEquals("This", test.get(2));
        assertEquals("MiddleOccurance", test.get(3));
        assertEquals("Is", test.get(4));
        assertEquals("Teddy", test.get(5));
        assertEquals("Todorov", test.get(6));
        int crst = test.lengthThing();
        for ( int i = test.size(); i < crst * 42; i++) {
            test.add(i, "Junk");
        }
        test.add(test.size(), "meow");
        assertEquals(test.get(test.size() - 1), "meow");
        test2.add("Test");
        test2.add("moo");
        test2.add("quack");
        test2.add("bark");
        test2.add("meow");
        int crst2 = test2.size();
        for ( int i = test2.size(); i < crst2 * 42; i++) {
            test2.add(i, "Junk");
        }

        test3.add("Test");
        test3.add("foxy");
        int crst3 = test3.lengthThing();
        for ( int i = test3.size(); i < crst3 * 42; i++) {
            test3.add(i, "Junk");
        }

        test.add(test.size(), "NotJunk");
        test2.add(test2.size(), "NotJunk");
        test3.add(test3.size(), "NotJunk");
        assertEquals(test.get(test.size() - 1), "NotJunk");
        assertEquals(test2.get(test2.size() - 1), "NotJunk");
        assertEquals(test3.get(test3.size() - 1), "NotJunk");
    }

    /**
     * Tests the .remove( int ) method.
     */
    @Test
    public void testRemoveIndex() {
        StringArrayList x = new StringArrayList();
        assertArrayEquals(test.toArray(), x.toArray());
        System.out.println(test.toArray());
        test.add("Hello");
        test.add("World!");
        test.add("I'm");
        test.add("Teddy!");
        test.remove(0);
        assertNull(test.remove(-1));
        assertNull(test.remove(10000000));
        test.remove(test.size() * 42);
        test.remove(test.size() - 1);
        assertEquals(true, test.remove("World!"));
        assertEquals(false, test.remove("NotHere!"));
        x.add("I'm");
        assertArrayEquals(test.toArray(), x.toArray());
    }

    /**
     * Tests the .get() method.
     */
    @Test
    public void testGet() {
        assertNull(test.get(0));
        assertNull(test.get(-1));
        assertNull(test.get(100000));
        test.add("tastytest");
        assertSame("tastytest", test.get(0));
        assertNull(test.get(-1));
        assertNull(test.get(100000));
        assertEquals("tastytest", test.get((int)(Math.sqrt(-16.0))));
    }

    /**
     * Tests the .set( String ) method.
     */
    @Test
    public void testSet() {
        assertNull(test.get(0));
        test.add("a");
        test.add("b");
        test.add("c");
        test.add("d");
        assertEquals(test.set(0, "good"), "a");
        assertNull(test.set(-1, "good"));
        assertNull(test.set(100000, "bad"));
        assertNull(test.set(test.size() , "poop"));
        assertNull(test.set((int)(Math.sqrt(16.0)), "poop"));
        assertEquals("good", test.set((int)(Math.sqrt(-16.0)), "poop"));
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
        test.add("b");
        test.add("c");
        test.add("d");
        test.add("e");
        assertEquals(1, test.indexOf("b"));
        assertEquals(-1, test.indexOf("jk"));
        assertEquals(0, test.indexOf("a"));
    }

    /**
     * moo
     */
    @Test
    public void testLastIndexOf() {
        test.add("Teddy");
        test.add("Person");
        test.add("Person");
        test.add("Test");
        test.add("Teddy");
        assertEquals(-1, test.lastIndexOf("notThere"));
        assertEquals(4, test.lastIndexOf("Teddy"));
        assertEquals(3, test.lastIndexOf("Test"));
        assertEquals(2, test.lastIndexOf("Person"));
    }

    /**
     * moo
     */
    @Test
    public void testContains() {
        test.add("Teddy");
        test.add("Me");
        test.add("You");
        test.add("Them");
        test.add("They");
        assertFalse(test.contains("Us"));
        assertTrue(test.contains("Me"));
        assertTrue(test.contains("They"));
    }

    /**
     * moo
     */
    @Test
    public void testClear() {
        StringArrayList temp = new StringArrayList();
        assertArrayEquals(temp.toArray(), test.toArray());
        test.add("Insig");
        test.add("Nificant");
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
        String[] temp = {"It", "Works!"};
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
        assertEquals(temp, test.toString());
    }

    /**
     * moo
     */
    @Test
    public void testTrimToSize() {
        for ( int i = 0; i < 30; i++) {
            test.add("meh");
        }
        test.trimToSize();
        assertEquals(test.size(), test.lengthThing());
        test2.add("meh");
        test2.add("meh2");
        test2.add("meh3");
        test2.trimToSize();
        String[] trial = new String[3];
        trial[0] = "meh";
        trial[1] = "meh2";
        trial[2] = "meh3";
        assertArrayEquals(trial, test2.toArray());
        
    }
    /**
     * moo
     */
    @Test
    public void testLengthThing() {
        assertEquals(10, test.lengthThing());
        assertEquals(2, test2.lengthThing());
        assertEquals(10, test3.lengthThing());
    }

}
