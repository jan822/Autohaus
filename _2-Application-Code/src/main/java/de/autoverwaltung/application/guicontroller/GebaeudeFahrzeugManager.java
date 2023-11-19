package de.autoverwaltung.application.guicontroller;

import de.autoverwaltung.domain.IRead;
import de.autoverwaltung.domain.fahrzeug.Auto;
import de.autoverwaltung.domain.fahrzeug.Fahrzeug;
import de.autoverwaltung.domain.fahrzeug.Motorrad;
import de.autoverwaltung.domain.gebaeude.Gebaeude;
import de.autoverwaltung.domain.gebaeude.Stellplatz;

import java.util.List;
import java.util.stream.Collectors;

public class GebaeudeFahrzeugManager {
    private Gebaeude gebaeude;
    private IRead readManager;

    public GebaeudeFahrzeugManager(String gebaeudeId, IRead readManager) {
        this.readManager = readManager;
        gebaeude = (Gebaeude) this.readManager.read(gebaeudeId);
    }

    public List<Auto> getAutosInGebaeude() {
        GebaeudeStellplatzManager platzManager = new GebaeudeStellplatzManager(gebaeude.getId(), readManager);
        List<Stellplatz> belegteStellplaetze = platzManager.getBelegteStellplaetzeInGebaeude();

        List<Auto> alleAutos = readManager.readAlleDatenNachKlasse(Auto.class);

        return alleAutos.stream()
                .filter(auto -> belegteStellplaetze.stream()
                        .anyMatch(stellplatz -> stellplatz.getFahrzeugID().equals(auto.getId())))
                .collect(Collectors.toList());
    }

    public List<Motorrad> getMotorraederInGebaeude() {
        GebaeudeStellplatzManager platzManager = new GebaeudeStellplatzManager(gebaeude.getId(), readManager);
        List<Stellplatz> belegteStellplaetze = platzManager.getBelegteStellplaetzeInGebaeude();
        System.out.println(belegteStellplaetze);

        List<Motorrad> alleMotorraeder = readManager.readAlleDatenNachKlasse(Motorrad.class);

        return alleMotorraeder.stream()
                .filter(motorrad -> belegteStellplaetze.stream()
                        .anyMatch(stellplatz -> stellplatz.getFahrzeugID().equals(motorrad.getId())))
                .collect(Collectors.toList());
    }

}

