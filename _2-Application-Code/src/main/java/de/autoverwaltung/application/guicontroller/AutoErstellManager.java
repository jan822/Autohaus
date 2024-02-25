package de.autoverwaltung.application.guicontroller;
import de.autoverwaltung.domain.ICreate;
import de.autoverwaltung.domain.IUpdate;
import de.autoverwaltung.domain.fahrzeug.*;
import de.autoverwaltung.domain.gebaeude.Stellplatz;

import java.util.UUID;

public class AutoErstellManager {
    private ICreate<Fahrzeug> createManager;
    private IUpdate<Stellplatz> updateManager;
    private ICsvUpdater<Fahrzeug> autoICsvUpdater;

    public AutoErstellManager(ICreate<Fahrzeug> createManager, ICsvUpdater autoICsvUpdater, IUpdate<Stellplatz> updateManager) {
        this.createManager = createManager;
        this.autoICsvUpdater = autoICsvUpdater;
        this.updateManager = updateManager;
    }

    public void autoAbspeichern(String marke, String modell, double preis, Waehrung waehrung, int kilometer, boolean tuev, int anzahlTueren, boolean navigationssystem, int anzahlSitzplaetze, Autotyp autotyp, double kofferraumvolumen, Stellplatz gewaehlterStellplatz) {
        String id = UUID.randomUUID().toString();

        Auto auto = new Auto(id, marke, modell, preis, waehrung, kilometer, tuev);
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
    public void motorradSpeichern(String marke, String modell, double preis, Waehrung waehrung, int kilometer, boolean tuev, boolean zweisitzer, boolean beiwagenGeeignet, MotorradTyp motorradTyp, boolean seitenstaenderBeidseitig, Stellplatz gewaehlterStellplatz) {
        String id = UUID.randomUUID().toString();

        Motorrad motorrad = new Motorrad(id, marke, modell, preis, waehrung, kilometer, tuev);
        motorrad.setZweisitzer(zweisitzer);
        motorrad.setBeiwagenGeeignet(beiwagenGeeignet);
        motorrad.setMotorradTyp(motorradTyp);
        motorrad.setSeitenstaenderBeidseitig(seitenstaenderBeidseitig);
        motorrad.setStellPlatzID(gewaehlterStellplatz.getID());

        createManager.create(motorrad);
        gewaehlterStellplatz.setFahrzeugID(id);
        updateManager.update(gewaehlterStellplatz);
        autoICsvUpdater.eintragHinzufuegen(motorrad);
    }

}

