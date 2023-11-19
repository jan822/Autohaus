package de.autoverwaltung.adapter.csvloader;

import de.autoverwaltung.domain.IEinzigartig;
import de.autoverwaltung.domain.fahrzeug.Motorrad;
import de.autoverwaltung.domain.fahrzeug.MotorradTyp;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvMotorradLoader {
    private String csvFilePath;

    public CsvMotorradLoader(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    public <T extends IEinzigartig> List<T> loadMotorraeder() {
        List<T> motorradListe = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            br.readLine(); // Überspringen der Kopfzeile
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Motorrad motorrad = parseMotorrad(values);
                motorradListe.add((T) motorrad);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return motorradListe;
    }

    private Motorrad parseMotorrad(String[] values) {
        String id = values[0];
        String stellPlatzID = values[1];
        String marke = values[2];
        String modell = values[3];
        double preis = Double.parseDouble(values[4]);
        int kilometer = Integer.parseInt(values[5]);
        boolean tuev = Boolean.parseBoolean(values[6]);
        boolean zweisitzer = Boolean.parseBoolean(values[7]);
        boolean beiwagenGeeignet = Boolean.parseBoolean(values[8]);
        MotorradTyp motorradTyp = MotorradTyp.valueOf(values[9].toUpperCase());
        boolean seitenstaenderBeidseitig = Boolean.parseBoolean(values[10]);

        Motorrad motorrad = new Motorrad(id, marke, modell, preis, kilometer, tuev);
        motorrad.setStellPlatzID(stellPlatzID);
        motorrad.setZweisitzer(zweisitzer);
        motorrad.setBeiwagenGeeignet(beiwagenGeeignet);
        motorrad.setMotorradTyp(motorradTyp);
        motorrad.setSeitenstaenderBeidseitig(seitenstaenderBeidseitig);
        return motorrad;
    }

}

