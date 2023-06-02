package io.functional.functionalinterface;

import java.util.List;
import java.util.function.Supplier;

public class MySupplier {

  public static void main(String[] args) {
    System.out.println(getDBConnectionUrl());
    System.out.println(getDBConnectionUrlsSupplier.get());
  }

  static String getDBConnectionUrl() {
    return "jdbc://localhost:5432/users";
  }

  static Supplier<List<String>> getDBConnectionUrlsSupplier = ()
      -> List.of(
      "jdbc://localhost:5432/users",
      "jdbc://localhost:5432/customer");

}
