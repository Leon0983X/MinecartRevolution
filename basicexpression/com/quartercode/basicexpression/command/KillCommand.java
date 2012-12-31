
package com.quartercode.basicexpression.command;

import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.util.EffectUtil.DEffect;
import com.quartercode.minecartrevolution.util.GlobalConfig;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommandInfo;

public class KillCommand implements ExpressionCommand {

    private final MinecartRevolution minecartRevolution;

    public KillCommand(final MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;
    }

    @Override
    public ExpressionCommandInfo getInfo() {

        return new ExpressionCommandInfo("k", "kill");
    }

    @Override
    public boolean canExecute(final Minecart minecart) {

        return true;
    }

    @Override
    public void execute(final Minecart minecart, final Object parameter) {

        minecart.eject();
        minecart.remove();

        if ((Boolean) minecartRevolution.getConfiguration().get(GlobalConfig.playEffects)) {
            DEffect.SMOKE.play(minecart);
        }
    }

}
