/*
 * This file is part of MinecartRevolution-Core.
 * Copyright (c) 2012 QuarterCode <http://www.quartercode.com/>
 *
 * MinecartRevolution-Core is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MinecartRevolution-Core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MinecartRevolution-Core. If not, see <http://www.gnu.org/licenses/>.
 */

package com.quartercode.minecartrevolution.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class UniqueList<E> extends ArrayList<E> {

    private static final long serialVersionUID = 905273589950384675L;

    public UniqueList() {

        super();
    }

    public UniqueList(Collection<? extends E> collection) {

        super(collection);
    }

    public UniqueList(int initialCapacity) {

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

}
