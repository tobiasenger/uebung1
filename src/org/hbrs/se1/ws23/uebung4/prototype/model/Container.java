package org.hbrs.se1.ws23.uebung4.prototype.model;

import java.io.*;
import java.util.*;

import static java.util.Collections.sort;


public class Container {
	 
	private List<UserStory> liste = null;

	private static Container instance = null;


	public static synchronized Container getInstance() {
		if (instance == null) {
			instance = new Container();
		}
		return instance;
	}


	private Container(){
		liste = new ArrayList<UserStory>();
	}


	public void search(String suchbegriff) {
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


	public void startAusgabe() {
		sort(liste);

		for (UserStory story : liste) {
			System.out.println(story.toString());
		}
	}


	public void store() throws ContainerException {
		ObjectOutputStream oos = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream( Commons.LOCATION );
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


	public void load() {
		ObjectInputStream ois = null;
		FileInputStream fis = null;
		try {
		  fis = new FileInputStream( Commons.LOCATION );
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


	public void addUserStory ( UserStory userStory ) throws ContainerException {
		if ( contains(userStory)) {
			ContainerException ex = new ContainerException("ID bereits vorhanden!");
			throw ex;
		}
		liste.add(userStory);
	}


	public boolean contains( UserStory userStory) {
		int ID = userStory.getId();
		for ( UserStory userStory1 : liste) {
			if ( userStory1.getId() == ID ) {
				return true;
			}
		}
		return false;
	}


	public int size() {
		return liste.size();
	}


	public List<UserStory> getCurrentList() {
		return this.liste;
	}


	private UserStory getUserStory(int id) {
		for ( UserStory userStory : liste) {
			if (id == userStory.getId() ){
				return userStory;
			}
		}
		return null;
	}
}
