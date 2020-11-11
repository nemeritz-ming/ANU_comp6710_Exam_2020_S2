package comp1110.exam;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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
    if(checksum){
      try{
        List<Integer> box = new ArrayList<>();
        List<String> first = new ArrayList<>();
        FileReader in = new FileReader(input);
        int i = 0;
        while ((i = in.read()) != -1) {
          i = in.read();
          box.add(i);
        }
        for (int j = 0; j < box.size(); j++) {
          if (j % 10 == 0 && j + 10 < box.size()){
            first.add(String.valueOf((char)(sum(box.subList(j+1,j+11)) % 26 + 97)));
          }
          else if (j % 10 == 0){
            first.add(String.valueOf((char)(sum(box.subList(j+1,box.size())) % 26 + 97)));
          }
        }
        List<Byte> Final = new ArrayList<>();


      }catch (IOException e){
        e.printStackTrace();
      }
    }
    else{
      FileInputStream in = null;
      FileOutputStream out = null;

      try {
        in = new FileInputStream(input);
        out = new FileOutputStream(output);
        int i = 0;
        while ((i = in.read()) != -1) {
          byte b = (byte) i;
          out.write(b);
        }
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        try {
          in.close();
          out.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }


  }
  public static int sum(List<Integer> box){
    int ans = 0;
    for (Integer i : box){
      ans += i;
    }
    return ans;
  }

}
