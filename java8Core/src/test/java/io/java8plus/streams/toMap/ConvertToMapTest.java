package io.java8plus.streams.toMap;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.concurrent.ConcurrentHashMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConvertToMapTest {
  private ConvertToMap convertToMap=new ConvertToMap();
  private List<Book> bookList;

  @BeforeEach
  public void init() {
    bookList = new ArrayList<>();
    bookList.add(new Book("The Fellowship", 1954, "0395489318"));
    bookList.add(new Book("The Two", 1954, "0345339711"));
    bookList.add(new Book("The Return", 1955, "0618129111"));
  }
  @Test
  public void whenConvertFromListToMap() {
    assertTrue(convertToMap.listToMap(bookList).size() == 3);
  }
  @Test
  public void whenMapHasDuplicateKey_without_merge_function_then_runtime_exception() {
    Assertions.assertThrows(IllegalStateException.class, () ->  convertToMap.listToMapWithDupKeyError(bookList) );
  }
  @Test
  public void whenMapHasDuplicatedKeyThenMergeFunctionHandleCollision(){
      Map<Integer, Book> booksByYear=convertToMap.listToMapWithDupKey(bookList);
      assertEquals(2, booksByYear.size());
      assertEquals("0395489318", booksByYear.get(1954).getIsbn());
  }
  @Test
  public void whenCreateConcurrentHashMap(){
     assertTrue(convertToMap.listToConcurrentMap(bookList) instanceof ConcurrentHashMap);
  }
  @Test
  public void whenMapIsSorted(){
    assertTrue(convertToMap.listToSortedMap(bookList).firstKey().equals("The Fellowship"));
    assertTrue(convertToMap.listToSortedMap(bookList).ceilingKey("The Fellowshipa").equals("The Return"));

  }
}