import de.autoverwaltung.adapter.csvloader.CsvGebaeudeLoader;
import de.autoverwaltung.domain.IEinzigartig;
import de.autoverwaltung.domain.gebaeude.Gebaeude;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvGebaudeLoaderTest {
    private File tempFile;
    private CsvGebaeudeLoader loader;

    @Test
    public void testParseGebaeude() throws IOException {
        //Arrange
        tempFile = File.createTempFile("testGebaude", ".csv");
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        writer.write("id,name,stellplatzKapazitaet\n");
        writer.write("8b157a6c-87b6-4847-9882-ed70ce2919f2,Gebäude 1,10\n");
        writer.close();

        //Act
        loader = new CsvGebaeudeLoader(tempFile.getAbsolutePath());
        List<IEinzigartig> gebaeude = loader.loadGebaeude();
        assertNotNull("Liste sollte nicht null sein", gebaeude);
        assertFalse("Liste sollte nicht leer sein", gebaeude.isEmpty());
        Gebaeude gebaeud = (Gebaeude) gebaeude.get(0);

        //Assert
        assertEquals("8b157a6c-87b6-4847-9882-ed70ce2919f2", gebaeud.getID());

        if (tempFile.exists()) {
            tempFile.delete();
        }
    }
}
