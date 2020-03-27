package com.devloperloka.downloadmanager;

import java.util.ArrayList;
import java.util.Collections;

public class scheduler {

    public static int arrange(ArrayList<Long> size) {
        int smallest;
        smallest = getSmallest(size);
        return smallest;
    }

    public static int getSmallest(ArrayList<Long> size) {
        return size.indexOf(Collections.min(size));
    }


}
