package io.datastructure.list;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author by ruisu
 * @package - io.datastructure.list
 * @name - ArrayToLinkedHashSet
 * @created on 17/10/2021
 */

public class ArrayToLinkedHashSet {
    public static void main(String[] args) {

        String[] strColors = {"blue", "green", "red", "white", "black"};

        /*
         * First convert an array to List and then
         * use the LinkedHashSet constructor
         */
        Set<String> lhSetColors = new LinkedHashSet<String>( Arrays.asList(strColors) );

        System.out.println("LinkedHashSet contains: " + lhSetColors);
    }
}
