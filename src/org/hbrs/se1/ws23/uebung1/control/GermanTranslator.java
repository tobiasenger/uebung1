package org.hbrs.se1.ws23.uebung1.control;

import java.util.HashMap;
import java.util.Map;

public class GermanTranslator implements Translator {
	HashMap<Integer, String> hm = new HashMap<Integer, String>();

	public String date = "Okt/2023"; // Default-Wert

	/**
	 * Methode zur Übersetzung einer Zahl in eine String-Repraesentation
	 */
	public String translateNumber( int number ) {
		String s;
		hm.put(1, "eins");
		hm.put(2, "zwei");
		hm.put(3, "drei");
		hm.put(4, "vier");
		hm.put(5, "fünf");
		hm.put(6, "sechs");
		hm.put(7, "sieben");
		hm.put(8, "acht");
		hm.put(9, "neun");
		hm.put(10, "zehn");
		if (hm.containsKey(number)) {
			return  hm.get(number);
		} else {
			return "Übersetzung der Zahl " + number + " nicht möglich " + Translator.version;
		}
	}

	/**
	 * Objektmethode der Klasse GermanTranslator zur Ausgabe einer Info.
	 */
	public void printInfo(){
		System.out.println( "GermanTranslator v1.9, erzeugt am " + this.date );
	}

	/**
	 * Setzen des Datums, wann der Uebersetzer erzeugt wurde (Format: Monat/Jahr (Beispiel: "Okt/2022"))
	 * Das Datum sollte system-intern durch eine Control-Klasse gesetzt werden und nicht von externen View-Klassen
	 */
	public void setDate( String date ) {
		this.date = date;
	}

}
