import de.autoverwaltung.application.guicontroller.FahrzeugVerkaufManager;
import de.autoverwaltung.application.guicontroller.ICsvUpdater;
import de.autoverwaltung.domain.IDelete;
import de.autoverwaltung.domain.IRead;
import de.autoverwaltung.domain.IUpdate;
import de.autoverwaltung.domain.fahrzeug.Fahrzeug;
import de.autoverwaltung.domain.gebaeude.Stellplatz;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class FahrzeugVerkaufManagerTest {
    private IDelete<Fahrzeug> deleteAutoMock;
    private IUpdate<Stellplatz> updateStellplatzMock;
    private IRead<Stellplatz> readStellplatzMock;
    private ICsvUpdater iCsvUpdaterMock;
    private Fahrzeug ausgewaehltesFahrzeugMock;
    private Stellplatz belegterStellplatzMock;

    private FahrzeugVerkaufManager manager;

    @Test
    void testFahrzeugLoeschen() {
        //Arrange
        deleteAutoMock = mock(IDelete.class);
        updateStellplatzMock = mock(IUpdate.class);
        readStellplatzMock = mock(IRead.class);
        iCsvUpdaterMock = mock(ICsvUpdater.class);
        ausgewaehltesFahrzeugMock = mock(Fahrzeug.class);
        belegterStellplatzMock = mock(Stellplatz.class);

        when(ausgewaehltesFahrzeugMock.getStellPlatzID()).thenReturn("stellplatzId");
        when(readStellplatzMock.read("stellplatzId")).thenReturn(belegterStellplatzMock);

        manager = new FahrzeugVerkaufManager(deleteAutoMock, updateStellplatzMock, readStellplatzMock, iCsvUpdaterMock, ausgewaehltesFahrzeugMock);
        // Act
        manager.fahrzeugLoeschen();

        // Assert
        verify(deleteAutoMock, times(2)).delete(ausgewaehltesFahrzeugMock);
        verify(readStellplatzMock, times(2)).read(ausgewaehltesFahrzeugMock.getStellPlatzID());
        verify(updateStellplatzMock, times(2)).update(belegterStellplatzMock);
        verify(iCsvUpdaterMock, times(2)).eintragLoeschen(ausgewaehltesFahrzeugMock);
    }
}
