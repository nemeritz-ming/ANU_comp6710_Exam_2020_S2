package comp1110.exam;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import java.util.Arrays;
import java.util.Random;

import static junit.framework.TestCase.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Q1FloorTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(500);

    public static int ITERATIONS = 10;
    Random r = new Random();

    @Test
    public void testEmpty() {
        test(new int[]{}, 2, 1);
        test(new int[]{}, 1, 0);
        test(new int[]{}, 0, -1);
        test(new int[]{}, -1, -2);
    }

    @Test
    public void testOne() {
        test(new int[]{1}, 0, 1);
        test(new int[]{2}, 2, 1);
        test(new int[]{2}, 1, 2);
        test(new int[]{3}, 2, 3);
        test(new int[]{-1}, 0, -1);
    }

    @Test
    public void testMultiple() {
        test(new int[]{1, 2}, 1, 2);
        test(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 1, 2);
        test(new int[]{2, 1}, 1, 2);
        test(new int[]{3, 2}, 1, 2);
        test(new int[]{8, 3, -5, 2, 1, 0}, 1, 2);
        test(new int[]{3, 1}, 1, 3);
        test(new int[]{0, 9}, 1, 9);
        test(new int[]{3, 1, 5, -4, 0}, 1, 3);
    }

    @Test
    public void testDuplicate() {
        test(new int[]{2, 2}, 1, 2);
        test(new int[]{3, 2, -2, 2}, 1, 2);
        test(new int[]{1, 3, 1}, 1, 3);
        test(new int[]{2, 2}, 1, 2);
    }

    @Test
    public void testNoneGreater() {
        test(new int[]{2, 2}, 2, 1);
        test(new int[]{-4, 2, 1}, 3, 2);
        test(new int[]{1, 3, 2}, 3, 2);
        test(new int[]{9, 7, 8}, 9, 8);
        test(new int[]{1, 0, 1}, 1, 0);
        test(new int[]{2, 2}, 2, 1);
        // trivial test
        test(new int[]{12, 2, 2}, 1, 2);
    }

    void test(int[] values, int floor, int expected) {
        String str = "";
        for (int i = 0; i < values.length; i++) {
            if (i != 0)
                str += ", ";
            str += values[i];
        }

        int result = Q1Floor.findGreater(values, floor);
        assertTrue("Q1Floor.findGreater(" + Arrays.toString(values) + ", " + floor + ") returned " + result + ", expected " + expected, result == expected);
    }
}
