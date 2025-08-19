package com.softwaremind;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Performance {
  public static void main(String[] args) {
//    anchorDemo();
    greedyDotDemo();
  }

  public static void anchorDemo() {
    String longText = "a".repeat(20_000_000) + "c";

    // non-anchored: tries from every index, scanning at least 20_000_000 times
    Pattern nonAnchoredPattern = Pattern.compile("a{5}b");
    long t1 = System.nanoTime();
    nonAnchoredPattern.matcher(longText).find(); // false, but had to check every possible starting index (times the number of remaining chars)
    long t2 = System.nanoTime();

    // anchored: has only one start position to check
    Pattern anchoredPattern = Pattern.compile("^a{5}b$"); // what you meant if validating the whole string
    long t3 = System.nanoTime();
    anchoredPattern.matcher(longText).find(); // false, fails after 6 characters
    long t4 = System.nanoTime();

    System.out.printf("non-anchored: took %.1f ms%n%n", (t2 - t1) / 1e6);
    System.out.printf("anchored: took %.3f ms%n", (t4 - t3)/1e6);
  }

  public static void greedyDotDemo() {
    //"aaaa,aaaa,aaaa,...,aaaa,SUCCESS!" - expecting 25x "aaaa," and then "SUCCESS!"
    //"aaaa,aaaa,aaaa,...,aaaa,FAIL!" - but we get 25x "aaaa," and then "FAIL!"
    //this blows up FAST - try with 30 or 50 fields
    final int FIELDS = 25;

    String longText = "aaaa,".repeat(FIELDS)
               + "FAIL!";

    //with dot
    Pattern dotPattern = Pattern.compile("^(.*,){"+FIELDS+"}SUCCESS!$");
    long t1 = System.nanoTime();
    Matcher m1 = dotPattern.matcher(longText);
    int c1 = 0;
    while (m1.find()) {
      //do something with the match
      c1++;
    }
    long t2 = System.nanoTime();

    // with precisely defined delimiter
    Pattern delimiterPattern = Pattern.compile("^([^,]]*,){"+FIELDS+"}SUCCESS!$");
    long t3 = System.nanoTime();
    Matcher m2 = delimiterPattern.matcher(longText);
    int c2 = 0;
    while (m2.find()) {
      //do something with the match
      c2++;
    }
    long t4 = System.nanoTime();

    //with dot
    Pattern possessivePattern = Pattern.compile("^(.*+,){"+FIELDS+"}SUCCESS!$");
    long t5 = System.nanoTime();
    Matcher m3 = possessivePattern.matcher(longText);
    int c3 = 0;
    while (m3.find()) {
      //do something with the match
      c3++;
    }
    long t6 = System.nanoTime();

    System.out.printf(".*,    - took %.1f ms - found %d%n", (t2 - t1)/1e6, c1);
    System.out.printf("[^,]*, - took %.1f ms - found %d%n", (t4 - t3)/1e6, c2);
    System.out.printf(".*+,   - took %.1f ms - found %d%n", (t6 - t5)/1e6, c3);
  }
}
