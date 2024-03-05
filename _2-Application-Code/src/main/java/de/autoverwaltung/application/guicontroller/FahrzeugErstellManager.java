package de.autoverwaltung.application.guicontroller;
import de.autoverwaltung.domain.ICreate;
import de.autoverwaltung.domain.IUpdate;
import de.autoverwaltung.domain.fahrzeug.*;
import de.autoverwaltung.domain.gebaeude.Stellplatz;

import java.util.UUID;

public class FahrzeugErstellManager {
    private ICreate<Fahrzeug> createManager;
    private IUpdate<Stellplatz> updateManager;
    private ICsvUpdater<Fahrzeug> fahrzeugICsvUpdater;

    public FahrzeugErstellManager(ICreate<Fahrzeug> createManager, ICsvUpdater fahrzeugICsvUpdater, IUpdate<Stellplatz> updateManager) {
        this.createManager = createManager;
        this.fahrzeugICsvUpdater = fahrzeugICsvUpdater;
        this.updateManager = updateManager;
    }

    public void autoAbspeichern(String marke, String modell, double preis, Waehrung waehrung, int kilometer, boolean tuev, int anzahlTueren, boolean navigationssystem, int anzahlSitzplaetze, Autotyp autotyp, double kofferraumvolumen, Stellplatz gewaehlterStellplatz) {
        String id = UUID.randomUUID().toString();
        Auto auto = new Auto(id, gewaehlterStellplatz.getID(), marke, modell, preis, waehrung, kilometer, tuev, anzahlTueren, navigationssystem, anzahlSitzplaetze, autotyp, kofferraumvolumen);
        fahrzeugUpdaten(auto, gewaehlterStellplatz, id);
    }
    public void motorradSpeichern(String marke, String modell, double preis, Waehrung waehrung, int kilometer, boolean tuev, boolean zweisitzer, boolean beiwagenGeeignet, MotorradTyp motorradTyp, boolean seitenstaenderBeidseitig, Stellplatz gewaehlterStellplatz) {
        String id = UUID.randomUUID().toString();
        Motorrad motorrad = new Motorrad(id, gewaehlterStellplatz.getID(), marke, modell, preis, waehrung, kilometer, tuev, zweisitzer, beiwagenGeeignet, motorradTyp, seitenstaenderBeidseitig);
        fahrzeugUpdaten(motorrad, gewaehlterStellplatz, id);
    }

    public void fahrzeugUpdaten(Fahrzeug fahrzeug, Stellplatz gewaehlterStellplatz, String id){
        createManager.create(fahrzeug);
        gewaehlterStellplatz.setFahrzeugID(id);
        updateManager.update(gewaehlterStellplatz);
        fahrzeugICsvUpdater.eintragHinzufuegen(fahrzeug);
    }

}

