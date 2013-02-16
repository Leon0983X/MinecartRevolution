
package com.quartercode.basicexpression.command;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Minecart;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import com.quartercode.basicexpression.BasicExpressionPlugin;
import com.quartercode.basicexpression.util.BasicExpressionConfig;
import com.quartercode.minecartrevolution.exception.MinecartRevolutionSilenceException;
import com.quartercode.minecartrevolution.expression.ExpressionCommand;
import com.quartercode.minecartrevolution.expression.ExpressionCommandInfo;
import com.quartercode.minecartrevolution.util.AliasUtil;
import com.quartercode.minecartrevolution.util.ItemData;
import com.quartercode.minecartrevolution.util.TypeArray;
import com.quartercode.minecartrevolution.util.TypeArray.Type;
import com.quartercode.quarterbukkit.QuarterBukkit;

public class CollectCommand extends ExpressionCommand {

    private final BasicExpressionPlugin plugin;

    public CollectCommand(final BasicExpressionPlugin plugin) {

        this.plugin = plugin;
    }

    @Override
    protected ExpressionCommandInfo createInfo() {

        return new ExpressionCommandInfo(new TypeArray(Type.NONE, Type.STRING, Type.DOUBLE), "co", "collect");
    }

    @Override
    public boolean canExecute(final Minecart minecart) {

        return minecart instanceof InventoryHolder;
    }

    @Override
    public void execute(final Minecart minecart, final Object parameter) {

        int radius = (int) plugin.getConfiguration().getLong(BasicExpressionConfig.COLLECT_DEFAULT_RADIUS);
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
                    QuarterBukkit.exception(new MinecartRevolutionSilenceException(minecartRevolution, e, "Failed to parse collect radius"));
                }
            }
        }

        if (items.size() > 0) {
            for (final String item : items) {
                collectItems((InventoryHolder) minecart, minecart, radius, item);
            }
        } else {
            collectItems((InventoryHolder) minecart, minecart, radius, null);
        }
    }

    private void collectItems(final InventoryHolder inventoryMinecart, final Minecart minecart, final int radius, final String string) {

        if (inventoryMinecart.getInventory().firstEmpty() < 0) {
            return;
        }

        for (final Entity entity : minecart.getNearbyEntities(radius, radius, radius)) {
            if (entity instanceof Item) {
                final Item item = (Item) entity;
                if (item.isDead()) {
                    continue;
                } else if (string != null && !AliasUtil.equals(new ItemData(item.getItemStack()), string)) {
                    continue;
                }

                inventoryMinecart.getInventory().addItem(new ItemStack[] { item.getItemStack() });
                item.remove();
            }
        }
    }

}
