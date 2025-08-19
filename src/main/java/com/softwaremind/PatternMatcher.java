package com.softwaremind;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatcher {
  private static final String TEST_INPUT = "user@email.org, resetpassword@microsoft.ru, mail@company.com";

  private static final Pattern SIMPLE_FULL_EMAIL_PATTERN = Pattern.compile(
    "\\b\\w+@\\w+\\.\\w+\\b"
  );

  public static void main(String[] args) {
    Matcher fullEmailMatcher = SIMPLE_FULL_EMAIL_PATTERN.matcher(TEST_INPUT);

    System.out.println("Input: " + TEST_INPUT);
    System.out.println("-----------------------------------------------------");

    System.out.println();
    System.out.println("matches(): " + fullEmailMatcher.matches());

//    fullEmailMatcher.region(20, TEST_INPUT.length());
//    fullEmailMatcher.useAnchoringBounds(false); //default true
//    fullEmailMatcher.useTransparentBounds(true); //default false (opaque bounds)

//    System.out.println();
//    System.out.println("lookingAt(): " + fullEmailMatcher.lookingAt() + "\n\t\tmatched at: " + fullEmailMatcher.start() + "-" + fullEmailMatcher.end() + " (" + fullEmailMatcher.group() + ")");


    System.out.println();
    System.out.println("find():");
    while (fullEmailMatcher.find()) {
      System.out.println(" - " + fullEmailMatcher.group());
    }
    System.out.println("-----------------------------------------------------");
  }
}
