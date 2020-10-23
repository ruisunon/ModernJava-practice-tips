package io.java8plus.streams.aggregate;

import java.math.BigDecimal;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter
@Getter
@ToString

public class Item implements Comparable<Item>{

  private String name;
  private int amount;
  private BigDecimal price;
  private int intPrice;

  @Override
  public int compareTo(Item o) {
    return this.name.compareTo(o.getName());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Item item = (Item) o;
    return amount == item.amount && intPrice == item.intPrice && Objects.equals(name, item.name) && Objects
        .equals(price, item.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, amount, price, intPrice);
  }
}