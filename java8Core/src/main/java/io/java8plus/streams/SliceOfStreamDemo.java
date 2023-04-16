package io.java8plus.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SliceOfStreamDemo {
  //1st way
   public static <T> Stream<T> getSliceOfStream(Stream<T> stream, int startIndex, int endIndex){
     return stream.skip(startIndex)// specify the number of elements to skip
            .limit(endIndex-startIndex+1); // specify the no. of elements the stream should be limited
   }
   // 2nd way
   public static <T> Stream<T> getSLiceOfStreamByCollectors(Stream<T> stream, int startIndex, int endIndex){
     return stream.collect(Collectors.collectingAndThen(Collectors.toList(), list-> list.stream().skip(startIndex).limit(endIndex-startIndex+1)));
   }

   //3rd method
   public static <T> Stream<T>  getSliceOfStreamBySublist(Stream<T> stream, int startIndex, int endIndex)
   {
     return stream
         // Convert the stream to list
         .collect(Collectors.toList())
         // Fetch the subList between the specified index
         .subList(startIndex, endIndex + 1)
         // Convert the subList to stream
         .stream();
   }
  public static void main(String[] args)
  {
    int arr[] = { 12, 13, 14, 15, 16, 17, 18 };

    // to index is within the range
    int[] copy = Arrays.copyOfRange(arr, 2, 6);

    // Create a new List with values 11 - 20
    List<Integer> list = new ArrayList<>();
    for (int i = 11; i <= 20; i++)
      list.add(i);

    // Create stream from list
    Stream<Integer> intStream = list.stream();

    // Print the stream
    System.out.println("List: " + list);

    // Get Slice of Stream
    // containing of elements from the 4th index to 8th
    Stream<Integer>
        sliceOfIntStream = getSliceOfStream(intStream, 4, 8);
    Stream<Integer>
        sliceOfIntStream2 = getSLiceOfStreamByCollectors(list.stream(), 4, 8);
    Stream<Integer>
        sliceOfIntStream3 = getSliceOfStreamBySublist(list.stream(), 4, 8);
    // Print the slice
    System.out.println("\nSlice of Stream:");
    sliceOfIntStream.forEach(System.out::println);
    System.out.println("\nSlice of Stream:");
    sliceOfIntStream2.forEach(System.out::println);
    System.out.println("\nSlice of Stream:");
    sliceOfIntStream3.forEach(System.out::println);
  }
}
