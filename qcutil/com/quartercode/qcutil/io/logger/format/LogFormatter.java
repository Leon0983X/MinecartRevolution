
package com.quartercode.qcutil.io.logger.format;

import com.quartercode.qcutil.io.logger.LogLevel;

public abstract class LogFormatter {

    public abstract String format(String record, LogLevel logLevel);

}
