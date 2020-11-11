package comp1110.exam;

/**
 * COMP1110 Final Exam, Question 2
 *
 * 5 Marks
 */
public class Q2Checksum {

  /**
   * Q2 Part I (2 marks)
   *
   * Open the specified input file.  If the checksum boolean is false, simply
   * copy all bytes of the input file to the specified output file.  Part II
   * covers the case where the checksum boolean is true.
   *
   *
   * Q2 Part II (3 marks)
   *
   * Open the specified input file.  If the checksum boolean is true and there
   * is at least 1 byte read in then copy all bytes of the input file to the
   * output file, inserting checksum letters before every ten (10) bytes, or
   * less if the end of file is reached. Otherwise simply copy all bytes of the
   * input file to the specified output file.
   *
   * The checksum is a letter from 'a' to 'z' which represents the integer
   * remainder of the sum of the next ten bytes (or all remaining bytes if
   * there are less than ten).
   *
   * So, if the file contained one byte, which had the value 78 ('N'), then
   * the checksum would 78 % 26 = 0, so the checksum character would be 'a',
   * and the output file would contain two bytes: 'a' followed by 'N'.
   *
   * If the input file contained two bytes which had the values 78 and 104
   * ('Nh'), then the checksum would be (78 + 104) % 26 = 25, so the checksum
   * character would be 'z', and the output file would contain three bytes: 'z'
   * followed by 'Nh'.
   *
   *
   * @param input The name of the input file
   * @param output The name of the output file
   * @param checksum if true, include checksums in the output file
   */
  public static void checksum(String input, String output, boolean checksum) {
    // FIXME complete this method
  }
}
