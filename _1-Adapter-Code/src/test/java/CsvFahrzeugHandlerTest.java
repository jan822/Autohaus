import de.autoverwaltung.adapter.csvhandler.CsvFahrzeugHandler;
import de.autoverwaltung.domain.fahrzeug.Auto;
import de.autoverwaltung.domain.fahrzeug.Autotyp;
import de.autoverwaltung.domain.fahrzeug.Waehrung;
import de.autoverwaltung.domain.gebaeude.Stellplatz;
import org.junit.Test;
import static org.junit.Assert.*;

public class CsvFahrzeugHandlerTest {

    @Test
    public void testBuildCsvLineAuto() {
        //Arrange
        CsvFahrzeugHandler handler = new CsvFahrzeugHandler();
        Auto auto = new Auto(
                "111",
                "666",
                "Volkswagen",
                "Golf",
                20000,
                Waehrung.EUR,
                50000,
                true,
                4,
                true,
                5,
                Autotyp.LIMOUSINE,
                380
        );
        //Act
        String csvLine = handler.buildCsvLine(auto);

        //Assert
        String expected = "111,666,Volkswagen,Golf,20000,EUR,50000,true,4,true,5,LIMOUSINE,380,";
        assertEquals(expected, csvLine);
    }
}
