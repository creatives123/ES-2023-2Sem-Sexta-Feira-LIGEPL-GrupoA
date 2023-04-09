package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

import Models.Horario;

public class HorarioJsonWriter {

    private static String filePath = "src/main/resources/teste.json"; 

    public static void writeToJson(List<Horario> horarios) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(new File(filePath), horarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
