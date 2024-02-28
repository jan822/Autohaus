package de.autoverwaltung.domain.fahrzeug;

import de.autoverwaltung.domain.IEinzigartig;

public abstract class Fahrzeug implements IEinzigartig {
    private String id;
    private String stellPlatzID;

    private String marke;
    private String modell;
    private Betrag betraaag;
    private double preis;
    private Waehrung waehrung;
    private int kilometer;
    private boolean tuev;

    // Konstruktor der Klasse Fahrzeug
    public Fahrzeug(String id, String marke, String modell, double preis, Waehrung waehrung, int kilometer, boolean tuev) {
        this.id = id;
        this.marke = marke;
        this.modell = modell;
        this.preis = preis;
        this.waehrung = waehrung;
        this.betraaag = new Betrag(preis, waehrung);
        this.kilometer = kilometer;
        this.tuev = tuev;
    }
    public Fahrzeug(String id, String stellPlatzID, String marke, String modell, double preis, Waehrung waehrung, int kilometer, boolean tuev) {
        this.id = id;
        this.marke = marke;
        this.stellPlatzID = stellPlatzID;
        this.modell = modell;
        this.preis = preis;
        this.waehrung = waehrung;
        this.betraaag = new Betrag(preis, waehrung);
        this.kilometer = kilometer;
        this.tuev = tuev;
    }

    public double getPreis(){
        return betraaag.getWert();
    }
    public Waehrung getWaehrung(){
        return betraaag.getWaehrung();
    }

    public Betrag getBetrag(){
        return betraaag;
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
    public String getID() {
        return id;
    }

    @Override
    public String toString() {
        return  getMarke() +
                ", " + getModell() +
                " (" + getKilometer();
    }
}
