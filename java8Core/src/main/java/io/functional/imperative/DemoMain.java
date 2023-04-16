package io.functional.imperative;

import static io.functional.imperative.DemoMain.Gender.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class DemoMain {
  public static void main(String[] args) {
    List<Person> people = List.of(
        new Person("John", MALE),
        new Person("Maria", FEMALE),
        new Person("Aisha", FEMALE),
        new Person("Alex", MALE),
        new Person("Alice", FEMALE)
    );

    System.out.println("// Imperative approach");
    // Imperative approach

    List<Person> females = new ArrayList<>();

    for (Person person : people) {
      if (FEMALE.equals(person.gender)) {
        females.add(person);
      }
    }

    for (Person female : females) {
      System.out.println(female);
    }
    Person man=new Person("sun", MALE);
    Person woman=new Person("Jing", FEMALE);
    System.out.println("man:" + man.toString());
    System.out.println("woman:" + woman.toString());
    swap(man, woman);
    System.out.println("swap man:" + man.toString());
    System.out.println("swap woman:" + woman.toString());
    System.out.println("// Declarative approach");

    // Declarative approach

    Predicate<Person> personPredicate = person -> FEMALE.equals(person.gender);

    List<Person> females2 = people.stream()
        .filter(personPredicate)
        .collect(Collectors.toList());
    females2.forEach(System.out::println);
  }

  private static void swap(Object o1, Object o2){
    Object temp=o1;
    o1=o2;
    o2=temp;
  }

  static class Person {
    private final String name;
    private final Gender gender;

    Person(String name, Gender gender) {
      this.name = name;
      this.gender = gender;
    }

    @Override
    public String toString() {
      return "Person{" +
          "name='" + name + '\'' +
          ", gender=" + gender +
          '}';
    }
  }

  enum Gender {
    MALE, FEMALE
  }

}
