package comp1110.exam;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Q4FerrisWheelTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(1000);

    static class Thing {
        final String name;

        public Thing(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Thing " + name;
        }
    }

    @Test
    public void testLoadNotEmpty() {
        Q4FerrisWheel<Thing> ferrisWheel = new Q4FerrisWheel<>(10);
        assertTrue("Newly created ferrisWheel should be empty!", ferrisWheel.isEmpty());
        Thing thing1 = new Thing("One");
        assertTrue("FerrisWheel.load() should not return false for empty wheel", ferrisWheel.load(thing1));
        assertFalse("FerrisWheel should not be empty after load", ferrisWheel.isEmpty());
    }

    @Test
    public void testLoadString() {
        Q4FerrisWheel<Thing> ferrisWheel = new Q4FerrisWheel<>(10);
        checkToString(ferrisWheel, ",,,,,,,,,");
        Thing thing1 = new Thing("One");
        assertTrue("FerrisWheel.load() should not return false for empty wheel", ferrisWheel.load(thing1));
        checkToString(ferrisWheel, "Thing One,,,,,,,,,");
    }

    private <T> void checkToString(Q4FerrisWheel<T> ferrisWheel, String expected) {
        assertTrue("Incorrect output from toString().  Expected \"" + expected + "\" but got \"" + ferrisWheel.toString() + "\"", expected.equals(ferrisWheel.toString()));
    }

    @Test
    public void testLoadUnload() {
        Q4FerrisWheel<Thing> ferrisWheel = new Q4FerrisWheel<>(8);
        Thing thingToDo = new Thing("to do");
        assertTrue("FerrisWheel.load() should not return false for empty wheel", ferrisWheel.load(thingToDo));
        checkUnload(ferrisWheel, thingToDo);
        assertTrue("FerrisWheel should be empty after one load and one unload", ferrisWheel.isEmpty());
    }

    @Test
    public void testLoadUnloadString() {
        Q4FerrisWheel<Thing> ferrisWheel = new Q4FerrisWheel<>(8);
        Thing thingToDo = new Thing("to do");
        assertTrue("FerrisWheel.load() should not return false for empty wheel", ferrisWheel.load(thingToDo));
        checkToString(ferrisWheel, "Thing to do,,,,,,,");
        checkUnload(ferrisWheel, thingToDo);
        checkToString(ferrisWheel, ",,,,,,,");
    }

    @Test
    public void testLoadSpinUnload() {
        final int SIZE = 7;
        Q4FerrisWheel<Thing> ferrisWheel = new Q4FerrisWheel<>(SIZE);
        Thing thing1 = new Thing("One");
        assertTrue("FerrisWheel.load() should not return false after one load and one spin", ferrisWheel.load(thing1));
        ferrisWheel.spin();
        Thing thing2 = new Thing("Two");
        assertTrue("FerrisWheel.load() should not return false after one load and one spin", ferrisWheel.load(thing2));
        checkUnload(ferrisWheel, thing2);
        assertFalse("FerrisWheel should not be empty after two loads and one unload", ferrisWheel.isEmpty());
        for (int i = 0; i < SIZE - 1; i++) {
            ferrisWheel.spin();
        }
        checkUnload(ferrisWheel, thing1);
        assertTrue("FerrisWheel should be empty after two loads and two unloads", ferrisWheel.isEmpty());
    }

    @Test
    public void testLoadSpinUnloadString() {
        final int SIZE = 7;
        Q4FerrisWheel<Thing> ferrisWheel = new Q4FerrisWheel<>(SIZE);
        Thing thing1 = new Thing("One");
        assertTrue("FerrisWheel.load() should not return false after one load and one spin", ferrisWheel.load(thing1));
        checkToString(ferrisWheel, "Thing One,,,,,,");
        ferrisWheel.spin();
        checkToString(ferrisWheel, ",,,,,,Thing One");
        Thing thing2 = new Thing("Two");
        assertTrue("FerrisWheel.load() should not return false after one load and one spin", ferrisWheel.load(thing2));
        checkToString(ferrisWheel, "Thing Two,,,,,,Thing One");
        checkUnload(ferrisWheel, thing2);
        checkToString(ferrisWheel, ",,,,,,Thing One");
        assertFalse("FerrisWheel should not be empty after two loads and one unload", ferrisWheel.isEmpty());
        for (int i = 0; i < SIZE - 1; i++) ferrisWheel.spin();
        checkToString(ferrisWheel, "Thing One,,,,,,");
        checkUnload(ferrisWheel, thing1);
        assertTrue("FerrisWheel should be empty after two loads and two unloads", ferrisWheel.isEmpty());
        checkToString(ferrisWheel, ",,,,,,");
    }

    @Test
    public void testUnloadEmptyWheel() {
        Q4FerrisWheel<String> ferrisWheel = new Q4FerrisWheel<>(11);
        assertNull("ferrisWheel.unload() should return null for empty wheel, but returned non-null", ferrisWheel.unload());
        assertTrue("FerrisWheel.load() should not return false for empty wheel", ferrisWheel.load("One"));
    }

    @Test
    public void testUnloadEmptyCar() {
        Q4FerrisWheel<String> ferrisWheel = new Q4FerrisWheel<>(15);
        assertTrue("FerrisWheel.load() should not return false for empty wheel", ferrisWheel.load("One"));
        ferrisWheel.spin();
        assertNull("ferrisWheel.unload() should return null for empty car, but returned non-null", ferrisWheel.unload());
    }

    @Test
    public void testLoadFullCar() {
        Q4FerrisWheel<String> ferrisWheel = new Q4FerrisWheel<>(16);
        assertTrue("FerrisWheel.load() should not return false for empty wheel", ferrisWheel.load("One"));
        assertFalse("ferrisWheel.load() should return false for full car, but returned true", ferrisWheel.load("Two"));
    }

    @Test
    public void testLoadFullDoesNotOverwrite() {
        Q4FerrisWheel<Thing> ferrisWheel = new Q4FerrisWheel<>(9);
        Thing thing1 = new Thing("One");
        ferrisWheel.load(thing1);
        Thing thing2 = new Thing("Two");
        assertFalse("ferrisWheel.load() should return false for full car, but returned true", ferrisWheel.load(thing2));
        // after failure to load thing2, thing1 should still be in car
        checkUnload(ferrisWheel, thing1);
    }

    @Test
    public void testLoadFullString() {
        Q4FerrisWheel<Thing> ferrisWheel = new Q4FerrisWheel<>(5);
        Thing thing1 = new Thing("One");
        ferrisWheel.load(thing1);
        checkToString(ferrisWheel, "Thing One,,,,");
        Thing thing2 = new Thing("Two");
        ferrisWheel.load(thing2);
        checkToString(ferrisWheel, "Thing One,,,,");
    }

    @Test
    public void testUnloadTwice() {
        Q4FerrisWheel<String> ferrisWheel = new Q4FerrisWheel<>(6);
        String a = "a";
        ferrisWheel.load(a);
        ferrisWheel.spin();
        String b = "b";
        ferrisWheel.load(b);
        String v1 = ferrisWheel.unload();
        assertTrue("ferrisWheel.unload() returned " + v1 + ", expected " + b, b.equals(v1));
        String v2 = ferrisWheel.unload();
        assertNull("ferrisWheel.unload() returned " + v2 + ", should be null", v2);
    }

    @Test
    public void testNoMemoryLeaks() {
        final int SIZE = 15;
        Q4FerrisWheel<Thing> ferrisWheel = new Q4FerrisWheel<>(SIZE);
        Thing thing1 = new Thing("One");
        ferrisWheel.load(thing1);
        ferrisWheel.spin();
        WeakReference<Thing> reference1 = new WeakReference<>(thing1);
        Thing thing2 = new Thing("Two");
        ferrisWheel.load(thing2);
        WeakReference<Thing> reference2 = new WeakReference<>(thing2);
        checkUnload(ferrisWheel, thing2);
        for (int i = 0; i < SIZE - 1; i++) ferrisWheel.spin();
        checkUnload(ferrisWheel, thing1);
        thing1 = null;
        thing2 = null;

        Runtime.getRuntime().gc();
        assertNull("A reference still exists to thing1 somewhere in the FerrisWheel!", reference1.get());
        assertNull("A reference still exists to thing2 somewhere in the FerrisWheel!", reference2.get());
    }

    @Test
    public void testLoadUnloadLarge() {
        List<String> range = IntStream.range(0, 3).boxed().map(String::valueOf).collect(Collectors.toList());
        Q4FerrisWheel<String> ferrisWheel = new Q4FerrisWheel<>(range.size());
        testCircle(range, ferrisWheel);
    }

    // assumes values list is initially as large as the wheel
    private void testCircle(List<String> values, Q4FerrisWheel<String> ferrisWheel) {
        for (String v : values) {
            assertTrue("ferrisWheel.load returned false for empty car, should return true", ferrisWheel.load(v));
            assertFalse("ferrisWheel should not be empty after loading " + v, ferrisWheel.isEmpty());
            ferrisWheel.spin();
        }
        for (String v : values) {
            String result = ferrisWheel.unload();
            assertTrue("ferrisWheel.pop() returned " + result + ", expected " + v, v.equals(result));
            ferrisWheel.spin();
        }
    }

    @Test
    public void testContainsBottom() {
        final int SIZE = 13;
        Q4FerrisWheel<Thing> ferrisWheel = new Q4FerrisWheel<>(SIZE);
        Thing thing1 = new Thing("One");
        Thing thing2 = new Thing("Two");
        assertFalse("ferrisWheel.contains(thing1) returned true, expected false", ferrisWheel.contains(thing1));
        assertFalse("ferrisWheel.contains(thing2) returned true, expected false", ferrisWheel.contains(thing2));
        ferrisWheel.load(thing1);
        assertTrue("ferrisWheel.contains(thing1) returned false, expected true", ferrisWheel.contains(thing1));
        ferrisWheel.spin();
        ferrisWheel.load(thing2);
        assertTrue("ferrisWheel.contains(thing2) returned false, expected true", ferrisWheel.contains(thing2));
        ferrisWheel.unload();
        assertTrue("ferrisWheel.contains(thing1) returned false, expected true", ferrisWheel.contains(thing1));
        for (int i = 0; i < SIZE - 1; i++) ferrisWheel.spin();
        ferrisWheel.unload();
        assertFalse("ferrisWheel.contains(thing1) returned true, expected false", ferrisWheel.contains(thing1));
        assertFalse("ferrisWheel.contains(null) returned true, expected false", ferrisWheel.contains(null));
    }

    @Test
    public void testContainsBuried() {
        final int SIZE = 4;
        Q4FerrisWheel<Thing> ferrisWheel = new Q4FerrisWheel<>(SIZE);
        Thing thing1 = new Thing("One");
        Thing thing2 = new Thing("Two");
        assertFalse("ferrisWheel.contains(thing1) returned true, expected false", ferrisWheel.contains(thing1));
        assertFalse("ferrisWheel.contains(thing2) returned true, expected false", ferrisWheel.contains(thing2));
        ferrisWheel.load(thing1);
        ferrisWheel.spin();
        assertTrue("ferrisWheel.contains(thing1) returned false, expected true", ferrisWheel.contains(thing1));
        assertFalse("ferrisWheel.contains(thing2) returned true, expected false", ferrisWheel.contains(thing2));
        ferrisWheel.load(thing2);
        ferrisWheel.spin();
        assertTrue("ferrisWheel.contains(thing1) returned false, expected true", ferrisWheel.contains(thing1));
        assertTrue("ferrisWheel.contains(thing2) returned false, expected true", ferrisWheel.contains(thing2));
        for (int i = 0; i < SIZE - 2; i++) ferrisWheel.spin();
        ferrisWheel.unload();
        assertFalse("ferrisWheel.contains(thing1) returned true, expected false", ferrisWheel.contains(thing1));
        assertTrue("ferrisWheel.contains(thing2) returned false, expected true", ferrisWheel.contains(thing2));
        ferrisWheel.spin();
        ferrisWheel.unload();
        assertFalse("ferrisWheel.contains(thing1) returned true, expected false", ferrisWheel.contains(thing1));
        assertFalse("ferrisWheel.contains(thing2) returned true, expected false", ferrisWheel.contains(thing2));
    }

    @Test
    public void testContainsRemoved() {
        Q4FerrisWheel<Thing> ferrisWheel = new Q4FerrisWheel<>(22);
        Thing thing1 = new Thing("One");
        ferrisWheel.load(thing1);
        assertTrue("ferrisWheel.contains(thing1) returned false, expected true", ferrisWheel.contains(thing1));
        ferrisWheel.unload();
        assertFalse("ferrisWheel.contains(thing1) returned true, expected flase", ferrisWheel.contains(thing1));
    }

    @Test
    public void testContainsDuplicateRemoved() {
        Q4FerrisWheel<Thing> ferrisWheel = new Q4FerrisWheel<>(7);
        Thing thing1 = new Thing("One");
        Thing thing2 = new Thing("Two");
        ferrisWheel.load(thing1);
        ferrisWheel.spin();
        ferrisWheel.load(thing2);
        ferrisWheel.spin();
        ferrisWheel.load(thing1);
        ferrisWheel.unload();
        assertTrue("ferrisWheel.contains(thing1) returned false, expected true", ferrisWheel.contains(thing1));
    }

    @Test
    public void testLoadUnloadTwiceString() {
        Q4FerrisWheel<Thing> ferrisWheel = new Q4FerrisWheel<>(9);
        Thing thingvellir = new Thing("vellir");
        ferrisWheel.load(thingvellir);
        ferrisWheel.unload();
        ferrisWheel.unload();
        checkToString(ferrisWheel, ",,,,,,,,");
        ferrisWheel.load(thingvellir);
        checkToString(ferrisWheel, "Thing vellir,,,,,,,,");
    }

    @Test
    public void testContainsLarge() {
        final int SIZE = 874;
        Q4FerrisWheel<String> ferrisWheel = new Q4FerrisWheel<>(SIZE);
        for (int i = 0; i < SIZE; i++) {
            ferrisWheel.load(String.valueOf(i));
            ferrisWheel.spin();
        }
        assertFalse("ferrisWheel.contains(null) returned true, expected false", ferrisWheel.contains(null));
        for (int i = 0; i < SIZE; i++) {
            assertTrue("ferrisWheel.contains(\"" + i + "\") returned false, expected true", ferrisWheel.contains(String.valueOf(i)));
        }
    }

    private void checkUnload(Q4FerrisWheel<Thing> ferrisWheel, Thing expected) {
        Thing result = ferrisWheel.unload();
        assertTrue("ferrisWheel.unload() returned " + result + ", expected " + expected, expected == result);
    }
}
