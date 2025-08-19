package com.softwaremind;

public class RegexInString {
  private static final String TEST_INPUT = "abcTEST.defTESTghi";

  public static void main(String[] args) {
    String regExpPretendingToBeAString = "TEST.";

    System.out.println();
    System.out.println("Is full match? " + TEST_INPUT.matches(regExpPretendingToBeAString));

    //annoyingly, this won't work, because contains() takes a literal CharSequence :(
    //System.out.println("Does it contain a match? " + TEST_INPUT.contains(regExpPretendingToBeAString));

    //will work with replaceAll() and replaceFirst()... but not with replace(), for whatever reason
    System.out.println();
    System.out.println("Replaced all: " + TEST_INPUT.replaceAll(regExpPretendingToBeAString, "[SNIP]"));

    System.out.println();
    System.out.println("Split:");
    String[] splitResult = TEST_INPUT.split(regExpPretendingToBeAString);
    for (String part : splitResult) {
      System.out.println(" - " + part);
    }
  }
}
