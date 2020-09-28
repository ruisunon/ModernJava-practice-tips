package io.java8plus.concurrency.stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class FindItemsBasedOnOtherStreamUnitTest {

  private List<Employee2> employeeList = new ArrayList<Employee2>();

  private List<Department> departmentList = new ArrayList<Department>();

  @Test
  public void givenDepartmentList_thenEmployeeListIsFilteredCorrectly() {
    Integer expectedId = 1002;

    populate(employeeList, departmentList);

    List<Employee2> filteredList = employeeList.stream().filter(empl -> departmentList.stream()
        .anyMatch(dept -> dept.getDepartment().equals("sales") && empl.getEmployeeId().equals(dept.getEmployeeId())))
        .collect(Collectors.toList());

    assertEquals(expectedId, filteredList.get(0).getEmployeeId());
  }

  private void populate(List<Employee2> EmplList, List<Department> deptList) {
    Employee2 employee1 = new Employee2(1001, "empl1");
    Employee2 employee2 = new Employee2(1002, "empl2");
    Employee2 employee3 = new Employee2(1003, "empl3");

    Collections.addAll(EmplList, employee1, employee2, employee3);

    Department department1 = new Department(1002, "sales");
    Department department2 = new Department(1003, "marketing");
    Department department3 = new Department(1004, "sales");

    Collections.addAll(deptList, department1, department2, department3);
  }
}

class Employee2 {

  private Integer employeeId;
  private String employeeName;

  Employee2(Integer employeeId, String employeeName) {
    super();
    this.employeeId = employeeId;
    this.employeeName = employeeName;
  }

  Integer getEmployeeId() {
    return employeeId;
  }

  public String getEmployeeName() {
    return employeeName;
  }

}

class Department {

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
