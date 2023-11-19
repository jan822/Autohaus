package de.autoverwaltung.plugin.ui;


import de.autoverwaltung.adapter.csvhandler.CsvFahrzeugHandler;
import de.autoverwaltung.application.guicontroller.AutoErstellManager;
import de.autoverwaltung.application.guicontroller.GebaeudeStellplatzManager;
import de.autoverwaltung.domain.fahrzeug.Autotyp;
import de.autoverwaltung.domain.fahrzeug.MotorradTyp;
import de.autoverwaltung.domain.gebaeude.Gebaeude;
import de.autoverwaltung.domain.gebaeude.Stellplatz;
import de.autoverwaltung.adapter.datenverwaltung.CreateManager;
import de.autoverwaltung.adapter.datenverwaltung.ReadManager;
import de.autoverwaltung.adapter.datenverwaltung.UpdateManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FahrzeugEingabeFenster extends JFrame {
    // Gemeinsame Felder
    private JTextField txtMarke, txtModell, txtPreis, txtKilometer;

    private JCheckBox chkTUEV;

    // Spezifische Felder für Auto
    private JTextField txtAnzahlTueren, txtAnzahlSitzplaetze, txtKofferraumvolumen;
    private JLabel lblAnzahlTueren, lblNavigationssystem, lblAnzahlSitzplaetze, lblAutotyp, lblKofferraumvolumen, lblAutoSpeicher;
    private JCheckBox chkNavigationssystem;
    private JComboBox<Autotyp> cmbAutotyp;
    private JButton autoSpeichern;

    // Spezifische Felder für Motorrad
    private JLabel lblZweisitzer, lblBeiwagenGeeignet, lblMotorradTyp, lblSeitenstaenderBeidseitig, lblMotorradSpeicher;
    private JCheckBox chkZweisitzer, chkBeiwagenGeeignet, chkSeitenstaenderBeidseitig;
    private JComboBox<MotorradTyp> cmbMotorradTyp;
    private JButton motorradSpeichern;

    // Parkplatz-Auswahl
    private JComboBox<Gebaeude> cmbGebaeude;
    private JComboBox<Stellplatz> cmbStellplatz;

    // Umschalter zwischen Auto und Motorrad
    private JToggleButton toggleFahrzeugTyp;

    public FahrzeugEingabeFenster() {
        setTitle("Neues Fahrzeug eingeben");
        setSize(450, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(0, 2));
        setupParkPositionSection();

        setupFahrzeugTypSwitch();

        // Gemeinsame Felder
        addCommonFields();

        // Spezifische Felder für Auto und Motorrad
        setupAutoFields();
        setupMotorradFields();
        toggleAutoFields(true);
        toggleMotorradFields(false);
    }

    private void setupFahrzeugTypSwitch() {
        addSectionTitle("Fahrzeug");
        addSectionTitle("");
        JLabel waehle = new JLabel("Auto oder Motorrad");
        toggleFahrzeugTyp = new JToggleButton("Auto", true);
        toggleFahrzeugTyp.addActionListener(e -> switchFahrzeugTyp());
        add(waehle);
        add(toggleFahrzeugTyp);
    }

    private void switchFahrzeugTyp() {
        boolean isAuto = toggleFahrzeugTyp.isSelected();
        toggleFahrzeugTyp.setText(isAuto ? "Auto" : "Motorrad");
        toggleAutoFields(isAuto);
        toggleMotorradFields(!isAuto);
    }

    private void toggleAutoFields(boolean shouldAdd) {
        if (shouldAdd) {
            // Hinzufügen der Auto-spezifischen Felder
            add(lblAnzahlTueren);
            add(txtAnzahlTueren);
            add(lblNavigationssystem);
            add(chkNavigationssystem);
            add(lblAnzahlSitzplaetze);
            add(txtAnzahlSitzplaetze);
            add(lblAutotyp);
            add(cmbAutotyp);
            add(lblKofferraumvolumen);
            add(txtKofferraumvolumen);
            add(lblAutoSpeicher);
            add(autoSpeichern);
        } else {
            // Entfernen der Auto-spezifischen Felder
            remove(lblAnzahlTueren);
            remove(txtAnzahlTueren);
            remove(lblNavigationssystem);
            remove(chkNavigationssystem);
            remove(lblAnzahlSitzplaetze);
            remove(txtAnzahlSitzplaetze);
            remove(lblAutotyp);
            remove(cmbAutotyp);
            remove(lblKofferraumvolumen);
            remove(txtKofferraumvolumen);
            remove(lblAutoSpeicher);
            remove(autoSpeichern);
        }
        this.revalidate();
        this.repaint();
    }


    private void toggleMotorradFields(boolean shouldAdd) {
        if (shouldAdd) {
            // Hinzufügen der Motorrad-spezifischen Felder
            add(lblZweisitzer);
            add(chkZweisitzer);
            add(lblBeiwagenGeeignet);
            add(chkBeiwagenGeeignet);
            add(lblMotorradTyp);
            add(cmbMotorradTyp);
            add(lblSeitenstaenderBeidseitig);
            add(chkSeitenstaenderBeidseitig);
            add(lblMotorradSpeicher);
            add(motorradSpeichern);
        } else {
            // Entfernen der Motorrad-spezifischen Felder
            remove(lblZweisitzer);
            remove(chkZweisitzer);
            remove(lblBeiwagenGeeignet);
            remove(chkBeiwagenGeeignet);
            remove(lblMotorradTyp);
            remove(cmbMotorradTyp);
            remove(lblSeitenstaenderBeidseitig);
            remove(chkSeitenstaenderBeidseitig);
            remove(lblMotorradSpeicher);
            remove(motorradSpeichern);
        }
        this.revalidate();
        this.repaint();
    }


    private void setupAutoFields() {
        // Anzahl der Türen
        lblAnzahlTueren = new JLabel("Anzahl Türen:");
        txtAnzahlTueren = new JTextField();
        add(lblAnzahlTueren);
        add(txtAnzahlTueren);

        // Navigationssystem
        lblNavigationssystem = new JLabel("Navigationssystem (Ja/Nein):");
        chkNavigationssystem = new JCheckBox();
        add(lblNavigationssystem);
        add(chkNavigationssystem);

        // Anzahl der Sitzplätze
        lblAnzahlSitzplaetze = new JLabel("Anzahl Sitzplätze:");
        txtAnzahlSitzplaetze = new JTextField();
        add(lblAnzahlSitzplaetze);
        add(txtAnzahlSitzplaetze);

        // Autotyp
        lblAutotyp = new JLabel("Autotyp:");
        cmbAutotyp = new JComboBox<>(Autotyp.values());
        add(lblAutotyp);
        add(cmbAutotyp);

        // Kofferraumvolumen
        lblKofferraumvolumen = new JLabel("Kofferraumvolumen:");
        txtKofferraumvolumen = new JTextField();
        add(lblKofferraumvolumen);
        add(txtKofferraumvolumen);

        // Speichern
        lblAutoSpeicher = new JLabel("");
        autoSpeichern = new JButton("Speichern");
        autoSpeichern.addActionListener(e -> autoSpeichern());
        add(lblAutoSpeicher);
        add(autoSpeichern);


        // Standardmäßig unsichtbar setzen
        toggleAutoFields(false);
    }

    private void setupMotorradFields() {
        // Zweisitzer
        lblZweisitzer = new JLabel("Zweisitzer (Ja/Nein):");
        chkZweisitzer = new JCheckBox();
        add(lblZweisitzer);
        add(chkZweisitzer);

        // Beiwagen geeignet
        lblBeiwagenGeeignet = new JLabel("Beiwagen geeignet (Ja/Nein):");
        chkBeiwagenGeeignet = new JCheckBox();
        add(lblBeiwagenGeeignet);
        add(chkBeiwagenGeeignet);

        // MotorradTyp
        lblMotorradTyp = new JLabel("Motorradtyp:");
        cmbMotorradTyp = new JComboBox<>(MotorradTyp.values());
        add(lblMotorradTyp);
        add(cmbMotorradTyp);

        // Seitenständer beidseitig
        lblSeitenstaenderBeidseitig = new JLabel("Seitenständer beidseitig (Ja/Nein):");
        chkSeitenstaenderBeidseitig = new JCheckBox();
        add(lblSeitenstaenderBeidseitig);
        add(chkSeitenstaenderBeidseitig);

        // Speichern
        lblMotorradSpeicher = new JLabel("");
        motorradSpeichern = new JButton("Speichern");
        motorradSpeichern.addActionListener(e -> motorradSpeichern());
        add(lblMotorradSpeicher);
        add(motorradSpeichern);

        // Standardmäßig unsichtbar setzen
        toggleMotorradFields(false);
    }

    private void setupParkPositionSection() {
        addSectionTitle("Parkposition");
        addSectionTitle("");
        cmbGebaeude = new JComboBox<>();
        cmbStellplatz = new JComboBox<>();
        cmbStellplatz.setEnabled(false);

        List<Gebaeude> gebaeudeListe = ReadManager.getInstance().readAlleDatenNachKlasse(Gebaeude.class);
        for (Gebaeude gebaeude : gebaeudeListe) {
            cmbGebaeude.addItem(gebaeude);
        }

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

    private void addCommonFields() {
        // Marke
        add(new JLabel("Marke:"));
        txtMarke = new JTextField();
        add(txtMarke);

        // Modell
        add(new JLabel("Modell:"));
        txtModell = new JTextField();
        add(txtModell);

        // Preis
        add(new JLabel("Preis:"));
        txtPreis = new JTextField();
        add(txtPreis);

        // Kilometer
        add(new JLabel("Kilometer:"));
        txtKilometer = new JTextField();
        add(txtKilometer);

        // TÜV
        add(new JLabel("TÜV (Ja/Nein):"));
        chkTUEV = new JCheckBox();
        add(chkTUEV);
    }

    private void autoSpeichern() {
        try {
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

            AutoErstellManager controller = new AutoErstellManager(CreateManager.getInstance(), new CsvFahrzeugHandler(), UpdateManager.getInstance());
            controller.autoAbspeichern(marke, modell, preis, kilometer, tuev, anzahlTueren, navigationssystem, anzahlSitzplaetze, autotyp, kofferraumvolumen, (Stellplatz) cmbStellplatz.getSelectedItem());
            JOptionPane.showMessageDialog(this, "Auto hinzugefügt: " + marke + " " + modell);
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Die Daten sind unvollständig oder vom falschen Typ.");
        }
    }

    private void motorradSpeichern() {
        try {
            String marke = txtMarke.getText();
            String modell = txtModell.getText();
            double preis = Double.parseDouble(txtPreis.getText());
            int kilometer = Integer.parseInt(txtKilometer.getText());
            boolean tuev = chkTUEV.isSelected();
            boolean zweisitzer = chkZweisitzer.isSelected();
            boolean beiwagenGeeignet = chkBeiwagenGeeignet.isSelected();
            MotorradTyp motorradTyp = (MotorradTyp) cmbMotorradTyp.getSelectedItem();
            boolean seitenstaenderBeidseitig = chkSeitenstaenderBeidseitig.isSelected();

            AutoErstellManager controller = new AutoErstellManager(CreateManager.getInstance(), new CsvFahrzeugHandler(), UpdateManager.getInstance());
            controller.motorradSpeichern(marke, modell, preis, kilometer, tuev, zweisitzer, beiwagenGeeignet, motorradTyp, seitenstaenderBeidseitig, (Stellplatz) cmbStellplatz.getSelectedItem());
            JOptionPane.showMessageDialog(this, "Motorrad hinzugefügt: " + marke + " " + modell);
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Die Daten sind unvollständig oder vom falschen Typ.");
        }
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