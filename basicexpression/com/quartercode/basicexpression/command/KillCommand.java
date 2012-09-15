
package com.quartercode.basicexpression.command;

import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.util.EffectUtil.DEffect;
import com.quartercode.minecartrevolution.util.GlobalConfig;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommandInfo;

public class KillCommand implements ExpressionCommand {

    private MinecartRevolution minecartRevolution;

    public KillCommand(MinecartRevolution minecartRevolution) {

        this.minecartRevolution = minecartRevolution;
    }

    @Override
    public ExpressionCommandInfo getInfo() {

        return new ExpressionCommandInfo("k", "kill");
    }

    @Override
    public boolean canExecute(Minecart minecart) {

        return true;
    }

    @Override
    public void execute(Minecart minecart, Object parameter) {

        if (parameter == null) {
            kill(minecart);
        } else if (String.valueOf(parameter).equalsIgnoreCase("empty")) {
            if (minecart.getPassenger() == null) {
                kill(minecart);
            }
        }
    }

    private void kill(Minecart minecart) {

        minecart.eject();
        minecart.remove();

        if ((Boolean) minecartRevolution.getConfiguration().get(GlobalConfig.playEffects)) {
            DEffect.SMOKE.play(minecart);
        }
    }

}
