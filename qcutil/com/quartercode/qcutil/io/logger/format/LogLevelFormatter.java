
package com.quartercode.qcutil.io.logger.format;

import com.quartercode.qcutil.io.logger.LogLevel;

public class LogLevelFormatter extends LogFormatter {

    public LogLevelFormatter() {

    }

    @Override
    public String format(final String record, final LogLevel logLevel) {

        return String.format("%-15s", "[" + logLevel.getPrefix() + "]") + record;
    }

}
