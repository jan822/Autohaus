package de.autoverwaltung.adapter.csvloader;

import de.autoverwaltung.domain.IEinzigartig;
import de.autoverwaltung.domain.fahrzeug.Auto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoaderUtils {

    public static <T extends IEinzigartig> List<T> load(String csvFilePath, ParserInterface<T> parser) {
        List<T> resultList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            br.readLine(); // Überspringen der Kopfzeile
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                T item = parser.parse(values);
                resultList.add(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
