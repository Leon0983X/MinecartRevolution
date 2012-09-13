
package com.quartercode.qcutil.io.logger.format;

import com.quartercode.qcutil.io.logger.LogLevel;
import com.quartercode.qcutil.utility.ObjectUtil;

public class ExtensionFormatter extends LogFormatter {

    protected String prefix;
    protected String suffix;

    public ExtensionFormatter() {

    }

    public ExtensionFormatter(String... extensions) {

        if (extensions.length >= 1) {
            setPrefix(extensions[0]);
        }
        if (extensions.length >= 2) {
            setSuffix(extensions[1]);
        }
    }

    public String getPrefix() {

        return prefix;
    }

    public void setPrefix(String prefix) {

        this.prefix = prefix;
    }

    public String getSuffix() {

        return suffix;
    }

    public void setSuffix(String suffix) {

        this.suffix = suffix;
    }

    @Override
    public String format(String record, LogLevel logLevel) {

        if (prefix != null) {
            record = prefix + record;
        }
        if (suffix != null) {
            record = record + suffix;
        }

        return record;
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + (prefix == null ? 0 : prefix.hashCode());
        result = prime * result + (suffix == null ? 0 : suffix.hashCode());
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
        ExtensionFormatter other = (ExtensionFormatter) obj;
        if (prefix == null) {
            if (other.prefix != null) {
                return false;
            }
        } else if (!prefix.equals(other.prefix)) {
            return false;
        }
        if (suffix == null) {
            if (other.suffix != null) {
                return false;
            }
        } else if (!suffix.equals(other.suffix)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return ObjectUtil.generateObjectStringWithNames(this, "prefix", "suffix");
    }

}
