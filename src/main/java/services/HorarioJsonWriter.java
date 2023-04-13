package services;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import models.Horario;

public class HorarioJsonWriter {

    //private static String filePath = "src/main/resources/teste.json";

    public static byte[] writeToJson(List<Horario> horarios) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            //mapper.writeValue(new File(filePath), horarios);

            // Nós queremos que gere um ficheiro para fazer download na pagina
            // para isso precisamos que retorne os bytes e depois do
            // lado da aplicação converte em ficehiro para download
            return mapper.writeValueAsBytes(horarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}
