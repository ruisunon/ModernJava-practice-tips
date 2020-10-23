package io.java8plus.streams.practice;

import io.java8plus.streams.Notes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

//How to convert a List of objects into a Map by considering duplicated keys and store them in sorted order?

public class StreamSortedOrderExample {
  public static void main(String[] args) {

    List<Notes> notes= new ArrayList<>();
    notes.add(new Notes(1, "note1", 11));
    notes.add(new Notes(2, "note2", 22));
    notes.add(new Notes(3, "note3", 33));
    notes.add(new Notes(4, "note4", 44));
    notes.add(new Notes(5, "note5", 55));
    notes.add(new Notes(6, "note4", 66));
    notes.add(new Notes(7, "note3", 77));
    // 1)
    notes.forEach(System.out::println);
    // Using Stream and filter
    //Output : note2
    notes.stream()
        .filter(s->s.getTagName().contains("note2"))
        .forEach(System.out::println);

    // 2)
    // How to check if list is empty in Java 8 using Optional, if not null iterate through the list and print the object
    Optional.ofNullable(notes)
        .orElseGet(Collections::emptyList) // creates empty immutable list: [] in case noteLst is null
        .stream().filter(Objects::nonNull) //loop throgh each object and consider non null objects
        .map(note -> note.getTagName()) // method reference, consider only tag name
        .forEach(System.out::println); // it will print tag names

    //Older way to sort, before java 8
//    notes.sort(new Comparator<Notes>() {
//      @Override
//      public int compare(Notes n1, Notes n2) {
//        return n1.getId()-n2.getId();
//      }
//    });

    // java 8 sort according to id 1,2,3,4,5
    notes.sort((n1, n2)->n1.getId()-n2.getId());

    //java 8 print the notes using lamda
    notes.forEach((note)->System.out.println(note));
    //The static overloaded methods, Collectors.toMap() return a Collector which produces a new instance of Map, p
    // opulated with keys per provided keyMapper function and values per provided valueMap function.
//    <T,K,U,M extends Map<K,U>> Collector<T,?,M> toMap(
//
//        Function<? super T,? extends K> keyMapper,
//
//        Function<? super T,? extends U> valueMapper,
//
//        BinaryOperator<U> mergeFunction,
//
//        Supplier<M> mapSupplier)
//
//    Parameters
//
//    keyMapper: A mapping function to produce the map keys for each input stream element.
//
//    valueMapper: A mapping function to produce the map values for each input stream element.
//
//    mergeFunction: A binary operator which is to resolve collisions between values associated
    //   with the same key. The inputs to this function are the values which belong to the same key.
//
//    mapSupplier: A function which provides a new instance of the desired implementation of the Map.
        Map<String, Long> notesRecords = notes.stream()
        .sorted(Comparator.comparingLong(Notes::getTagId).reversed())
        // sorting is based on TagId 55,44,33,22,11
        // consider old value 44 for dupilcate key, it keeps order
        .collect(Collectors.toMap(
            Notes::getTagName,  //key mapper
            Notes::getTagId,    // value mapper
            (oldValue, newValue) -> newValue,  // merge function -> BinaryOperator
            LinkedHashMap::new)
        );

    System.out.println("Notes : " + notesRecords);
  }
}
