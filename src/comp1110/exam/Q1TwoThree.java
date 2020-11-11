package comp1110.exam;

/**
 * COMP1110 Final Exam, Question 1.2 (harder)
 *
 * 5 Marks
 */
public class Q1TwoThree {
  /**
   * Given a string, return true if the string represents a twothree number,
   * or false otherwise.
   *
   * A twothree number is recursively defined as an integer that is divisible
   * by either two or three AND the left n - 1 most digits of the number are
   * also a twothree number.
   *
   * So for example, 6 is a twothree number, and 62 is a twothree number since
   * 62 is divisible by 2 and the left n - 1 digits are '6', which is also
   * a twothree number.  22222 and 22223 are both twothree numbers.  267 is a
   * twothree number but 265 is not since 265 is not divisible by 2 or 3. 2652
   * is not a twothree number because 265 is not a twothree number.
   *
   * An empty string or a string with anything other than a digit from 0-9 is
   * not a twothree number.
   *
   * @param number A number
   * @return true if number is a twothree number as defined above.
   */
  public static boolean twothree(String number) {
    return true;  // FIXME complete this method
  }
}
