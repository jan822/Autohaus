package de.autoverwaltung.plugin.ui;

import de.autoverwaltung.adapter.csvhandler.CsvAutoHandler;
import de.autoverwaltung.application.guicontroller.AutoVerkaufManager;
import de.autoverwaltung.application.guicontroller.GebaeudeFahrzeugManager;
import de.autoverwaltung.domain.IRead;
import de.autoverwaltung.domain.fahrzeug.Auto;
import de.autoverwaltung.domain.gebaeude.Gebaeude;
import de.autoverwaltung.plugin.datenverwaltung.DeleteManager;
import de.autoverwaltung.plugin.datenverwaltung.ReadManager;
import de.autoverwaltung.plugin.datenverwaltung.UpdateManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AutoVerkaufFenster extends JFrame {
    private JComboBox<Gebaeude> cmbGebaeude;
    private JComboBox<Auto> cmbAuto;
    private JButton verkaufenButton;

    public AutoVerkaufFenster() {
        setTitle("Auto verkaufen");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(0, 1));

        cmbGebaeude = new JComboBox<>();
        cmbGebaeude.addActionListener(e -> gebaeudeAusgewaehlt());
        cmbAuto = new JComboBox<>();
        cmbAuto.setEnabled(false);

        verkaufenButton = new JButton("Auto verkaufen");
        verkaufenButton.setEnabled(false);
        verkaufenButton.addActionListener(e -> autoVerkaufen());

        add(cmbGebaeude);
        add(cmbAuto);
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
            ladeAutoDaten(ausgewaehltesGebaeude.getId());
        }
    }

    private void ladeAutoDaten(String gebaeudeId) {
        cmbAuto.removeAllItems();
        cmbAuto.setEnabled(true);
        GebaeudeFahrzeugManager manager = new GebaeudeFahrzeugManager(gebaeudeId, ReadManager.getInstance());
        List<Auto> autos = manager.getAutosInGebaeude();
        for (Auto auto : autos) {
            cmbAuto.addItem(auto);
        }
        verkaufenButton.setEnabled(!autos.isEmpty());
    }

    private void autoVerkaufen() {
        Auto ausgewaehltesAuto = (Auto) cmbAuto.getSelectedItem();
        AutoVerkaufManager verkaufManager = new AutoVerkaufManager(DeleteManager.getInstance(), UpdateManager.getInstance(), ReadManager.getInstance(), new CsvAutoHandler(),ausgewaehltesAuto);
        if (ausgewaehltesAuto != null) {
            JOptionPane.showMessageDialog(this, "Auto verkauft: " + ausgewaehltesAuto.getMarke() + " " + ausgewaehltesAuto.getModell());
        }
        dispose();
    }
}

