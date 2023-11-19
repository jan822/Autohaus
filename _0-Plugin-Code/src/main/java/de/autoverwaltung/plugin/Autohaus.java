package de.autoverwaltung.plugin;

import de.autoverwaltung.adapter.datenverwaltung.*;
import de.autoverwaltung.adapter.csvloader.GesamtLoader;
import de.autoverwaltung.plugin.ui.Startbildschirm;

import javax.swing.*;


// relative Pfade! , JUnit Tests
public class Autohaus {
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
