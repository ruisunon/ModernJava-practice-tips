package io.corejava.string;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

/** * Java Program to find duplicate characters in String. * * *
 */

public class FindDuplicateCharacters{ public static void main(String args[]) {
    printDuplicateCharacters("Programming");

    printDuplicateCharacters("Combination");
    printDuplicateCharacters("Java");
}
/* * Find all duplicate characters in a String and print each of them. */
public static void printDuplicateCharacters(String word) {

    char[] characters = word.toCharArray();
    // build HashMap with character and number of times they appear in String
    Map<Character, Integer> charMap = new HashMap<Character, Integer>();
    for (Character ch : characters) {
        int count=charMap.getOrDefault(ch, 0);
        charMap.put(ch, count + 1);
    }
    // Iterate through HashMap to print all duplicate characters of String
   charMap.entrySet().stream().filter(entry -> entry.getValue()>1).forEach(System.out::println);

    List<Integer> list = new ArrayList<Integer>();

    for(int i = 1; i< 10; i++){
        list.add(i);
    }

    Stream<Integer> stream = list.stream();
    Integer[] evenNumbersArr = stream.filter(i -> i%2 == 0).toArray(Integer[]::new);
    System.out.print(evenNumbersArr);

    // Creating a Stream of Strings
    Stream<String> astream = Stream.of("Geeks", "for", "gfg", "GeeksQuiz");

    // Using Stream toArray() and filtering
    // the elements that starts with 'G'
    Object[] arr = astream.filter(str -> str.startsWith("G")).toArray();

    // Displaying the elements in array arr
    System.out.println(Arrays.toString(arr));
    List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp", "javascript", "Go Lang");
    Predicate<String> startsWithJ = (n) -> n.startsWith("J") || n.startsWith("j");
    Predicate<String> fourLetterLong = (n) -> n.length() >= 4;
    Predicate<String> goLang= s -> s.contains("Go");
    languages.stream() .filter(startsWithJ.and(fourLetterLong).or(goLang)).forEach(
            (n) -> System.out.print("\nName, after filering : " + n)
    );
  }
}
