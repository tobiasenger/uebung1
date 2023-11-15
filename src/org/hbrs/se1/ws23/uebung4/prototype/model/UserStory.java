package org.hbrs.se1.ws23.uebung4.prototype.model;

import java.io.Serializable;

public class UserStory implements Serializable, Comparable {
        // ToDo: Sind die Attribute der Klasse UserStory vollständig? (F4)
        // Nein, es fehlen die kurze Beschreibung (String), ein kurzes Akzeptanzkriterium (String) und private setzen

        private String titel;
        private String project;
        private String beschreibung;
        private String akzeptanz;
        private int id = 0;
        private int mehrwert = 0;
        private int strafe = 0;
        private int aufwand = 0;
        private int risk = 0;
        private double prio = 0.0;


        public String getProject() {
            return project;
        }

        public void setProject(String project) {
            this.project = project;
        }


        public String getBeschreibung() {
            return beschreibung;
        }

        public void setBeschreibung(String beschreibung) {
            this.beschreibung = beschreibung;
        }

        public String getAkzeptanz() {
            return akzeptanz;
        }

        public void setAkzeptanz(String akzeptanz) {
            this.akzeptanz = akzeptanz;
        }

        public UserStory(int id, String titel, String beschreibung, String akzeptanz, int mehrwert, int strafe,
                         int aufwand, int risk, double prio) {
            this.id = id;
            this.titel = titel;
            this.beschreibung = beschreibung;
            this.akzeptanz = akzeptanz;
            this.mehrwert = mehrwert;
            this.strafe = strafe;
            this.aufwand = aufwand;
            this.risk = risk;
            this.prio = prio;
        }

        public UserStory() {
        }

        public double getPrio() {
            return prio;
        }

        public void setPrio(double prio) {
            this.prio = prio;
        }

        public String getTitel() {
            return titel;
        }
        public void setTitel(String titel) {
            this.titel = titel;
        }
        public int getAufwand() {
            return aufwand;
        }
        public void setAufwand(int aufwand) {
            this.aufwand = aufwand;
        }
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public int getMehrwert() {
            return mehrwert;
        }
        public void setMehrwert(int mehrwert) {
            this.mehrwert = mehrwert;
        }
        public int getRisk() {
            return risk;
        }
        public void setRisk(int risk) {
            this.risk = risk;
        }
        public int getStrafe() {
            return strafe;
        }
        public void setStrafe(int strafe) {
            this.strafe = strafe;
        }

        @Override
        public String toString() {
            return "|\t" + id + "\t|\t" + titel + "\t|\t" + beschreibung + "\t|\t" + akzeptanz + "\t|\t" + mehrwert + "\t|\t" + strafe + "\t|\t"
                    + aufwand + "\t|\t" + risk + "\t|\t" + prio + "\t|\t" + project + "\t|";
        }

    @Override
    public int compareTo(Object o) {
        if(o instanceof UserStory) {
            int prioStoryThis = (int)(this.getPrio()*100000);
            int prioStoryOther = (int)(((UserStory) o).getPrio()*100000);
            return prioStoryThis - prioStoryOther;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
