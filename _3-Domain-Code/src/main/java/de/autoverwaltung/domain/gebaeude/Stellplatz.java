
package de.autoverwaltung.domain.gebaeude;

import de.autoverwaltung.domain.IEinzigartig;

public class Stellplatz implements IEinzigartig {
    private String id;
    private String fahrzeugID;
    private String gebaeudeID;

    public String getID() {
        return id;
    }

    public void setID(String ID) {
        this.id = ID;
    }

    public String getFahrzeugID() {
        return fahrzeugID;
    }

    public void setFahrzeugID(String fahrzeugID) {
        this.fahrzeugID = fahrzeugID;
    }

    public String getGebaeudeID() {
        return gebaeudeID;
    }

    public void setGebaeudeID(String gebaeudeID) {
        this.gebaeudeID = gebaeudeID;
    }

// Konstruktoren, Getter und Setter hier einfügen
}
