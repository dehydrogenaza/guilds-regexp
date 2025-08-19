package com.softwaremind;

import java.util.regex.Pattern;

public class Replacements {
  private static final String TEST_INPUT = """
      Name: Edward "Winnie" Pooh
      Nationality: English
      Address: 100 Acre Wood, East Sussex, England
      
      Name: Moomintroll
      Nationality: Finnish
      Address: Moominvalley, Arctic Circle
      
      Name: Papa Smurf
      Nationality: Belgian
      Address: Smurf Village
      
      THE END
      """;

  private static final Pattern CONTACT_INFO_PATTERN = Pattern.compile(
      "(?m)"
      + "^Name:\\s+(?<name>.+)$\\s*"
      + "^Nationality:\\s+(?<nationality>.+)$\\s*"
      + "Address:\\s+(?<address>(?<addressMain>[\\w\\s]+?)(?<addressRest>,.+)?)$"
  );

  public static void main(String[] args) {
    var contactInfoMatcher = CONTACT_INFO_PATTERN.matcher(TEST_INPUT);

    System.out.println();
    System.out.println("find():");
    System.out.println("-----------------------------------------------------");

    while (contactInfoMatcher.find()) {
      System.out.println(" - Name: " + contactInfoMatcher.group("name"));
      System.out.println(" - Nationality: " + contactInfoMatcher.group("nationality"));
      System.out.println(" - Address: " + contactInfoMatcher.group("address"));
      System.out.println(" - Address Main: " + contactInfoMatcher.group("addressMain"));
      System.out.println("-----------------------------------------------------");
    }

    simpleReplace();
    customReplace();
  }

  private static void simpleReplace() {
    var contactInfoMatcher = CONTACT_INFO_PATTERN.matcher(TEST_INPUT);

    System.out.println();
    System.out.println("replaceAll():");
    System.out.println("-----------------------------------------------------");

    System.out.println("*** Top Secret ***");
    String replaced1 = contactInfoMatcher.replaceAll(
        "[TOP SECRET]"
    );
    System.out.println(replaced1);
    System.out.println("-----------------------------------------------------");

    //    contactInfoMatcher.reset(); //not needed!

    System.out.println("*** With numbered replacement groups ***");
    String replaced2 = contactInfoMatcher.replaceAll(
        "$1 ($4)"
    );
    System.out.println(replaced2);
    System.out.println("-----------------------------------------------------");

    System.out.println("*** With named replacement groups ***");
    String replaced3 = contactInfoMatcher.replaceAll(
        "${name} (${addressMain})"
    );
    System.out.println(replaced3);
    System.out.println("-----------------------------------------------------");

    System.out.println("*** With dollar sign ***");
    String replaced4 = contactInfoMatcher.replaceAll(
        "${name} Cash: 50$"
    );
//    String replaced4 = contactInfoMatcher.replaceAll(
//        Matcher.quoteReplacement("${name} Cash: 50$")
//    );
    System.out.println(replaced4);
    System.out.println("-----------------------------------------------------");
  }

  private static void customReplace() {
    var contactInfoMatcher = CONTACT_INFO_PATTERN.matcher(TEST_INPUT);

    System.out.println();
    System.out.println("replaceAll() with custom replacement:");
    System.out.println("-----------------------------------------------------");

    String replaced1 = contactInfoMatcher.replaceAll(matchResult -> {
      String name = matchResult.group("name");
      String addressMain = matchResult.group("addressMain");
      return name + " lives in " + addressMain.toUpperCase();
    });
    System.out.println(replaced1);
    System.out.println("-----------------------------------------------------");

    contactInfoMatcher.reset();

    var replaced2 = new StringBuilder(); //StringBuilder if we don't care about thread safety
    while (contactInfoMatcher.find()) {
      String name = contactInfoMatcher.group("name");
      String addressMain = contactInfoMatcher.group("addressMain");
      contactInfoMatcher.appendReplacement(replaced2, name + " lives in " + addressMain.toUpperCase());
    }
//    contactInfoMatcher.appendTail(replaced2);
    System.out.println(replaced2);
    System.out.println("-----------------------------------------------------");
  }
}
