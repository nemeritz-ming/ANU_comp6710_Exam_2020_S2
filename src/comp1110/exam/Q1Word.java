package comp1110.exam;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * COMP1110 Final Exam, Question 1.3 (harder)
 *
 * 5 Marks
 */
public class Q1Word {
  /**
   * This question is about a word finding puzzle.
   *
   * You will be given a square grid of upper case characters ('A' to 'Z'),
   * represented as a single array of chars.   Each value in the grid is a
   * letter between 'A' and 'Z'. You will also be given a target word.
   * Your task is to try to find the target word within the grid.  If you find
   * it, you return the index of the grid element holding the first letter of
   * the word.  If you can't find it, return -1.
   *
   * Words can be 'hidden' in the grid by consecutive letters being in
   * horizontally or vertically adjacent elements (but not diagonally).
   * When creating words, letters in the grid may only be used ONCE.
   *
   *
   *         test(new char[]{'W','L','O','A','S','G','C','N','H'}, "LOGS", 1);
   *
   * For example, given the 3 x 3 gird [W, L, O, A, S, G, C, N, H] and the
   * target "LOGS", you would return 1.   Visualizing the grid:
   *
   *    W L O
   *    A S G
   *    C N H
   *
   *    _ L O
   *    _ S G
   *    _ _ _
   *
   * The word LOGS is 'hidden' in the upper right four elements of the grid,
   * starting at offset 1 then going left, then down, then right is 1.
   *
   *
   *
   *         test(new char[]{'G','S','R','Y','I','C','O','I','E','S','K','C','Q','H','A','C','R','A','X','P','X','A','N','X','X','N','M','B','K','Z','R','O','T','G','B','M'}, "CRACKS", 15);

   * Given the 6 x 6 grid [G, S, R, Y, I, C, O, I, E, S, K, C, Q, H, A, C, R, A, X, P, X, A, N, X, X, N, M, B, K, Z, R, O, T, G, B, M],
   * and the target CRACKS, you would return 15.  Visualizing the grid:
   *
   *  G S R Y I C
   *  O I E S K C
   *  Q H A C R A
   *  X P X A N X
   *  X N M B K Z
   *  R O T G B M
   *
   *  _ _ _ _ _ _
   *  _ _ _ S K C
   *  _ _ _ C R A
   *  _ _ _ _ _ _
   *  _ _ _ _ _ _
   *  _ _ _ _ _ _
   *
   *  The word CRACKS is 'hidden' in the grid starting at location 15,
   *  then going right, then right, the up, then left, then left.
   *
   * @param grid An array of characters between A and Z, where the size of the
   *             array is square (1, 4, 9, 16 etc).
   * @param target A target word which may be hidden in the grid.
   * @return If the target word is hidden in the grid, return the location
   * (index) within the grid of the first letter of the target word, or -1
   * if it is not in the grid.
   */
  public static int find(char[] grid, String target) {
    return -2;  // FIXME complete this method
  }
}
