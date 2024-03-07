package de.autoverwaltung.adapter.csvloader;


import de.autoverwaltung.domain.fahrzeug.Auto;
import de.autoverwaltung.domain.fahrzeug.Autotyp;
import de.autoverwaltung.domain.fahrzeug.Waehrung;


public class CsvAutoLoader implements ParserInterface{
    public CsvAutoLoader() {
    }


    @Override
    public Object parse(String[] values) {
        String id = values[0];
        String stellPlatzID = values[1]; // Zusätzliches Feld für Stellplatz-ID
        String marke = values[2];
        String modell = values[3];
        Double preis = Double.parseDouble(values[4]);
        Waehrung waherung = Waehrung.valueOf(values[5].toUpperCase());
        int kilometer = Integer.parseInt(values[6]);
        boolean tuev = Boolean.parseBoolean(values[7]);
        int anzahlTueren = Integer.parseInt(values[8]);
        boolean navigationssystem = Boolean.parseBoolean(values[9]);
        int anzahlSitzplaetze = Integer.parseInt(values[10]);
        Autotyp autotyp = Autotyp.valueOf(values[11].toUpperCase());
        double kofferraumvolumen = Double.parseDouble(values[12]);

        Auto auto = new Auto(id, marke, modell, preis, waherung, kilometer, tuev);
        auto.setStellPlatzID(stellPlatzID);
        auto.setAnzahlTueren(anzahlTueren);
        auto.setNavigationssystem(navigationssystem);
        auto.setAnzahlSitzplaetze(anzahlSitzplaetze);
        auto.setAutotyp(autotyp);
        auto.setKofferraumvolumen(kofferraumvolumen);

        return auto;
    }
}

