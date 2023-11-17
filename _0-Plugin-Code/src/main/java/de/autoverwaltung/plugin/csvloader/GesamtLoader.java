package de.autoverwaltung.plugin.csvloader;

import de.autoverwaltung.domain.IEinzigartig;
import de.autoverwaltung.domain.fahrzeug.Auto;
import de.autoverwaltung.plugin.datenverwaltung.CreateManager;
import de.autoverwaltung.plugin.datenverwaltung.DatenRepository;

import java.util.ArrayList;
import java.util.List;

public class GesamtLoader {

    public GesamtLoader() {
        this.datenLaden();
    }

    public <T extends IEinzigartig> void datenLaden() {
        List<T> entities = new ArrayList<>();
        String csvPath = "C:\\SAPDevelop\\SWE_Advanced\\Autohaus\\resources\\CSV\\";

        CsvAutoLoader autoLoader = new CsvAutoLoader(csvPath + "autos.csv");
        entities.addAll(autoLoader.loadAutos());

        CsvGebaeudeLoader gebaeudeLoader = new CsvGebaeudeLoader(csvPath + "gebaeude.csv");
        entities.addAll(gebaeudeLoader.loadGebaeude());

        CsvStellplatzLoader stellplatzLoader = new CsvStellplatzLoader(csvPath + "stellplaetze.csv");
        entities.addAll(stellplatzLoader.loadStellplaetze());

        for (T entity : entities) {
            CreateManager.getInstance().create(entity);
        }
        System.out.println(entities.size());
        System.out.println("Daten geladen");
    }

}
