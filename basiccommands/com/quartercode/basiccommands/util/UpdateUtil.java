
package com.quartercode.basiccommands.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.conf.Conf;
import com.quartercode.minecartrevolution.conf.FileConf;
import com.quartercode.minecartrevolution.conf.URLConf;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.qcutil.io.File;
import com.quartercode.qcutil.net.NetConnection;
import com.quartercode.qcutil.version.Version;

public class UpdateUtil {

    public static Version getLatestVersion() throws MalformedURLException, IOException {

        return new Version(new NetConnection(new URL(URLConf.BUILD_VERSION)).getContent(null));
    }

    public static boolean isNewVersionAvaiable() {

        try {
            Version latestVersion = getLatestVersion();

            if (latestVersion.bigger(Conf.VERSION)) {
                return true;
            } else {
                return false;
            }
        }
        catch (Exception e) {
            MinecartRevolution.handleSilenceThrowable(e);
        }

        return false;
    }

    public static void update(Logger logger, CommandSender commandSender) {

        try {
            String downloadFile = "plugin.jar";

            sendMessage(logger, commandSender, Lang.getValue("basiccommands.update.update"), true);

            sendMessage(logger, commandSender, Lang.getValue("basiccommands.update.download", "file", downloadFile), true);
            new NetConnection(new URL(URLConf.BUILD_FILE)).touch(null);
            new NetConnection(new URL(URLConf.BUILD_FILE)).download(new File(FileConf.PLUGIN_FOLDER.getParentFile(), Conf.NAME + ".jar"), null);
            sendMessage(logger, commandSender, Lang.getValue("basiccommands.update.downloaded", "file", downloadFile), true);

            sendMessage(logger, commandSender, Lang.getValue("basiccommands.update.reload"), true);
            Bukkit.reload();
            sendMessage(logger, commandSender, Lang.getValue("basiccommands.update.reloaded"), true);

            sendMessage(logger, commandSender, Lang.getValue("basiccommands.update.updated"), true);
        }
        catch (Exception e) {
            sendMessage(null, commandSender, Lang.getValue("basiccommands.update.error"), false);
            MinecartRevolution.handleThrowable(e);
        }
    }

    private static void sendMessage(Logger logger, CommandSender commandSender, String message, boolean recolor) {

        if (recolor) {
            message = ChatColor.GOLD + message;
        }

        if (logger != null) {
            logger.info(message);
        }
        if (commandSender != null) {
            commandSender.sendMessage(message);
        }
    }

    private UpdateUtil() {

    }

}
