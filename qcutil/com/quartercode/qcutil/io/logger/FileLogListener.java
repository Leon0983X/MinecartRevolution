
package com.quartercode.qcutil.io.logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import com.quartercode.qcutil.Event;
import com.quartercode.qcutil.Listener;
import com.quartercode.qcutil.QcUtil;
import com.quartercode.qcutil.io.File;
import com.quartercode.qcutil.utility.ObjectUtil;

public class FileLogListener implements Listener {

    public static Listener getNewLineListener() {

        return new Listener() {

            @Override
            public Object onEvent(final Event event) {

                try {
                    final File file = (File) event.getProperty("logfile");
                    final PrintWriter fileWriter = (PrintWriter) event.getProperty("filewriter");
                    if (!file.read().isEmpty()) {
                        fileWriter.println();
                    }
                }
                catch (final Throwable t) {
                    QcUtil.handleThrowable(t);
                }

                return null;
            }
        };
    }

    public static String getDefaultLogMarkString(final String info) {

        return "----- " + info + " --------------------";
    }

    public static Listener getLogMarkListener(final String logMarkString) {

        return new Listener() {

            @Override
            public Object onEvent(final Event event) {

                final PrintWriter fileWriter = (PrintWriter) event.getProperty("filewriter");
                fileWriter.println(logMarkString);

                return null;
            }
        };
    }

    protected File        logFile;
    protected Logger      logger;
    protected PrintWriter fileWriter;

    public FileLogListener(final File logFile, final Logger logger, final Listener... newLogListeners) throws FileNotFoundException, IOException {

        this.logFile = logFile;
        this.logger = logger;

        if (!logFile.getParentFile().exists()) {
            logFile.getParentFile().mkdirs();
        }

        String[] oldLog = null;
        String oldLogContent = null;
        if (logFile.exists()) {
            oldLogContent = logFile.read();
            if (!oldLogContent.isEmpty()) {
                oldLog = oldLogContent.split("\n");
            }
        }

        fileWriter = new PrintWriter(logFile);

        if (oldLog != null) {
            for (final String logRecord : oldLog) {
                fileWriter.println(logRecord);
            }
        }
        fileWriter.flush();

        new Event(Arrays.asList(newLogListeners), "logger", this, "logfile", logFile, "filewriter", fileWriter).fire();
    }

    @Override
    public Object onEvent(final Event event) {

        final Logger logger = (Logger) event.getProperty("logger");

        if (this.logger.equals(logger)) {
            final String record = (String) event.getProperty("logrecord");
            fileWriter.println(record);
            fileWriter.flush();
        }

        return null;
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + (fileWriter == null ? 0 : fileWriter.hashCode());
        result = prime * result + (logFile == null ? 0 : logFile.hashCode());
        result = prime * result + (logger == null ? 0 : logger.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FileLogListener other = (FileLogListener) obj;
        if (fileWriter == null) {
            if (other.fileWriter != null) {
                return false;
            }
        } else if (!fileWriter.equals(other.fileWriter)) {
            return false;
        }
        if (logFile == null) {
            if (other.logFile != null) {
                return false;
            }
        } else if (!logFile.equals(other.logFile)) {
            return false;
        }
        if (logger == null) {
            if (other.logger != null) {
                return false;
            }
        } else if (!logger.equals(other.logger)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return ObjectUtil.generateObjectStringWithNames(this, "logFile", "logger");
    }

}
