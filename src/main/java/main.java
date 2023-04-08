import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.List;

import Models.Horario;

public class main{
    public static void main(String[] args) throws IOException {
         // Path to the CSV file
        String filePath = "src/main/resources/horario-exemplo.csv";

        // Read the CSV file and create a list of Horario objects
        List<Horario> horarios = HorarioCsvReader.readCsv(filePath);

        // Print the list of Horario objects
        for (Horario horario : horarios) {
            System.out.println(horario);
        }
    }
}
