import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RegexTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testHasYeses() {
        assertTrue(Regex.hasYeses("Yes"));
        assertTrue(Regex.hasYeses("yes"));
        assertTrue(Regex.hasYeses("YeSeSESes"));
        assertTrue(Regex.hasYeses("Yesssss"));
        assertTrue(Regex.hasYeses("Ye"));
        assertTrue(Regex.hasYeses("Yeeeeeess"));
        assertTrue(Regex.hasYeses("Y"));
        assertTrue(Regex.hasYeses("y"));
        assertTrue(Regex.hasYeses("yeseseses"));
        assertTrue(Regex.hasYeses("yes!"));
        assertTrue(Regex.hasYeses("yes?"));
        assertTrue(Regex.hasYeses("Yes?"));
        assertTrue(Regex.hasYeses("Yes!"));
        assertTrue(Regex.hasYeses("Yeseses!!!!!"));
        assertTrue(Regex.hasYeses("Yeseses?!?!?!!?!?"));
        assertTrue(Regex.hasYeses("YesEseSes????"));
        assertTrue(Regex.hasYeses("yeseses!!!!"));
        assertTrue(Regex.hasYeses("yesseseSSsesEes?"));
        assertTrue(Regex.hasYeses("yesesssese!!?!?"));
    }

    @Test
    public void testIsProperWord() {
        assertTrue(Regex.isProperWord("Proper"));
        assertFalse(Regex.isProperWord("not"));
        assertFalse(Regex.isProperWord("Not either"));
    }

    @Test
    public void testIsScrabble() {
        assertTrue(Regex.isScrabble("asdd"));
        assertTrue(Regex.isScrabble("ASdAS"));
        assertTrue(Regex.isScrabble("ASDFGHJ"));
        assertTrue(Regex.isScrabble("asdfghj"));
        assertFalse(Regex.isScrabble("sdasdasad"));
        assertFalse(Regex.isScrabble("2"));
        assertFalse(Regex.isScrabble("asdasd2asasdad"));
        assertFalse(Regex.isScrabble("asd5"));
    }

    @Test
    public void testIsMoneyAmount() {
        assertTrue(Regex.isMoneyAmount("$32.32"));
        assertTrue(Regex.isMoneyAmount("$0.12"));
        assertTrue(Regex
                .isMoneyAmount("$234092340234923840230423089480239423.23"));
        assertFalse(Regex.isMoneyAmount("21.21"));
        assertFalse(Regex.isMoneyAmount("$.12"));
        assertFalse(Regex.isMoneyAmount("$0.123"));
        assertFalse(Regex.isMoneyAmount("$0.1"));
        assertFalse(Regex.isMoneyAmount("$.1"));
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(Regex.isPalindrome("baaaaaab"));
        assertTrue(Regex.isPalindrome("baaaaaaab"));
        assertTrue(Regex.isPalindrome("bAaaAaab"));
        assertFalse(Regex.isPalindrome("baaaaaad"));
        assertFalse(Regex.isPalindrome("baaaaaaad"));
    }
}
