
package com.quartercode.basicexpression.command;

import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.util.EffectUtil.DEffect;
import com.quartercode.minecartrevolution.util.GlobalConfig;
import com.quartercode.minecartrevolution.util.TypeArray;
import com.quartercode.minecartrevolution.util.TypeArray.Type;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommandInfo;

public class KillCommand extends ExpressionCommand {

    private final MinecartRevolution minecartRevolution;

    public KillCommand(final MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;
    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo(new TypeArray(Type.NONE), "k", "kill");
    }

    @Override
    public boolean canExecute(final Minecart minecart) {

        return true;
    }

    @Override
    public void execute(final Minecart minecart, final Object parameter) {

        minecart.eject();
        minecart.remove();

        if (minecartRevolution.getConfiguration().getBool(GlobalConfig.PLAY_EFFECTS)) {
            DEffect.SMOKE.play(minecart);
        }
    }

}
