package org.hbrs.se1.ws23.uebung4.prototype.control;

import org.hbrs.se1.ws23.uebung4.prototype.view.Commons;

public class Main {
    public static void main (String[] args) {
        Commons.begruessung();
        try {
            InputDialog.startEingabe();
        } catch (Exception e) {
            System.out.println("Fehler bei der Programmausführung");
        }
    }
}

