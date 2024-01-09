package org.hbrs.se1.ws23.uebung8;

public class HotelsucheAdapter implements HotelsucheInterface {
    @Override
    public SuchErgebnis sucheHotel(SuchAuftrag suchAuftrag) {
        ReiseAnbieter reiseAnbieter = new ReiseAnbieter();
        QueryObject queryObject = transformSuchAuftrag(suchAuftrag);
        QueryResult queryResult = reiseAnbieter.executeQuery(queryObject);
        SuchErgebnis suchErgebnis = transformSuchErgebnis(queryResult);
        return suchErgebnis;
    }

    private QueryObject transformSuchAuftrag(SuchAuftrag suchAuftrag) {
        QueryObject qObject = new QueryObject();
        // Transformation SuchAuftrag zu QueryObject
        return qObject;
    }

    private SuchErgebnis transformSuchErgebnis(QueryResult qResult) {
        SuchErgebnis suchErgebnis = new SuchErgebnis();
        // Transformation QueryResult zu SuchErgebnis
        return suchErgebnis;
    }
}
