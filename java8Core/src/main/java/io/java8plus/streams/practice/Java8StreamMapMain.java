package io.java8plus.streams.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.java8plus.streams.entities.City;
import io.java8plus.streams.entities.Department;
import io.java8plus.streams.entities.Employee;
import io.java8plus.streams.entities.Park;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Java8StreamMapMain {

  public static void main(String args[]) {

    Predicate<Employee> p1 = s -> s.name.startsWith("F");
    Predicate<Employee> p2 = s -> s.age < 28 && s.name.startsWith("Z");
    List<Employee> listOfEmployees = createListOfEmployees();

    /* anyMatch() method checks whether any Stream element matches
     * the specified predicate
     */
    boolean b3 = listOfEmployees.stream().anyMatch(p1);
    System.out.println(b3);
    boolean b4 = listOfEmployees.stream().anyMatch(p2);

    Set<String> taskList = listOfEmployees.stream().flatMap(e -> e.getTasks().stream()).collect(Collectors.toSet());
    List<String> parkingLots =
        listOfEmployees.stream().flatMap(e -> e.getListOfCities().stream()).flatMap(city -> city.getParkList().stream())
            .flatMap(park -> park.getParkingLots().stream()).collect(Collectors.toList());
    int  maxTasks =
        listOfEmployees.stream().map(e -> e.getTasks().size()).peek(System.out::println).max(Integer::compare).get();

    // list the last min age;
    Employee minByAge=listOfEmployees.stream().min((e1, e2)-> e1.getAge()>e2.getAge()? 1: -1).orElseThrow(
        NoSuchElementException::new);
    Employee minByAge1=listOfEmployees.stream().min(Comparator.comparing(Employee::getAge)).orElseThrow(
        NoSuchElementException::new);
    // get the list of employee with the min age
    Employee tt = listOfEmployees.stream().
        reduce((t1, t2) -> t1.getAge() > t2.getAge() ? t2 : t1)
        .orElse(null);
    //taskList.forEach(System.out::println);
    //parkingLots.forEach(System.out::println);
    System.out.println("max:"+maxTasks);
    System.out.println("Employee:"+minByAge.toString());
    System.out.println("Employee tt:"+tt.toString());
    givenDepartmentList_thenEmployeeListIsFilteredCorrectly();

  }

  public static List<Employee> createListOfEmployees() {
    List<Employee> listOfEmployees = new ArrayList<>();
    Employee emp1 = new Employee("Alibaba", 1001, 20, createCities(), Arrays.asList("task1", "task11", "task111", "task1111"));
    Employee emp2 = new Employee("Frank Zhang", 1002, 24, createCities(), Arrays.asList("task2", "task21", "task211", "task2111"));
    Employee emp3 = new Employee("Leslie W", 1003, 20, createCities(), Arrays.asList("task3", "task31", "task311", "task3111"));
    Employee emp4 = new Employee("Patel SA.", 1004,20, createCities(), Arrays.asList("task4", "task41", "task411", "task4111", "task4411"));
    listOfEmployees.add(emp1);
    listOfEmployees.add(emp2);
    listOfEmployees.add(emp3);
    listOfEmployees.add(emp4);
    return listOfEmployees;
  }

  public static List<City> createCities() {
    List<City> cities = new ArrayList<>();
    City city1 = new City("toronto", createParks());
    City city2 = new City("Markham", createParks());
    City city3 = new City("Richmond Hill", createParks());
    cities.add(city1);
    cities.add(city2);
    cities.add(city3);
    return cities;
  }

  public static List<Park> createParks() {
    List<Park> parks = new ArrayList<>();
    Park park1 = new Park("center park", Arrays.asList("lot one", "lot two", "lot 3", "lot 4"));
    Park park2 = new Park("Richmond park", Arrays.asList("lot fifteen", "lot sixteen", "lot 13", "lot 14"));
    Park park3 = new Park("Water park", Arrays.asList("lot ten", "lot 22", "lot 33", "lot 44"));
    parks.add(park1);
    parks.add(park2);
    parks.add(park3);
    return parks;
  }
  public static void givenDepartmentList_thenEmployeeListIsFilteredCorrectly() {
     List<Employee> employeeList = new ArrayList<Employee>();

     List<Department> departmentList = new ArrayList<Department>();

    Integer expectedId = 1002;

    populate(employeeList, departmentList);

    List<Employee> filteredList = employeeList.stream().filter(empl -> departmentList.stream()
        .anyMatch(dept -> dept.getDepartment().equals("sales") && empl.getId().equals(dept.getEmployeeId())))
        .collect(Collectors.toList());
    System.out.println("Employee id:"+ filteredList.get(0).getId());
    assertEquals(expectedId, filteredList.get(0).getId());
  }

  private static void populate(List<Employee> EmplList, List<Department> deptList) {
    Employee employee1 = new Employee(1001, "empl1");
    Employee employee2 = new Employee(1002, "empl2");
    Employee employee3 = new Employee(1003, "empl3");

    Collections.addAll(EmplList, employee1, employee2, employee3);

    Department department1 = new Department(1002, "sales");
    Department department2 = new Department(1003, "marketing");
    Department department3 = new Department(1004, "sales");

    Collections.addAll(deptList, department1, department2, department3);
  }
}