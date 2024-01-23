package org.hbrs.se1.ws23.uebung9;

public abstract class CoreDocument implements Document {

    private int id;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
