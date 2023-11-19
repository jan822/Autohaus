package de.autoverwaltung.adapter.csvloader;


import de.autoverwaltung.domain.IEinzigartig;
import de.autoverwaltung.domain.fahrzeug.Auto;
import de.autoverwaltung.domain.fahrzeug.Autotyp;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvAutoLoader {
    private String csvFilePath;

    public CsvAutoLoader(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    public <T extends IEinzigartig> List<T> loadAutos() {
        List<T> autos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Auto auto = parseAuto(values);
                autos.add((T) auto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return autos;
    }

    private Auto parseAuto(String[] values) {
        String id = values[0];
        String stellPlatzID = values[1]; // Zusätzliches Feld für Stellplatz-ID
        String marke = values[2];
        String modell = values[3];
        double preis = Double.parseDouble(values[4]);
        int kilometer = Integer.parseInt(values[5]);
        boolean tuev = Boolean.parseBoolean(values[6]);
        int anzahlTueren = Integer.parseInt(values[7]);
        boolean navigationssystem = Boolean.parseBoolean(values[8]);
        int anzahlSitzplaetze = Integer.parseInt(values[9]);
        Autotyp autotyp = Autotyp.valueOf(values[10].toUpperCase());
        double kofferraumvolumen = Double.parseDouble(values[11]);

        Auto auto = new Auto(id, marke, modell, preis, kilometer, tuev);
        auto.setStellPlatzID(stellPlatzID);
        auto.setAnzahlTueren(anzahlTueren);
        auto.setNavigationssystem(navigationssystem);
        auto.setAnzahlSitzplaetze(anzahlSitzplaetze);
        auto.setAutotyp(autotyp);
        auto.setKofferraumvolumen(kofferraumvolumen);

        return auto;
    }

}

