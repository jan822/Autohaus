package de.autoverwaltung.adapter.csvloader;

import de.autoverwaltung.domain.IEinzigartig;
import de.autoverwaltung.domain.gebaeude.Stellplatz;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvStellplatzLoader {
    private String csvFilePath;

    public CsvStellplatzLoader(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    public <T extends IEinzigartig> List<T> loadStellplaetze() {
        List<T> stellplatzListe = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            br.readLine(); // Überspringen der Kopfzeile
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Stellplatz stellplatz = parseStellplatz(values);
                stellplatzListe.add((T) stellplatz);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stellplatzListe;
    }

    private Stellplatz parseStellplatz(String[] values) {
        String id = values[0];
        String fahrzeugID = values[1].isEmpty() ? null : values[1];
        String gebaeudeID = values[2];
        String name = values[3]; // Hinzugefügter Wert für den Namen des Stellplatzes

        Stellplatz stellplatz = new Stellplatz();
        stellplatz.setID(id);
        if(fahrzeugID != null) stellplatz.setFahrzeugID(fahrzeugID);
        stellplatz.setGebaeudeID(gebaeudeID);
        stellplatz.setName(name); // Setzt den Namen des Stellplatzes

        return stellplatz;
    }

}
