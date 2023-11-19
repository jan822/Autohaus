package de.autoverwaltung.plugin.ui;

import de.autoverwaltung.adapter.csvhandler.CsvFahrzeugHandler;
import de.autoverwaltung.application.guicontroller.FahrzeugVerkaufManager;
import de.autoverwaltung.application.guicontroller.GebaeudeFahrzeugManager;
import de.autoverwaltung.domain.fahrzeug.Auto;
import de.autoverwaltung.domain.fahrzeug.Fahrzeug;
import de.autoverwaltung.domain.fahrzeug.Motorrad;
import de.autoverwaltung.domain.gebaeude.Gebaeude;
import de.autoverwaltung.adapter.datenverwaltung.DeleteManager;
import de.autoverwaltung.adapter.datenverwaltung.ReadManager;
import de.autoverwaltung.adapter.datenverwaltung.UpdateManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FahrzeugVerkaufFenster extends JFrame {
    private JComboBox<Gebaeude> cmbGebaeude;
    private JComboBox<Fahrzeug> cmbFahrzeug;
    private JButton verkaufenButton;

    public FahrzeugVerkaufFenster() {
        setTitle("Fahrzeug verkaufen");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(0, 1));

        cmbGebaeude = new JComboBox<>();
        cmbGebaeude.addActionListener(e -> gebaeudeAusgewaehlt());
        cmbFahrzeug = new JComboBox<>();
        cmbFahrzeug.setEnabled(false);

        verkaufenButton = new JButton("Fahrzeug verkaufen");
        verkaufenButton.setEnabled(false);
        verkaufenButton.addActionListener(e -> fahrzeugVerkaufen());

        add(cmbGebaeude);
        add(cmbFahrzeug);
        add(verkaufenButton);

        ladeGebaeudeDaten();
    }

    private void ladeGebaeudeDaten() {
        List<Gebaeude> gebaeudeListe = ReadManager.getInstance().readAlleDatenNachKlasse(Gebaeude.class);
        for (Gebaeude gebaeude : gebaeudeListe) {
            cmbGebaeude.addItem(gebaeude);
        }
    }

    private void gebaeudeAusgewaehlt() {
        Gebaeude ausgewaehltesGebaeude = (Gebaeude) cmbGebaeude.getSelectedItem();
        if (ausgewaehltesGebaeude != null) {
            ladeFahrzeugDaten(ausgewaehltesGebaeude.getId());
            ladeFahrzeugDaten(ausgewaehltesGebaeude.getId());
        }
    }

    private void ladeFahrzeugDaten(String gebaeudeId) {
        cmbFahrzeug.removeAllItems();
        cmbFahrzeug.setEnabled(true);
        GebaeudeFahrzeugManager manager = new GebaeudeFahrzeugManager(gebaeudeId, ReadManager.getInstance());
        List<Auto> autos = manager.getAutosInGebaeude();
        List<Motorrad> motorraeder = manager.getMotorraederInGebaeude();
        for (Auto auto : autos) {
            cmbFahrzeug.addItem(auto);
        }
        for (Motorrad motorrad : motorraeder) {
            cmbFahrzeug.addItem(motorrad);
        }
        verkaufenButton.setEnabled(!autos.isEmpty() && !motorraeder.isEmpty());
    }

    private void fahrzeugVerkaufen() {
        Fahrzeug ausgewaehltesFahrzeug = (Fahrzeug) cmbFahrzeug.getSelectedItem();
        FahrzeugVerkaufManager verkaufManager = new FahrzeugVerkaufManager(DeleteManager.getInstance(), UpdateManager.getInstance(), ReadManager.getInstance(), new CsvFahrzeugHandler(), ausgewaehltesFahrzeug);

        boolean isAuto = ausgewaehltesFahrzeug instanceof Auto;
        if (ausgewaehltesFahrzeug != null) {
            String dialogMessage = isAuto ? "Auto verkauft: " : "Motorrad verkauft: ";
            dispose();
            JOptionPane.showMessageDialog(this, dialogMessage + ausgewaehltesFahrzeug.getMarke() + " " + ausgewaehltesFahrzeug.getModell());
        }
    }
}

