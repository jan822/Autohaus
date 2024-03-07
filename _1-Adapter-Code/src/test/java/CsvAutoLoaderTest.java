import de.autoverwaltung.adapter.csvloader.CsvAutoLoader;
import de.autoverwaltung.adapter.csvloader.LoaderUtils;
import de.autoverwaltung.domain.IEinzigartig;
import de.autoverwaltung.domain.fahrzeug.Auto;
import de.autoverwaltung.domain.fahrzeug.Motorrad;
import de.autoverwaltung.domain.fahrzeug.MotorradTyp;
import de.autoverwaltung.domain.fahrzeug.Waehrung;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvAutoLoaderTest {

    private File tempFile;
    private CsvAutoLoader loader;


    @Test
    public void testParseAuto() throws IOException {
        //Arange
        tempFile = File.createTempFile("testAuto", ".csv");
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        writer.write("ID,stellPlatzID,Marke,Modell,Preis,Waehrung,Kilometer,TUEV,AnzahlTueren,Navigationssystem,AnzahlSitzplaetze,Autotyp,Kofferraumvolumen\n");
        writer.write("2a4fbc9a-3ee5-44a1-a5cd-42e45dbf741d,157b4892-67de-419f-9ea9-43437b357a48,Volkswagen,Golf,20000,EUR,50000,true,4,true,5,LIMOUSINE,380\n");
        writer.close();

        //Act
        loader = new CsvAutoLoader();
        List<IEinzigartig> autos = LoaderUtils.load(tempFile.getAbsolutePath(), new CsvAutoLoader());
        assertNotNull("Liste sollte nicht null sein", autos);
        assertFalse("Liste sollte nicht leer sein", autos.isEmpty());
        Auto auto = (Auto) autos.get(0);

        //Assert
        assertEquals("2a4fbc9a-3ee5-44a1-a5cd-42e45dbf741d", auto.getID());

        if (tempFile.exists()) {
            tempFile.delete();
        }
    }
}

