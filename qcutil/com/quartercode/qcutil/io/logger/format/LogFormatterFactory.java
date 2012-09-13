
package com.quartercode.qcutil.io.logger.format;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.quartercode.qcutil.io.logger.LogLevel;

public class LogFormatterFactory {

    public static LogFormatter getTimestampFormatter(final String timestampFormat) {

        return new LogFormatter() {

            @Override
            public String format(String record, LogLevel logLevel) {

                return new SimpleDateFormat(timestampFormat).format(new Date()) + " " + record;
            }
        };
    }

    private LogFormatterFactory() {

    }

}
