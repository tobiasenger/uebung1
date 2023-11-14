package org.hbrs.se1.ws23.uebung4.prototype;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.sort;

/*
 * Klasse zum Management sowie zur Eingabe unnd Ausgabe von User-Stories.
 * Die Anwendung wird über dies Klasse auch gestartet (main-Methode hier vorhanden)
 *
 * erstellt von Julius P., H-BRS 2023, Version 1.0
 *
 * Strategie für die Wiederverwendung (Reuse):
 * - Anlegen der Klasse UserStory
 * - Anpassen des Generic in der List-Klasse (ALT: Member, NEU: UserStory)
 * - Anpassen der Methodennamen
 *
 * ToDo: Was ist ihre Strategie zur Wiederverwendung? (F1)
 *
 * Alternative 1:
 * Klasse UserStory implementiert Interface Member (UserStory implements Member)
 * Vorteil: Wiederverwendung von Member, ID verwenden; Strenge Implementierung gegen Interface
 * Nachteil: Viele Casts notwendig, um auf die anderen Methoden zuzugreifen
 *
 * Alternative 2:
 * Container mit Generic entwickeln (z.B. Container<E>))
 *
 * Entwurfsentscheidung: Die wichtigsten Zuständigkeiten (responsibilities) sind in einer Klasse, d.h. Container,
 * diese liegt in einem Package.
 * ToDo: Wie bewerten Sie diese Entscheidung? (F2, F6)
 * 
 */

public class Container {
	 
	// Interne ArrayList zur Abspeicherung der Objekte vom Type UserStory
	private List<UserStory> liste = null;
	
	// Statische Klassen-Variable, um die Referenz
	// auf das einzige Container-Objekt abzuspeichern
	// Diese Variante sei thread-safe, so hat Hr. P. es gehört... stimmt das?
	// Todo: Bewertung Thread-Safeness (F1)
	// Nachteil: ggf. geringer Speicherbedarf, da Singleton zu Programmstart schon erzeugt wird
	// Todo: Bewertung Speicherbedarf (F1)
	private static Container instance = new Container();
	
	// URL der Datei, in der die Objekte gespeichert werden 
	final static String LOCATION = "src/org/hbrs/se1/ws23/uebung4/prototype/allStories.ser";

	/**
	 * Liefert ein Singleton zurück.
	 * @return
	 */
	public static Container getInstance() {
		return instance;
	}
	
	/**
	 * Vorschriftsmäßiges Ueberschreiben des Konstruktors (private) gemaess Singleton-Pattern (oder?)
	 * nun auf private gesetzt! Vorher ohne Access Qualifier (--> dann package-private)
	 */
	private Container(){
		liste = new ArrayList<UserStory>();
	}
	
	/**
	 * Start-Methoden zum Starten des Programms 
	 * (hier koennen ggf. weitere Initialisierungsarbeiten gemacht werden spaeter)
	 */
	public static void main (String[] args) throws Exception {
		// ToDo: Bewertung Exception-Handling (F3, F7)
		Container con = Container.getInstance();
		con.startEingabe(); 
	}
	
