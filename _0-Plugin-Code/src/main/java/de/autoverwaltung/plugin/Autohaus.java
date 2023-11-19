package de.autoverwaltung.plugin;

import de.autoverwaltung.plugin.csvloader.CsvGebaeudeLoader;
import de.autoverwaltung.plugin.csvloader.GesamtLoader;
import de.autoverwaltung.plugin.datenverwaltung.*;
import de.autoverwaltung.plugin.ui.Startbildschirm;

import javax.swing.*;

public class Autohaus {
// Auto verkaufen: csv autoID von belegtem Stellplatz löschen/ zeile updaten
// Fahrzeug umparken: auto.csv stellPlatzId updaten, in stellplaetze.csv fahrzeugID von alten Stellplatz löschen und bei neunem Stellplatz einfügen ++++ entity manager
    public static void main(String[] args) {

        // Datentruktur und Manager initialisieren
        DatenRepository.getInstance();
        CreateManager.getInstance();
        DeleteManager.getInstance();
        ReadManager.getInstance();
        UpdateManager.getInstance();

        // Daten laden
        GesamtLoader gesamtLoader = new GesamtLoader();

        // UI laden
        SwingUtilities.invokeLater(() -> {
            Startbildschirm frame = new Startbildschirm();
            frame.setVisible(true);
        });

    }
}
