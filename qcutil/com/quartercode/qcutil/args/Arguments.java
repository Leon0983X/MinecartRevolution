
package com.quartercode.qcutil.args;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Arguments implements Serializable {

    private static final long          serialVersionUID         = 5438756406272881239L;

    public static final ArgumentFilter STANDARD_ARGUMENT_FILTER = new PrefixArgumentFilter("-", "/");

    protected List<String>             arguments;
    protected ArgumentFilter           argumentFilter;

    public Arguments() {

        arguments = new ArrayList<String>();
        argumentFilter = STANDARD_ARGUMENT_FILTER;
    }

    public Arguments(List<String> arguments) {

        this();
        setArguments(arguments);
    }

    public Arguments(String[] arguments) {

        this();
        setArguments(arguments);
    }

    public Arguments(String arguments) {

        this();
        setArguments(arguments);
    }

    public boolean isUseable() {

        if (arguments == null || arguments.size() < 1) {
            return false;
        } else {
            return true;
        }
    }

    public void setArguments(List<String> arguments) {

        this.arguments = arguments;
    }

    public void setArguments(String[] arguments) {

        this.arguments = Arrays.asList(arguments);
    }

    public void setArguments(String arguments) {

        setArguments(arguments.split(" "));
    }

    public List<String> getArgumentList() {

        return arguments;
    }

    public String[] getArgumentArray() {

        String[] array = new String[arguments.size()];
        for (int counter = 0; counter < arguments.size(); counter++) {
            array[counter] = arguments.get(counter);
        }

        return array;
    }

    public String getArgumentString() {

        String argumentString = "";
        for (int counter = 0; counter < arguments.size(); counter++) {
            argumentString += arguments.get(counter).toString();
            if (counter < arguments.size() - 1) {
                argumentString += " ";
            }
        }

        return argumentString;
    }

    public void addArguments(String... arguments) {

        this.arguments.addAll(Arrays.asList(arguments));
    }

    public void removeArguments(int startIndex, int endIndex) {

        ArrayList<String> removeList = new ArrayList<String>();
        for (int counter = startIndex; counter <= endIndex; counter++) {
            removeList.add(arguments.get(counter));
        }

        arguments.removeAll(removeList);
    }

    public ArgumentFilter getArgumentFilter() {

        return argumentFilter;
    }

    public void setArgumentFilter(ArgumentFilter argumentFilter) {

        this.argumentFilter = argumentFilter;
    }

    public boolean isMarkSet(String mark, boolean ignoreCase) {

        for (String argument : arguments) {
            if (argumentFilter.equalsWithMarkString(argument.toString(), mark.toString(), ignoreCase)) {
                return true;
            }
        }

        return false;
    }

    public boolean isParameterSet(String mark, boolean ignoreCase) {

        return getParameter(mark, ignoreCase) != null;
    }

    public String getArgument(int index) {

        if (index < arguments.size()) {
            return arguments.get(index);
        } else {
            return null;
        }
    }

    public String getMark(int index) {

        if (argumentFilter.isMark(getArgument(index))) {
            return arguments.get(index);
        } else {
            return null;
        }
    }

    public String getParameter(int index) {

        if (!argumentFilter.isMark(getArgument(index + 1))) {
            return arguments.get(index + 1);
        } else {
            return null;
        }
    }

    public String getParameter(String mark, boolean ignoreCase) {

        if (isMarkSet(mark, ignoreCase)) {
            for (int counter = 0; counter < arguments.size(); counter++) {
                if (getMark(counter) != null && argumentFilter.equalsWithMarkString(getMark(counter), mark, ignoreCase)) {
                    return getParameter(counter);
                }
            }
        }

        return null;
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + (argumentFilter == null ? 0 : argumentFilter.hashCode());
        result = prime * result + (arguments == null ? 0 : arguments.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Arguments other = (Arguments) obj;
        if (argumentFilter == null) {
            if (other.argumentFilter != null) {
                return false;
            }
        } else if (!argumentFilter.equals(other.argumentFilter)) {
            return false;
        }
        if (arguments == null) {
            if (other.arguments != null) {
                return false;
            }
        } else if (!arguments.equals(other.arguments)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return getArgumentString();
    }

}
