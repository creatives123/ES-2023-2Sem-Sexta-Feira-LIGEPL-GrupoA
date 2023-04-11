import java.io.IOException;
import java.util.List;

import Models.Horario;
import services.HorarioCsvReader;
import services.HorarioJsonWriter;
import services.HorarioJSONReader;

public class main{
    public static void main(String[] args) throws IOException {
         // Path to the CSV file
        String filePath = "src/main/resources/horario_exemplo.csv";
        

        // Read the CSV file and create a list of Horario objects
        List<Horario> horarios = HorarioCsvReader.readCsv(filePath);

        // Save the file to JSON
        HorarioJsonWriter.writeToJson(horarios);
        filePath = "src/main/resources/test_one.json";

        // Read the JSON file and create list of Horario objects
        List<Horario> horariosFromJSON = HorarioJSONReader.readJSON(filePath);

        System.out.println(horariosFromJSON);
    

    }
}
