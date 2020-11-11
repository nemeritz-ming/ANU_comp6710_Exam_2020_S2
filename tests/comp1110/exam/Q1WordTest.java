package comp1110.exam;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Q1WordTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(2000);

    @Test
    public void testOne() {
        test(new char[]{'A'}, "A", 0);
        test(new char[]{'B'}, "A", -1);
    }

    @Test
    public void testTwo() {
        test(new char[]{'A', 'B', 'C', 'D'}, "AB", 0);
        test(new char[]{'A', 'B', 'C', 'D'}, "Z00", -1);
        test(new char[]{'A', 'B', 'C', 'D'}, "AD", -1);
        test(new char[]{'A', 'B', 'C', 'D'}, "BC", -1);
        test(new char[]{'A', 'B', 'C', 'D'}, "ABDC", 0);
        test(new char[]{'A', 'B', 'C', 'D'}, "DCAB", 3);
    }

    @Test
    public void testThree() {
        test(new char[]{'W','L','O','A','S','G','C','N','H'}, "LOGS", 1);
        test(new char[]{'Q','F','C','S','D','Y','N','L','M'}, "LOGS", -1);
        test(new char[]{'O','Z','C','M','I','C','R','A','H'}, "HARM", 8);
        test(new char[]{'W','H','Z','V','O','C','C','N','J'}, "HARM", -1);
        test(new char[]{'H','Z','N','X','K','A','C','S','Y'}, "KAYS", 4);
        test(new char[]{'R','X','T','I','U','G','J','L','I'}, "KAYS", -1);
        test(new char[]{'T','E','T','C','Y','M','T','T','R'}, "EL", -1);
        test(new char[]{'G','J','I','B','W','Z','N','O','O'}, "ZOON", 5);
        test(new char[]{'G','V','I','X','W','K','F','I','K'}, "ZOON", -1);
        test(new char[]{'Q','U','I','X','H','A','S','S','R'}, "SHA", 7);
    }

    @Test
    public void testFour() {
        test(new char[]{'F','W','Z','R','V','W','N','J','E','R','S','G','L','B','R','A'}, "GARBLERS", 11);
        test(new char[]{'A','D','S','F','O','T','K','L','J','B','U','G','J','F','H','T'}, "GARBLERS", -1);
        test(new char[]{'B','M','I','U','H','W','D','E','L','S','D','R','W','Z','Y','B'}, "DERBY", 6);
        test(new char[]{'Z','Q','A','Q','C','B','V','Q','U','F','B','J','P','M','V','H'}, "DERBY", -1);
        test(new char[]{'F','P','Z','N','D','S','A','B','N','F','B','A','U','O','T','S'}, "STOUND", 15);
        test(new char[]{'O','X','A','D','S','M','O','T','K','P','L','Q','S','K','B','W'}, "STOUND", -1);
        test(new char[]{'H','H','K','A','D','I','S','E','V','S','T','R','Y','P','E','R'}, "RESISTER", 11);
        test(new char[]{'V','M','D','X','U','J','Q','S','N','A','V','L','F','H','T','X'}, "RESISTER", -1);
        test(new char[]{'M','J','W','D','E','N','E','L','D','L','S','D','B','O','O','O'}, "LOOSENED", 9);
        test(new char[]{'U','H','N','K','E','H','M','Q','K','J','U','I','V','W','P','B'}, "LOOSENED", -1);
    }

    @Test
    public void testSix() {
        test(new char[]{'O','I','N','H','P','I','O','H','I','E','L','H','J','R','B','C','L','N','T','D','C','T','N','M','M','G','H','C','D','U','P','A','X','U','J','U'}, "JEMADAR", -1);
        test(new char[]{'Y','Q','L','A','F','D','Y','T','S','A','J','E','Z','I','C','E','S','W','E','U','T','X','H','R','Y','E','E','T','F','E','D','I','A','T','E','R'}, "SEXTETTE", 16);
        test(new char[]{'X','Q','P','X','S','I','E','B','B','O','P','S','H','N','O','H','A','J','C','E','S','A','E','J','N','W','E','V','F','R','K','J','H','G','W','U'}, "SEXTETTE", -1);
        test(new char[]{'G','S','R','Y','I','C','O','I','E','S','K','C','Q','H','A','C','R','A','X','P','X','A','N','X','X','N','M','B','K','Z','R','O','T','G','B','M'}, "CRACKS", 15);
        test(new char[]{'R','P','E','E','G','G','I','F','T','R','P','I','Y','E','K','W','I','I','X','C','V','K','J','S','Q','P','K','V','J','R','A','I','W','I','S','N'}, "CRACKS", -1);
        test(new char[]{'Q','M','R','J','E','K','Y','V','E','D','H','I','U','F','M','E','P','S','K','W','D','T','K','E','H','L','U','O','N','E','O','A','O','L','O','A'}, "DEMETON", 9);
        test(new char[]{'B','A','X','O','S','G','A','I','F','T','G','P','M','P','N','L','E','A','F','K','M','H','U','R','Q','V','U','R','Q','L','J','O','G','X','L','Q'}, "DEMETON", -1);
        test(new char[]{'W','D','T','C','O','G','I','R','T','M','O','L','P','T','Y','C','W','G','X','Z','J','A','X','B','P','B','B','X','F','N','L','Z','E','O','E','C'}, "TRIPTYCA", 8);
        test(new char[]{'H','N','A','I','B','V','A','O','X','G','L','J','K','C','O','U','V','P','U','G','P','M','S','D','B','M','W','X','W','H','P','H','T','G','Y','V'}, "TRIPTYCA", -1);
        test(new char[]{'Y','Y','K','D','Z','X','F','S','M','N','V','N','Q','Z','R','F','E','I','A','T','O','Z','E','T','K','L','U','B','P','R','R','O','T','O','M','I'}, "TRIMOTOR", 23);
    }

    private void test(char grid[], String t, int expected) {
        int result = Q1Word.find(grid, t);
        String arr = "{";
        for (int i = 0; i < grid.length; i++) {
            arr += (i > 0 ? ",":"")+"'"+grid[i]+"'";
        }
        arr += "}";

        assertTrue("Q1Word.find(" + arr + ", " + t + ") returned " + result + ", expected " + expected, result == expected);
    }
}
