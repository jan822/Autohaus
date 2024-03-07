package de.autoverwaltung.plugin.ui;


import de.autoverwaltung.adapter.csvhandler.CsvFahrzeugHandler;
import de.autoverwaltung.application.guicontroller.FahrzeugErstellManager;
import de.autoverwaltung.application.guicontroller.GebaeudeStellplatzManager;
import de.autoverwaltung.domain.fahrzeug.Autotyp;
import de.autoverwaltung.domain.fahrzeug.MotorradTyp;
import de.autoverwaltung.domain.fahrzeug.Waehrung;
import de.autoverwaltung.domain.gebaeude.Gebaeude;
import de.autoverwaltung.domain.gebaeude.Stellplatz;
import de.autoverwaltung.adapter.datenverwaltung.CreateManager;
import de.autoverwaltung.adapter.datenverwaltung.ReadManager;
import de.autoverwaltung.adapter.datenverwaltung.UpdateManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FahrzeugEingabeFenster extends JFrame {
    // Gemeinsame Felder 0-5 Auto, 6-10 Motorrad, 11-16 Gemeinsam
    private JLabel[] jLabels = new JLabel[17];
    private JTextField txtMarke, txtModell, txtPreis, txtKilometer;
    private JCheckBox chkTUEV;

    // Spezifische Felder für Auto
    private JTextField txtAnzahlTueren, txtAnzahlSitzplaetze, txtKofferraumvolumen;
    private JCheckBox chkNavigationssystem;
    private JComboBox<Autotyp> cmbAutotyp;
    private JComboBox<Waehrung> cmbWaehrung;
    private JButton autoSpeichern;

    // Spezifische Felder für Motorrad
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

        erstelleFahrzeugTypSwitch();
        erstelleJLabels();

        // Gemeinsame Felder
        addGemeinsameFelder();

        // Spezifische Felder für Auto und Motorrad
        erstelleAutoFelder();
        erstelleMotorradFelder();
        autoFelderEinblenden(true);
        toggleMotorradFields(false);
    }

    private void erstelleJLabels() {
        jLabels[0] = new JLabel("Anzahl Türen:");
        jLabels[1] = new JLabel("Navigationssystem (Ja/Nein):");
        jLabels[2] = new JLabel("Anzahl Sitzplätze:");
        jLabels[3] = new JLabel("Autotyp:");
        jLabels[4] = new JLabel("Kofferraumvolumen:");
        jLabels[5] = new JLabel("");

        jLabels[6] = new JLabel("Zweisitzer (Ja/Nein):");
        jLabels[7] = new JLabel("Beiwagen geeignet (Ja/Nein):");
        jLabels[8] = new JLabel("Motorradtyp:");
        jLabels[9] = new JLabel("Seitenständer beidseitig (Ja/Nein):");
        jLabels[10] = new JLabel("");

        jLabels[11] = new JLabel("Marke:");
        jLabels[12] = new JLabel("Modell");
        jLabels[13] = new JLabel("Preis");
        jLabels[14] = new JLabel("Währung");
        jLabels[15] = new JLabel("Kilometer");
        jLabels[16] = new JLabel("TÜV (Ja/Nein):");
    }

    private void erstelleFahrzeugTypSwitch() {
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
        autoFelderEinblenden(isAuto);
        toggleMotorradFields(!isAuto);
    }

    private void autoFelderEinblenden(boolean shouldAdd) {
        JComponent[] tempComp = new JComponent[]{txtAnzahlTueren, chkNavigationssystem, txtAnzahlSitzplaetze, cmbAutotyp, txtKofferraumvolumen, autoSpeichern};
        // Hinzufügen und Entfernen der Auto-spezifischen Felder
        for (int i = 0; i < 6; i++) {
            if (shouldAdd) {
                add(jLabels[i]);
                add(tempComp[i]);
            } else {
                remove(jLabels[i]);
                remove(tempComp[i]);
            }
        }
        this.revalidate();
        this.repaint();
    }

    private void toggleMotorradFields(boolean shouldAdd) {
        JComponent[] tempComp = new JComponent[]{chkZweisitzer, chkBeiwagenGeeignet, cmbMotorradTyp, chkSeitenstaenderBeidseitig, motorradSpeichern};
        // Hinzufügen und Entfernen der Auto-spezifischen Felder
        for (int i = 0; i < 5; i++) {
            if (shouldAdd) {
                add(jLabels[i + 6]);
                add(tempComp[i]);
            } else {
                remove(jLabels[i + 6]);
                remove(tempComp[i]);
            }
        }
        this.revalidate();
        this.repaint();
    }

    private void erstelleAutoFelder() {
        JComponent[] components = {
                txtAnzahlTueren = new JTextField(),
                chkNavigationssystem = new JCheckBox(),
                txtAnzahlSitzplaetze = new JTextField(),
                cmbAutotyp = new JComboBox<>(Autotyp.values()),
                txtKofferraumvolumen = new JTextField(),
                autoSpeichern = new JButton("Speichern")
        };
        for (JComponent component : components) {
            add(component);
        }
        autoSpeichern.addActionListener(e -> autoSpeichern());
        autoFelderEinblenden(false);
    }

    private void erstelleMotorradFelder() {
        JComponent[] components = {
                chkZweisitzer = new JCheckBox(),
                chkBeiwagenGeeignet = new JCheckBox(),
                cmbMotorradTyp = new JComboBox<>(MotorradTyp.values()),
                chkSeitenstaenderBeidseitig = new JCheckBox(),
                motorradSpeichern = new JButton("Speichern")
        };
        for (JComponent component : components) {
            add(component);
        }
        motorradSpeichern.addActionListener(e -> motorradSpeichern());
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

    private void addGemeinsameFelder() {
        JComponent[] components = {
                txtMarke = new JTextField(),
                txtModell = new JTextField(),
                txtPreis = new JTextField(),
                cmbWaehrung = new JComboBox<>(Waehrung.values()),
                txtKilometer = new JTextField(),
                chkTUEV = new JCheckBox()
        };

        for (int i = 0; i <= 5; i++) {
            ergänzeJLabelUndJComponent(jLabels[i + 11], components[i]);
        }
    }

    private void ergänzeJLabelUndJComponent(JLabel label, JComponent component) {
        add(label);
        add(component);
    }


    private void autoSpeichern() {
        try {
            String marke = txtMarke.getText();
            String modell = txtModell.getText();
            double preis = Double.parseDouble(txtPreis.getText());
            Waehrung waehrung = (Waehrung) cmbWaehrung.getSelectedItem();
            int kilometer = Integer.parseInt(txtKilometer.getText());
            boolean tuev = chkTUEV.isSelected();
            int anzahlTueren = Integer.parseInt(txtAnzahlTueren.getText());
            boolean navigationssystem = chkNavigationssystem.isSelected();
            int anzahlSitzplaetze = Integer.parseInt(txtAnzahlSitzplaetze.getText());
            Autotyp autotyp = (Autotyp) cmbAutotyp.getSelectedItem();
            double kofferraumvolumen = Double.parseDouble(txtKofferraumvolumen.getText());

            FahrzeugErstellManager controller = new FahrzeugErstellManager(CreateManager.getInstance(), new CsvFahrzeugHandler(), UpdateManager.getInstance());
            controller.autoAbspeichern(marke, modell, preis, waehrung, kilometer, tuev, anzahlTueren, navigationssystem, anzahlSitzplaetze, autotyp, kofferraumvolumen, (Stellplatz) cmbStellplatz.getSelectedItem());
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
            Waehrung waehrung = (Waehrung) cmbWaehrung.getSelectedItem();
            int kilometer = Integer.parseInt(txtKilometer.getText());
            boolean tuev = chkTUEV.isSelected();
            boolean zweisitzer = chkZweisitzer.isSelected();
            boolean beiwagenGeeignet = chkBeiwagenGeeignet.isSelected();
            MotorradTyp motorradTyp = (MotorradTyp) cmbMotorradTyp.getSelectedItem();
            boolean seitenstaenderBeidseitig = chkSeitenstaenderBeidseitig.isSelected();

            FahrzeugErstellManager controller = new FahrzeugErstellManager(CreateManager.getInstance(), new CsvFahrzeugHandler(), UpdateManager.getInstance());
            controller.motorradSpeichern(marke, modell, preis, waehrung, kilometer, tuev, zweisitzer, beiwagenGeeignet, motorradTyp, seitenstaenderBeidseitig, (Stellplatz) cmbStellplatz.getSelectedItem());
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