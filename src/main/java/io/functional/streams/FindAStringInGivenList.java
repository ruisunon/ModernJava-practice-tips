package io.functional.streams;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
public class FindAStringInGivenList {

  public List<String> findUsingLoopWithRegex(String search, List<String> list) {

    List<String> matches = new ArrayList<String>();

    String pattern = ".*" + search + ".*";
    Pattern p = Pattern.compile(pattern);

    for (String str : list) {
      if (p.matcher(str).matches()) {
        matches.add(str);
      }
    }

    return matches;
  }


  public List<String> findUsingLoop(String search, List<String> list) {

    List<String> matches = new ArrayList<String>();

    for (String str : list) {
      if (str.contains(search)) {
        matches.add(str);
      }
    }

    return matches;
  }

  public List<String> findUsingStream(String search, List<String> list) {

    List<String> matchingElements =
        list.stream().filter(str -> str.trim().contains(search)).collect(Collectors.toList());

    return matchingElements;
  }
}
