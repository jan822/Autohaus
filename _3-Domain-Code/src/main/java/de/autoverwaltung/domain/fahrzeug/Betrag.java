package de.autoverwaltung.domain.fahrzeug;

public class Betrag {
    private double wert;
    private Waehrung waehrung;

    public Betrag(double wert, Waehrung waehrung) {
        this.wert = wert;
        this.waehrung = waehrung;
    }

    public double getWert() {
        return wert;
    }

    public void setWert(double wert) {
        this.wert = wert;
    }

    public Waehrung getWaehrung() {
        return waehrung;
    }

    public void setWaehrung(Waehrung waehrung) {
        this.waehrung = waehrung;
    }
}
