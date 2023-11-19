package de.autoverwaltung.adapter.csvhandler;

import de.autoverwaltung.application.guicontroller.ICsvUpdater;
import de.autoverwaltung.domain.fahrzeug.Auto;
import de.autoverwaltung.domain.gebaeude.Stellplatz;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvStellplatzHandler implements ICsvUpdater {
    private static final String CSV_FILE_PATH = "_1-Adapter-Code\\src\\main\\resources\\CSV\\stellplaetze.csv";


    public void updateStellplatz(String stellPlatzID, String fahrzeugID) throws IOException {
        List<String> lines = new ArrayList<>();
        boolean updated = false;

        // Lesen der aktuellen Daten aus der CSV-Datei
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(stellPlatzID)) {
                    String[] values = line.split(",");
                    values[1] = fahrzeugID; // Aktualisieren der fahrzeugID
                    line = String.join(",", values);
                    updated = true;
                }
                lines.add(line);
            }
        }

        // Prüfen, ob die Aktualisierung stattgefunden hat
        if (!updated) {
            throw new IOException("Stellplatz mit ID " + stellPlatzID + " wurde nicht gefunden.");
        }

        // Überschreiben der CSV-Datei mit den aktualisierten Daten
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(CSV_FILE_PATH))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    @Override
    public void eintragLoeschen(Object object) {

    }

    @Override
    public void eintragHinzufuegen(Object object) {

    }

    @Override
    public void eintragAendern(Object obj) {
        Stellplatz stellplatz = (Stellplatz) obj;
        List<String> updatedLines = new ArrayList<>();
        String updatedLine = buildCsvLine(stellplatz);

        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(stellplatz.getID())) {
                    // Ersetzen der Zeile mit den aktualisierten Informationen
                    updatedLines.add(updatedLine);
                } else {
                    updatedLines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH))) {
            for (String line : updatedLines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String buildCsvLine(Stellplatz stellplatz) {
        return String.join(",",
                stellplatz.getID(),
                stellplatz.getFahrzeugID(),
                stellplatz.getGebaeudeID(),
                stellplatz.getName()
        );

    }
}

