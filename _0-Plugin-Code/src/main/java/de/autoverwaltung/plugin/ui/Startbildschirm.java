package de.autoverwaltung.plugin.ui;

import de.autoverwaltung.domain.IEinzigartig;
import de.autoverwaltung.domain.gebaeude.Gebaeude;
import de.autoverwaltung.adapter.datenverwaltung.ReadManager;

import javax.swing.*;
import java.awt.*;

import java.util.List;

public class Startbildschirm extends JFrame {

    public <T extends IEinzigartig> Startbildschirm() {
        setTitle("Autohausverwaltung");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header mit Buttons
        JPanel headerPanel = new JPanel(new BorderLayout());
        JButton addButton = new JButton("Auto hinzufügen");
        addButton.addActionListener(e -> {
            AutoEingabeFenster eingabeFenster = new AutoEingabeFenster();
            eingabeFenster.setVisible(true);
        });

        JButton sellButton = new JButton("Fahrzeug verkaufen");
        sellButton.addActionListener(e -> {
            FahrzeugVerkaufFenster verkaufFenster = new FahrzeugVerkaufFenster();
            verkaufFenster.setVisible(true);
        });

        headerPanel.add(addButton, BorderLayout.WEST);
        headerPanel.add(sellButton, BorderLayout.EAST);

        // Gebäude-Bereich
        JPanel gebaeudePanel = new JPanel();
        gebaeudePanel.setLayout(new GridLayout(0, 1));
        gebaeudePanel.add(new JLabel("Gebäude", SwingConstants.LEFT));


        // Buttons für Gebäude
        ReadManager.getInstance().readAlleDaten();
        List<Gebaeude> gebaeude = ReadManager.getInstance().readAlleDatenNachKlasse(Gebaeude.class);

        for (Gebaeude gebaeude1 : gebaeude) {
            JButton gebaeudeButton = new JButton(gebaeude1.getName());
            gebaeudePanel.add(gebaeudeButton);
            gebaeudeButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Zentriert die Buttons

            gebaeudeButton.addActionListener(e -> {
                StellplatzTabelleFenster tabelleFenster = new StellplatzTabelleFenster(gebaeude1.getId());
                tabelleFenster.setVisible(true);
            });
        }

        JSeparator separator2 = new JSeparator();
        gebaeudePanel.add(new JButton("Alle Autos von allen Gebäuden"));
        gebaeudePanel.add(separator2);

        // Footer mit Buttons
        JPanel footerPanel = new JPanel(new BorderLayout());
        JButton homeButton = new JButton("Startseite");
        JButton parkButton = new JButton("Fahrzeug umparken");
        parkButton.addActionListener(e -> {
                FahrzeugUmparkenFenster fahrzeugUmparkFenster = new FahrzeugUmparkenFenster();
                fahrzeugUmparkFenster.setVisible(true);
                }
        );
        footerPanel.add(homeButton, BorderLayout.CENTER);
        footerPanel.add(parkButton, BorderLayout.EAST);

        // Hinzufügen der Panels zum Hauptfenster
        add(headerPanel, BorderLayout.NORTH);
        add(gebaeudePanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
    }
}


