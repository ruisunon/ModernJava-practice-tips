package io.java8plus.streams;

import io.java8plus.streams.entities.Student;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FindDifferenceOfCollections {
  public static void main(String[] args) {
    int arr[] = {12, 13, 14, 15, 16, 17, 18};

    // to index is within the range
    int[] copy = Arrays.copyOfRange(arr, 2, 6);

    // Create a new List with values 11 - 20
    List<Integer> list = new ArrayList<>();
    for (int i = 11; i <= 20; i++)
      list.add(i);
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
    List<Student> studentList1 = new ArrayList<Student>();

    studentList1.add(new Student("Paul", 11, "Economics", 78.9));
    studentList1.add(new Student("Zevin", 12, "Computer Science", 91.2));
    studentList1.add(new Student("Harish", 13, "History", 83.7));
    studentList1.add(new Student("Xiano", 14, "Literature", 71.5));
    studentList1.add(new Student("Soumya", 15, "Economics", 77.5));
    studentList1.add(new Student("Asif", 16, "Mathematics", 89.4));
    studentList1.add(new Student("Nihira", 17, "Computer Science", 84.6));
    Set<Integer> ids = studentList.stream()
        .map(Student::getId)
        .collect(Collectors.toSet());
    List<Student> parentBooks = studentList1.stream()
        .filter(student -> !ids.contains(student .getId()))
        .collect(Collectors.toList());
  }
}
