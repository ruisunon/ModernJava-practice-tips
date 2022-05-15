package io.java8plus.streams.spliterator;

import java.util.*;

public class SpliteratorTryAdvanceExample {
     public static void main(String[] args){
          List<String> list = Arrays.asList("Apple", "Banana", "Orange");
          Spliterator<String> s11 = list.spliterator();
          Spliterator<String> s12 = s11.trySplit();
          System.out.println(s11.estimateSize());
          System.out.println(s12.getExactSizeIfKnown());
          s11.forEachRemaining(System.out::println);
          System.out.println("-- traversing the other half of the spliterator --- ");
          s12.forEachRemaining(System.out::println);
          System.out.println(s11.estimateSize());
          System.out.println(s12.getExactSizeIfKnown());
          list = Arrays.asList("Apple", "Banana", "Orange");
          Spliterator<String> s=list.spliterator();
          s.tryAdvance(System.out::println);
          System.out.println(" --- bulk traversal");
          s.forEachRemaining(System.out::println);

          System.out.println(" --- attempting tryAdvance again");
          boolean b = s.tryAdvance(System.out::println);
          System.out.println("Element exists: " + b);

          SortedSet<Test> set =
                  new TreeSet<>((o1, o2) ->
                          o1.str.compareTo(o2.str));

          set.add(new Test("two"));
          set.add(new Test("one"));
          Spliterator<Test> sr = set.spliterator();
          System.out.println(sr.getComparator());
          System.out.println(set);
     }

     private static class Test {
          private final String str;

          private Test (String str) {
               this.str = str;
          }

          @Override
          public String toString () {
               return "Test{str='" + str + "'}";
          }
     }
}
