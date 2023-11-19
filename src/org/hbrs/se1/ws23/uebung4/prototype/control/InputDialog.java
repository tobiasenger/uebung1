package org.hbrs.se1.ws23.uebung4.prototype.control;

import org.hbrs.se1.ws23.uebung4.prototype.model.Container;
import org.hbrs.se1.ws23.uebung4.prototype.model.ContainerException;
import org.hbrs.se1.ws23.uebung4.prototype.model.UserStory;
import org.hbrs.se1.ws23.uebung4.prototype.model.persistence.PersistenceException;
import org.hbrs.se1.ws23.uebung4.prototype.view.UserStoryView;
import org.hbrs.se1.ws23.uebung4.prototype.view.Commons;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class InputDialog {
    public static void startEingabe() throws ContainerException, Exception {

        while ( true ) {
            Commons.eingabebefehl("");
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
            Commons.fehlerAusgabe(Commons.ErrorType.NummernFormat);
        }

        if(userStory != null) {
            while(Container.getInstance().contains(userStory)) {
                Commons.eingabebefehl("Eine User Story mit dieser ID existiert bereits.\n" +
                        "Bitte geben Sie für Ihre User Story eine andere ID ein!");
                userStory.setId(new Scanner(System.in).nextInt());
            }
            try {
                Container.getInstance().addUserStory(userStory);
            } catch (ContainerException ce) {
                Commons.fehlerAusgabe(Commons.ErrorType.SpeicherFehler);
            }

        }
    }


    private static void store() {
        try {
            Container.getInstance().store();
        } catch (PersistenceException pe) {
            Commons.fehlerAusgabe(Commons.ErrorType.SpeicherFehler);
        }
    }


    private static void load() {
        try {
            Container.getInstance().load();
        } catch (PersistenceException pe) {
            Commons.fehlerAusgabe(Commons.ErrorType.LadeFehler);
        }
    }


    private static void dump() {
        Commons.eingabebefehl("Um User Stories zu einem Projekt auszugeben, bitte Projektnamen eingeben,\n" +
                "für eine tabellarische Übersicht aller Stories bitte ohne Eingabe Enter drücken.");
        String suchbegriff = new Scanner(System.in).nextLine();
        if(suchbegriff.equals("")) {
            UserStoryView.showList(Container.getInstance().getCurrentList());
        } else {
            UserStoryView.showProjectList(Container.getInstance().getCurrentList(), suchbegriff);
        }
    }


    private static void search() {
        Commons.eingabebefehl("Bitte Suchbegriff eingeben.");
        String suchbegriff = new Scanner(System.in).nextLine();
        List<UserStory> liste = Container.getInstance().getCurrentList()
                .stream()
                .filter(userStory -> userStory.getProject().equals(suchbegriff))
                .toList();
        UserStoryView.showList(liste);
    }


    private static UserStory parameterEinlesen()  {
        List<Integer> scrumNumbers = Arrays.asList(0,1,2,3,5,8,13,20,40,100);

        Commons.eingabebefehl("Für welches Projekt möchten Sie eine User Story erstellen?");
        String projekt = new Scanner(System.in).nextLine();

        Commons.eingabebefehl("Bitte geben Sie die ID ein.");
        int id = new Scanner(System.in).nextInt();

        Commons.eingabebefehl("Bitte geben Sie den Titel der User Story ein.");
        String titel = new Scanner(System.in).nextLine();

        Commons.eingabebefehl("Bitte geben Sie eine kurze Beschreibung ein.");
        String beschreibung = new Scanner(System.in).nextLine();

        Commons.eingabebefehl("Bitte geben Sie ein Akzeptanzkriterium ein.");
        String akzeptanz = new Scanner(System.in).nextLine();

        int mehrwert = 0;
        while (mehrwert > 5 || mehrwert < 1) {
            Commons.eingabebefehl("Bitte geben Sie den Mehrwert ein.");
            mehrwert = new Scanner(System.in).nextInt();
            if(mehrwert > 5 || mehrwert < 1) Commons.fehlerAusgabe(Commons.ErrorType.WertSpanne);
        }

        int strafe = 0;
        while (strafe > 5 || strafe < 1) {
            Commons.eingabebefehl("Bitte geben Sie die Strafe ein.");
            strafe = new Scanner(System.in).nextInt();
            if(strafe > 5 || strafe < 1) Commons.fehlerAusgabe(Commons.ErrorType.WertSpanne);
        }

        int risiko = 0;
        while (risiko > 5 || risiko < 1) {
            Commons.eingabebefehl("Bitte geben Sie das Risiko ein.");
            risiko = new Scanner(System.in).nextInt();
            if(risiko > 5 || risiko < 1) Commons.fehlerAusgabe(Commons.ErrorType.WertSpanne);
        }

        int aufwand = -1;
        while (!scrumNumbers.contains(aufwand)) {
            Commons.eingabebefehl("Bitte geben Sie den Aufwand ein.");
            aufwand = new Scanner(System.in).nextInt();
            if(!scrumNumbers.contains(aufwand)) Commons.fehlerAusgabe(Commons.ErrorType.ScrumSpanne);
        }

        double prio = (double)(mehrwert + strafe) / (aufwand + risiko);
        UserStory userStory = new UserStory(id, titel, beschreibung, akzeptanz, mehrwert, strafe,
                aufwand, risiko, prio);
        userStory.setProject(projekt);

        Commons.angelegtAusgabe(userStory.getId());

        return userStory;
    }
}
