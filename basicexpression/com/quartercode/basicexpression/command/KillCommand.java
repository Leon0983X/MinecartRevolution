
package com.quartercode.basicexpression.command;

import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.expression.ExpressionCommandInfo;
import com.quartercode.minecartrevolution.util.EffectUtil.DEffect;
import com.quartercode.minecartrevolution.util.GlobalConfig;
import com.quartercode.minecartrevolution.util.MinecartUtil;
import com.quartercode.minecartrevolution.util.TypeArray;
import com.quartercode.minecartrevolution.util.TypeArray.Type;

public class KillCommand extends ExpressionCommand {

    public KillCommand() {

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
        MinecartUtil.remove(minecart);

        if (minecartRevolution.getConfiguration().getBool(GlobalConfig.PLAY_EFFECTS)) {
            DEffect.SMOKE.play(minecart);
        }
    }

}
