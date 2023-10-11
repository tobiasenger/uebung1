package org.hbrs.se1.ws23.uebung1.test;

import org.hbrs.se1.ws23.uebung1.control.GermanTranslator;
import org.hbrs.se1.ws23.uebung1.control.Translator;
import org.hbrs.se1.ws23.uebung1.view.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GermanTranslatorTest {

    @Test
    void aPositiveTest() {
        GermanTranslator translator = new GermanTranslator();
        String value = translator.translateNumber(1);
        assertEquals("eins", value);
        value = translator.translateNumber(2);
        assertEquals("zwei", value);
        value = translator.translateNumber(3);
        assertEquals("drei", value);
        value = translator.translateNumber(4);
        assertEquals("vier", value);
        value = translator.translateNumber(5);
        assertEquals("fünf", value);
        value = translator.translateNumber(6);
        assertEquals("sechs", value);
        value = translator.translateNumber(7);
        assertEquals("sieben", value);
        value = translator.translateNumber(8);
        assertEquals("acht", value);
        value = translator.translateNumber(9);
        assertEquals("neun", value);
        value = translator.translateNumber(10);
        assertEquals("zehn", value);
    }

    @Test
    void aNegativeTest() {
        GermanTranslator translator = new GermanTranslator();
        String value = translator.translateNumber(0);
        assertEquals("Übersetzung der Zahl " + 0 + " nicht möglich " + Translator.version, value);
        value = translator.translateNumber(11);
        assertEquals("Übersetzung der Zahl " + 11 + " nicht möglich " + Translator.version, value);
        value = translator.translateNumber(-1);
        assertEquals("Übersetzung der Zahl " + -1 + " nicht möglich " + Translator.version, value);
    }
}