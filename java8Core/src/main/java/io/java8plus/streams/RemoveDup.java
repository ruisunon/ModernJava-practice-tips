package io.java8plus.streams;
//Write a Java 8 program to remove the duplicate elements from the list?
//
//  Answer: In this program, we have stored the elements into an array and converted them into a list. Thereafter,
//  we have used stream and collected it to “Set” with the help of the “Collectors.toSet()” method.

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RemoveDup {
    public static void main(String[] args){
        Integer[] array1= new Integer[]{ 1, 9, 8, 7,7 ,8,9};
        List<Integer> listDup = Arrays.asList(array1);
        // Converted the Array of type Integer into List
        Set<Integer> setNoDups= listDup.stream().collect(Collectors.toSet());
        setNoDups.forEach(( i ) -> System.out.print(" " + i));
        concatenate2Streams();
    }

   // Write a Java 8 program to concatenate two Streams?
   // Answer: In this program, we have created two Streams from the two already created lists and then
    // concatenated them using a concat() method in which two lists are passed as an argument.
    // Finally, printed the elements of the concatenated stream.

    static void concatenate2Streams() {

            List<String> list1 = Arrays.asList("Java", "8");
            List<String> list2 = Arrays.asList("explained", "through", "programs");
            Stream<String> concatStream = Stream.concat(list1.stream(), list2.stream());
            // Concatenated the list1 and list2 by converting them into Stream
            concatStream.forEach(str -> System.out.print(str + " "));
            // Printed the Concatenated Stream
        }
    }
