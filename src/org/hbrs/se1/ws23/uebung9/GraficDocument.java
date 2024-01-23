package org.hbrs.se1.ws23.uebung9;

public class GraficDocument extends CoreDocument {

    String url;

    public GraficDocument(int id, String url) {
        setId(id);
        this.url = url;
    }
    @Override
    public int byteSize() {
        return 1200;
    }
}
