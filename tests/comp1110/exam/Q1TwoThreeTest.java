package comp1110.exam;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Q1TwoThreeTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(1000);

    @Test
    public void testEmpty() {
        test("", false);
    }

    @Test
    public void testOne() {
        test("2", true);
        test("6", true);
        test("7", false);
        test("9", true);
        test("0", false);
    }

    @Test
    public void testTwo() {
        test("33", true);
        test("22", true);
        test("62", true);
        test("52", false);
        test("25", false);
    }

    @Test
    public void testThree() {
        test("336", true);
        test("228", true);
        test("627", true);
        test("331", false);
        test("221", false);
        test("625", false);
        test("526", false);
        test("252", false);
    }

    @Test
    public void testFive() {
        test("3362", true);
        test("2286", true);
        test("6272", true);
        test("3363", true);
        test("2283", true);
        test("6273", true);
        test("3315", false);
        test("2213", false);
        test("6252", false);
        test("5262", false);
        test("2522", false);
    }

    private void test(String s, boolean e) {
        boolean r = Q1TwoThree.twothree(s);
        assertTrue("For string \""+s+"\", expected '"+e+"' but got '"+r+"'", e == r);
    }
}
