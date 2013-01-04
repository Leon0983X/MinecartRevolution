
package com.quartercode.basicexpression.command;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.StorageMinecart;
import org.bukkit.inventory.ItemStack;
import com.quartercode.basicexpression.BasicExpressionPlugin;
import com.quartercode.basicexpression.util.BasicExpressionConfig;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.util.MaterialAliasConfig;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.util.expression.ExpressionCommandInfo;

public class CollectCommand extends ExpressionCommand {

    private final BasicExpressionPlugin plugin;

    public CollectCommand(final BasicExpressionPlugin plugin) {

        this.plugin = plugin;
    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo("co", "collect");
    }

    @Override
    public boolean canExecute(final Minecart minecart) {

        return minecart instanceof StorageMinecart;
    }

    @Override
    public void execute(final Minecart minecart, final Object parameter) {

        int radius = 5;
        final List<String> items = new ArrayList<String>();

        if (parameter != null) {
            for (final String part : String.valueOf(parameter).split(",")) {
                try {
                    if (part.startsWith("r")) {
                        if (plugin.getConfiguration().getLong(BasicExpressionConfig.COLLECT_MAX_RADIUS) < 0 || Integer.parseInt(part.replaceAll("r", "")) <= plugin.getConfiguration().getLong(BasicExpressionConfig.COLLECT_MAX_RADIUS)) {
                            radius = Integer.parseInt(part.replaceAll("r", ""));
                        }
                    } else {
                        items.add(part);
                    }
                }
                catch (final NumberFormatException e) {
                    MinecartRevolution.handleSilenceThrowable(e);
                }
            }
        }

        if (items.size() > 0) {
            for (final String item : items) {
                collectItems((StorageMinecart) minecart, radius, item);
            }
        } else {
            collectItems((StorageMinecart) minecart, radius, null);
        }
    }

    private void collectItems(final StorageMinecart storageMinecart, final int radius, final String string) {

        if (storageMinecart.getInventory().firstEmpty() < 0) {
            return;
        }

        for (final Entity entity : storageMinecart.getNearbyEntities(radius, radius, radius)) {
            if (entity instanceof Item) {
                final Item item = (Item) entity;
                if (item.isDead()) {
                    continue;
                } else if (string != null && !MaterialAliasConfig.equals(item.getItemStack(), string)) {
                    continue;
                }

                storageMinecart.getInventory().addItem(new ItemStack[] { item.getItemStack() });
                item.remove();
            }
        }
    }

}
