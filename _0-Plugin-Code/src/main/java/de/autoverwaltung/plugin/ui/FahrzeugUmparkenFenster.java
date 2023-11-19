package de.autoverwaltung.plugin.ui;

import de.autoverwaltung.adapter.csvhandler.CsvAutoHandler;
import de.autoverwaltung.adapter.csvhandler.CsvStellplatzHandler;
import de.autoverwaltung.application.guicontroller.AutoVerkaufManager;
import de.autoverwaltung.application.guicontroller.FahrzeugUmparkenManager;
import de.autoverwaltung.application.guicontroller.GebaeudeFahrzeugManager;
import de.autoverwaltung.application.guicontroller.GebaeudeStellplatzManager;
import de.autoverwaltung.domain.fahrzeug.Auto;
import de.autoverwaltung.domain.fahrzeug.Fahrzeug;
import de.autoverwaltung.domain.fahrzeug.Motorrad;
import de.autoverwaltung.domain.gebaeude.Gebaeude;
import de.autoverwaltung.domain.gebaeude.Stellplatz;
import de.autoverwaltung.plugin.datenverwaltung.DeleteManager;
import de.autoverwaltung.plugin.datenverwaltung.ReadManager;
import de.autoverwaltung.plugin.datenverwaltung.UpdateManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FahrzeugUmparkenFenster extends JFrame {

    private JComboBox<Gebaeude> cmbGebaeude;
    private JComboBox<Gebaeude> cmbGebaeude2;
    private JComboBox<Fahrzeug> cmbFahrzeug;
    private JComboBox<Stellplatz> cmbStellplatz2;
    private JComboBox<Stellplatz> cmbStellplatz;
    private JButton umparkButton;

    private JTextField tfStellPlatzinfo;

    public FahrzeugUmparkenFenster() {
        setTitle("Auto umparken");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(0, 1));
        JLabel fahrzeugAuswaehlen = new JLabel("Fahrzeug auswählen");
        add(fahrzeugAuswaehlen);

        umparkButton = new JButton("Fahrzeug umparken");
        umparkButton.addActionListener(e -> umparken());
        umparkButton.setEnabled(false);

        cmbGebaeude = new JComboBox<>();
        cmbGebaeude.addActionListener(e -> gebaeudeAusgewaehlt());

        cmbFahrzeug = new JComboBox<>();
        cmbFahrzeug.addActionListener(e -> fahrzeugAusgewaehlt());
        cmbFahrzeug.setEnabled(false);

        cmbStellplatz = new JComboBox<>();
        cmbStellplatz.addActionListener(e -> stellplatzAusgewaehlt());
        cmbStellplatz.setEnabled(false);

        add(cmbGebaeude);
        add(cmbStellplatz);
        add(cmbFahrzeug);

        JLabel parkpositionAuswaehlen = new JLabel("Neue Parkposition auswählen");
        add(new JLabel(""));
        add(parkpositionAuswaehlen);
        cmbGebaeude2 = new JComboBox<>();
        cmbGebaeude2.addActionListener(e -> gebaeudeAusgewaehlt2());

        cmbStellplatz2 = new JComboBox<>();
        cmbStellplatz2.setEnabled(false);

        ladeGebaeudeDaten();
        add(cmbGebaeude2);
        add(cmbStellplatz2);

        add(umparkButton);
    }
    private void fahrzeugAusgewaehlt() {
        Fahrzeug ausgewaehltesFahrzeug = (Fahrzeug) cmbFahrzeug.getSelectedItem();
        if (ausgewaehltesFahrzeug != null) {
            for (int i = 0; i < cmbStellplatz.getItemCount(); i++) {
                Stellplatz stellplatz = cmbStellplatz.getItemAt(i);
                if (stellplatz.getFahrzeugID().equals(ausgewaehltesFahrzeug.getId())) {
                    cmbStellplatz.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    private void stellplatzAusgewaehlt() {
        Stellplatz ausgewaehlterStellplatz = (Stellplatz) cmbStellplatz.getSelectedItem();
        if (ausgewaehlterStellplatz != null) {
            for (int i = 0; i < cmbFahrzeug.getItemCount(); i++) {
                Fahrzeug fahrzeug = cmbFahrzeug.getItemAt(i);
                if (fahrzeug.getStellPlatzID().equals(ausgewaehlterStellplatz.getID())) {
                    cmbFahrzeug.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    private void umparken() {
        Fahrzeug ausgewaehltesFahrzeug = (Fahrzeug) cmbFahrzeug.getSelectedItem();
        Stellplatz alterStellplatz = (Stellplatz) cmbStellplatz.getSelectedItem();
        Stellplatz neuerStellplatz = (Stellplatz) cmbStellplatz2.getSelectedItem();

        if (ausgewaehltesFahrzeug != null && neuerStellplatz != null) {
            FahrzeugUmparkenManager umparkenManager = new FahrzeugUmparkenManager(UpdateManager.getInstance(), new CsvAutoHandler(), new CsvStellplatzHandler());
            umparkenManager.fahrzeugUmparken(ausgewaehltesFahrzeug, alterStellplatz, neuerStellplatz);
            dispose();
            JOptionPane.showMessageDialog(this, "Fahrzeug wurde umgeparkt.");
        }
    }

    private void ladeGebaeudeDaten() {
        java.util.List<Gebaeude> gebaeudeListe = ReadManager.getInstance().readAlleDatenNachKlasse(Gebaeude.class);
        for (Gebaeude gebaeude : gebaeudeListe) {
            cmbGebaeude.addItem(gebaeude);
            cmbGebaeude2.addItem(gebaeude);
        }
    }

    private void gebaeudeAusgewaehlt() {
        Gebaeude ausgewaehltesGebaeude = (Gebaeude) cmbGebaeude.getSelectedItem();
        if (ausgewaehltesGebaeude != null) {
            ladeFahrzeugDaten(ausgewaehltesGebaeude.getId());
            ladeStellplatzDaten(ausgewaehltesGebaeude.getId(), cmbStellplatz, true);
        }
    }
    private void gebaeudeAusgewaehlt2() {
        Gebaeude ausgewaehltesGebaeude = (Gebaeude) cmbGebaeude2.getSelectedItem();
        if (ausgewaehltesGebaeude != null) {
            ladeStellplatzDaten(ausgewaehltesGebaeude.getId(), cmbStellplatz2, false);
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
        umparkButton.setEnabled(!autos.isEmpty() && !motorraeder.isEmpty());
    }

    private void ladeStellplatzDaten(String gebaeudeId, JComboBox cmbStell, boolean belegteStellplatze) {
        cmbStell.removeAllItems();
        cmbStell.setEnabled(true);

        GebaeudeStellplatzManager stellplatzManager = new GebaeudeStellplatzManager(gebaeudeId, ReadManager.getInstance());
        List<Stellplatz> stellplaetze = belegteStellplatze? stellplatzManager.getBelegteStellplaetzeInGebaeude(): stellplatzManager.getNichtBelegteStellplaetzeInGebaeude();
        for (Stellplatz stellplatz : stellplaetze) {
            cmbStell.addItem(stellplatz);
        }
        umparkButton.setEnabled(!stellplaetze.isEmpty());
    }
}
