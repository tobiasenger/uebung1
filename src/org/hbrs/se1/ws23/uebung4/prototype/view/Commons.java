package org.hbrs.se1.ws23.uebung4.prototype.view;

public class Commons {

    public enum ErrorType {
        SpeicherFehler,
        LadeFehler,
        NummernFormat,
        WertSpanne,
        ScrumSpanne,
        ProgrammFehler
    }


    public static void help() {
        System.out.println("Folgende Befehle stehen zur Verfuegung:\n");
        System.out.println("enter:\tEingabe einer User Story");
        System.out.println("store:\tAbspeichern der User Stories");
        System.out.println("load:\tLaden von User Stories");
        System.out.println("dump:\tSortierte Ausgabe der User Stories");
        System.out.println("search:\tSuche nach User Stories nach Projekten");
        System.out.println("exit:\tVerlassen der Anwendung\n");
    }


    public static void begruessung() {
        System.out.println("\nUserStory-Tool V1.0 by Julius P. & Tobias E. (dedicated to all our friends)");
    }


    public static void eingabebefehl(String message) {
        System.out.println(message);
        System.out.print( "> ");
    }


    public static void angelegtAusgabe(int id) {
        System.out.println("User Story mit der ID " + id + " erfolgreich angelegt.");
    }


    public static void storiesReingeladen(int anzahl) {
        System.out.println("Es wurden " + anzahl + " Stories erfolgreich reingeladen.");
    }

    public static void storiesGespeichert(int anzahl) {
        System.out.println("Es wurden " + anzahl + " Stories erfolgreich gespeichert.");
    }


    public static void fehlerAusgabe(ErrorType e) {
        switch (e) {
            case LadeFehler:
                System.out.println("Fehler beim Laden"); break;
            case SpeicherFehler:
                System.out.println("Fehler beim Speichern"); break;
            case WertSpanne:
                System.out.println("Der Wert für Mehrwert, Strafe und Risiko muss zwischen 1 und 5 liegen."); break;
            case ScrumSpanne:
                System.out.println("Der Wert für Aufwand muss 0, 1, 2, 3, 5, 8, 13, 20, 40 oder 100 betragen."); break;
            case NummernFormat:
                System.out.println("Die Werte hatten ein falsches Format."); break;
            case ProgrammFehler:
                System.out.println("Das Programm wurde unerwartet beendet."); break;
        }
    }
}
