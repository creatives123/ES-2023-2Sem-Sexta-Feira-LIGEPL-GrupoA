import java.io.IOException;
import java.util.List;

import Models.Horario;

public class main{
    public static void main(String[] args) throws IOException {
         // Path to the CSV file
        String filePath = "src/main/resources/horario_exemplo.csv";

        // Read the CSV file and create a list of Horario objects
        List<Horario> horarios = HorarioCsvReader.readCsv(filePath);

        // Print the list of Horario objects
        for (Horario horario : horarios) {
            System.out.println(horario);
        }

    }
}
