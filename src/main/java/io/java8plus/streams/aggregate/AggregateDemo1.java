package io.java8plus.streams.aggregate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AggregateDemo1 {
  public static void main(String[] args) {
    // method one
    Map<Item, List<Item>> newList = getList().stream().collect(Collectors.groupingBy(Item::getName)).entrySet().stream()
        .collect(Collectors.toMap(x -> {
          int sumAmount = x.getValue().stream().mapToInt(Item::getAmount).sum();
          int sumPrice = x.getValue().stream().mapToInt(Item::getIntPrice).sum();
          return new Item(x.getKey(), sumAmount, BigDecimal.valueOf(sumAmount), sumPrice);
        }, Map.Entry::getValue, (e1, e2) -> e2, TreeMap::new));
    System.out.println("newList: " + newList.toString());

    // method two
    newList = getList().stream().collect(Collectors.groupingBy(Item::getName)).entrySet().stream().collect(Collectors
        .toMap(e -> e.getValue().stream().collect(Collectors.reducing(
            (l, r) -> new Item(l.getName(), l.getAmount() + r.getAmount(), BigDecimal.valueOf(l.getIntPrice() + r.getIntPrice()),
                l.getIntPrice() + r.getIntPrice()))).get(),
            e -> e.getValue(), (e1, e2) -> e1, TreeMap::new));
    System.out.println("newList: " + newList.toString());

  }

  public static List<Item> getList() {
    List<Item> items = Arrays
        .asList(new Item("apple", 10, new BigDecimal("9.99"), 10),
            new Item("banana", 20, new BigDecimal("19.99"), 20),
            new Item("orange", 10, new BigDecimal("29.99"), 30),
            new Item("watermelon", 103, new BigDecimal("329.99"), 330),
            new Item("papaya", 20, new BigDecimal("9.99"), 10),
            new Item("apple", 10, new BigDecimal("9.99"), 10),
            new Item("orange", 110, new BigDecimal("229.99"), 230),
            new Item("watermelon", 10, new BigDecimal("29.99"), 30),
            new Item("papaya", 20, new BigDecimal("9.99"), 10),
            new Item("apple", 120, new BigDecimal("129.99"), 130),
            new Item("banana", 10, new BigDecimal("19.99"), 20),
            new Item("apple", 20, new BigDecimal("9.99"), 10));
    return items;
  }
  final class Pair {
    final int amount;
    final int price;

    Pair(int amount, int price) {
      this.amount = amount;
      this.price = price;
    }
  }
}
