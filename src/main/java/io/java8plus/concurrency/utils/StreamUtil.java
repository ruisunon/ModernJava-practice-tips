package io.java8plus.concurrency.utils;

import static java.util.Comparator.comparing;
import static java.util.function.UnaryOperator.identity;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class StreamUtil {

  public static <T, K, V> Function<T, Map.Entry<K, V>> toEntry(Function<T, K> keyFunc, Function<T, V> valueFunc){
    return t -> new AbstractMap.SimpleEntry<>(keyFunc.apply(t), valueFunc.apply(t));
  }

  public static <ITEM, FIELD extends Comparable<FIELD>> Optional<ITEM> maxBy(Function<ITEM, FIELD> extractor, Collection<ITEM> items) {
    return items.stream()
        .map(toEntry(identity(), extractor))
        .max(comparing(Map.Entry::getValue))
        .map(Map.Entry::getKey);
  }

 // Thing maxThing =  maxBy(Thing::getField, things).orElse(null);

 // AnotherThing maxAnotherThing = maxBy(AnotherThing::getAnotherField, anotherThings).orElse(null);
}
