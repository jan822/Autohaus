package de.autoverwaltung.adapter.csvloader;

import de.autoverwaltung.domain.gebaeude.Stellplatz;


public class CsvStellplatzLoader implements ParserInterface {

    @Override
    public Object parse(String[] values) {
        String id = values[0];
        String fahrzeugID = values[1].isEmpty() ? null : values[1];
        String gebaeudeID = values[2];
        String name = values[3]; // Hinzugefügter Wert für den Namen des Stellplatzes

        Stellplatz stellplatz = new Stellplatz();
        stellplatz.setID(id);
        if (fahrzeugID != null) stellplatz.setFahrzeugID(fahrzeugID);
        stellplatz.setGebaeudeID(gebaeudeID);
        stellplatz.setName(name); // Setzt den Namen des Stellplatzes

        return stellplatz;
    }
}

