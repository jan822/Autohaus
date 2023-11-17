package de.autoverwaltung.application.guicontroller;

import de.autoverwaltung.domain.fahrzeug.Auto;
import de.autoverwaltung.domain.gebaeude.Gebaeude;
import de.autoverwaltung.domain.gebaeude.Stellplatz;
import de.autoverwaltung.plugin.datenverwaltung.ReadManager;

import java.util.List;
import java.util.stream.Collectors;

public class GebaeudeStellplatzManager {
    private Gebaeude gebaeude;

    public GebaeudeStellplatzManager(String gebaeudeId) {
        gebaeude = (Gebaeude) ReadManager.getInstance().read(gebaeudeId);
    }

    // public List<Stellplatz> getStellplaetzeInGebaeude(String gebaeudeId) {
    //     // Annahme: Stellplätze sind im DatenRepository gespeichert und haben eine Referenz zum Gebäude
    //     return datenRepository.readAlleDaten().values().stream()
    //             .filter(e -> e instanceof Stellplatz)
    //             .map(e -> (Stellplatz) e)
    //             .filter(s -> gebaeudeId.equals(s.getGebaeudeID()))
    //             .collect(Collectors.toList());
    // }
    //
    // public Auto getAutoByStellplatzId(String stellplatzId) {
    //     // Annahme: Autos sind im DatenRepository gespeichert und haben eine Referenz zum Stellplatz
    //     return datenRepository.readAlleDaten().values().stream()
    //             .filter(e -> e instanceof Auto)
    //             .map(e -> (Auto) e)
    //             .filter(a -> stellplatzId.equals(a.getStellPlatzID()))
    //             .findFirst()
    //             .orElse(null);
    // }

}