	/*
	 * Diese Methode realisiert eine Eingabe ueber einen Scanner
	 * Alle Exceptions werden an den aufrufenden Context (hier: main) weitergegeben (throws)
	 * Das entlastet den Entwickler zur Entwicklungszeit und den Endanwender zur Laufzeit
	 */
	public void startEingabe() throws ContainerException, Exception {
		String strInput = null;
		
		// Initialisierung des Eingabe-View
		// ToDo: Funktionsweise des Scanners erklären (F3)
		Scanner scanner = new Scanner( System.in );

		System.out.println("\nUserStory-Tool V1.0 by Julius P. (dedicated to all my friends)");
		while ( true ) {
			// Ausgabe eines Texts zur Begruessung
			System.out.print( "\n> "  );

			strInput = scanner.nextLine();
		
			// Extrahiert ein Array aus der Eingabe
			String[] strings = strInput.split(" ");

			// 	Falls 'help' eingegeben wurde, werden alle Befehle ausgedruckt
			if ( strings[0].equals("help") ) {
				System.out.println("Folgende Befehle stehen zur Verfuegung:\n");
				System.out.println("enter:\tEingabe einer User Story");
				System.out.println("store:\tAbspeichern der User Stories");
				System.out.println("load:\tLaden von User Stories");
				System.out.println("dump:\tSortierte Ausgabe der User Stories");
				System.out.println("search:\tSuche nach User Stories nach Projekten");
				System.out.println("exit:\tVerlassen der Anwendung\n");
			}
			// Auswahl der bisher implementierten Befehle:
			if ( strings[0].equals("dump") ) {
				startAusgabe();
			}
			// Auswahl der bisher implementierten Befehle:
			if ( strings[0].equals("enter") ) {
				UserStory userStory = null;
				try {
					userStory = parameterEinlesen();
				} catch (NumberFormatException nfe) {
					System.out.println("\nUngültiges Eingabeformat, bitte versuchen Sie es erneut.\nGeben Sie für die ID," +
							" Mehrwert, Strafe, Aufwand und\nRisiko nur ganzzahlige Werte ein.\n\nFür alle anderen Eingaben " +
							"können Sie einen beliebigen\nFreitext wählen.\n");
				}
				if(userStory != null) {
					while(contains(userStory)) {
						System.out.println("\nEine User Story mit dieser ID existiert bereits.\n" +
								"Bitte geben Sie für Ihre User Story eine andere ID ein!\n");
						System.out.print( "> "  );
						userStory.setId(new Scanner(System.in).nextInt());
					}
					try {
						addUserStory(userStory);
					} catch (ContainerException ce) {
						System.out.println("\nSpeichervorgang Fehlgeschlagen!");
					}
				}
			}
								
			if (  strings[0].equals("store")  ) {
				this.store();
			}

			if (  strings[0].equals("load")  ) {
				load();
			}

			if (  strings[0].equals("search")  ) {
				System.out.println("Bitte Suchbegriff eingeben.");
				System.out.print( "> "  );
				String suchbegriff = new Scanner(System.in).next();
				search(suchbegriff);
			}

			if (  strings[0].equals("exit")  ) {
				break;
			}
		} // Ende der Schleife
	}

	private void search(String suchbegriff) {
		ArrayList<UserStory> projektStories = new ArrayList<>();
		for(UserStory userStory: liste) {
			if (userStory.getProject().contains(suchbegriff)) {
				projektStories.add(userStory);
			}
		}
		sort(projektStories);
		for (UserStory projektStory : projektStories) {
			System.out.println(projektStory.toString());
		}
	}

	private UserStory parameterEinlesen() throws ContainerException {
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
		System.out.println("Bitte geben Sie die Strafe ein.");
		System.out.print( "> "  );
		int strafe = new Scanner(System.in).nextInt();
		System.out.println("Bitte geben Sie den Aufwand ein.");
		System.out.print( "> "  );
		int aufwand = new Scanner(System.in).nextInt();
		System.out.println("Bitte geben Sie das Risiko ein.");
		System.out.print( "> "  );
		int risiko = new Scanner(System.in).nextInt();
		double prio = (double)(mehrwert + strafe) / (aufwand + risiko);
		UserStory userStory = new UserStory(id, titel, beschreibung, akzeptanz, mehrwert, strafe,
				aufwand, risiko, prio);
		userStory.setProject(projekt);
		return userStory;
	}

