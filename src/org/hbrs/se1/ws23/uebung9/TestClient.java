package org.hbrs.se1.ws23.uebung9;

public class TestClient {

    public static void main(String[] args) {
        ComplexDocument doc0 = new ComplexDocument(0);
        TextDocument doc2 = new TextDocument(2, "Die Klausur im Fach SE findet bald statt!", TextDocument.Encoding.UTF8);
        ComplexDocument doc3 = new ComplexDocument(3);
        GraficDocument doc4 = new GraficDocument(4, "localhost:8080");
        TextDocument doc5 = new TextDocument(5, "Software Engineering I ist eine Vorlesung in den Studiengaengen BIS und BCS", TextDocument.Encoding.UTF32);

        try {
            doc3.add(doc4);
            doc3.add(doc5);
            doc0.add(doc2);
            doc0.add(doc3);
        } catch (DocumentIDException e) {
            System.out.println("Hinzufügen fehlerhaft! " + e.getMessage());
            System.exit(0);
        }

        System.out.println(doc0.byteSize());

    }
}
