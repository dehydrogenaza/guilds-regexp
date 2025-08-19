package com.softwaremind;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Groups {
  private static final String TEST_INPUT = """
      user@email.org,
      resetpassword@microsoft.ru,
      mail@company.com
      """;

  public static void main(String[] args) {
    emailParts();
  }

  private static final Pattern EMAIL_PARTS_PATTERN = Pattern.compile(
      "\\b(\\w+)@(\\w+)\\.(\\w+)\\b"
  );

  private static void emailParts() {
    Matcher emailPartsMatcher = EMAIL_PARTS_PATTERN.matcher(TEST_INPUT);

    System.out.println();
    System.out.println("find():");
    System.out.println("-----------------------------------------------------");

    while (emailPartsMatcher.find()) {
      System.out.println(" - MATCH: " + emailPartsMatcher.group());
      System.out.println("   Username: " + emailPartsMatcher.group(1));
      System.out.println("   Server: " + emailPartsMatcher.group(2));
      System.out.println("   Top Domain: " + emailPartsMatcher.group(3));
      System.out.println("-----------------------------------------------------");
    }

    System.out.println("-----------------------------------------------------");
  }

  private static final Pattern EMAIL_PARTS_PATTERN_NAMED = Pattern.compile(
      "\\b(?<username>\\w+)@(?<server>\\w+)\\.(?<topDomain>\\w+)\\b", Pattern.COMMENTS
  );

//  private static final Pattern EMAIL_PARTS_PATTERN_NAMED = Pattern.compile(
//      """
//            \\b                 # word-character boundary
//            (?<username>\\w+)   # at least one [a-zA-Z0-9_]
//            @                   #
//            (?<server>\\w+)     # at least one [a-zA-Z0-9_]
//            \\.                 # for simplicity, assuming a single dot (no subdomains)
//            (?<topDomain>\\w+)  # at least one [a-zA-Z0-9_]
//            \\b                 # word-character boundary
//            """,
//      Pattern.COMMENTS
//  );

//  private static void emailParts() {
//    Matcher emailPartsMatcher = EMAIL_PARTS_PATTERN_NAMED.matcher(TEST_INPUT);
//
//    System.out.println();
//    System.out.println("-----------------------------------------------------");
//    System.out.println("find():");
//
//    while (emailPartsMatcher.find()) {
//      System.out.println(" - MATCH: " + emailPartsMatcher.group());
//      System.out.println("   Username: " + emailPartsMatcher.group("username"));
//      System.out.println("   Server: " + emailPartsMatcher.group("server"));
//      System.out.println("   Top Domain: " + emailPartsMatcher.group("topDomain"));
//      System.out.println("-----------------------------------------------------");
//    }
//
//    System.out.println("-----------------------------------------------------");
//  }
}
