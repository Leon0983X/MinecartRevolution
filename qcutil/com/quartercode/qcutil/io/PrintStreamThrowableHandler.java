
package com.quartercode.qcutil.io;

import java.io.PrintStream;
import com.quartercode.qcutil.ThrowableHandler;
import com.quartercode.qcutil.utility.ObjectUtil;

public class PrintStreamThrowableHandler implements ThrowableHandler {

    protected PrintStream printStream;

    public PrintStreamThrowableHandler(final PrintStream printStream) {

        setPrintStream(printStream);
    }

    public PrintStream getPrintStream() {

        return printStream;
    }

    public void setPrintStream(final PrintStream printStream) {

        this.printStream = printStream;
    }

    @Override
    public void handleThrowable(final Throwable throwable) {

        throwable.printStackTrace(printStream);
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + (printStream == null ? 0 : printStream.hashCode());
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
        final PrintStreamThrowableHandler other = (PrintStreamThrowableHandler) obj;
        if (printStream == null) {
            if (other.printStream != null) {
                return false;
            }
        } else if (!printStream.equals(other.printStream)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return ObjectUtil.generateObjectStringWithNames(this, "printStream");
    }
}
