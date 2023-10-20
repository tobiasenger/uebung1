package org.hbrs.se1.ws23.uebung2;

public class ConcreteMember implements Member {

    private static Integer serial = 0;
    private final Integer id;

    public ConcreteMember() {
        id = serial++;
    }

    @Override
    public Integer getID() {
        return id;
    }
}
