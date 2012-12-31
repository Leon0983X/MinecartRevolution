
package com.quartercode.basicexpression.command;

import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommandInfo;

public class AnnounceCommand implements ExpressionCommand {

    public AnnounceCommand() {

    }

    @Override
    public ExpressionCommandInfo getInfo() {

        return new ExpressionCommandInfo("a", "announce");
    }

    @Override
    public boolean canExecute(final Minecart minecart) {

        return minecart.getPassenger() != null && minecart.getPassenger() instanceof Player;
    }

    @Override
    public void execute(final Minecart minecart, final Object parameter) {

        if (parameter != null) {
            ((Player) minecart.getPassenger()).sendMessage(String.valueOf(parameter).replaceAll("&0", "§0").replaceAll("&1", "§1").replaceAll("&2", "§2").replaceAll("&3", "§3").replaceAll("&4", "§4").replaceAll("&5", "§5").replaceAll("&6", "§6").replaceAll("&7", "§7").replaceAll("&8", "§8").replaceAll("&9", "§9").replaceAll("&a", "§a").replaceAll("&b", "§b").replaceAll("&c", "§c").replaceAll("&d", "§d").replaceAll("&e", "§e").replaceAll("&f", "§f").replaceAll("&k", "§k").replaceAll("&l", "§l").replaceAll("&n", "§n").replaceAll("&o", "§o").replaceAll("&r", "§r"));
        }
    }

}
