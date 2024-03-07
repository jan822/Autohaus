package de.autoverwaltung.adapter.csvloader;

import de.autoverwaltung.domain.fahrzeug.Motorrad;
import de.autoverwaltung.domain.fahrzeug.MotorradTyp;
import de.autoverwaltung.domain.fahrzeug.Waehrung;


public class CsvMotorradLoader implements ParserInterface{

    @Override
    public Object parse(String[] values) {
        String id = values[0];
        String stellPlatzID = values[1];
        String marke = values[2];
        String modell = values[3];
        double preis = Double.parseDouble(values[4]);
        Waehrung waehrung = Waehrung.valueOf(values[5].toUpperCase());
        int kilometer = Integer.parseInt(values[6]);
        boolean tuev = Boolean.parseBoolean(values[7]);
        boolean zweisitzer = Boolean.parseBoolean(values[8]);
        boolean beiwagenGeeignet = Boolean.parseBoolean(values[9]);
        MotorradTyp motorradTyp = MotorradTyp.valueOf(values[10].toUpperCase());
        boolean seitenstaenderBeidseitig = Boolean.parseBoolean(values[11]);

        Motorrad motorrad = new Motorrad(id, marke, modell, preis, waehrung, kilometer, tuev);
        motorrad.setStellPlatzID(stellPlatzID);
        motorrad.setZweisitzer(zweisitzer);
        motorrad.setBeiwagenGeeignet(beiwagenGeeignet);
        motorrad.setMotorradTyp(motorradTyp);
        motorrad.setSeitenstaenderBeidseitig(seitenstaenderBeidseitig);
        return motorrad;
    }
}

