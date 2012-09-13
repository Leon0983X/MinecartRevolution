
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

    public OnceList(Collection<? extends E> collection) {

        super(collection);
    }

    public OnceList(int initialCapacity) {

        super(initialCapacity);
    }

    @Override
    public boolean add(E element) {

        if (!contains(element)) {
            return super.add(element);
        } else {
            return false;
        }
    }

    @Override
    public void add(int index, E element) {

        if (!contains(element)) {
            super.add(index, element);
        }
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {

        boolean result = true;
        for (E element : collection) {
            if (!contains(element)) {
                if (!add(element)) {
                    result = false;
                }
            }
        }

        return result;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {

        Iterator<? extends E> iterator = collection.iterator();
        E element = null;
        for (int counter = index; iterator.hasNext(); counter++, element = iterator.next()) {
            add(counter, element);
        }

        return true;
    }

    @Override
    public E set(int index, E element) {

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
