
package com.quartercode.qcutil.ds;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import com.quartercode.qcutil.utility.ObjectUtil;

public class OnceList<E> extends ArrayList<E> {

    private static final long serialVersionUID = -7492239711802874604L;

    public OnceList() {

        super();
    }

    public OnceList(final Collection<? extends E> collection) {

        super(collection);
    }

    public OnceList(final int initialCapacity) {

        super(initialCapacity);
    }

    @Override
    public boolean add(final E element) {

        if (!contains(element)) {
            return super.add(element);
        } else {
            return false;
        }
    }

    @Override
    public void add(final int index, final E element) {

        if (!contains(element)) {
            super.add(index, element);
        }
    }

    @Override
    public boolean addAll(final Collection<? extends E> collection) {

        boolean result = true;
        for (final E element : collection) {
            if (!contains(element)) {
                if (!add(element)) {
                    result = false;
                }
            }
        }

        return result;
    }

    @Override
    public boolean addAll(final int index, final Collection<? extends E> collection) {

        final Iterator<? extends E> iterator = collection.iterator();
        E element = null;
        for (int counter = index; iterator.hasNext(); counter++, element = iterator.next()) {
            add(counter, element);
        }

        return true;
    }

    @Override
    public E set(final int index, final E element) {

        if (!contains(element)) {
            return super.set(index, element);
        } else {
            return get(index);
        }
    }

    @Override
    public String toString() {

        return ObjectUtil.generateObjectString(this, "elements", super.toString());
    }

}
