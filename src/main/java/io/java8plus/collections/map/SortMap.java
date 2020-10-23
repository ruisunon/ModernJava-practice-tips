package io.java8plus.collections.map;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class SortMap {

  public static void main(String[] args) {
    Map<String, Integer> someMap = getUnSortedMap2();
    Set<Entry<String, Integer>> entries = someMap.entrySet();
    Set<String> keySet = someMap.keySet();
    Collection<Integer> values = someMap.values();

    Stream<Entry<String, Integer>> entriesStream=entries.stream();
    // get map's keys
    Optional<String> optionalName=someMap.entrySet().stream().filter(e-> e.getValue()>800).map(Map.Entry::getKey).findFirst();
    System.out.println("optional name : " + optionalName.orElse("dddd"));
    Map<Integer, String> unSortedMap = getUnSortedMap();
    System.out.println("Unsorted Map : " + unSortedMap);

    //LinkedHashMap preserve the ordering of elements in which they are inserted
    LinkedHashMap<Integer, String> sortedMap = new LinkedHashMap<>();

    unSortedMap.entrySet().stream().sorted(Map.Entry.comparingByValue())
        .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

    System.out.println("Sorted Map   : " + sortedMap);

    //Use Comparator.reverseOrder() for reverse ordering
    //LinkedHashMap preserve the ordering of elements in which they are inserted
    LinkedHashMap<Integer, String> reverseSortedMap = new LinkedHashMap<>();
    unSortedMap.entrySet()
        .stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

    System.out.println("Reverse Sorted Map   : " + reverseSortedMap);
  }

  public static Map<Integer, String> getUnSortedMap() {
    Map<Integer, String> map = new HashMap<Integer, String>();

    map.put(101, "Hemendra");
    map.put(99, "Andrew");
    map.put(103, "Anish");
    map.put(18, "Mohan");
    map.put(11, "Christine");
    map.put(109, "Rebeca");
    map.put(111, "David");
    map.put(19, "Rahim");
    map.put(10, "Krishna");
    return map;
  }
  public static Map<String, Integer> getUnSortedMap2() {
    Map<String, Integer> map = new HashMap<String, Integer>();

    map.put( "Hemendra", 101);
    map.put("Andrew", 99);
    map.put( "Anish",23);
    map.put("Mohan",44);
    map.put("Christine",56);
    map.put( "Rebeca",12);
    map.put( "David",32);
    map.put("Rahim",44);
    map.put("Krishna", 22);
    return map;
  }
}
