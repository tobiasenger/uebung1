package org.hbrs.se1.ws23.uebung3;

import org.hbrs.se1.ws23.uebung3.control.Container;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategyStream;
import org.hbrs.se1.ws23.uebung3.view.Client;

public class Main {
    public static void main(String[] args) {
        Container container = Container.getInstance();
        container.setPersistence_strategy(new PersistenceStrategyStream<>());
        Client.createAddDump();
    }
}
