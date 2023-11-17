package de.autoverwaltung.plugin;

import de.autoverwaltung.plugin.csvloader.CsvGebaeudeLoader;
import de.autoverwaltung.plugin.csvloader.GesamtLoader;
import de.autoverwaltung.plugin.datenverwaltung.*;
import de.autoverwaltung.plugin.ui.Startbildschirm;

import javax.swing.*;
import java.io.IOException;

public class Autohaus {
    // public static DataReader dataReader;

    public static void main(String[] args) {

        // Datentruktur und Manager initialisieren
        DatenRepository datenRepository = DatenRepository.getInstance();
        CreateManager createManager = CreateManager.getInstance();
        DeleteManager deleteManager = DeleteManager.getInstance();
        ReadManager readManager = ReadManager.getInstance();
        UpdateManager updateManager = UpdateManager.getInstance();

        // Daten laden
        GesamtLoader gesamtLoader = new GesamtLoader();

        // UI laden
        SwingUtilities.invokeLater(() -> {
            Startbildschirm frame = new Startbildschirm();
            frame.setVisible(true);
        });

    }
}
