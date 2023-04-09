import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Models.Horario;

public class HorarioCsvReader {
    public static List<Horario> readCsv(String filePath) {
        List<Horario> horarios = new ArrayList<>();

            try {
                CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
                CSVReader reader = new CSVReaderBuilder(new FileReader(filePath))
                    .withCSVParser(parser)
                    .withSkipLines(1)
                    .build();
            reader.skip(2);
            String[] fields;

            while ((fields = reader.readNext()) != null) {
                Horario horario = new Horario(
                    fields[0],
                    fields[1],
                    fields[2],
                    fields[3],
                    fields[4].isEmpty() ? 0 : Integer.parseInt(fields[4]),
                    fields[5],
                    fields[6],
                    fields[7],
                    fields[8],
                    fields[9],
                    fields[10].isEmpty() ? 0 : Integer.parseInt(fields[10])
                );

                horarios.add(horario);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return horarios;
    }
}
