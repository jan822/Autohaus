package de.autoverwaltung.application.guicontroller;

import de.autoverwaltung.domain.IDelete;
import de.autoverwaltung.domain.IEinzigartig;
import de.autoverwaltung.domain.IRead;
import de.autoverwaltung.domain.IUpdate;
import de.autoverwaltung.domain.fahrzeug.Auto;
import de.autoverwaltung.domain.gebaeude.Stellplatz;

public class AutoVerkaufManager {
    IDelete<Auto> deleteAuto;
    IUpdate<Stellplatz> updateStellplatz;
    IRead<Stellplatz> readStellplatz;
    ICsvUpdater iCsvUpdater;
    Auto ausgewaehltesAuto;

    public AutoVerkaufManager(IDelete<Auto> delete, IUpdate<Stellplatz> update, IRead<Stellplatz> read, ICsvUpdater iCsvUpdater, Auto ausgewaehltesAuto) {
        this.deleteAuto = delete;
        this.updateStellplatz = update;
        this.ausgewaehltesAuto = ausgewaehltesAuto;
        this.iCsvUpdater = iCsvUpdater;
        this.readStellplatz = read;
        this.autoLoeschen();
    }

    public void autoLoeschen(){
        //EntitiyManager Updaten
        deleteAuto.delete(ausgewaehltesAuto);
        Stellplatz belegterStellplatz = readStellplatz.read(ausgewaehltesAuto.getStellPlatzID());
        belegterStellplatz.setFahrzeugID(null);
        updateStellplatz.update(belegterStellplatz);
        //Csv akutalisieren
        iCsvUpdater.eintragLoeschen(ausgewaehltesAuto);
    }


}
