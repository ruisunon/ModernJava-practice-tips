package io.java8plus.streams.practice;
import io.java8plus.streams.entities.Employee;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
public class ParallelStreamExample1 {
  private static final String DIR = System.getProperty("user.dir") + "/test/";

  public static void main(String[] args) throws IOException {

    Files.createDirectories(Paths.get(DIR));

    ParallelStreamExample1 obj = new ParallelStreamExample1();

    List<Employee> employees = obj.generateEmployee(100);

    // normal, sequential
    //employees.stream().forEach(ParallelExample5::save); 		// 27s-29s

    // parallel
    //employees.parallelStream().forEach(ParallelStreamExample1::save); // 7s-
    printmain();

  }
  public static void printmain() {

    long count = Stream.iterate(0, n -> n + 1)
        .limit(10_000)
        //.parallel()   with this 23s, without this 1m 10s
        .filter(ParallelStreamExample1::isPrime)
        .peek(x -> System.out.format("%s\t", x))
        .count();

    System.out.println("\nTotal: " + count);

  }

  public static boolean isPrime(int number) {
    if (number <= 1) return false;
    return !IntStream.rangeClosed(2, number / 2).anyMatch(i -> number % i == 0);
  }
  private static void save(Employee input) {

    try (FileOutputStream fos = new FileOutputStream(new File(DIR + input.getName() + ".txt"));
        ObjectOutputStream obs = new ObjectOutputStream(fos)) {
      obs.writeObject(input);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  private List<Employee> generateEmployee(int num) {

    return Stream.iterate(0, n -> n + 1)
        .limit(num)
        .map(x -> {
          return new Employee(
              generateRandomName(4),
              generateRandomAge(15, 100),
              generateRandomSalary(900.00, 200_000.00)
          );
        })
        .collect(Collectors.toList());

  }

  private String generateRandomName(int length) {

    return new Random()
        .ints(5, 97, 122) // 97 = a , 122 = z
        .mapToObj(x -> String.valueOf((char) x))
        .collect(Collectors.joining());

  }

  private int generateRandomAge(int min, int max) {
    return new Random()
        .ints(1, min, max)
        .findFirst()
        .getAsInt();
  }

  private BigDecimal generateRandomSalary(double min, double max) {
    return new BigDecimal(new Random()
        .doubles(1, min, max)
        .findFirst()
        .getAsDouble()).setScale(2, RoundingMode.HALF_UP);
  }

}