package de.autoverwaltung.adapter.csvhandler;

import de.autoverwaltung.application.guicontroller.ICsvUpdater;
import de.autoverwaltung.domain.fahrzeug.Auto;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CsvAutoHandler implements ICsvUpdater {

    public CsvAutoHandler() {
    }

    private static final String CSV_FILE_PATH = "C:\\SAPDevelop\\SWE_Advanced\\Autohaus\\resources\\CSV\\autos.csv";

    @Override
    public void updateCsv(Object auto) {
        String newLine = buildCsvLine((Auto) auto);
        try {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH, true))) {
                writer.newLine();
                writer.write(newLine);
                CsvStellplatzHandler stellplatzHandler = new CsvStellplatzHandler();
                stellplatzHandler.updateStellplatz(((Auto) auto).getStellPlatzID(), ((Auto) auto).getId());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String buildCsvLine(Auto auto) {
        return String.join(",",
                auto.getId(),
                auto.getStellPlatzID(),
                auto.getMarke(),
                auto.getModell(),
                String.valueOf((int) auto.getPreis()),
                String.valueOf(auto.getKilometer()),
                String.valueOf(auto.isTuev()),
                String.valueOf(auto.getAnzahlTueren()),
                String.valueOf(auto.isNavigationssystem()),
                String.valueOf(auto.getAnzahlSitzplaetze()),
                auto.getAutotyp().toString(),
                String.valueOf((int) auto.getKofferraumvolumen())
        )+",";
    }
}
