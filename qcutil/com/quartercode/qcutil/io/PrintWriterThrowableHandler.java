
package com.quartercode.qcutil.io;

import java.io.PrintWriter;
import com.quartercode.qcutil.ThrowableHandler;
import com.quartercode.qcutil.utility.ObjectUtil;

public class PrintWriterThrowableHandler implements ThrowableHandler {

    protected PrintWriter printWriter;

    public PrintWriterThrowableHandler(PrintWriter printWriter) {

        setPrintWriter(printWriter);
    }

    public PrintWriter getPrintWriter() {

        return printWriter;
    }

    public void setPrintWriter(PrintWriter printWriter) {

        this.printWriter = printWriter;
    }

    @Override
    public void handleThrowable(Throwable throwable) {

        throwable.printStackTrace(printWriter);
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + (printWriter == null ? 0 : printWriter.hashCode());
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
        PrintWriterThrowableHandler other = (PrintWriterThrowableHandler) obj;
        if (printWriter == null) {
            if (other.printWriter != null) {
                return false;
            }
        } else if (!printWriter.equals(other.printWriter)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return ObjectUtil.generateObjectStringWithNames(this, "printWriter");
    }

}
