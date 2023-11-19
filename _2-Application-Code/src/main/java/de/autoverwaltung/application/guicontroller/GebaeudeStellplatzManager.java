package de.autoverwaltung.application.guicontroller;

import de.autoverwaltung.domain.IEinzigartig;
import de.autoverwaltung.domain.IRead;
import de.autoverwaltung.domain.fahrzeug.Auto;
import de.autoverwaltung.domain.gebaeude.Gebaeude;
import de.autoverwaltung.domain.gebaeude.Stellplatz;

import java.util.List;
import java.util.stream.Collectors;

public class GebaeudeStellplatzManager {
    private Gebaeude gebaeude;
    private IRead readManager;

    public GebaeudeStellplatzManager(String gebaeudeId, IRead readManager) {
        this.readManager = readManager;
        gebaeude = (Gebaeude) this.readManager.read(gebaeudeId);
    }

    //alle Stellplätze vom Gebäude
    public <T extends IEinzigartig> List<Stellplatz> getStellplaetzeInGebaeude() {
        List<Stellplatz> alleStellPlätze = readManager.readAlleDatenNachKlasse(Stellplatz.class);
        return alleStellPlätze.stream().filter(el -> el.getGebaeudeID().equals(gebaeude.getID())).collect(Collectors.toList());
    }

    public <T extends IEinzigartig> List<Stellplatz> getBelegteStellplaetzeInGebaeude() {
        List<Stellplatz> allePlätze = getStellplaetzeInGebaeude();
        return allePlätze.stream().filter(el -> el.getFahrzeugID() != null).collect(Collectors.toList());
    }
    public <T extends IEinzigartig> List<Stellplatz> getNichtBelegteStellplaetzeInGebaeude() {
        List<Stellplatz> allePlätze = getStellplaetzeInGebaeude();
        return allePlätze.stream().filter(el -> el.getFahrzeugID() == null).collect(Collectors.toList());
    }

}
