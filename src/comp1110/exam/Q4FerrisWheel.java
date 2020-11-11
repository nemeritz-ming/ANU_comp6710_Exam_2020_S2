package comp1110.exam;

/**
 * COMP1110 Final Exam, Question 4
 *
 * 20 Marks
 *
 * A ferris wheel has a fixed number of cars arranged in a circle.
 * Each car may either be empty, or hold a single object of type T.
 * At any time, one car is at the bottom of the wheel,
 * and all other cars are in the air.
 * Objects can be loaded or unloaded from the car at the bottom,
 * and cannot be loaded or unloaded from any other car.
 * The wheel can spin around to move a new car to the bottom.
 */
public class Q4FerrisWheel<T> {
    /**
     * Create a FerrisWheel with the given number of cars.
     *
     * @param numberOfCars the number of cars in the FerrisWheel
     */
    public Q4FerrisWheel(int numberOfCars) {
        // FIXME complete this constructor
    }

    /**
     * @return true if this ferris wheel is empty, that is, if
     * every car is empty
     */
    public boolean isEmpty() {
        // FIXME complete this method
        return false;
    }

    /**
     * If the bottom car is empty, load the given object into the car
     * and return true; otherwise return false.
     *
     * @param obj the object to load into the car; must not be null
     * @return true if the object was loaded
     */
    public boolean load(T obj) {
        // FIXME complete this method
        return false;
    }

    /**
     * If the bottom car is not empty, remove the object from the bottom
     * car and return it.
     * Otherwise, return null.
     *
     * @return the object that was in the bottom car, or null if no such object
     */
    public T unload() {
        // FIXME complete this method
        return null;
    }

    /**
     * Spin this ferris wheel, moving all cars forward around the wheel by one
     * position (thereby changing the bottom car).
     * The last car becomes the first car,
     * the first car becomes the second car, and so on.
     */
    public void spin() {
        // FIXME complete this method
    }

    /**
     * Check whether a given value is contained in this ferris wheel.
     * Specifically, returns true if value is not null and
     * an element e is contained in this ferris wheel such that e.equals(value).
     *
     * @param value the value to search for
     * @return true if the value is contained in this ferris wheel
     */
    public boolean contains(T value) {
        // FIXME complete this method
        return false;
    }

    /**
     * Create a String representation of this ferris wheel.
     * Objects are listed in order around the wheel, starting with the bottom
     * car, then the car that is next in order to become the bottom car, and
     * so on ending with the car that is last in order to become the bottom
     * car.
     * Values in the wheel are separated by commas (without spaces).
     * Each value is converted to string as by {@link String#valueOf(Object)}.
     * If a car is empty (null), its value is represented by the empty string,
     * still including a comma to mark its place in the wheel.
     * For example, a wheel of five cars in which:
     * - the bottom car contains "b"
     * - the following car contains "c"
     * - the following car is empty
     * - the following car is empty
     * - the following car contains "a"
     * would be represented as "b,c,,,a".
     * A wheel of three cars where the bottom car contains "zz" and the other
     * cars are empty would be represented as "zz,,".
     * An empty wheel of seven cars would be represented as ",,,,,,".
     *
     * @return a String representation of this ferris wheel
     */
    public String toString() {
        // FIXME complete this method
        return null;
    }
}

