
package com.quartercode.minecartrevolution.util.expression;

import com.quartercode.minecartrevolution.util.TypeArray;

public class ExpressionCommandInfo {

    private final TypeArray typeArray;
    private final String[]  commandLabels;

    public ExpressionCommandInfo(final TypeArray typeArray, final String... commandLabels) {

        this.typeArray = typeArray;
        this.commandLabels = commandLabels;
    }

    public TypeArray getTypeArray() {

        return typeArray;
    }

    public String[] getCommandLabels() {

        return commandLabels;
    }

}
