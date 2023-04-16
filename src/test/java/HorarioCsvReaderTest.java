
import com.opencsv.exceptions.CsvValidationException;
import services.HorarioCsvReader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

class HorarioCsvReaderTest {

    @Test
    void testProcessCsvStream_EmptyFile() throws IOException, CsvValidationException {
        String inputCsv = "";
        InputStream inputStream = new ByteArrayInputStream(inputCsv.getBytes(StandardCharsets.UTF_8));

        // Vamos correr o método a ser testado
        IOException exception = Assertions.assertThrows(IOException.class, () -> HorarioCsvReader.processCsvStream(inputStream));
        Assertions.assertEquals("Ficheiro CSV sem nenhuma linha valida", exception.getMessage());
    }
}