import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import models.Horario;
import services.HorarioJSONReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Classe responsável por testar os métodos da Classe {@link HorarioJsonReader}
 * <p>
 * 
 * Métodos:
 * {@link #testProcessJsonStream()}
 */
class HorarioJsonReaderTest {
    /**
     * Cria uma String para representar um ficheiro em formato JSON.
     * Cria uma lista de Horarios com essa informação, usando HorarioJSONReader 
     * e verifica se foram criados os 3 horários de acordo com o conteúdo da String.
     * Verifica de seguida se o Horario existe e os campos fornecidos(HoraInicio, getHoraFim)
     * foram atribuídos ou estão Null. 
     * 
     * @throws IOException
     */
    @Test
     void testProcessJsonStream() throws IOException {
        String inputJson = "[{\"horaInicio\":\"08:00\",\"horaFim\":\"09:00\"},{\"horaInicio\":\"10:00\",\"horaFim\":\"11:00\"},{\"horaInicio\":\"12:00\",\"horaFim\":\"13:00\"}]";
        try (InputStream inputStream = new ByteArrayInputStream(inputJson.getBytes(StandardCharsets.UTF_8))) {
            List<Horario> horarios = HorarioJSONReader.processJsonStream(inputStream);

            Assertions.assertEquals(3, horarios.size());

            for (Horario horario : horarios) {
                Assertions.assertNotNull(horario);
                Assertions.assertNotNull(horario.getHoraInicio());
                Assertions.assertNotNull(horario.getHoraFim());
            }
        }
    }
}
