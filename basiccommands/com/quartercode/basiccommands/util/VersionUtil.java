
package com.quartercode.basiccommands.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
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

public class VersionUtil {

    private static final String TITLE_TAG = "title";
    private static final String LINK_TAG  = "link";
    private static final String ITEM_TAG  = "item";

    private static URL          feedUrl;

    static {

        try {
            feedUrl = new URL(URLConf.BUKKIT_DEV_FILEFEED);
        }
        catch (MalformedURLException e) {
            MinecartRevolution.handleThrowable(e);
        }
    }

    public static Version getLatestVersion() {

        String title;
        try {
            title = getFeedData().get("title");

            if (title.split(" ").length >= 2) {
                return new Version(title.split(" ")[1]);
            }
        }
        catch (IOException e) {
            MinecartRevolution.handleThrowable(e);
        }
        catch (XMLStreamException e) {
            MinecartRevolution.handleThrowable(e);
        }

        return null;
    }

    public static boolean newVersionAvaiable() {

        Version latestVersion = getLatestVersion();

        if (latestVersion != null) {
            if (latestVersion.bigger(Conf.VERSION)) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    public static boolean tryUpdate(Logger logger, CommandSender commandSender) {

        if (newVersionAvaiable()) {
            forceUpdate(logger, commandSender);
            return true;
        } else {
            return false;
        }
    }

    public static void forceUpdate(Logger logger, CommandSender commandSender) {

        try {
            URL url = new URL(getFileURL(getFeedData().get("link")));

            sendMessage(logger, commandSender, Lang.getValue("basiccommands.update.update"), true);

            sendMessage(logger, commandSender, Lang.getValue("basiccommands.update.download", "file", url.getFile()), true);
            new NetConnection(url).touch(null);
            new NetConnection(url).download(new File(FileConf.PLUGIN_FOLDER.getParentFile(), Conf.NAME + ".jar"), null);
            sendMessage(logger, commandSender, Lang.getValue("basiccommands.update.downloaded", "file", url.getFile()), true);

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

        if (logger != null && !commandSender.equals(Bukkit.getConsoleSender())) {
            logger.info(message);
        }
        if (commandSender != null) {
            commandSender.sendMessage(message);
        }
    }

    private static String getFileURL(String link) throws IOException {

        URL url = new URL(link);
        URLConnection connection = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line;
        while ( (line = reader.readLine()) != null) {
            if (line.contains("<li class=\"user-action user-action-download\">")) {
                return line.split("<a href=\"")[1].split("\">Download</a>")[0];
            }
        }
        connection = null;
        reader.close();

        return null;
    }

    private static Map<String, String> getFeedData() throws IOException, XMLStreamException {

        Map<String, String> returnMap = new HashMap<String, String>();
        String title = null;
        String link = null;

        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        InputStream inputStream = feedUrl.openStream();
        XMLEventReader eventReader = inputFactory.createXMLEventReader(inputStream);

        while (eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();
            if (event.isStartElement()) {
                if (event.asStartElement().getName().getLocalPart().equals(TITLE_TAG)) {
                    event = eventReader.nextEvent();
                    title = event.asCharacters().getData();
                    continue;
                }
                if (event.asStartElement().getName().getLocalPart().equals(LINK_TAG)) {
                    event = eventReader.nextEvent();
                    link = event.asCharacters().getData();
                    continue;
                }
            } else if (event.isEndElement()) {
                if (event.asEndElement().getName().getLocalPart().equals(ITEM_TAG)) {
                    returnMap.put("title", title);
                    returnMap.put("link", link);
                    return returnMap;
                }
            }
        }

        return returnMap;
    }

    private VersionUtil() {

    }

}
