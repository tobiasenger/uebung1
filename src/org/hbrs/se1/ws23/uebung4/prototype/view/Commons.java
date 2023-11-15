package org.hbrs.se1.ws23.uebung4.prototype.view;

public class Commons {

    public static void searchStart() {
        System.out.println("Bitte Suchbegriff eingeben.");
    }


    public enum ErrorType {
        SpeicherFehler,
        LadeFehler,
        AndereFehler
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


    public static void inputArrow() {
        System.out.print( "\n> "  );
    }


    public static void begruessung() {
        System.out.println("\nUserStory-Tool V1.0 by Julius P. (dedicated to all my friends)");
    }


    public static void fehlerAusgabe(ErrorType e) {
        switch (e) {
            case LadeFehler:
                System.out.println("Fehler beim Laden");
            case SpeicherFehler:
                System.out.println("Fehler beim Speichern");
            case AndereFehler:
                System.out.println("Anderer Fehler");
        }
    }
}
