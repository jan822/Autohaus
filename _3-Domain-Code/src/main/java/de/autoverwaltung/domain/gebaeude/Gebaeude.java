
package de.autoverwaltung.domain.gebaeude;

import de.autoverwaltung.domain.IEinzigartig;

public class Gebaeude implements IEinzigartig {
    private String id;
    private String name;
    private int stellplatzKapazitaet;

    // Konstruktoren, Getter und Setter hier einfügen

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStellplatzKapazitaet() {
        return stellplatzKapazitaet;
    }

    public void setStellplatzKapazitaet(int stellplatzKapazitaet) {
        this.stellplatzKapazitaet = stellplatzKapazitaet;
    }

    @Override
    public Object getID() {
        return id;
    }

    @Override
    public String toString() {
        return getName();
    }
}
