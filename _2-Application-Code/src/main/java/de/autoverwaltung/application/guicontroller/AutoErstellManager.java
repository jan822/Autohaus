package de.autoverwaltung.application.guicontroller;
import de.autoverwaltung.domain.ICreate;
import de.autoverwaltung.domain.IUpdate;
import de.autoverwaltung.domain.fahrzeug.Auto;
import de.autoverwaltung.domain.fahrzeug.Autotyp;
import de.autoverwaltung.domain.gebaeude.Stellplatz;

import java.util.UUID;

public class AutoErstellManager {
    private ICreate<Auto> createManager;
    private IUpdate<Stellplatz> updateManager;
    private ICsvUpdater<Auto> autoICsvUpdater;

    public AutoErstellManager(ICreate<Auto> createManager, ICsvUpdater autoICsvUpdater, IUpdate<Stellplatz> updateManager) {
        this.createManager = createManager;
        this.autoICsvUpdater = autoICsvUpdater;
        this.updateManager = updateManager;
    }

    public void speichern(String marke, String modell, double preis, int kilometer, boolean tuev, int anzahlTueren, boolean navigationssystem, int anzahlSitzplaetze, Autotyp autotyp, double kofferraumvolumen, Stellplatz gewaehlterStellplatz) {
        String id = UUID.randomUUID().toString();

        Auto auto = new Auto(id, marke, modell, preis, kilometer, tuev);
        auto.setAnzahlTueren(anzahlTueren);
        auto.setStellPlatzID(gewaehlterStellplatz.getID());
        auto.setNavigationssystem(navigationssystem);
        auto.setAnzahlSitzplaetze(anzahlSitzplaetze);
        auto.setAutotyp(autotyp);
        auto.setKofferraumvolumen(kofferraumvolumen);

        createManager.create(auto);
        gewaehlterStellplatz.setFahrzeugID(id);
        updateManager.update(gewaehlterStellplatz);
        autoICsvUpdater.eintragHinzufuegen(auto);
    }
}

