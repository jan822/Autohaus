package de.autoverwaltung.plugin.ui;


import de.autoverwaltung.adapter.csvhandler.CsvAutoHandler;
import de.autoverwaltung.application.guicontroller.AutoErstellManager;
import de.autoverwaltung.application.guicontroller.GebaeudeStellplatzManager;
import de.autoverwaltung.domain.fahrzeug.Autotyp;
import de.autoverwaltung.domain.gebaeude.Gebaeude;
import de.autoverwaltung.domain.gebaeude.Stellplatz;
import de.autoverwaltung.adapter.datenverwaltung.CreateManager;
import de.autoverwaltung.adapter.datenverwaltung.ReadManager;
import de.autoverwaltung.adapter.datenverwaltung.UpdateManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AutoEingabeFenster extends JFrame {
    private JTextField txtMarke, txtModell, txtPreis, txtKilometer, txtAnzahlTueren, txtAnzahlSitzplaetze, txtKofferraumvolumen;
    private JCheckBox chkTUEV, chkNavigationssystem;
    private JComboBox<Autotyp> cmbAutotyp;

    private JComboBox<Gebaeude> cmbGebaeude;

    private JComboBox<Stellplatz> cmbStellplatz;


    public AutoEingabeFenster() {
        setTitle("Neues Auto eingeben");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(0, 2));

        addSectionTitle("Autodetails");
        addSectionTitle("");
        add(new JLabel("Marke:"));
        txtMarke = new JTextField();
        add(txtMarke);

        add(new JLabel("Modell:"));
        txtModell = new JTextField();
        add(txtModell);

        add(new JLabel("Preis:"));
        txtPreis = new JTextField();
        add(txtPreis);

        add(new JLabel("Kilometer:"));
        txtKilometer = new JTextField();
        add(txtKilometer);

        add(new JLabel("TUEV (Ja/Nein):"));
        chkTUEV = new JCheckBox();
        add(chkTUEV);

        add(new JLabel("Anzahl Türen:"));
        txtAnzahlTueren = new JTextField();
        add(txtAnzahlTueren);

        add(new JLabel("Navigationssystem (Ja/Nein):"));
        chkNavigationssystem = new JCheckBox();
        add(chkNavigationssystem);

        add(new JLabel("Anzahl Sitzplätze:"));
        txtAnzahlSitzplaetze = new JTextField();
        add(txtAnzahlSitzplaetze);

        add(new JLabel("Autotyp:"));
        cmbAutotyp = new JComboBox<>(Autotyp.values());
        add(cmbAutotyp);

        add(new JLabel("Kofferraumvolumen:"));
        txtKofferraumvolumen = new JTextField();
        add(txtKofferraumvolumen);

        addSectionTitle("");
        addSectionTitle("");
        addSectionTitle("Parkposition");
        addSectionTitle("");
        setupParkPositionSection();

        addSectionTitle("");
        addSectionTitle("");
        addSectionTitle("");
        JButton speichern = new JButton("Speichern");
        speichern.addActionListener(e -> speichern());
        add(speichern);
    }

    private void speichern() {
        String marke = txtMarke.getText();
        String modell = txtModell.getText();
        double preis = Double.parseDouble(txtPreis.getText());
        int kilometer = Integer.parseInt(txtKilometer.getText());
        boolean tuev = chkTUEV.isSelected();
        int anzahlTueren = Integer.parseInt(txtAnzahlTueren.getText());
        boolean navigationssystem = chkNavigationssystem.isSelected();
        int anzahlSitzplaetze = Integer.parseInt(txtAnzahlSitzplaetze.getText());
        Autotyp autotyp = (Autotyp) cmbAutotyp.getSelectedItem();
        double kofferraumvolumen = Double.parseDouble(txtKofferraumvolumen.getText());

        AutoErstellManager controller = new AutoErstellManager(CreateManager.getInstance(), new CsvAutoHandler(), UpdateManager.getInstance());
        controller.speichern(marke, modell, preis, kilometer, tuev, anzahlTueren, navigationssystem, anzahlSitzplaetze, autotyp, kofferraumvolumen, (Stellplatz) cmbStellplatz.getSelectedItem());
        dispose();
    }


    private void setupParkPositionSection() {
        cmbGebaeude = new JComboBox<>();
        cmbStellplatz = new JComboBox<>();
        cmbStellplatz.setEnabled(false);

        List<Gebaeude> gebaeudeListe = ReadManager.getInstance().readAlleDatenNachKlasse(Gebaeude.class);
        for (Gebaeude gebaeude : gebaeudeListe) {
            cmbGebaeude.addItem(gebaeude);
        }

        // Listener für Gebäude-Dropdown
        cmbGebaeude.addActionListener(e -> {
            Gebaeude ausgewaehltesGebaeude = (Gebaeude) cmbGebaeude.getSelectedItem();
            if (ausgewaehltesGebaeude != null) {
                updateStellplatzDropdown(ausgewaehltesGebaeude.getId());
            }
        });

        add(new JLabel("Gebäude:"));
        add(cmbGebaeude);

        add(new JLabel("Stellplatz:"));
        add(cmbStellplatz);
    }

    private void updateStellplatzDropdown(String gebaeudeId) {
        cmbStellplatz.removeAllItems();
        cmbStellplatz.setEnabled(true);

        // Lade Stellplatzdaten für das ausgewählte Gebäude
        GebaeudeStellplatzManager manager = new GebaeudeStellplatzManager(gebaeudeId, ReadManager.getInstance());
        List<Stellplatz> stellplaetze = manager.getNichtBelegteStellplaetzeInGebaeude();

        for (Stellplatz stellplatz : stellplaetze) {
            cmbStellplatz.addItem(stellplatz);
        }
    }

    private void addSectionTitle(String title) {
        JLabel label = new JLabel(title, JLabel.LEFT);
        label.setFont(new Font(label.getFont().getName(), Font.BOLD, 16));
        add(label);
    }


}

