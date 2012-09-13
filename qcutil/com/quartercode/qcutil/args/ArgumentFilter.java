
package com.quartercode.qcutil.args;

public interface ArgumentFilter {

    public boolean isMark(String mark);

    public boolean equalsWithMarkString(String argument, String markString, boolean ignoreCase);

}
