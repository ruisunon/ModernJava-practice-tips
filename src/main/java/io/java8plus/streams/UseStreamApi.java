package io.java8plus.streams;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class UseStreamApi {
  public static void main(String[] args)
  {
    Stream<Integer> stream = Stream.of( new Integer[]{1,2,3,4,5,6,7,8,9} );
    //stream.forEach(p -> System.out.println(p));

    // generate() or iterate()
    Stream<String> randomNumbers = Stream.generate(() ->String.valueOf ((new Random()).nextInt(100)));
    //randomNumbers.limit(20).forEach(System.out::println);

    // String chars or tokens
    IntStream IntStream = "12345_abcdefg".chars();
    IntStream.forEach(p -> System.out.println(p));

    //OR
    Stream<String> strStream = Stream.of("A$B$C".split("\\$"));
    strStream.forEach(p -> System.out.println(p));
  }
}
