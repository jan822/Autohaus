import de.autoverwaltung.domain.fahrzeug.Auto;
import de.autoverwaltung.domain.fahrzeug.Waehrung;
import de.autoverwaltung.domain.gebaeude.Gebaeude;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.util.UUID;

public class GebaeudeTest {
    @Test
    public void test_gebaeude_constructor() {
        //Arrange
        String id = String.valueOf(UUID.randomUUID());
        //Act
        Gebaeude gebaeude = new Gebaeude(id, "Gebäude", 10);
        String actual = gebaeude.getName();
        //Assert
        AssertJUnit.assertEquals(actual, "Gebäude");
    }
}
