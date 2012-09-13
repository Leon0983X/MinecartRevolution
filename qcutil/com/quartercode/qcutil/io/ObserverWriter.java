
package com.quartercode.qcutil.io;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import com.quartercode.qcutil.Event;
import com.quartercode.qcutil.Listener;
import com.quartercode.qcutil.utility.ObjectUtil;

public class ObserverWriter extends Writer {

    protected Writer         writer;
    protected List<Listener> listeners = new ArrayList<Listener>();

    public ObserverWriter(Writer writer) {

        this.writer = writer;
    }

    public Writer getWriter() {

        return writer;
    }

    public void setListeners(List<Listener> listeners) {

        this.listeners = listeners;
    }

    public List<Listener> getListeners() {

        return listeners;
    }

    @Override
    public void close() throws IOException {

        writer.close();
        new Event(listeners, "type", "close").fire();
    }

    @Override
    public void flush() throws IOException {

        writer.flush();
        new Event(listeners, "type", "flush").fire();
    }

    @Override
    public void write(int c) throws IOException {

        writer.write(c);
        new Event(listeners, "type", "write int", "data", c).fire();
    }

    @Override
    public void write(char cbuf[]) throws IOException {

        writer.write(cbuf);
        new Event(listeners, "type", "write chararray", "data", cbuf).fire();
    }

    @Override
    public void write(char cbuf[], int off, int len) throws IOException {

        writer.write(cbuf, off, len);
        new Event(listeners, "type", "write chararray part", "data", cbuf, "off", off, "len", len).fire();
    }

    @Override
    public void write(String str) throws IOException {

        writer.write(str);
        new Event(listeners, "type", "write string", "data", str).fire();
    }

    @Override
    public void write(String str, int off, int len) throws IOException {

        writer.write(str, off, len);
        new Event(listeners, "type", "write string part", "data", str, "off", off, "len", len).fire();
    }

    @Override
    public Writer append(CharSequence csq) throws IOException {

        writer.append(csq);
        new Event(listeners, "type", "append charsequence", "data", csq).fire();
        return this;
    }

    @Override
    public Writer append(CharSequence csq, int start, int end) throws IOException {

        writer.append(csq, start, end);
        new Event(listeners, "type", "append charsequence part", "data", csq, "start", start, "end", end).fire();
        return this;
    }

    @Override
    public Writer append(char c) throws IOException {

        writer.append(c);
        new Event(listeners, "type", "append char", "data", c).fire();
        return this;
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + (listeners == null ? 0 : listeners.hashCode());
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
        ObserverWriter other = (ObserverWriter) obj;
        if (listeners == null) {
            if (other.listeners != null) {
                return false;
            }
        } else if (!listeners.equals(other.listeners)) {
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

        return ObjectUtil.generateObjectStringWithNames(this, "writer", "listeners");
    }

}
