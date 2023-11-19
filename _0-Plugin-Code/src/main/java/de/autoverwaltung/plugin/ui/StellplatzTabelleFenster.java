package de.autoverwaltung.plugin.ui;

import de.autoverwaltung.application.guicontroller.GebaeudeFahrzeugManager;
import de.autoverwaltung.domain.fahrzeug.Auto;
import de.autoverwaltung.domain.fahrzeug.Motorrad;
import de.autoverwaltung.adapter.datenverwaltung.ReadManager;
import de.autoverwaltung.domain.gebaeude.Stellplatz;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StellplatzTabelleFenster extends JFrame {

    public StellplatzTabelleFenster(String gebaeudeId) {
        setTitle("Stellplätze im Gebäude");
        setSize(700, 600); // Erhöhte Größe für zwei Tabellen
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(2, 1)); // Zwei Reihen für zwei Tabellen

        // Panel für Autos
        JPanel autoPanel = new JPanel(new BorderLayout());
        autoPanel.add(createLabel("Autos"), BorderLayout.NORTH);
        autoPanel.add(createAutoTabelle(gebaeudeId), BorderLayout.CENTER);
        add(autoPanel);

        // Panel für Motorräder
        JPanel motorradPanel = new JPanel(new BorderLayout());
        motorradPanel.add(createLabel("Motorräder"), BorderLayout.NORTH);
        motorradPanel.add(createMotorradTabelle(gebaeudeId), BorderLayout.CENTER);
        add(motorradPanel);
    }

    private JScrollPane createAutoTabelle(String gebaeudeId) {
        GebaeudeFahrzeugManager manager = new GebaeudeFahrzeugManager(gebaeudeId, ReadManager.getInstance());
        List<Auto> fahrzeuge = manager.getAutosInGebaeude();

        String[] columnNames = {
                "Stellplatz", "Marke", "Modell", "Preis", "Kilometer", "TUEV", "Autotyp", "Anzahl Sitzplätze", "Kofferraumvolumen"
        };
        Object[][] data = new Object[fahrzeuge.size()][columnNames.length];

        int i = 0;
        for (Auto auto : fahrzeuge) {
            Stellplatz temp = (Stellplatz) ReadManager.getInstance().read(auto.getStellPlatzID());
            data[i][0] = temp.getName();
            data[i][1] = auto.getMarke();
            data[i][2] = auto.getModell();
            data[i][3] = auto.getPreis();
            data[i][4] = auto.getKilometer();
            data[i][5] = auto.isTuev() ? "Ja" : "Nein";
            data[i][6] = auto.getAutotyp().toString();
            data[i][7] = auto.getAnzahlSitzplaetze();
            data[i][8] = auto.getKofferraumvolumen();
            i++;
        }

        return new JScrollPane(new JTable(data, columnNames));
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text, JLabel.CENTER);
        return label;
    }

    private JScrollPane createMotorradTabelle(String gebaeudeId) {
        GebaeudeFahrzeugManager manager = new GebaeudeFahrzeugManager(gebaeudeId, ReadManager.getInstance());
        List<Motorrad> motorraeder = manager.getMotorraederInGebaeude();

        String[] motorradColumnNames = {
                "Stellplatz", "Marke", "Modell", "Preis", "Kilometer", "TUEV", "Motorradtyp", "Zweisitzer", "Beiwagen geeignet", "Seitenständer beidseitig"
        };
        Object[][] motorradData = new Object[motorraeder.size()][motorradColumnNames.length];

        int i = 0;
        for (Motorrad motorrad : motorraeder) {
            motorradData[i][0] = motorrad.getStellPlatzID();
            motorradData[i][1] = motorrad.getMarke();
            motorradData[i][2] = motorrad.getModell();
            motorradData[i][3] = motorrad.getPreis();
            motorradData[i][4] = motorrad.getKilometer();
            motorradData[i][5] = motorrad.isTuev() ? "Ja" : "Nein";
            motorradData[i][6] = motorrad.getMotorradTyp().toString();
            motorradData[i][7] = motorrad.isZweisitzer() ? "Ja" : "Nein";
            motorradData[i][8] = motorrad.isBeiwagenGeeignet() ? "Ja" : "Nein";
            motorradData[i][9] = motorrad.isSeitenstaenderBeidseitig() ? "Ja" : "Nein";
            i++;
        }

        JTable motorradTable = new JTable(motorradData, motorradColumnNames);
        return new JScrollPane(motorradTable);
    }


}


