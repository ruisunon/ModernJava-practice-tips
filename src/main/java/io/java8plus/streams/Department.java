package io.java8plus.streams;

public class Department {

  private Integer employeeId;
  private String department;

  public Department(Integer employeeId, String department) {
    super();
    this.employeeId = employeeId;
    this.department = department;
  }

  public Integer getEmployeeId() {
    return employeeId;
  }

  public String getDepartment() {
    return department;
  }
}