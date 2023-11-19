package de.autoverwaltung.adapter.csvhandler;

import de.autoverwaltung.application.guicontroller.ICsvUpdater;
import de.autoverwaltung.domain.IEinzigartig;
import de.autoverwaltung.domain.fahrzeug.Auto;
import de.autoverwaltung.domain.fahrzeug.Fahrzeug;
import de.autoverwaltung.domain.fahrzeug.Motorrad;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvAutoHandler implements ICsvUpdater {
    private CsvStellplatzHandler stellplatzHandler;
    public CsvAutoHandler() {
        stellplatzHandler = new CsvStellplatzHandler();
    }

    private static final String CSV_FILE_PATH_AUTOS = "C:\\SAPDevelop\\SWE_Advanced\\Autohaus\\resources\\CSV\\autos.csv";
    private static final String CSV_FILE_PATH_MOTORRAEDER = "C:\\SAPDevelop\\SWE_Advanced\\Autohaus\\resources\\CSV\\motorrad.csv";

    @Override
    public void eintragLoeschen(Object object) {
        Auto auto = (Auto) object;
        List<String> zeilenBehalten = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH_AUTOS))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.contains(auto.getId())) {
                    zeilenBehalten.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH_AUTOS))) {
            for (String line : zeilenBehalten) {
                writer.write(line);
                writer.newLine();
            }
            stellplatzHandler.updateStellplatz(auto.getStellPlatzID(), "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void eintragHinzufuegen(Object auto) {
        String newLine = buildCsvLine((Auto) auto);
        try {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH_AUTOS, true))) {
                writer.newLine();
                writer.write(newLine);
                stellplatzHandler.updateStellplatz(((Auto) auto).getStellPlatzID(), ((Auto) auto).getId());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eintragAendern(Object obj) {
        String csvFilePath;
        String updatedLine;

        if (obj instanceof Auto) {
            Auto auto = (Auto) obj;
            csvFilePath = CSV_FILE_PATH_AUTOS;
            updatedLine = buildCsvLine(auto);
        } else if (obj instanceof Motorrad) {
            Motorrad motorrad = (Motorrad) obj;
            csvFilePath = CSV_FILE_PATH_MOTORRAEDER;
            updatedLine = buildCsvLine(motorrad);
        } else {
            return;
        }

        List<String> updatedLines = new ArrayList<>();
        String objId = (String) ((IEinzigartig) obj).getID();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(objId)) {
                    updatedLines.add(updatedLine);
                } else {
                    updatedLines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
            for (String line : updatedLines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String buildCsvLine(Fahrzeug fahrzeug) {
        if(fahrzeug instanceof Auto) {
            Auto auto = (Auto) fahrzeug;
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
            ) + ",";
        } else {
            Motorrad motorrad = (Motorrad) fahrzeug;
            return String.join(",",
                    motorrad.getId(),
                    motorrad.getStellPlatzID(),
                    motorrad.getMarke(),
                    motorrad.getModell(),
                    String.valueOf(motorrad.getPreis()),
                    String.valueOf(motorrad.getKilometer()),
                    String.valueOf(motorrad.isTuev()),
                    String.valueOf(motorrad.isZweisitzer()),
                    String.valueOf(motorrad.isBeiwagenGeeignet()),
                    motorrad.getMotorradTyp().toString(),
                    String.valueOf(motorrad.isSeitenstaenderBeidseitig())
            );
        }
    }
}
