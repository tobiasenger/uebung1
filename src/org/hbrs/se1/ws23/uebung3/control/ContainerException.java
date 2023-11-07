package org.hbrs.se1.ws23.uebung3.control;

public class ContainerException extends Exception {
    public ContainerException(Integer id) {
        System.err.println("Das Member-Objekt mit der ID " + id + " ist bereits vorhanden!");
    }
}