	/**
	 * Diese Methode realisiert die Ausgabe.
	 */
	public void startAusgabe() {

		// Hier möchte Herr P. die Liste mit einem eigenen Sortieralgorithmus sortieren und dann
		// ausgeben. Allerdings weiss der Student hier nicht weiter

		// [Sortierung ausgelassen]
		// Todo: Implementierung Sortierung (F4)
		sort(liste);

		// Klassische Ausgabe ueber eine For-Each-Schleife
		for (UserStory story : liste) {
			System.out.println(story.toString());
		}

		// [Variante mit forEach-Methode / Streams (--> Kapitel 9, Lösung Übung Nr. 2)?
		//  Gerne auch mit Beachtung der neuen US1
		// (Filterung Projekt = "ein Wert (z.B. Coll@HBRS)" und Risiko >=5
		// Todo: Implementierung Filterung mit Lambda (F5)

	}

	/*
	 * Methode zum Speichern der Liste. Es wird die komplette Liste
	 * inklusive ihrer gespeicherten UserStory-Objekte gespeichert.
	 * 
	 */
	private void store() throws ContainerException {
		ObjectOutputStream oos = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream( Container.LOCATION );
			oos = new ObjectOutputStream(fos);

			oos.writeObject( this.liste );
			System.out.println( this.size() + " UserStory wurden erfolgreich gespeichert!");
		}
		catch (IOException e) {
			e.printStackTrace();
		  //  Delegation in den aufrufendem Context
		  // (Anwendung Pattern "Chain Of Responsibility)
		  throw new ContainerException("Fehler beim Abspeichern");
		}
	}

	/*
	 * Methode zum Laden der Liste. Es wird die komplette Liste
	 * inklusive ihrer gespeicherten UserStory-Objekte geladen.
	 * 
	 */
	public void load() {
		ObjectInputStream ois = null;
		FileInputStream fis = null;
		try {
		  fis = new FileInputStream( Container.LOCATION );
		  ois = new ObjectInputStream(fis);
		  
		  // Auslesen der Liste
		  Object obj = ois.readObject();
		  if (obj instanceof List<?>) {
			  this.liste = (List) obj;
		  }
		  System.out.println("Es wurden " + this.size() + " UserStory erfolgreich reingeladen!");
		}
		catch (IOException e) {
			System.out.println("LOG (für Admin): Datei konnte nicht gefunden werden!");
		}
		catch (ClassNotFoundException e) {
			System.out.println("LOG (für Admin): Liste konnte nicht extrahiert werden (ClassNotFound)!");
		}
		finally {
		  if (ois != null) try { ois.close(); } catch (IOException e) {}
		  if (fis != null) try { fis.close(); } catch (IOException e) {}
		}
	}

	/**
	 * Methode zum Hinzufügen eines Mitarbeiters unter Wahrung der Schlüsseleigenschaft
	 * @param userStory
	 * @throws ContainerException
	 */
	public void addUserStory ( UserStory userStory ) throws ContainerException {
		if ( contains(userStory)) {
			ContainerException ex = new ContainerException("ID bereits vorhanden!");
			throw ex;
		}
		liste.add(userStory);
	}

	/**
	 * Prüft, ob eine UserStory bereits vorhanden ist
	 * @param userStory
	 * @return
	 */
	private boolean contains( UserStory userStory) {
		int ID = userStory.getId();
		for ( UserStory userStory1 : liste) {
			if ( userStory1.getId() == ID ) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Ermittlung der Anzahl von internen UserStory
	 * -Objekten
	 * @return
	 */
	public int size() {
		return liste.size();
	}

	/**
	 * Methode zur Rückgabe der aktuellen Liste mit Stories
	 * Findet aktuell keine Anwendung bei Hr. P.
	 * @return
	 */
	public List<UserStory> getCurrentList() {
		return this.liste;
	}

	/**
	 * Liefert eine bestimmte UserStory zurück
	 * @param id
	 * @return
	 */
	private UserStory getUserStory(int id) {
		for ( UserStory userStory : liste) {
			if (id == userStory.getId() ){
				return userStory;
			}
		}
		return null;
	}

}
