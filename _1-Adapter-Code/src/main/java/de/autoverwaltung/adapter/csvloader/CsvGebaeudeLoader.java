package de.autoverwaltung.adapter.csvloader;

import de.autoverwaltung.domain.gebaeude.Gebaeude;

public class CsvGebaeudeLoader implements ParserInterface{


    @Override
    public Object parse(String[] values) {
        String id = values[0];
        String name = values[1];
        int stellplatzKapazitaet = Integer.parseInt(values[2]);

        Gebaeude gebaeude = new Gebaeude(id, name, stellplatzKapazitaet);

        return gebaeude;
    }
}

