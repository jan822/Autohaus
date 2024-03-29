package de.autoverwaltung.domain.fahrzeug;

import de.autoverwaltung.domain.gebaeude.Stellplatz;

public class Auto extends Fahrzeug {
    private int anzahlTueren;
    private boolean navigationssystem;
    private int anzahlSitzplaetze;
    private Autotyp autotyp;
    private double kofferraumvolumen;
    public Auto(String id, String marke, String modell, double preis, Waehrung waehrung, int kilometer, boolean tuev) {
        super(id, marke, modell, preis, waehrung, kilometer, tuev);
    }
    public Auto(String id, String stellplatzID, String marke, String modell, double preis, Waehrung waehrung, int kilometer, boolean tuev, int anzahlTueren, boolean navigationssystem, int anzahlSitzplaetze, Autotyp autotyp, double kofferraumvolumen) {
        super(id, stellplatzID, marke, modell, preis, waehrung, kilometer, tuev);
        this.anzahlTueren = anzahlTueren;
        this.navigationssystem = navigationssystem;
        this.anzahlSitzplaetze = anzahlSitzplaetze;
        this.autotyp = autotyp;
        this.kofferraumvolumen = kofferraumvolumen;
    }

    public int getAnzahlTueren() {
        return anzahlTueren;
    }

    public void setAnzahlTueren(int anzahlTueren) {
        this.anzahlTueren = anzahlTueren;
    }

    public boolean isNavigationssystem() {
        return navigationssystem;
    }

    public void setNavigationssystem(boolean navigationssystem) {
        this.navigationssystem = navigationssystem;
    }

    public int getAnzahlSitzplaetze() {
        return anzahlSitzplaetze;
    }

    public void setAnzahlSitzplaetze(int anzahlSitzplaetze) {
        this.anzahlSitzplaetze = anzahlSitzplaetze;
    }

    public Autotyp getAutotyp() {
        return autotyp;
    }

    public void setAutotyp(Autotyp autotyp) {
        this.autotyp = autotyp;
    }

    public double getKofferraumvolumen() {
        return kofferraumvolumen;
    }

    public void setKofferraumvolumen(double kofferraumvolumen) {
        this.kofferraumvolumen = kofferraumvolumen;
    }

}
