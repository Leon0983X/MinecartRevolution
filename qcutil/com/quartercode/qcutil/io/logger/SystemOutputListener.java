
package com.quartercode.qcutil.io.logger;

import com.quartercode.qcutil.Event;
import com.quartercode.qcutil.Listener;

public class SystemOutputListener implements Listener {

    public SystemOutputListener() {

    }

    @Override
    public Object onEvent(final Event event) {

        final String record = (String) event.getProperty("logrecord");
        final LogLevel logLevel = (LogLevel) event.getProperty("loglevel");

        if (!logLevel.isCritical()) {
            System.out.println(record);
        } else {
            System.err.println(record);
        }

        return null;
    }

}
