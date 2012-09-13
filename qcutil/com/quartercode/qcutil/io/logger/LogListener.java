
package com.quartercode.qcutil.io.logger;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;
import com.quartercode.qcutil.Event;
import com.quartercode.qcutil.Listener;
import com.quartercode.qcutil.utility.ObjectUtil;

public class LogListener implements Listener {

    protected PrintStream outputStream;
    protected PrintWriter writer;

    public LogListener(OutputStream outputStream) {

        this.outputStream = new PrintStream(outputStream);
    }

    public LogListener(Writer writer) {

        this.writer = new PrintWriter(writer);
    }

    @Override
    public Object onEvent(Event event) {

        String record = (String) event.getProperty("logrecord");

        if (outputStream != null) {
            outputStream.println(record);
        }
        if (writer != null) {
            writer.println(record);
            writer.flush();
        }

        return null;
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + (outputStream == null ? 0 : outputStream.hashCode());
        result = prime * result + (writer == null ? 0 : writer.hashCode());
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
        LogListener other = (LogListener) obj;
        if (outputStream == null) {
            if (other.outputStream != null) {
                return false;
            }
        } else if (!outputStream.equals(other.outputStream)) {
            return false;
        }
        if (writer == null) {
            if (other.writer != null) {
                return false;
            }
        } else if (!writer.equals(other.writer)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return ObjectUtil.generateObjectStringWithNames(this, "outputStream", "writer");
    }

}
