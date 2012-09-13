
package com.quartercode.qcutil.version;

import java.awt.Desktop;
import java.net.URI;
import javax.swing.JOptionPane;
import com.quartercode.qcutil.QcUtil;

public class VersionCheck {

    public static boolean guiJavaVersionCheck(Version latestJavaVersion, boolean agressive) {

        return guiVersionCheck(new Version(System.getProperty("java.version")), latestJavaVersion, "Java", "http://www.java.com/download", agressive);
    }

    public static boolean guiVersionCheck(Version currentVersion, Version latestVersion, String programName, String downloadSite) {

        return guiVersionCheck(currentVersion, latestVersion, programName, downloadSite, false);
    }

    public static boolean guiVersionCheck(Version currentVersion, Version latestVersion, String programName, String downloadSite, boolean agressive) {

        return guiVersionCheck(currentVersion, latestVersion, programName, programName + " Version Checker", downloadSite, agressive);
    }

    public static boolean guiVersionCheck(Version currentVersion, Version latestVersion, String programName, String frameTitle, String downloadSite, boolean agressive) {

        if (currentVersion.lesser(latestVersion)) {
            if (JOptionPane.showConfirmDialog(null, "You have an old " + programName + " version (" + currentVersion.getVersionString() + "). Update to version " + latestVersion.getVersionString() + "?", frameTitle, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                return guiUpdate(latestVersion, programName, frameTitle, downloadSite);
            } else if (agressive) {
                if (JOptionPane.showConfirmDialog(null, "Really don't want to update? New versions are more stable etc. Update to the new version is very recommed!", frameTitle, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                    return guiUpdate(latestVersion, programName, frameTitle, downloadSite);
                }
            }
        }

        return false;
    }

    public static boolean guiUpdate(Version latestVersion, String programName, String frameTitle, String downloadSite) {

        try {
            Desktop.getDesktop().browse(new URI(downloadSite));
            return true;
        }
        catch (Throwable t) {
            QcUtil.handleThrowable(t);
            JOptionPane.showMessageDialog(null, "Problem while opening the web browser: " + t.getMessage() + ". Press OK to see next step.", frameTitle, JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "Please start your web browser and navigate to " + downloadSite + " to download and install " + programName + " " + latestVersion.getVersionString() + ".", frameTitle, JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
    }

    private VersionCheck() {

    }

}
