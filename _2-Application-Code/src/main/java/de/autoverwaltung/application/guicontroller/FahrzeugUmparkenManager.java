package de.autoverwaltung.application.guicontroller;

import de.autoverwaltung.domain.IEinzigartig;
import de.autoverwaltung.domain.IUpdate;
import de.autoverwaltung.domain.fahrzeug.Fahrzeug;
import de.autoverwaltung.domain.gebaeude.Stellplatz;

public class FahrzeugUmparkenManager {

    private IUpdate<IEinzigartig> updateManager;
    private ICsvUpdater iCsvFahrzeugUpdater;
    private ICsvUpdater iCsvStellplatzUpdater;

    public FahrzeugUmparkenManager(IUpdate<IEinzigartig> updateManager, ICsvUpdater iCsvFahrzeugUpdater, ICsvUpdater iCsvStellplatzUpdater) {
        this.updateManager = updateManager;
        this.iCsvFahrzeugUpdater = iCsvFahrzeugUpdater;
        this.iCsvStellplatzUpdater = iCsvStellplatzUpdater;
    }

    public void fahrzeugUmparken(Fahrzeug ausgewaehltesFahrzeug, Stellplatz alterStellplatz, Stellplatz neuerStellplatz) {
        neuerStellplatz.setFahrzeugID(ausgewaehltesFahrzeug.getId());
        alterStellplatz.setFahrzeugID("");
        ausgewaehltesFahrzeug.setStellPlatzID(neuerStellplatz.getID());

        updateManager.update(ausgewaehltesFahrzeug);
        updateManager.update(alterStellplatz);
        updateManager.update(neuerStellplatz);

        iCsvFahrzeugUpdater.eintragAendern(ausgewaehltesFahrzeug);
        iCsvStellplatzUpdater.eintragAendern(alterStellplatz);
        iCsvStellplatzUpdater.eintragAendern(neuerStellplatz);
    }
}

