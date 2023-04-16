package io.algorithm.strings;

import java.util.Optional;
import java.util.stream.IntStream;

public class PalindromeByJava8
{
    public static void main(String[] args)
    {
        //Take input string from the user

        System.out.println("Enter input string :");

        String inputString = "sc.nextLine()".replaceAll("\\s+", "").toLowerCase();

        //Java 8 code to check for palindrome

        boolean isItPalindrome = IntStream.range(0, inputString.length()/2).
                noneMatch(i -> inputString.charAt(i) != inputString.charAt(inputString.length() - i -1));

        if (isItPalindrome)
        {
            System.out.println(inputString+" is a palindrome");
        }
        else
        {
            System.out.println(inputString+" is not a palindrome");
        }

        Optional<Integer> opInt1 = Optional.of(200); //Line 1
        Optional opInt2 = opInt1.filter(x-> x > 100); //Line 2
        System.out.println(opInt2.get()); //Line 3

    }
}