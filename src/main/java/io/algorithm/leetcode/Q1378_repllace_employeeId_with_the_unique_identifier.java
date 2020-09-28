package io.algorithm.leetcode;
//Table: Employees
//
//    +---------------+---------+
//    | Column Name   | Type    |
//    +---------------+---------+
//    | id            | int     |
//    | name          | varchar |
//    +---------------+---------+
//    id is the primary key for this table.
//    Each row of this table contains the id and the name of an employee in a company.
//    Table: EmployeeUNI
//
//    +---------------+---------+
//    | Column Name   | Type    |
//    +---------------+---------+
//    | id            | int     |
//    | unique_id     | int     |
//    +---------------+---------+
//    (id, unique_id) is the primary key for this table.
//    Each row of this table contains the id and the corresponding unique id of an employee in the company.
//    Write an SQL query to show the unique ID of each user, If a user doesn’t have a unique ID replace just show null.
//
//    Return the result table in any order.
//
//    The query result format is in the following example:
//
//    Example 1:
//    Employees table:
//    +----+----------+
//    | id | name     |
//    +----+----------+
//    | 1  | Alice    |
//    | 7  | Bob      |
//    | 11 | Meir     |
//    | 90 | Winston  |
//    | 3  | Jonathan |
//    +----+----------+
//
//    EmployeeUNI table:
//    +----+-----------+
//    | id | unique_id |
//    +----+-----------+
//    | 3  | 1         |
//    | 11 | 2         |
//    | 90 | 3         |
//    +----+-----------+
//
//    EmployeeUNI table:
//    +-----------+----------+
//    | unique_id | name     |
//    +-----------+----------+
//    | null      | Alice    |
//    | null      | Bob      |
//    | 2         | Meir     |
//    | 3         | Winston  |
//    | 1         | Jonathan |
//    +-----------+----------+
//
//    Alice and Bob don't have a unique ID, We will show null instead.
//    The unique ID of Meir is 2.
//    The unique ID of Winston is 3.
//    The unique ID of Jonathan is 1.
public class Q1378_repllace_employeeId_with_the_unique_identifier {
   String SQL="select unique_id, name from employees left join employeeUNI on employees.id=employeeUNI.id";
}
