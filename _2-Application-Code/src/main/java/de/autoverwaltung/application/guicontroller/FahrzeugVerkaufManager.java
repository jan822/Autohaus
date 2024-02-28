package de.autoverwaltung.application.guicontroller;

import de.autoverwaltung.domain.IDelete;
import de.autoverwaltung.domain.IRead;
import de.autoverwaltung.domain.IUpdate;
import de.autoverwaltung.domain.fahrzeug.Fahrzeug;
import de.autoverwaltung.domain.gebaeude.Stellplatz;

public class FahrzeugVerkaufManager {
    IDelete<Fahrzeug> deleteAuto;
    IUpdate<Stellplatz> updateStellplatz;
    IRead<Stellplatz> readStellplatz;
    ICsvUpdater iCsvUpdater;
    Fahrzeug ausgewaehltesFahrzeug;

    public FahrzeugVerkaufManager(IDelete<Fahrzeug> delete, IUpdate<Stellplatz> update, IRead<Stellplatz> read, ICsvUpdater iCsvUpdater, Fahrzeug ausgewaehltesFahrzeug) {
        this.deleteAuto = delete;
        this.updateStellplatz = update;
        this.ausgewaehltesFahrzeug = ausgewaehltesFahrzeug;
        this.iCsvUpdater = iCsvUpdater;
        this.readStellplatz = read;
        this.fahrzeugLoeschen();
    }

    public void fahrzeugLoeschen(){
        //EntitiyManager Updaten
        deleteAuto.delete(ausgewaehltesFahrzeug);
        Stellplatz belegterStellplatz = readStellplatz.read(ausgewaehltesFahrzeug.getStellPlatzID());
        belegterStellplatz.setFahrzeugID(null);
        updateStellplatz.update(belegterStellplatz);
        //CSV aktualisieren
        iCsvUpdater.eintragLoeschen(ausgewaehltesFahrzeug);
    }


}
