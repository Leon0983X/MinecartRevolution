/*
 * This file is part of MinecartRevolution-Basiccommands.
 * Copyright (c) 2012 QuarterCode <http://www.quartercode.com/>
 *
 * MinecartRevolution-Basiccommands is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MinecartRevolution-Basiccommands is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MinecartRevolution-Basiccommands. If not, see <http://www.gnu.org/licenses/>.
 */

package com.quartercode.minecartrevolution.basiccommands.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import com.quartercode.minecartrevolution.core.MinecartRevolution;
import com.quartercode.minecartrevolution.core.command.MRCommandHandler;
import com.quartercode.minecartrevolution.core.exception.SilentMinecartRevolutionException;
import com.quartercode.minecartrevolution.core.get.URLConf;
import com.quartercode.quarterbukkit.api.command.Command;
import com.quartercode.quarterbukkit.api.command.CommandInfo;
import com.quartercode.quarterbukkit.api.exception.ExceptionHandler;

public class InfoCommand extends MRCommandHandler {

    public InfoCommand() {

    }

    @Override
    public CommandInfo createInfo() {

        return new CommandInfo(true, null, MinecartRevolution.getLang().get("basiccommands.info.description"), "minecartrevolution.command.info", "info");
    }

    @Override
    public void execute(Command command) {

        InfoThread thread = new InfoThread();
        thread.sender = command.getSender();
        thread.start();
    }

    class InfoThread extends Thread {

        public CommandSender sender;

        @Override
        public void run() {

            int downloadCount = getDownloadCount();

            sender.sendMessage(ChatColor.GREEN + "========== [" + MinecartRevolution.getLang().get("basiccommands.info.start") + "] ==========");

            if (downloadCount >= 0) {
                sender.sendMessage(ChatColor.YELLOW + "Total " + minecartRevolution.getName() + " Downloads: " + ChatColor.DARK_GREEN + downloadCount);
                sender.sendMessage("");
            }

            sender.sendMessage("This plugin was born at a Tuesday, on the");
            sender.sendMessage(ChatColor.DARK_PURPLE + "21th February 2012" + ChatColor.RESET + ", and filled with creativity.");
            sender.sendMessage("The first run was with only a few lines of code, and that");
            sender.sendMessage("was the beginning of a big thing which's called");
            sender.sendMessage(ChatColor.GOLD + minecartRevolution.getName() + ChatColor.RESET + ". Today it's one of the largest & most");
            sender.sendMessage("versatile Minecart plugins ever made.");
            sender.sendMessage(ChatColor.AQUA + "There is no limit with " + ChatColor.GOLD + minecartRevolution.getName() + ChatColor.RESET + "!");
        }

        private int getDownloadCount() {

            try {
                URL url = new URL(URLConf.BUKKIT_DEV_URL);
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

                String line;
                while ( (line = reader.readLine()) != null) {
                    if (line.contains("<dt>Downloads</dt>")) {
                        try {
                            line = reader.readLine();
                            return Integer.parseInt(line.split("\"")[1].trim());
                        } catch (NumberFormatException e) {
                            return -1;
                        }
                    }
                }
                reader.close();
            } catch (IOException e) {
                ExceptionHandler.exception(new SilentMinecartRevolutionException(minecartRevolution, e, "Failed to load download count"));
            }

            return -1;
        }
    }

}
