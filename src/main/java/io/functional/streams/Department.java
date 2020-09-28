package io.functional.streams;

public class Department {

  private Integer employeeId;
  private String department;

  Department(Integer employeeId, String department) {
    super();
    this.employeeId = employeeId;
    this.department = department;
  }

  Integer getEmployeeId() {
    return employeeId;
  }

  String getDepartment() {
    return department;
  }
}