package io.functional.streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RegexPredicateExample {

  public static void main(String[] args) {
    // Compile regex as predicate
    Predicate<String> emailFilter = Pattern.compile("^(.+)@example.com$").asPredicate();
    Pattern pattern = Pattern.compile("^(.+)@example.com$");

    // Input list
    List<String> emails = Arrays.asList("al.ex@example.com", "bob@yahoo.com", "cat@google.com", "da..vid@example.com", "sadddvid@example.com");

    // Apply predicate filter
    List<String> desiredEmails = emails.stream().filter(emailFilter).collect(Collectors.<String>toList());

    // Now perform desired operation
    desiredEmails.forEach(System.out::println);

    for(String email : emails)
    {
      Matcher matcher = pattern.matcher(email);

      if(matcher.matches())
      {
        System.out.println(email);
      }
    }
  }
}