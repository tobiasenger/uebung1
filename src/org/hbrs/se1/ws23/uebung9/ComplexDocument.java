package org.hbrs.se1.ws23.uebung9;

import java.util.ArrayList;
import java.util.List;

public class ComplexDocument implements Document {
    List<Document> documents = new ArrayList<>();

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setId(int id) {

    }
}
