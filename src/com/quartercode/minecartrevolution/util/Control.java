
package com.quartercode.minecartrevolution.util;

import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.plugin.PluginManager;

public abstract class Control {

    protected boolean executeExpression(Minecart minecart, String expression) {

        if (expression != null && !expression.isEmpty()) {
            try {
                PluginManager.getMinecartRevolution().getExpressionExecutor().execute(minecart, expression);
            }
            catch (Exception e) {
                MinecartRevolution.handleSilenceThrowable(e);
            }

            return true;
        } else {
            return false;
        }
    }

}
