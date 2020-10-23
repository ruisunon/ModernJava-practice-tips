package io.java8plus.streams.toMap;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
public class ConvertToMap {
  public Map<String, String> listToMap(List<Book> books) {
    return books.stream().collect(Collectors.toMap(Book::getIsbn, Book::getName));
  }

  public Map<Integer, Book> listToMapWithDupKeyError(List<Book> books) {
    return books.stream().collect(Collectors.toMap(Book::getReleaseYear, Function.identity()));
  }

  public Map<Integer, Book> listToMapWithDupKey(List<Book> books) {
    return books.stream().collect(Collectors.toMap(Book::getReleaseYear, Function.identity(), (existing, replacement) -> existing));
  }

  public Map<Integer, Book> listToConcurrentMap(List<Book> books) {
    //    public static <T, K, U, M extends ConcurrentMap<K, U>> Collector<T, ?, M> toConcurrentMap(
    //        @NotNull Function<? super T, ? extends K> keyMapper,
    //        @NotNull Function<? super T, ? extends U> valueMapper,
    //        BinaryOperator<U> mergeFunction,
    //        Supplier<M> mapFactory)
    //  Map<Integer, Book> ans=books.stream().collect(Collectors.toMap(Book::getReleaseYear, Function.identity(), (obj1, obj2) -> obj2, ConcurrentHashMap::new));
    Map<Integer, Book> ans=books.stream().sorted(Comparator.comparing(Book::getIsbn).reversed()).collect(Collectors.toMap(Book::getReleaseYear, Function.identity(), (obj1, obj2) -> obj2, ConcurrentHashMap::new));
    System.out.println("con-map:" + ans);
    return books.stream().collect(Collectors.toMap(Book::getReleaseYear, Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
  }

  public TreeMap<String, Book> listToSortedMap(List<Book> books) {
    Map<String, Book> ans=books.stream().collect(Collectors.toMap(Book::getName, Function.identity(), (o1, o2) -> o1, TreeMap::new));
    System.out.println("tree-map:" + ans);
    return books.stream().collect(Collectors.toMap(Book::getName, Function.identity(), (o1, o2) -> o1, TreeMap::new));
  }
}
