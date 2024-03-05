import de.autoverwaltung.application.guicontroller.FahrzeugErstellManager;
import de.autoverwaltung.application.guicontroller.ICsvUpdater;
import de.autoverwaltung.domain.ICreate;
import de.autoverwaltung.domain.IUpdate;
import de.autoverwaltung.domain.fahrzeug.*;
import de.autoverwaltung.domain.gebaeude.Stellplatz;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class FahrzeugErstellManagerTest {

    private ICreate<Fahrzeug> createManagerMock;
    private IUpdate<Stellplatz> updateManagerMock;
    private ICsvUpdater<Fahrzeug> autoICsvUpdaterMock;
    private Stellplatz gewaehlterStellplatzMock;
    private FahrzeugErstellManager manager;

    @Test
    void testMotorradSpeichern() {
        // Arrange
        createManagerMock = mock(ICreate.class);
        updateManagerMock = mock(IUpdate.class);
        autoICsvUpdaterMock = mock(ICsvUpdater.class);
        gewaehlterStellplatzMock = mock(Stellplatz.class);

        manager = new FahrzeugErstellManager(createManagerMock, autoICsvUpdaterMock, updateManagerMock);

        String marke = "Yamaha";
        String modell = "YZF-R1";
        double preis = 15000;
        Waehrung waehrung = Waehrung.EUR;
        int kilometer = 10000;
        boolean tuev = true;
        boolean zweisitzer = true;
        boolean beiwagenGeeignet = false;
        MotorradTyp motorradTyp = MotorradTyp.SPORT;
        boolean seitenstaenderBeidseitig = true;

        when(gewaehlterStellplatzMock.getID()).thenReturn("stellplatz");

        // Act
        manager.motorradSpeichern(marke, modell, preis, waehrung, kilometer, tuev, zweisitzer, beiwagenGeeignet, motorradTyp, seitenstaenderBeidseitig, gewaehlterStellplatzMock);

        // Assert
        verify(createManagerMock).create(any(Motorrad.class));
        verify(gewaehlterStellplatzMock).setFahrzeugID(anyString());
        verify(updateManagerMock).update(gewaehlterStellplatzMock);
        verify(autoICsvUpdaterMock).eintragHinzufuegen(any(Motorrad.class));
    }

    @Test
    void testAutoAbspeichern() {
        // Arrange
        createManagerMock = mock(ICreate.class);
        updateManagerMock = mock(IUpdate.class);
        autoICsvUpdaterMock = mock(ICsvUpdater.class);
        gewaehlterStellplatzMock = mock(Stellplatz.class);

        manager = new FahrzeugErstellManager(createManagerMock, autoICsvUpdaterMock, updateManagerMock);

        String marke = "Volkswagen";
        String modell = "Golf";
        double preis = 20000;
        Waehrung waehrung = Waehrung.EUR;
        int kilometer = 50000;
        boolean tuev = true;
        int anzahlTueren = 4;
        boolean navigationssystem = true;
        int anzahlSitzplaetze = 5;
        Autotyp autotyp = Autotyp.KOMBI;
        double kofferraumvolumen = 380;

        when(gewaehlterStellplatzMock.getID()).thenReturn("stellplatz456");

        // Act
        manager.autoAbspeichern(marke, modell, preis, waehrung, kilometer, tuev, anzahlTueren, navigationssystem, anzahlSitzplaetze, autotyp, kofferraumvolumen, gewaehlterStellplatzMock);

        // Assert
        verify(createManagerMock).create(any(Auto.class));
        verify(gewaehlterStellplatzMock).setFahrzeugID(anyString());
        verify(updateManagerMock).update(gewaehlterStellplatzMock);
        verify(autoICsvUpdaterMock).eintragHinzufuegen(any(Auto.class));
    }

}
