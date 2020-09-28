package io.functional.streams;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

  public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    String name;
    int age;
    private Integer id;
    private BigDecimal salary;

    List<City> listOfCities;
    List<String> tasks;

    public Employee(String name, Integer id, int age,List<City> listOfCities, List<String> tasks) {
      super();
      this.name = name;
      this.age = age;
      this.id=id;
      this.listOfCities=listOfCities;
      this.tasks=tasks;
    }
    public Employee( Integer id,String name) {
      this.name = name;
      this.id=id;
    }
    public Employee( String name, int age, BigDecimal salary ){
      this.name = name;
      this.age=age;
      this.salary=salary;
    }


    @Override
    public String toString() {
      return "Employee{" + "name='" + name + '\'' + ", age=" + age + ", listOfCities=" + listOfCities.size() + ", tasks="
          + tasks.size() + '}';
    }

    public BigDecimal getSalary() {
      return salary;
    }

    public void setSalary(BigDecimal salary) {
      this.salary = salary;
    }

    public Integer getId() {
      return id;
    }

    public void setId(Integer id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }
    public int getAge() {
      return age;
    }
    public void setAge(int age) {
      this.age = age;
    }

    public List<City> getListOfCities() {
      return listOfCities;
    }

    public void setListOfCities(List<City> listOfCities) {
      this.listOfCities = listOfCities;
    }

    public List<String> getTasks() {
      return tasks;
    }

    public void setTasks(List<String> tasks) {
      this.tasks = tasks;
    }
  }
