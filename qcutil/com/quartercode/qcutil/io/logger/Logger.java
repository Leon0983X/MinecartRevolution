
package com.quartercode.qcutil.io.logger;

import java.util.ArrayList;
import java.util.List;
import com.quartercode.qcutil.Event;
import com.quartercode.qcutil.Listener;
import com.quartercode.qcutil.io.logger.format.LogFormatter;
import com.quartercode.qcutil.io.logger.format.LogFormatterFactory;
import com.quartercode.qcutil.io.logger.format.LogLevelFormatter;
import com.quartercode.qcutil.utility.ObjectUtil;

public class Logger {

    protected String             name;
    protected List<LogFormatter> formatters = new ArrayList<LogFormatter>();
    protected List<Listener>     listeners  = new ArrayList<Listener>();

    public Logger(String name) {

        setName(name);
        setStandardFormatters();
        setStandardListeners();
    }

    public Logger(List<Listener> listeners) {

        setStandardFormatters();
        setListeners(listeners);
    }

    public Logger(String name, List<Listener> listeners) {

        setName(name);
        setStandardFormatters();
        setListeners(listeners);
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public List<LogFormatter> getFormatters() {

        return formatters;
    }

    public void setFormatters(List<LogFormatter> formatters) {

        this.formatters = formatters;
    }

    public void setStandardFormatters() {

        formatters.clear();
        formatters.add(new LogLevelFormatter());
        formatters.add(LogFormatterFactory.getTimestampFormatter("MM/dd/yyyy HH:mm:ss"));
    }

    public List<Listener> getListeners() {

        return listeners;
    }

    public void setListeners(List<Listener> listeners) {

        this.listeners = listeners;
    }

    public void setStandardListeners() {

        listeners.clear();
        listeners.add(new SystemOutputListener());
    }

    protected String formatRecord(LogLevel logLevel, String record) {

        String formattedRecord = record;

        for (LogFormatter formatter : formatters) {
            formattedRecord = formatter.format(formattedRecord, logLevel);
        }

        return formattedRecord;
    }

    public void log(LogLevel logLevel, String record) {

        if (logLevel != null) {
            String logEntry = formatRecord(logLevel, record);
            new Event(listeners, "logger", this, "loglevel", logLevel, "rawrecord", record, "logrecord", logEntry).fire();
        }
    }

    public void info(String record) {

        log(LogLevel.INFO, record);
    }

    public void warning(String record) {

        log(LogLevel.WARNING, record);
    }

    public void exception(String record) {

        log(LogLevel.EXCEPTION, record);
    }

    public void error(String record) {

        log(LogLevel.ERROR, record);
    }

    public void handleThrowable(Throwable throwable) {

        LogLevel logLevel;
        if (throwable instanceof RuntimeException && ! (throwable instanceof RuntimeException)) {
            logLevel = LogLevel.EXCEPTION;
        } else {
            logLevel = LogLevel.ERROR;
        }

        log(logLevel, throwable.toString());
        for (StackTraceElement stackTraceElement : throwable.getStackTrace()) {
            log(logLevel, "    at " + stackTraceElement.toString());
        }
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + (formatters == null ? 0 : formatters.hashCode());
        result = prime * result + (listeners == null ? 0 : listeners.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Logger other = (Logger) obj;
        if (formatters == null) {
            if (other.formatters != null) {
                return false;
            }
        } else if (!formatters.equals(other.formatters)) {
            return false;
        }
        if (listeners == null) {
            if (other.listeners != null) {
                return false;
            }
        } else if (!listeners.equals(other.listeners)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return ObjectUtil.generateObjectStringWithNames(this, "name");
    }

}
