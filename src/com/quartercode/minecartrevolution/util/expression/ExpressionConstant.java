
package com.quartercode.minecartrevolution.util.expression;

import org.bukkit.entity.Minecart;

public interface ExpressionConstant {

    public ExpressionConstantInfo getInfo();

    public Object getValue(Minecart minecart);

}
