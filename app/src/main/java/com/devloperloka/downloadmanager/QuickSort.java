package com.devloperloka.downloadmanager;

import java.util.ArrayList;

public class QuickSort {

    public static void sort(ArrayList<Long> size, ArrayList<String> id, int low, int high) {
        if (low < high) {
            int pi = partition(size, id, low, high);
            sort(size, id, low, pi - 1);
            sort(size, id, pi + 1, high);
        }
    }

    public static int partition(ArrayList<Long> size, ArrayList<String> id, int low, int high) {
        long pivot = size.get(high);
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {
            if (size.get(j) <= pivot) {
                i++;
                //Swap size array
                long temp = size.get(i);
                size.set(i, size.get(j));
                size.set(j, temp);

                //Swap id array
                String temp1 = id.get(i);
                id.set(i, id.get(j));
                id.set(j, temp1);

            }
        }

        //Swap size array
        long temp = size.get(i + 1);
        size.set(i + 1, size.get(high));
        size.set(high, temp);

        //Swap id array
        String temp1 = id.get(i + 1);
        id.set(i + 1, id.get(high));
        id.set(high, temp1);

        return i + 1;
    }

}