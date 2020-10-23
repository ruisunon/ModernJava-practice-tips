package io.java8plus.streams.collectors;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ReduceExample {
  // Driver code
  public static void main(String[] args)
  {
    ReduceExample re=new ReduceExample();
    // To get the product of all elements in given range excluding the
    // rightmost element

    long product1= LongStream.range(2, 8).dropWhile(a -> a>6).reduce( (n1, n2) -> n1*n2).orElse(-1);
    System.out.println("The product is : " + product1);
    int product = IntStream.range(2, 8)
        .reduce((num1, num2) -> num1 * num2)
        .orElse(-1);

    // Displaying the product
    System.out.println("The product is : " + product);
    re.dropwhileReduce();
  }

  void dropwhileReduce(){
    // create a stream of numbers from 1 to 10
    Stream<Integer> stream = Stream.of(4, 4, 5, 6, 7, 8, 9, 10);

    // apply dropWhile to drop all the numbers matches passed predicate
    List<Integer> list
        = stream.dropWhile(number -> (number / 4 == 1))
        .collect(Collectors.toList());

    // print list
    System.out.println(list);

    // create a stream of names
    Stream<String> streams = Stream.of("aman", "amar", "suraj", "suvam", "Zahafuj");

    // apply dropWhile to drop all the names
    // matches passed predicate
    List<String> list1
        = streams.dropWhile(name -> (name.charAt(0) == 'a'))
        .collect(Collectors.toList());

    // print list
    System.out.println(list1);

      IntStream stream3 = "12345_abcdefg".chars();
      stream3.forEach(p -> System.out.println(p));

      //OR

      Stream<String> stream6 = Stream.of("A$B$C".split("\\$"));
      stream6.forEach(p -> System.out.println(p));

  }

}
