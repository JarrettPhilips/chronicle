package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Sort {
    /*
        Global Variables
    */
    private static ArrayList<LMLEntryPanel> entries;
    private static int numberOfEntries;

    /*
        Functions
    */
    public static ArrayList<LMLEntryPanel> sortEntryList(ArrayList<LMLEntryPanel> list){
        // check for empty or null array
        if(list == null || list.size() == 0){
            return list;
        }
        entries = list;
        numberOfEntries = list.size();
        quicksort(0, numberOfEntries - 1);

        //reverseOrder();
        Collections.reverse(entries);
        return entries;
    }

    private static void quicksort(int low, int high) {
        int i = low, j = high;
        // Get the pivot element from the middle of the list
        int pivot = entries.get(low + (high-low)/2).getDate();

        // Divide into two lists
        while (i <= j) {
            // If the current value from the left list is smaller than the pivot
            // element then get the next element from the left list
            while (entries.get(i).getDate() < pivot) {
                i++;
            }
            // If the current value from the right list is larger than the pivot
            // element then get the next element from the right list
            while (entries.get(j).getDate() > pivot) {
                j--;
            }

            // If we have found a value in the left list which is larger than
            // the pivot element and if we have found a value in the right list
            // which is smaller than the pivot element then we exchange the
            // values.
            // As we are done we can increase i and j
            if (i <= j) {
                exchange(i, j);
                i++;
                j--;
            }
        }

        //Recursiony bit
        if (low < j)
            quicksort(low, j);
        if (i < high)
            quicksort(i, high);
    }

    private static void exchange(int i, int j) {
        LMLEntryPanel temp = entries.get(i);
        entries.set(i, entries.get(j));
        entries.set(j, temp);
    }
}
