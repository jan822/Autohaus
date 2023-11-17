package de.autoverwaltung.domain.fahrzeug;

public class Betrag {
    private double wert;
    private Waehrung waehrung;

    public Betrag(double wert, Waehrung waehrung) {
        this.wert = wert;
        this.waehrung = waehrung;
    }

}
