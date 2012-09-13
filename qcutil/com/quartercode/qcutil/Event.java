
package com.quartercode.qcutil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.quartercode.qcutil.utility.ObjectUtil;

public class Event implements Serializable {

    private static final long          serialVersionUID = -6662748908662427780L;

    protected List<? extends Listener> listeners;
    protected Map<String, Object>      properties       = new HashMap<String, Object>();
    protected boolean                  fired;

    public Event() {

    }

    public Event(List<? extends Listener> listeners) {

        this.listeners = listeners;
    }

    public Event(Map<String, Object> properties) {

        this.properties = properties;
    }

    public Event(Object... properties) {

        setProperties(properties);
    }

    public Event(List<? extends Listener> listeners, Map<String, Object> properties) {

        this.listeners = listeners;
        this.properties = properties;
    }

    public Event(List<? extends Listener> listeners, Object... properties) {

        this.listeners = listeners;
        setProperties(properties);
    }

    public List<? extends Listener> getListeners() {

        return listeners;
    }

    public Object getProperty(String key) {

        return properties.get(key);
    }

    protected void setProperties(Object... properties) {

        this.properties = new HashMap<String, Object>();
        for (int counter = 0; counter < properties.length; counter += 2) {
            this.properties.put(properties[counter].toString(), properties[counter + 1]);
        }
    }

    public boolean isFired() {

        return fired;
    }

    public List<Object> fire() {

        fired = true;

        List<Object> results = new ArrayList<Object>();
        for (Listener listener : listeners) {
            results.add(listener.onEvent(this));
        }

        return results;
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + (fired ? 1231 : 1237);
        result = prime * result + (listeners == null ? 0 : listeners.hashCode());
        result = prime * result + (properties == null ? 0 : properties.hashCode());
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
        Event other = (Event) obj;
        if (fired != other.fired) {
            return false;
        }
        if (listeners == null) {
            if (other.listeners != null) {
                return false;
            }
        } else if (!listeners.equals(other.listeners)) {
            return false;
        }
        if (properties == null) {
            if (other.properties != null) {
                return false;
            }
        } else if (!properties.equals(other.properties)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return ObjectUtil.generateObjectStringWithNames(this, "fired", "properties", "listeners");
    }

}
