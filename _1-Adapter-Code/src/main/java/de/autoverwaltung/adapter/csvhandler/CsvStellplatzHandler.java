package de.autoverwaltung.adapter.csvhandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvStellplatzHandler {
    private static final String CSV_FILE_PATH = "C:\\SAPDevelop\\SWE_Advanced\\Autohaus\\resources\\CSV\\stellplaetze.csv";


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
}

