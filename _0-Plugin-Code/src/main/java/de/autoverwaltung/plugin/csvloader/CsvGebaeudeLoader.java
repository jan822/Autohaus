package de.autoverwaltung.plugin.csvloader;

import de.autoverwaltung.domain.IEinzigartig;
import de.autoverwaltung.domain.gebaeude.Gebaeude;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvGebaeudeLoader {
    private String csvFilePath;

    public CsvGebaeudeLoader(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    public <T extends IEinzigartig> List<T> loadGebaeude() {
        List<T> gebaeudeListe = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Gebaeude gebaeude = parseGebaeude(values);
                gebaeudeListe.add((T) gebaeude);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gebaeudeListe;
    }

    private Gebaeude parseGebaeude(String[] values) {
        String id = values[0];
        String name = values[1];
        int stellplatzKapazitaet = Integer.parseInt(values[2]);

        Gebaeude gebaeude = new Gebaeude();
        gebaeude.setId(id);
        gebaeude.setName(name);
        gebaeude.setStellplatzKapazitaet(stellplatzKapazitaet);

        return gebaeude;
    }
}

