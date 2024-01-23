package org.hbrs.se1.ws23.uebung9;

public interface Document extends Iterable {
    int byteSize();
    int getId();
    void setId(int id);
}
