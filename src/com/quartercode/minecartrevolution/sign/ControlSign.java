
package com.quartercode.minecartrevolution.sign;

import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.util.Control;

public abstract class ControlSign extends Control {

    public abstract ControlSignInfo getInfo();

    public abstract void execute(Minecart minecart, Location signLocation, String label, Sign sign);

}
