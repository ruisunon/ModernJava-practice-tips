package io.java8plus.concurrency.utils;

import static java.util.Comparator.comparing;
import static java.util.function.UnaryOperator.identity;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamUtil {

  public static <T, K, V> Function<T, Map.Entry<K, V>> toEntry(Function<T, K> keyFunc, Function<T, V> valueFunc){
    return t -> new AbstractMap.SimpleEntry<>(keyFunc.apply(t), valueFunc.apply(t));
  }
  // Generic function to get Slice of a
  // Stream from startIndex to endIndex
  public static <T> Stream<T>  getSliceOfStream(Stream<T> stream, int startIndex, int endIndex)
  {
    return stream.collect(Collectors.collectingAndThen(

        // 1st argument
        // Convert the stream to list
        Collectors.toList(),

        // 2nd argument
        list -> list.stream()
            // specify the number of elements to skip
            .skip(startIndex)

            // specify the no. of elements the stream
            // that should be limited
            .limit(endIndex - startIndex + 1)));
  }

  public static <ITEM, FIELD extends Comparable<FIELD>> Optional<ITEM> maxBy(Function<ITEM, FIELD> extractor, Collection<ITEM> items) {
    return items.stream()
        .map(toEntry(identity(), extractor))
        .max(comparing(Map.Entry::getValue))
        .map(Map.Entry::getKey);
  }

 // Thing maxThing =  maxBy(Thing::getField, things).orElse(null);

 // AnotherThing maxAnotherThing = maxBy(AnotherThing::getAnotherField, anotherThings).orElse(null);
  /**
   * This method shows how to make a null safe stream from a collection  through the use of
   * Java SE 8’s Optional Container
   *
   * @param collection The collection that is to be converted into a stream
   * @return The stream that has been created from the collection or an empty stream if the collection is null
   */
  public Stream<String> collectionAsStream(Collection<String> collection) {

     return Optional.ofNullable(collection).map(Collection::stream).orElseGet(Stream::empty);
  }
  /**
   * This method shows how to make a null safe stream from a collection through the use of a check
   * to prevent null dereferences
   *
   * @param collection The collection that is to be converted into a stream
   * @return The stream that has been created from the collection or an empty stream if the collection is null
   */
  public Stream<String> collectionAsStream1(Collection<String> collection) {
    return collection == null ? Stream.empty() : collection.stream();
  }
}
