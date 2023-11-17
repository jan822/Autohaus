package de.autoverwaltung.application.guicontroller;

import de.autoverwaltung.domain.fahrzeug.Auto;
import de.autoverwaltung.domain.gebaeude.Gebaeude;
import de.autoverwaltung.domain.gebaeude.Stellplatz;
import de.autoverwaltung.plugin.datenverwaltung.DatenRepository;
import de.autoverwaltung.plugin.datenverwaltung.ReadManager;

import java.util.ArrayList;
import java.util.List;

public class GebaeudeFahrzeugManager {
    private DatenRepository datenRepository;

    public GebaeudeFahrzeugManager(DatenRepository datenRepository) {
        this.datenRepository = datenRepository;
    }

    // public List<Auto> getAutosInGebaeude(String gebaeudeId) {
    //     List<Auto> autosInGebaeude = new ArrayList<>();
    //     Gebaeude gebaeude = (Gebaeude) ReadManager.getInstance().read(gebaeudeId);
    //     // GebaeudeStellplatzManager
    //     for (Stellplatz stellplatz : gebaeude.getStellplaetzeInGebaeude(gebaeudeId)) {
    //         Auto auto = DatenRepository.getInstance().getAutoByStellplatzId(stellplatz.getID());
    //         if (auto != null) {
    //             autosInGebaeude.add(auto);
    //         }
    //     }
    //     return autosInGebaeude;
    // }

    // Weitere Methoden...
}

