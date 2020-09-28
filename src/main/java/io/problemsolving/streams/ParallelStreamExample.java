package io.problemsolving.streams;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelStreamExample {

  public static void main(String[] args) {

    System.out.println("Normal...");

    IntStream range = IntStream.rangeClosed(1, 10);
    range.forEach(x -> {
      System.out.println("Thread : " + Thread.currentThread().getName() + ", value: " + x);
    });

    System.out.println("Parallel...");

    IntStream range2 = IntStream.rangeClosed(1, 10);
    range2.parallel().forEach(x -> {
      System.out.println("Thread : " + Thread.currentThread().getName() + ", value: " + x);
    });


    //
    streamArray();

  }

  public static void streamArray() {

    //int[] -> IntStream -> Stream<Integer> -> Integer[]
    int[] num = {3, 4, 5};

    //1. int[] -> IntStream
    IntStream stream = Arrays.stream(num);

    //2. IntStream -> Stream<Integer>
    Stream<Integer> boxed = stream.boxed();
    // to convert a stream of primitives , we must first box the elements in their wrapper classes,
    // then collect the wrapped objects in a collection  -> boxed stream

    //3. Stream<Integer> -> Integer[]
    Integer[] result = boxed.toArray(Integer[]::new);

    System.out.println(Arrays.toString(result));

    // one line
    Integer[] oneLineResult = Arrays.stream(num).boxed().toArray(Integer[]::new);
    System.out.println(Arrays.toString(oneLineResult));
  }

  public static void giveRangeOfLongs_whenSummedInParallel_shouldBeEqualToExpectedTotal()
      throws InterruptedException, ExecutionException {

    long firstNum = 1;
    long lastNum = 1_000_000;

    List<Long> aList = LongStream.rangeClosed(firstNum, lastNum).boxed()
        .collect(Collectors.toList());

    ForkJoinPool customThreadPool = new ForkJoinPool(4);
    long actualTotal = customThreadPool.submit(
        () -> aList.parallelStream().reduce(0L, Long::sum)).get();

  }


}