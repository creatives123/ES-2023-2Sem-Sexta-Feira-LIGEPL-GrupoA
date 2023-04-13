import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import models.Horario;
import services.HorarioJsonWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class HorarioJsonWriterTest {

    private final String testFilePath = "src/test/resources/testes.json";

    @Test
    void writeToJson_WithValidHorarios_ShouldCreateJsonFile() throws IOException {
        // Arrange
        List<Horario> horarios = new ArrayList<Horario>();
        horarios.add(new Horario(new String[] { "curso 1", "unidadeCurricular 1", "turno 1", "turma 1", "20", "segunda",
                "9:00", "11:00", "dataAula 1", "sala 1", "30" }));
        horarios.add(new Horario(new String[] { "curso 2", "unidadeCurricular 2", "turno 2", "turma 2", "30", "terça",
                "11:00", "13:00", "dataAula 2", "sala 2", "40" }));
        String expectedJson = "[{\"curso\":[\"curso 1\"],\"unidadeCurricular\":\"unidadeCurricular 1\",\"turno\":\"turno 1\",\"turma\":[\"turma 1\"],\"inscritos\":20,\"diaSemana\":\"segunda\",\"horaInicio\":\"9:00\",\"horaFim\":\"11:00\",\"dataAula\":\"dataAula 1\",\"sala\":\"sala 1\",\"lotacao\":30},{\"curso\":[\"curso 2\"],\"unidadeCurricular\":\"unidadeCurricular 2\",\"turno\":\"turno 2\",\"turma\":[\"turma 2\"],\"inscritos\":30,\"diaSemana\":\"terça\",\"horaInicio\":\"11:00\",\"horaFim\":\"13:00\",\"dataAula\":\"dataAula 2\",\"sala\":\"sala 2\",\"lotacao\":40}]";

        // Act
        HorarioJsonWriter.writeToJson(horarios);

        // Assert
        byte[] actualBytes = HorarioJsonWriter.writeToJson(horarios);
        String actualJson = new String(actualBytes);
        assertEquals(expectedJson, actualJson);

        // Write bytes to file
        FileOutputStream outputStream = new FileOutputStream(testFilePath);
        outputStream.write(actualBytes);
        outputStream.close();

        // Read file contents
        String fileContents = new String(Files.readAllBytes(Paths.get(testFilePath)));
        assertEquals(expectedJson, fileContents);

    }

    @Test
    void writeToJson_WithNullHorarios_ShouldThrowNullPointerException() {
        // Arrange
        List<Horario> horarios = null;

        // Assert
        assertThrows(NullPointerException.class, () -> HorarioJsonWriter.writeToJson(horarios));
    }

}
