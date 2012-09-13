
package com.quartercode.qcutil.io;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.quartercode.qcutil.utility.ObjectUtil;

public class MultiWriter extends Writer {

    protected List<Writer> writers;

    public MultiWriter(Writer... writers) {

        this.writers = new ArrayList<Writer>(Arrays.asList(writers));
    }

    public void setWriters(List<Writer> writers) {

        this.writers = writers;
    }

    public List<Writer> getWriters() {

        return writers;
    }

    @Override
    public void close() throws IOException {

        for (Writer writer : writers) {
            writer.close();
        }
    }

    @Override
    public void flush() throws IOException {

        for (Writer writer : writers) {
            writer.flush();
        }
    }

    @Override
    public void write(int c) throws IOException {

        for (Writer writer : writers) {
            writer.write(c);
        }
    }

    @Override
    public void write(char[] cbuf) throws IOException {

        for (Writer writer : writers) {
            writer.write(cbuf);
        }
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {

        for (Writer writer : writers) {
            writer.write(cbuf, off, len);
        }
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + (writers == null ? 0 : writers.hashCode());
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
        MultiWriter other = (MultiWriter) obj;
        if (writers == null) {
            if (other.writers != null) {
                return false;
            }
        } else if (!writers.equals(other.writers)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return ObjectUtil.generateObjectStringWithNames(this, "writers");
    }

}
