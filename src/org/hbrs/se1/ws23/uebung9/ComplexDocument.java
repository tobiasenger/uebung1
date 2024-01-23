package org.hbrs.se1.ws23.uebung9;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ComplexDocument implements Document {

    private int id;
    private List<Document> documents = new ArrayList<>();

    public ComplexDocument(int id) {
        this.id = id;
    }

    public void add(Document document) throws DocumentIDException {
        if (document.getId() == this.getId()) {
            throw new DocumentIDException("ID " + this.getId() + " schon vorhanden.");
        } else if (document instanceof ComplexDocument) {
            for (int id: ((ComplexDocument) document).listIds()) {
                if (id == this.getId()) {
                    throw new DocumentIDException("ID " + id + " schon vorhanden.");
                }
            }
        }
        documents.add(document);
    }

    public void delete(Document document) {
        documents.remove(document);
    }

    public void delete(int documentId) {
        Document documentToDelete = null;
        for(Document document: documents) {
            if(document.getId() == documentId) {
                documentToDelete = document;
            }
        }
        if(documentToDelete != null) {
            documents.remove(documentToDelete);
        } else {
            System.out.println("Kein Dokument mit der ID " + documentId + " gefunden.");
        }
    }

    public void printIDs() {
        for(Object doc: this) {
            if (doc instanceof Document) {
                if (doc instanceof ComplexDocument) {
                    System.out.println(">> " + ((ComplexDocument) doc).getId());
                    ((ComplexDocument) doc).printIDs();
                    System.out.println("<< " + ((ComplexDocument) doc).getId());
                }
                if (doc instanceof CoreDocument) {
                    System.out.println(((CoreDocument) doc).getId());
                }
            }
        }
    }

    public List<Integer> listIds() {
        List<Integer> listOfIds = new ArrayList<>();
        for(Object doc: this) {
            listOfIds.add(((Document) doc).getId());
            if (doc instanceof ComplexDocument) {
                listOfIds.addAll(((ComplexDocument) doc).listIds());
            }
        }
        return listOfIds;
    }

    @Override
    public int byteSize() {
        int sumSize = 0;
        for(Document document: documents) {
            sumSize += document.byteSize();
        }
        return sumSize;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Iterator iterator() {
        return new DocumentIterator();
    }

    public class DocumentIterator implements Iterator {

        int pos = 0;

        @Override
        public boolean hasNext() {
            return pos < documents.size();
        }

        @Override
        public Object next() {
            return documents.get(pos++);
        }
    }
}
