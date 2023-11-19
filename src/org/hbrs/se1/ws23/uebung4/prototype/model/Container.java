package org.hbrs.se1.ws23.uebung4.prototype.model;

import org.hbrs.se1.ws23.uebung4.prototype.model.persistence.PersistenceException;
import org.hbrs.se1.ws23.uebung4.prototype.model.persistence.PersistenceStrategy;
import org.hbrs.se1.ws23.uebung4.prototype.model.persistence.PersistenceStrategyStream;
import org.hbrs.se1.ws23.uebung4.prototype.view.Commons;

import java.util.*;


public class Container {
	 
	private List<UserStory> liste = null;


	private static Container instance = null;


	private PersistenceStrategy<UserStory> persistence = new PersistenceStrategyStream<>();

	public static synchronized Container getInstance() {
		if (instance == null) {
			instance = new Container();
		}
		return instance;
	}


	private Container(){
		liste = new ArrayList<UserStory>();
	}

	public void store() throws PersistenceException {
		persistence.save(liste);
		org.hbrs.se1.ws23.uebung4.prototype.view.Commons.storiesGespeichert(liste.size());
	}
	public void load() throws PersistenceException {
		liste = persistence.load();
		org.hbrs.se1.ws23.uebung4.prototype.view.Commons.storiesReingeladen(liste.size());
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
