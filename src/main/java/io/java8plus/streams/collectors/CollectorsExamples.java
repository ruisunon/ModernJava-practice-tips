package io.java8plus.streams.collectors;

import io.java8plus.streams.Student;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
//    java.util.stream.Collector interface contains four functions that work together to accumulate input elements
//    into a mutable result container and optionally performs a final transformation on the result. Those four
//    functions are,
//
//    a) Supplier() :
//
//    A function that creates and returns a new mutable result container.
//
//    b) accumulator() :
//
//    A function that accumulates a value into a mutable result container.
//
//    c) combiner() :
//
//    A function that accepts two partial results and merges them.
//
//    d) finisher() :
//
//    A function that performs final transformation from the intermediate accumulation type to the final result type.

public class CollectorsExamples {

  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(8, 2, 5, 7, 3, 6);

    //collect() method returning List of OddNumbers

    List<Integer> oddNumbers = numbers.stream().filter(i -> i % 2 != 0).collect(Collectors.toList());

    System.out.println(oddNumbers);
    List<Student> studentList = new ArrayList<Student>();

    studentList.add(new Student("Paul", 11, "Economics", 78.9));
    studentList.add(new Student("Zevin", 12, "Computer Science", 91.2));
    studentList.add(new Student("Harish", 13, "History", 83.7));
    studentList.add(new Student("Xiano", 14, "Literature", 71.5));
    studentList.add(new Student("Soumya", 15, "Economics", 77.5));
    studentList.add(new Student("Asif", 16, "Mathematics", 89.4));
    studentList.add(new Student("Nihira", 17, "Computer Science", 84.6));
    studentList.add(new Student("Mitshu", 18, "History", 73.5));
    studentList.add(new Student("Vijay", 19, "Mathematics", 92.8));
    studentList.add(new Student("Harry", 20, "History", 71.9));
    String namesJoined = studentList.stream().map(Student::getName).collect(Collectors.joining(", "));

    System.out.println(namesJoined);

    Long studentCount = studentList.stream().collect(Collectors.counting());

    System.out.println(studentCount);

    //    Collectors.maxBy() :
    //    This method returns a Collector that collects largest element in a stream according to supplied Comparator.
    Optional<Double> highPercentage =
        studentList.stream().map(Student::getPercentage).collect(Collectors.maxBy(Comparator.naturalOrder()));

    System.out.println(highPercentage);

    //    Collectors.groupingBy() :
    //
    //    This method groups the input elements according supplied classifier and returns the results in a Map.
    //
    //    Example : Grouping the students by subject
    Map<String, List<Student>> studentsGroupedBySubject =
        studentList.stream().collect(Collectors.groupingBy(Student::getSubject));

    System.out.println(studentsGroupedBySubject);

    //    Collectors.partitioningBy() :
    //
    //    This method partitions the input elements according to supplied Predicate and returns a Map<Boolean,
    //    List<T>>. Under the true key, you will find elements which match given Predicate and under the false key,
    //    you will find the elements which doesn’t match given Predicate.
    //
    //    Example : Partitioning the students who got above 80.0% from who don’t.

    Map<Boolean, List<Student>> studentspartionedByPercentage =
        studentList.stream().collect(Collectors.partitioningBy(student -> student.getPercentage() > 80.0));

    System.out.println(studentspartionedByPercentage);

    //    Collectors.collectingAndThen() :
    //
    //    This is a special method which lets you to perform one more action on the result after collecting the result.
    //
    //    Example : Collecting first three students into List and making it unmodifiable

    List<Student> first3Students = studentList.stream().limit(3)
        .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));

    System.out.println(first3Students);
  }
}