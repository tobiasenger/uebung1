package org.hbrs.se1.ws23.uebung9;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static Document textD1 = new TextDocument(1, "Inhalt1", TextDocument.Encoding.UTF8);
    public static Document textD2 = new TextDocument(2, "Inhalt2", TextDocument.Encoding.UTF16);
    public static Document textD3 = new TextDocument(3, "Inhalt3", TextDocument.Encoding.UTF32);
    public static Document textD4 = new TextDocument(4, "Inhalt4", TextDocument.Encoding.UTF8);
    public static Document textD5 = new TextDocument(5, "Inhalt5", TextDocument.Encoding.UTF16);
    public static Document textD6 = new TextDocument(6, "Inhalt6", TextDocument.Encoding.UTF32);
    public static Document textD7 = new TextDocument(7, "Inhalt7", TextDocument.Encoding.UTF8);
    public static Document textD8 = new TextDocument(8, "Inhalt8", TextDocument.Encoding.UTF16);
    public static Document grafD1 = new GraficDocument(9, "www.url.de");
    public static Document grafD2 = new GraficDocument(10, "www.url.de");
    public static Document grafD3 = new GraficDocument(11, "www.url.de");
    public static Document grafD4 = new GraficDocument(12, "www.url.de");
    public static Document grafD5 = new GraficDocument(13, "www.url.de");
    public static Document grafD6 = new GraficDocument(14, "www.url.de");
    public static Document grafD7 = new GraficDocument(15, "www.url.de");
    public static Document grafD8 = new GraficDocument(16, "www.url.de");
    public static ComplexDocument compD1 = new ComplexDocument(101);
    public static ComplexDocument compD2 = new ComplexDocument(102);
    public static ComplexDocument compD3 = new ComplexDocument(103);
    public static ComplexDocument compD4 = new ComplexDocument(104);

    public static void main(String[] args) {
        try {
            compD1.add(textD1);
            compD1.add(textD2);
            compD2.add(textD3);
            compD3.add(textD4);
            compD1.add(textD5);
            compD3.add(textD6);
            compD4.add(textD7);
            compD1.add(textD8);
            compD1.add(grafD1);
            compD2.add(grafD2);
            compD1.add(grafD3);
            compD4.add(grafD4);
            compD3.add(grafD5);
            compD2.add(grafD6);
            compD2.add(grafD7);
            compD1.add(grafD8);
            compD1.add(compD2);
            compD2.add(compD3);
            compD1.add(compD4);
            compD4.add(compD1);
        } catch (DocumentIDException d) {
            System.out.println("Fehlgeschlagen! " + d.getMessage());
        }

        System.out.println(compD1.byteSize());

        compD1.printIDs();

        for(Integer i: compD1.listIds()) {
            System.out.println(i);
        }
    }
}
