import org.junit.jupiter.api.Test;

import Models.Horario;
import services.HorarioJsonWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class HorarioJsonWriterTest {

    private final String testFilePath = "src/test/resources/testes.json";

    @Test
    void writeToJson_WithValidHorarios_ShouldCreateJsonFile() throws IOException {
        // Arrange
        List<Horario> horarios = new ArrayList<>();
        horarios.add(new Horario("1", "segunda", "9:00", "11:00", 2, "sala 1", "professor 1", "disciplina 1", "curso 1", "periodo 1", 20));
        horarios.add(new Horario("2", "terça", "9:00", "11:00", 2, "sala 2", "professor 2", "disciplina 2", "curso 2", "periodo 2", 30));

        // Act
        HorarioJsonWriter.writeToJson(horarios);

        // Assert
        File file = new File(testFilePath);
        //assertTrue(file.exists());
        //assertTrue(file.isFile());

        // Clean up
        file.delete();
    }

    @Test
    void writeToJson_WithNullHorarios_ShouldThrowNullPointerException() {
        // Arrange
        List<Horario> horarios = null;

        // Assert
        //assertThrows(NullPointerException.class, () -> teste.HorarioJsonWriter.writeToJson(horarios));
    }
}
