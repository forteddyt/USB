import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class Regex2Test {
    private String[] tempNames = { "John Doe", "Doe, Jane", "John W Booth",
            "Teddy", "Rabena" };
    private String[] ansNames = { "Booth, John W", "Doe, Jane", "Doe, John" };

    @Test
    public void test() {
        assertArrayEquals(ansNames, (Regex2.getGuestNames(tempNames)));
    }
}
