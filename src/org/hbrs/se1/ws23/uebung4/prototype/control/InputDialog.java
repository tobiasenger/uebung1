package org.hbrs.se1.ws23.uebung4.prototype.control;

import org.hbrs.se1.ws23.uebung4.prototype.model.Container;
import org.hbrs.se1.ws23.uebung4.prototype.model.ContainerException;
import org.hbrs.se1.ws23.uebung4.prototype.model.UserStory;
import org.hbrs.se1.ws23.uebung4.prototype.view.UserStoryView;
import org.hbrs.se1.ws23.uebung4.prototype.view.Commons;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputDialog {
    public static void startEingabe() throws ContainerException, Exception {

        while ( true ) {
            Commons.inputArrow();
            switch(new Scanner(System.in).nextLine()) {
                case "help": help(); break;
                case "enter": enter(); break;
                case "store": store(); break;
                case "load": load(); break;
                case "dump": dump(); break;
                case "search": search(); break;
                case "exit": exit();
            }
        }
    }


    private static void exit() {
        System.exit(0);
    }


    private static void help() {
         Commons.help();
     }


    private static void enter() {
        UserStory userStory = null;
        try {
            userStory = parameterEinlesen();
        } catch (NumberFormatException nfe) {
            System.out.println("\nUngültiges Eingabeformat, bitte versuchen Sie es erneut.\nGeben Sie für die ID," +
                    " Mehrwert, Strafe, Aufwand und\nRisiko nur ganzzahlige Werte ein.\n\nFür alle anderen Eingaben " +
                    "können Sie einen beliebigen\nFreitext wählen.\n");
        } catch (ContainerException ce) {
            System.out.println(ce.getMessage());
        }
        if(userStory != null) {
            while(Container.getInstance().contains(userStory)) {
                System.out.println("\nEine User Story mit dieser ID existiert bereits.\n" +
                        "Bitte geben Sie für Ihre User Story eine andere ID ein!\n");
                System.out.print( "> "  );
                userStory.setId(new Scanner(System.in).nextInt());
            }
            try {
                Container.getInstance().addUserStory(userStory);
            } catch (ContainerException ce) {
                System.out.println("\nSpeichervorgang Fehlgeschlagen!");
            }
        }
    }


    private static void store() {
        try {
            Container.getInstance().store();
        } catch (ContainerException e) {
            Commons.fehlerAusgabe(Commons.ErrorType.SpeicherFehler);
        }
    }


    private static void load() {
        Container.getInstance().load();
    }


    private static void dump() {
        UserStoryView.showList(Container.getInstance().getCurrentList());
    }


    private static void search() {
        Commons.searchStart();
        Commons.inputArrow();
        String suchbegriff = new Scanner(System.in).next();
        Container.getInstance().search(suchbegriff);
    }


    private static UserStory parameterEinlesen() throws ContainerException {
        List<Integer> scrumNumbers = Arrays.asList(0,1,2,3,5,8,13,20,40,100);
        System.out.println("Für welches Projekt möchten Sie eine User Story erstellen?");
        System.out.print( "> "  );
        String projekt = new Scanner(System.in).nextLine();
        System.out.println("Bitte geben Sie die ID ein.");
        System.out.print( "> "  );
        int id = new Scanner(System.in).nextInt();
        System.out.println("Bitte geben Sie den Titel der User Story ein.");
        System.out.print( "> "  );
        String titel = new Scanner(System.in).nextLine();
        System.out.println("Bitte geben Sie eine kurze Beschreibung ein.");
        System.out.print( "> "  );
        String beschreibung = new Scanner(System.in).nextLine();
        System.out.println("Bitte geben Sie ein Akzeptanzkriterium ein.");
        System.out.print( "> "  );
        String akzeptanz = new Scanner(System.in).nextLine();
        System.out.println("Bitte geben Sie den Mehrwert ein.");
        System.out.print( "> "  );
        int mehrwert = new Scanner(System.in).nextInt();
        if (mehrwert > 5 || mehrwert < 1) {
            throw new ContainerException("Der Wert muss zwischen 1 und 5 liegen.");
        }
        System.out.println("Bitte geben Sie die Strafe ein.");
        System.out.print( "> "  );
        int strafe = new Scanner(System.in).nextInt();
        if (strafe > 5 || strafe < 1) {
            throw new ContainerException("Der Wert muss zwischen 1 und 5 liegen.");
        }
        System.out.println("Bitte geben Sie den Aufwand ein.");
        System.out.print( "> "  );
        int aufwand = new Scanner(System.in).nextInt();
        if (!scrumNumbers.contains(aufwand)) {
            throw new ContainerException("Der Wert muss 0, 1, 2, 3, 5, 8, 13, 20, 40 oder 100 sein.");
        }
        System.out.println("Bitte geben Sie das Risiko ein.");
        System.out.print( "> "  );
        int risiko = new Scanner(System.in).nextInt();
        if (risiko > 5 || risiko < 1) {
            throw new ContainerException("Der Wert muss zwischen 1 und 5 liegen.");
        }
        double prio = (double)(mehrwert + strafe) / (aufwand + risiko);
        UserStory userStory = new UserStory(id, titel, beschreibung, akzeptanz, mehrwert, strafe,
                aufwand, risiko, prio);
        userStory.setProject(projekt);
        return userStory;
    }
}
