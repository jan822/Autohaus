package de.autoverwaltung.plugin.ui;

import de.autoverwaltung.domain.IEinzigartig;
import de.autoverwaltung.domain.gebaeude.Gebaeude;
import de.autoverwaltung.plugin.datenverwaltung.DatenRepository;
import de.autoverwaltung.plugin.datenverwaltung.ReadManager;

import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Startbildschirm extends JFrame {

    public <T extends IEinzigartig> Startbildschirm() {
        setTitle("Autohausverwaltung");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header mit Buttons
        JPanel headerPanel = new JPanel(new BorderLayout());
        JButton addButton = new JButton("Auto hinzufügen");
        JButton sellButton = new JButton("Auto verkaufen");
        headerPanel.add(addButton, BorderLayout.WEST);
        headerPanel.add(sellButton, BorderLayout.EAST);

        // Gebäude-Bereich
        JPanel gebaeudePanel = new JPanel();
        gebaeudePanel.setLayout(new GridLayout(0, 1));
        gebaeudePanel.add(new JLabel("Gebäude", SwingConstants.LEFT));


        // Beispiel: Buttons für Gebäude
        ReadManager.getInstance().readAlleDaten();
        List<Gebaeude> gebaeude = ReadManager.getInstance().readAlleDatenNachKlasse(Gebaeude.class);

        for (Gebaeude gebaeude1 : gebaeude) {
            JButton gebaeudeButton = new JButton(gebaeude1.getName());
            gebaeudePanel.add(gebaeudeButton);
            gebaeudeButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Zentriert die Buttons
        }

        JSeparator separator2 = new JSeparator();
        gebaeudePanel.add(new JButton("Alle Autos von allen Gebäuden"));
        gebaeudePanel.add(separator2);

        // Footer mit Buttons
        JPanel footerPanel = new JPanel(new BorderLayout());
        JButton homeButton = new JButton("Startseite");
        JButton parkButton = new JButton("Fahrzeug umparken");
        footerPanel.add(homeButton, BorderLayout.CENTER);
        footerPanel.add(parkButton, BorderLayout.EAST);

        // Hinzufügen der Panels zum Hauptfenster
        add(headerPanel, BorderLayout.NORTH);
        add(gebaeudePanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
    }
}


