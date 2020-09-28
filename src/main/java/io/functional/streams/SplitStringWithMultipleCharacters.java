package io.functional.streams;

import java.util.Arrays;
import java.util.Comparator;

public class SplitStringWithMultipleCharacters {

  public static void main(String[] args) {
    String[] split = "1,2|3.4$5".split("[,|.]");
    System.out.println(Arrays.toString(split));
    SplitStringWithMultipleCharacters solution = new SplitStringWithMultipleCharacters();
    String str =
        "row1col1 for earch ! line 2? asf.row2col2\r\nrow2col1 tsest. row2col2\r\nrow3? asdfasdacol1 a b.row3col2";
    int res = solution.splitStringOne(str);
    System.out.println(res);
    solution.multiSplitter();
  }

  int splitStringOne(String text) {
    String[] lines = text.split("\\n");
    String[] sentences = text.split("\\?|\\.|!");
    int max = 0;
    Integer result= Arrays.stream(text.split("\\n")).flatMap(line -> Arrays.stream(line.split("\\?|\\.|!")))
        .flatMap(sentence -> Arrays.stream(sentence.split("\\s+"))).map(wo -> wo.length()).max(Integer::compare).orElse(0);
         //max(Comparator.naturalOrder());//.collect(Collectors.toList());

    return result;
  }
  void multiSplitter(){
    //defining a String object
    String s = "If you don't li????ke some~thing, chan    ge.it.";
    //split string by multiple delimiters
    String[] stringarray = s.split("[,.!?' ]+");
    //iterate over string array
    for(int i=0; i< stringarray.length; i++)
    {
      //prints the tokens
      System.out.println(stringarray[i]);
    }

    String str = "how-to-do-in-ja|Sva. provides-java-tutorials.";

    String[] strArray = str.split("[-|.]"); // note the difference with "-|\\."

    System.out.println(Arrays.toString(strArray));
  }
}
