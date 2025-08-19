package com.softwaremind;

public class RegexWarmUp {
  public static void main(String[] args) throws InterruptedException {
    //test using regex101.com (not an ad btw)
    var bd = new  java.math.BigDecimal("123");
    var thr = new Thread(() -> System.out.println("Thread is running"));
    var warmUp = new RegexWarmUp();

    System.out.println("BigDecimal: " + bd);
    thr.start();
    thr.join();
  }
}