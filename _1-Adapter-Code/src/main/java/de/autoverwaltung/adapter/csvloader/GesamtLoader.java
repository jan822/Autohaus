package de.autoverwaltung.adapter.csvloader;

import de.autoverwaltung.domain.IEinzigartig;
import de.autoverwaltung.adapter.datenverwaltung.CreateManager;

import java.util.ArrayList;
import java.util.List;

public class GesamtLoader {

    public GesamtLoader() {
        this.datenLaden();
    }

    public <T extends IEinzigartig> void datenLaden() {
        List<T> entities = new ArrayList<>();
        String csvPath = "_1-Adapter-Code\\src\\main\\resources\\CSV\\";
        entities.addAll(LoaderUtils.load(csvPath + "autos.csv", new CsvAutoLoader()));
        entities.addAll(LoaderUtils.load(csvPath + "gebaeude.csv", new CsvGebaeudeLoader()));
        entities.addAll(LoaderUtils.load(csvPath + "stellplaetze.csv", new CsvStellplatzLoader()));
        entities.addAll(LoaderUtils.load(csvPath + "motorrad.csv", new CsvMotorradLoader()));

        for (T entity : entities) {
            CreateManager.getInstance().create(entity);
        }
        System.out.println("Daten geladen");
    }

}
