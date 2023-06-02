package io.java8plus.streams.practice;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Stream.max() method:
//  Optional<T> max(Comparator<? super T> comparator)
//
public class StreamMaxExample {

  public static void main(String[] args) {
    // Creating a list of integers
    List<Integer> list = Arrays.asList(-9, -18, 0, 25, 4);

    // Using stream.max() to get maximum
    // element according to provided Comparator
    // and storing in variable var
    Integer var = list.stream().max(Integer::compare).get();
    Optional<Integer> var1 = list.stream().max(Comparator.reverseOrder());
    System.out.println(var);
    System.out.println(var1.get());

    // Creating a list of Strings
    List<String> list1 = Arrays.asList("G", "E", "E", "K","g", "e", "e", "k");

    // using Stream.max() method with Comparator
    // Here, the character with maximum ASCII value is stored in variable MAX
    String MAX = list1.stream().max(Comparator.comparing(String::valueOf)).get();

    // Displaying the maximum element in
    // the stream according to provided Comparator
    System.out.println("Maximum element in the "  + "stream is : " + MAX);

    // Create a collection of an array
    // of names also contain nulls
    String[] strings = { "aman", "suvam",  null, "sahil", null };
    String[] strings2 = { "amaee3n", "suv333am",  "dsdfasd", "sahasdfil", null };

    // print the array
    System.out.println("Before sorting: "  + Arrays.toString(strings));

    // apply nullsFirst method and sort the array
    Arrays.sort(strings,  Comparator.nullsFirst(Comparator.naturalOrder()));

    // print the array
    System.out.println("After sorting: " + Arrays.toString(strings));
    test();

    //great exmple
    // Using Stream in Java 8:

    String[] both = Stream.concat(Arrays.stream(strings), Arrays.stream(strings2))
        .toArray(String[]::new);
    //Or like this, using flatMap:

    String[] both2 = Stream.of(strings, strings2).flatMap(Stream::of)
        .toArray(String[]::new);
    System.out.println("After merging: " + Arrays.toString(both2));
    //To do this for a generic type you have to use reflection:

//    @SuppressWarnings("unchecked")
//    T[] both = Stream.concat(Arrays.stream(a), Arrays.stream(b)).toArray(
//        size -> (T[]) Array.newInstance(a.getClass().getComponentType(), size));
  }
  public static void test() {
    List<Integer> listOfIntegers = Arrays.asList(1, 2, 3, 4, 56, 7, 89, 10);
    Integer max = listOfIntegers.stream().mapToInt(v -> v).max().orElseThrow(NoSuchElementException::new);
    System.out.println("max:=" + max);
    Stream<Integer> numStream=Stream.of(1, 4, 2,3, 5);
    numStream.sorted(Comparator.reverseOrder()).forEach(System.out::println);
    System.out.println(Stream.of("foo", "test", "adb").sorted(Comparator.comparingInt(String::length).reversed()).collect(Collectors.toList()));
  }

  //To do this for a generic type you have to use reflection:

  //    @SuppressWarnings("unchecked")
  //    T[] both = Stream.concat(Arrays.stream(a), Arrays.stream(b)).toArray(
  //        size -> (T[]) Array.newInstance(a.getClass().getComponentType(), size));
//  @SuppressWarnings("unchecked")
//  public void merge(){
//
//        Object[] both = Stream.concat(Arrays.stream(a), Arrays.stream(b)).toArray(
//            size -> (Object[]) Array.newInstance(a.getClass().getComponentType(), size));
//  }
}
