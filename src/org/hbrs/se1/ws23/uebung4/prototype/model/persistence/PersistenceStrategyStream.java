package org.hbrs.se1.ws23.uebung4.prototype.model.persistence;

import org.hbrs.se1.ws23.uebung4.prototype.model.Commons;
import org.hbrs.se1.ws23.uebung4.prototype.model.Container;

import java.io.*;
import java.util.List;

public class PersistenceStrategyStream<E> implements PersistenceStrategy<E> {

    @Override
    public void save(List<E> list) throws PersistenceException {
        ObjectOutputStream oos = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream( Commons.LOCATION );
            oos = new ObjectOutputStream(fos);

            oos.writeObject( Container.getInstance().getCurrentList() );
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new PersistenceException(PersistenceException.ExceptionType.SaveFailure, "Fehler beim Speichern");
        }
    }

    @Override
    public List<E> load() throws PersistenceException {
        ObjectInputStream ois = null;
        FileInputStream fis = null;
        List<E> liste = null;
        try {
            fis = new FileInputStream( Commons.LOCATION );
            ois = new ObjectInputStream(fis);

            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                liste = (List) obj;
            }
        }
        catch (IOException e) {}
        catch (ClassNotFoundException e) {}
        finally {
            if (ois != null) try { ois.close(); } catch (IOException e) {}
            if (fis != null) try { fis.close(); } catch (IOException e) {}
        }
        return liste;
    }
}
