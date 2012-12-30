
package com.quartercode.qcutil.ds;

import java.util.Collection;

public class ClassList extends OnceList<Class<?>> {

    private static final long serialVersionUID = -1561981976742618171L;

    public ClassList() {

        super();
    }

    public ClassList(final Collection<Class<?>> collection) {

        super(collection);
    }

    public ClassList(final int initialCapacity) {

        super(initialCapacity);
    }

}
