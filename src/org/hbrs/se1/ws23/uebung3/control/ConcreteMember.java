package org.hbrs.se1.ws23.uebung3.control;

public class ConcreteMember implements Member {

    private final Integer id;

    public ConcreteMember(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public String toString() {
        return "Member (ID = " + id + ")";
    }


}
