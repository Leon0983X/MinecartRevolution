
package com.quartercode.qcutil.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import com.quartercode.qcutil.Event;
import com.quartercode.qcutil.Listener;
import com.quartercode.qcutil.utility.ObjectUtil;

public class ObserverOutputStream extends OutputStream {

    protected OutputStream   outputStream;
    protected List<Listener> listeners = new ArrayList<Listener>();

    public ObserverOutputStream(final OutputStream outputStream) {

        this.outputStream = outputStream;
    }

    public OutputStream getOutputStream() {

        return outputStream;
    }

    public void setListeners(final List<Listener> listeners) {

        this.listeners = listeners;
    }

    public List<Listener> getListeners() {

        return listeners;
    }

    @Override
    public void close() throws IOException {

        outputStream.close();
        new Event(listeners, "type", "close").fire();
    }

    @Override
    public void flush() throws IOException {

        outputStream.flush();
        new Event(listeners, "type", "flush").fire();
    }

    @Override
    public void write(final int b) throws IOException {

        outputStream.write(b);
        new Event(listeners, "type", "write int", "data", b).fire();
    }

    @Override
    public void write(final byte b[]) throws IOException {

        outputStream.write(b);
        new Event(listeners, "type", "write bytearray", "data", b).fire();
    }

    @Override
    public void write(final byte b[], final int off, final int len) throws IOException {

        outputStream.write(b, off, len);
        new Event(listeners, "type", "write bytearray part", "data", b, "off", off, "len", len).fire();
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + (listeners == null ? 0 : listeners.hashCode());
        result = prime * result + (outputStream == null ? 0 : outputStream.hashCode());
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
        final ObserverOutputStream other = (ObserverOutputStream) obj;
        if (listeners == null) {
            if (other.listeners != null) {
                return false;
            }
        } else if (!listeners.equals(other.listeners)) {
            return false;
        }
        if (outputStream == null) {
            if (other.outputStream != null) {
                return false;
            }
        } else if (!outputStream.equals(other.outputStream)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return ObjectUtil.generateObjectStringWithNames(this, "outputStream", "listeners");
    }

}
