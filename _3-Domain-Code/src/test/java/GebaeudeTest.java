import de.autoverwaltung.domain.fahrzeug.Auto;
import de.autoverwaltung.domain.fahrzeug.Waehrung;
import de.autoverwaltung.domain.gebaeude.Gebaeude;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.testng.AssertJUnit.assertEquals;

public class GebaeudeTest {
    @Test
    public void test_gebaeude_constructor() {
        //Arrange
        String id = String.valueOf(UUID.randomUUID());
        //Act
        Gebaeude gebaeude = new Gebaeude(id, "Gebäude", 10);
        String actual = gebaeude.getName();
        //Assert
        assertEquals(actual, "Gebäude");
    }
}
