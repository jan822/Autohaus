import de.autoverwaltung.application.guicontroller.FahrzeugUmparkenManager;
import de.autoverwaltung.application.guicontroller.ICsvUpdater;
import de.autoverwaltung.domain.IEinzigartig;
import de.autoverwaltung.domain.IUpdate;
import de.autoverwaltung.domain.fahrzeug.Fahrzeug;
import de.autoverwaltung.domain.gebaeude.Stellplatz;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class FahrzeugUmparkenManagerTest {
    private FahrzeugUmparkenManager manager;
    private Fahrzeug mockFahrzeug;
    private Stellplatz mockAlterStellplatz, mockNeuerStellplatz;
    private IUpdate<IEinzigartig> mockUpdateManager;
    private ICsvUpdater mockCsvFahrzeugUpdater, mockCsvStellplatzUpdater;

    @Test
    void testFahrzeugUmparken() {
        // Arrange
        mockUpdateManager = mock(IUpdate.class);
        mockCsvFahrzeugUpdater = mock(ICsvUpdater.class);
        mockCsvStellplatzUpdater = mock(ICsvUpdater.class);
        manager = new FahrzeugUmparkenManager(mockUpdateManager, mockCsvFahrzeugUpdater, mockCsvStellplatzUpdater);

        mockFahrzeug = mock(Fahrzeug.class);
        mockAlterStellplatz = mock(Stellplatz.class);
        mockNeuerStellplatz = mock(Stellplatz.class);

        when(mockFahrzeug.getId()).thenReturn("F123");
        when(mockNeuerStellplatz.getID()).thenReturn("N1");
        when(mockAlterStellplatz.getID()).thenReturn("A1");

        // Act
        manager.fahrzeugUmparken(mockFahrzeug, mockAlterStellplatz, mockNeuerStellplatz);

        // Assert
        verify(mockNeuerStellplatz).setFahrzeugID("F123");
        verify(mockAlterStellplatz).setFahrzeugID("");
        verify(mockFahrzeug).setStellPlatzID("N1");

        verify(mockUpdateManager).update(mockFahrzeug);
        verify(mockUpdateManager).update(mockAlterStellplatz);
        verify(mockUpdateManager).update(mockNeuerStellplatz);

        verify(mockCsvFahrzeugUpdater).eintragAendern(mockFahrzeug);
        verify(mockCsvStellplatzUpdater).eintragAendern(mockAlterStellplatz);
        verify(mockCsvStellplatzUpdater).eintragAendern(mockNeuerStellplatz);
    }
}
