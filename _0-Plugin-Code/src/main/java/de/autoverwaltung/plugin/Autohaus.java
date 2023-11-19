package de.autoverwaltung.plugin;

import de.autoverwaltung.adapter.datenverwaltung.*;
import de.autoverwaltung.adapter.csvloader.GesamtLoader;
import de.autoverwaltung.plugin.ui.Startbildschirm;

import javax.swing.*;


// relative Pfade! , JUnit Tests
public class Autohaus {
    public static void main(String[] args) {
        DatenRepository.getInstance();
        CreateManager.getInstance();
        DeleteManager.getInstance();
        ReadManager.getInstance();
        UpdateManager.getInstance();

        GesamtLoader gesamtLoader = new GesamtLoader();

        SwingUtilities.invokeLater(() -> {
            Startbildschirm frame = new Startbildschirm();
            frame.setVisible(true);
        });

    }
}
