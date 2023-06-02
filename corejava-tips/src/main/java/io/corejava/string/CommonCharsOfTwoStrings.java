package io.corejava.string;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
//https://javaconceptoftheday.com/print-common-characters-between-two-strings-in-alphabetical-order-in-java/
public class CommonCharsOfTwoStrings {

    private static void printCommonChars(String firstString, String secondString)
    {
        char[] firstStringToCharArray = firstString.replaceAll("\\s+", "").toCharArray();

        char[] secondStringToCharArray = secondString.replaceAll("\\s+", "").toCharArray();

        Set<Character> firstStringSet = new TreeSet<>();

        Set<Character> secondStringSet = new TreeSet<>();

        for (char c : firstStringToCharArray)
        {
            firstStringSet.add(c);
        }

        for (char c : secondStringToCharArray)
        {
            secondStringSet.add(c);
        }

        firstStringSet.retainAll(secondStringSet);

        System.out.println("Common characters in alphabetical order : "+firstStringSet);

        System.out.println("Count : "+firstStringSet.size());
    }

    public static void main(String[] args)
    {

        String firstString ="StringJavaStruts";

        String secondString = "PythonGoRustSpring";

        printCommonChars(firstString, secondString);

    }
}