package de.autoverwaltung.domain.fahrzeug;

public class Motorrad extends Fahrzeug{
    private boolean zweisitzer;
    private boolean beiwagenGeeignet;
    private MotorradTyp motorradTyp;
    private boolean seitenstaenderBeidseitig;

    public Motorrad(String id, String marke, String modell, double preis, Waehrung waehrung, int kilometer, boolean tuev) {
        super(id, marke, modell, preis, waehrung, kilometer, tuev);
    }

    public boolean isZweisitzer() {
        return zweisitzer;
    }

    public void setZweisitzer(boolean zweisitzer) {
        this.zweisitzer = zweisitzer;
    }

    public boolean isBeiwagenGeeignet() {
        return beiwagenGeeignet;
    }

    public void setBeiwagenGeeignet(boolean beiwagenGeeignet) {
        this.beiwagenGeeignet = beiwagenGeeignet;
    }

    public MotorradTyp getMotorradTyp() {
        return motorradTyp;
    }

    public void setMotorradTyp(MotorradTyp motorradTyp) {
        this.motorradTyp = motorradTyp;
    }

    public boolean isSeitenstaenderBeidseitig() {
        return seitenstaenderBeidseitig;
    }

    public void setSeitenstaenderBeidseitig(boolean seitenstaenderBeidseitig) {
        this.seitenstaenderBeidseitig = seitenstaenderBeidseitig;
    }
}
