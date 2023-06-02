package io.functional.others;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class ComparatorDemo {
  public static void main(String[] args) {

    // initializing unsorted short array
    Short sArr[] = new Short[]{3, 13, 1, 9, 21};

    // let us print all the elements available in list
    for (short number : sArr) {
      System.out.println("Number = " + number);
    }

    // create a comparator
    Comparator<Short> comp = Collections.reverseOrder();

    // sorting array with reverse order using comparator
    Arrays.sort(sArr, comp);

    // let us print all the elements available in list
    System.out.println("short array with some sorted values(1 to 4) is:");
    for (short number : sArr) {
      System.out.println("Number = " + number);
    }
  }
}
