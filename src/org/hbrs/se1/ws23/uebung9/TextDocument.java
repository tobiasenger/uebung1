package org.hbrs.se1.ws23.uebung9;

public class TextDocument extends CoreDocument {

    private String content;

    private Encoding encoding;

    public enum Encoding {

        UTF8("UTF-8"), UTF16("UTF-16"), UTF32("UTF-32");

        private final String encoding;

        Encoding(final String encoding) {
            this.encoding = encoding;
        }
        @Override
        public String toString() {
            return encoding;
        }
    }

    public TextDocument(int id, String content, Encoding encoding) {
        setId(id);
        this.content = content;
        this.encoding = encoding;
    }

    @Override
    public int byteSize() {
        try {
            return content.getBytes(encoding.toString()).length;
        } catch (java.io.UnsupportedEncodingException e) {
            System.out.println("Fehler beim Encoding von Document " + getId() + ".");
            return 0;
        }
    }
}
