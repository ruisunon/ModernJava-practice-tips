package io.java8plus.streams.reduce;

import io.java8plus.streams.entities.NumberUtils;
import io.java8plus.streams.entities.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;

public class ReduceApp {

  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
    int result1 = numbers.stream().reduce(0, (a, b) -> a + b);
    System.out.println(result1);

    int result2 = numbers.stream().reduce(0, Integer::sum);
    System.out.println(result2);

    List<String> letters = Arrays.asList("a", "b", "c", "d", "e");
    String result3 = letters.stream().reduce("", (a, b) -> a + b);
    System.out.println(result3);

    String result4 = letters.stream().reduce("", String::concat);
    System.out.println("result4="+ result4);

    List<User> users = Arrays.asList(new User("John", 30), new User("Julie", 35));
    int result6 = users.stream().reduce(0, (partialAgeResult, user) -> partialAgeResult + user.getAge(), Integer::sum);
    System.out.println("result6="+ result6);

    String result7 = letters.parallelStream().reduce("", String::concat);
    System.out.println("result7="+ result7);

    int result8 =
        users.parallelStream().reduce(0, (partialAgeResult, user) -> partialAgeResult + user.getAge(), Integer::sum);
    System.out.println("result8="+result8);

    List<User> userList = new ArrayList<>();
    for (int i = 0; i <= 1000000; i++) {
      userList.add(new User("John" + i, i));
    }

    long t1 = System.currentTimeMillis();
    int result9 =
        userList.stream().reduce(0, (partialAgeResult, user) -> partialAgeResult + user.getAge(), Integer::sum);
    long t2 = System.currentTimeMillis();
    System.out.println(result9);
    System.out.println("Sequential stream time: " + (t2 - t1) + "ms");
//* @param <U> The type of the result
//     * @param identity the identity value for the combiner function
//        * @param accumulator: function for incorporating an additional element into a result
//        * @param combiner:  function for combining two values, which must be
//     *                    compatible with the accumulator function
//        * @return the result of the reduction
//     * @see #reduce(BinaryOperator)
//        * @see #reduce(Object, BinaryOperator)
//        */
//    <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator,  BinaryOperator<U> combiner);
    //
    long t3 = System.currentTimeMillis();
    int result10 =
        userList.parallelStream().reduce(0, (partialAgeResult, user) -> partialAgeResult + user.getAge(), Integer::sum);
    long t4 = System.currentTimeMillis();
    System.out.println(result10);
    System.out.println("Parallel stream time: " + (t4 - t3) + "ms");

    int result11 = NumberUtils.divideListElements(numbers, 1);
    System.out.println(result11);

    int result12 = NumberUtils.divideListElementsWithExtractedTryCatchBlock(numbers, 0);
    System.out.println(result12);
  }
}
