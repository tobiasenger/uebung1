package org.hbrs.se1.ws23.uebung9;

import java.util.Iterator;

public abstract class CoreDocument implements Document, Iterable {

    private int id;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Iterator iterator() {
        return new DocumentIterator();
    }

    public class DocumentIterator implements Iterator {

        boolean unread = true;

        @Override
        public boolean hasNext() {
            return unread;
        }

        @Override
        public Object next() {
            unread = false;
            return this;
        }
    }
}
