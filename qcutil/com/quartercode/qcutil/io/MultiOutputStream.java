
package com.quartercode.qcutil.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.quartercode.qcutil.utility.ObjectUtil;

public class MultiOutputStream extends OutputStream {

    protected List<OutputStream> outputStreams;

    public MultiOutputStream(final OutputStream... outputStreams) {

        this.outputStreams = new ArrayList<OutputStream>(Arrays.asList(outputStreams));
    }

    public List<OutputStream> getOutputStreams() {

        return outputStreams;
    }

    public void setOutputStreams(final List<OutputStream> outputStreams) {

        this.outputStreams = outputStreams;
    }

    @Override
    public void close() throws IOException {

        for (final OutputStream outputStream : outputStreams) {
            outputStream.close();
        }
    }

    @Override
    public void flush() throws IOException {

        for (final OutputStream outputStream : outputStreams) {
            outputStream.flush();
        }
    }

    @Override
    public void write(final int b) throws IOException {

        for (final OutputStream outputStream : outputStreams) {
            outputStream.write(b);
        }
    }

    @Override
    public void write(final byte[] b) throws IOException {

        for (final OutputStream outputStream : outputStreams) {
            outputStream.write(b);
        }
    }

    @Override
    public void write(final byte[] b, final int off, final int len) throws IOException {

        for (final OutputStream outputStream : outputStreams) {
            outputStream.write(b, off, len);
        }
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + (outputStreams == null ? 0 : outputStreams.hashCode());
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
        final MultiOutputStream other = (MultiOutputStream) obj;
        if (outputStreams == null) {
            if (other.outputStreams != null) {
                return false;
            }
        } else if (!outputStreams.equals(other.outputStreams)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return ObjectUtil.generateObjectStringWithNames(this, "outputStreams");
    }

}
