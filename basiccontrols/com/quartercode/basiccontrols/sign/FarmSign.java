
package com.quartercode.basiccontrols.sign;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.minecartrevolution.sign.ControlSign;
import com.quartercode.minecartrevolution.sign.ControlSignInfo;

public class FarmSign extends ControlSign {

    public FarmSign() {

    }

    @Override
    protected ControlSignInfo createInfo() {

        return new ControlSignInfo(Lang.getValue("basiccontrols.signs.farm.name"), Lang.getValue("basiccontrols.signs.farm.description"), "farm.place", "farm.destroy", "farm");
    }

    @Override
    public void execute(final Minecart minecart, final String label, final Sign sign) {

        int radius = -1;
        final List<String> types = new ArrayList<String>();

        for (final String line : sign.getLines()) {
            if (line.toLowerCase().contains("farm")) {
                continue;
            }

            try {
                radius = Integer.parseInt(line);
            }
            catch (final NumberFormatException e) {
                types.add(line);
            }
        }

        for (final String type : types) {
            executeExpression(minecart, (type.startsWith("+") ? "+ " : "") + (type.startsWith("-") ? "- " : "") + "farm " + type.replaceAll("\\+", "").replaceAll("-", "").trim() + (radius > 0 ? " " + radius : ""));
        }
    }

}
