
package com.quartercode.qcutil.io.logger;

import java.io.Serializable;
import com.quartercode.qcutil.utility.ObjectUtil;

public abstract class LogLevel implements Serializable {

    private static final long    serialVersionUID = -4396956882187182526L;

    public static final LogLevel INFO             = getLogLevel("INFO", false);
    public static final LogLevel WARNING          = getLogLevel("WARNING", false);
    public static final LogLevel EXCEPTION        = getLogLevel("EXCEPTION", true);
    public static final LogLevel ERROR            = getLogLevel("ERROR", true);

    public static LogLevel getLogLevel(final String prefix, final boolean critical) {

        return new LogLevel() {

            private static final long serialVersionUID = -4709872188861342513L;

            @Override
            public boolean isCritical() {

                return critical;
            }

            @Override
            public String getPrefix() {

                return prefix;
            }
        };
    }

    public abstract boolean isCritical();

    public abstract String getPrefix();

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + (getPrefix() == null ? 0 : getPrefix().hashCode());
        result = prime * result + (isCritical() ? 1231 : 1237);
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
        final LogLevel other = (LogLevel) obj;
        if (getPrefix() == null) {
            if (other.getPrefix() != null) {
                return false;
            }
        } else if (!getPrefix().equals(other.getPrefix())) {
            return false;
        }
        if (isCritical() != other.isCritical()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return ObjectUtil.generateObjectString(this, "critical", isCritical(), "prefix", getPrefix());
    }

}
