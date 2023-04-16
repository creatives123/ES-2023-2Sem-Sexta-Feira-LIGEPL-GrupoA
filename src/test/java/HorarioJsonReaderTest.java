import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import models.Horario;
import services.HorarioJSONReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

class HorarioJsonReaderTest {

    @Test
     void testProcessJsonStream() throws IOException {
        // vamos adicionar só horas de inicio e fim
        String inputJson = "[{\"horaInicio\":\"08:00\",\"horaFim\":\"09:00\"},{\"horaInicio\":\"10:00\",\"horaFim\":\"11:00\"},{\"horaInicio\":\"12:00\",\"horaFim\":\"13:00\"}]";
        try (InputStream inputStream = new ByteArrayInputStream(inputJson.getBytes(StandardCharsets.UTF_8))) {
            // Metodo a ser testado
            List<Horario> horarios = HorarioJSONReader.processJsonStream(inputStream);

            Assertions.assertEquals(3, horarios.size());

            // Vamos validar se existe ou não aqueles dois campos.
            for (Horario horario : horarios) {
                Assertions.assertNotNull(horario);
                Assertions.assertNotNull(horario.getHoraInicio());
                Assertions.assertNotNull(horario.getHoraFim());
            }
        }
    }
}
