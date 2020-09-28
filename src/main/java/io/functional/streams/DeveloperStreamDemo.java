package io.functional.streams;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


// terminal operation:
// collect, anyMatch, allMatch, nonMatch,
// reduce, forEach, count, max, min
public class DeveloperStreamDemo {
  public static void main(String[] args) throws IOException {

    String[][] dataArray = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}, {"g", "h"}};

    List<String> listOfAllChars = Arrays.stream(dataArray)
        .flatMap(x -> Arrays.stream(x))
        .collect(Collectors.toList());

    System.out.println(listOfAllChars);

    List<String> strings = Pattern.compile("\\|")
        .splitAsStream("010|020202")
        .collect(Collectors.toList());
    System.out.println(strings);

    flatmapReduce();
    flatmapArray();
    flatmapList();
  }
  //
  public static void flatmapReduce(){
    List<List<Integer>> listOfList = Arrays.asList(
        Arrays.asList(1, 2, 3, 4),
        Arrays.asList(5, 6, 7, 8),
        Arrays.asList(9, 10, 11, 12)
    );

    System.out.println(listOfList);
    // [[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12]]

    int sum = listOfList.stream()
        .flatMap(intList -> intList.stream())
        .reduce(0, (i1, i2) -> i1 + i2);

    System.out.println(sum);
    // 78
  }

  public static void flatmapArray(){
    //
    List<Developer> developers = Arrays.asList(
        new Developer("Jack", Stream.of("Java", "Node.js", "Angular")
            .collect(Collectors.toSet())),
        new Developer("Joe", Stream.of("C++", "ActiveMQ", "HTML", "CSS", "Java")
            .collect(Collectors.toSet())),
        new Developer("Peter", Stream.of("Python", "Node.js", "C++", "Vue.js")
            .collect(Collectors.toSet())),
        new Developer("Mary", Stream.of("Node.js", "Angular", "React", "CSS")
            .collect(Collectors.toSet()))
    );

    //System.out.println(developers);
    // [{name = Jack, skills = [Java, Node.js, Angular]}, {name = Joe, skills = [Java, C++, CSS, HTML, ActiveMQ]},
    //  {name = Peter, skills = [Vue.js, C++, Node.js, Python]}, {name = Mary, skills = [CSS, Node.js, React, Angular]}]

    Set<String> skills = developers.stream()
        .flatMap(developer -> developer.getSkills().stream())
        .collect(Collectors.toSet());

    System.out.println(skills);
    // [Java, Vue.js, C++
  }
  public static void flatmapList(){
    List<List<String>> listOfList = Arrays.asList(
        Arrays.asList("Java", "Django", "Vue", "Spring Cloud"),
        Arrays.asList("Python", "Spring Boot", "React"),
        Arrays.asList("Angular", "JQuery", "Spring JPA")
    );

    //System.out.println(listOfList);
    // [[Java, Django, Vue, Spring Cloud], [Python, Spring Boot, React], [Angular, JQuery, Spring JPA]]

    List<String> results = listOfList.stream()
        .flatMap(strList -> strList.stream())
        .filter(str -> str.contains("Spring")).collect(Collectors.toList());

    System.out.println(results);
  }
}
