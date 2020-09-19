package io.java8plus.concurrency.collector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class CollectorExample {
  public static void main (String[] args) {
    String s = Stream.of("Mike", "Nicki", "John", "56", "0o").collect(new
        MyCollector());
    System.out.println(s);
    List<String> s2 = Stream.of("Mike", "Nicki", "John")
        .parallel()
        .collect(new MyCollector2());
    System.out.println(s);

    String s1 = Stream.of("Mike", "Nicki", "John")
        .parallel()
        .unordered()
        .collect(new MyCollector1());
  }

  private static class MyCollector implements Collector<String, StringBuilder, String> {

    @Override
    public Supplier<StringBuilder> supplier () {
      return StringBuilder::new;
    }

    @Override
    public BiConsumer<StringBuilder, String> accumulator () {
      return (sb, s) -> sb.append(", ").append(s);
    }

    @Override
    public BinaryOperator<StringBuilder> combiner () {
      return StringBuilder::append;
    }

    @Override
    public Function<StringBuilder, String> finisher () {
      return stringBuilder -> stringBuilder.toString();
    }

    @Override
    public Set<Characteristics> characteristics () {
      return Collections.emptySet();
    }
  }
  private static class MyCollector1 implements
      Collector<String, StringBuffer, String> {

    @Override
    public Supplier<StringBuffer> supplier () {
      return () -> {
        System.out.println("supplier call");
        return new StringBuffer();
      };
    }

    @Override
    public BiConsumer<StringBuffer, String> accumulator () {
      return (sb, s) -> {
        System.out.println("accumulator function call,"
            + "accumulator container: "
            + System.identityHashCode(sb)
            + " thread: "
            + Thread.currentThread().getName()
            + ", processing: " + s);
        sb.append(" ").append(s);
      };
    }

    @Override
    public BinaryOperator<StringBuffer> combiner () {
      return (stringBuilder, s) -> {
        System.out.println("combiner function call");
        return stringBuilder.append(s);
      };
    }

    @Override
    public Function<StringBuffer, String> finisher () {
      return stringBuilder -> stringBuilder.toString();
    }

    @Override
    public Set<Characteristics> characteristics () {
      // return Collections.emptySet();
      return EnumSet.of(Collector.Characteristics.CONCURRENT);
    }
  }

  private static class MyCollector2 implements Collector<String, List<String>, List<String>> {

    @Override
    public Supplier<List<String>> supplier () {
      return ArrayList::new;
    }

    @Override
    public BiConsumer<List<String>, String> accumulator () {
      return List::add;
    }

    @Override
    public BinaryOperator<List<String>> combiner () {
      return (list, list2) -> {
        list.addAll(list2);
        return list;
      };
    }

    @Override
    public Function<List<String>, List<String>> finisher () {
      return null;
    }

    @Override
    public Set<Characteristics> characteristics () {
      //   return Collections.emptySet();
      return EnumSet.of(Characteristics.IDENTITY_FINISH);
//      return EnumSet.of(Collector.Characteristics.CONCURRENT
//          , Characteristics.UNORDERED);
    }
  }
}