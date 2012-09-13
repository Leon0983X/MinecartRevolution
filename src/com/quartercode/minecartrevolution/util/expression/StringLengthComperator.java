
package com.quartercode.minecartrevolution.util.expression;

import java.util.Comparator;

public class StringLengthComperator implements Comparator<String> {

    public StringLengthComperator() {

    }

    @Override
    public int compare(String o1, String o2) {

        if (o1.length() > o2.length()) {
            return 1;
        } else if (o1.length() < o2.length()) {
            return -1;
        } else {
            return 0;
        }
    }

}
