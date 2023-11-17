package de.autoverwaltung.domain.fahrzeug;

import de.autoverwaltung.domain.IEinzigartig;

public abstract class Fahrzeug implements IEinzigartig {
    private String id;
    private String stellPlatzID;

    private String marke;
    private String modell;
    private double preis;
    private int kilometer;
    private boolean tuev;

    // Konstruktor der Klasse Fahrzeug
    public Fahrzeug(String id, String marke, String modell, double preis, int kilometer, boolean tuev) {
        this.id = id;
        this.marke = marke;
        this.modell = modell;
        this.preis = preis;
        this.kilometer = kilometer;
        this.tuev = tuev;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStellPlatzID() {
        return stellPlatzID;
    }

    public void setStellPlatzID(String stellPlatzID) {
        this.stellPlatzID = stellPlatzID;
    }

    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public int getKilometer() {
        return kilometer;
    }

    public void setKilometer(int kilometer) {
        this.kilometer = kilometer;
    }

    public boolean isTuev() {
        return tuev;
    }

    public void setTuev(boolean tuev) {
        this.tuev = tuev;
    }

    @Override
    public Object getID() {
        return id;
    }
}
