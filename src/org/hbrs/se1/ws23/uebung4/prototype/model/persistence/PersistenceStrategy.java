package org.hbrs.se1.ws23.uebung4.prototype.model.persistence;

import java.util.List;

public interface PersistenceStrategy<E> {
    public void save(List<E> member) throws PersistenceException;
    public List<E> load() throws PersistenceException;
}
