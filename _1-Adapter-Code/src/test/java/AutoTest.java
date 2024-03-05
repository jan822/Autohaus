import de.autoverwaltung.domain.fahrzeug.Auto;
import de.autoverwaltung.domain.fahrzeug.Betrag;
import de.autoverwaltung.domain.fahrzeug.Waehrung;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.testng.AssertJUnit.assertEquals;

public class AutoTest {

    @Test
    public void test_auto_constructor() {
        // Arrange
        String titel = "Test Auto";
        String id = String.valueOf(UUID.randomUUID());
        //Act
        Auto auto = new Auto(id, "Mercedes", "C-Klasse", 6000, Waehrung.EUR, 10000, true);
        String actual = auto.getMarke();
        //Assert
        AssertJUnit.assertEquals(actual, "Mercedes");
    }

    @Test
    public void test_betrag_constructor() {
        // Arrange
        String titel = "Test Betrag";
        String id = String.valueOf(UUID.randomUUID());
        //Act
        Auto auto = new Auto(id, "Mercedes", "C-Klasse", 6000, Waehrung.EUR, 10000, true);
        Betrag betrag = new Betrag(6000, Waehrung.EUR);
        //Assert
        AssertJUnit.assertEquals(betrag.toString(), auto.getBetrag().toString());
    }
}
