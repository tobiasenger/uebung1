package org.hbrs.se1.ws23.uebung1.control;

public class Factory {
    public static Translator createGermanTranslator() {
        return new GermanTranslator();
    }
}
