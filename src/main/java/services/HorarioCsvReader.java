package services;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Models.Horario;

public class HorarioCsvReader {

    public static List<Horario> processCsvStream(InputStream inputStream) throws IOException, CsvValidationException {
        List<Horario> horarios = new ArrayList<>();

        try (CSVReader reader = createCsvReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String[] line;

            while ((line = reader.readNext()) != null) {
                Horario horario = createHorario(line);
                if (horario != null) {
                    horarios.add(horario);
                }
            }
        }
        if (horarios.isEmpty()){
            throw new IOException("Ficheiro CSV sem nenhuma linha valida");
        }
        return horarios;
    }

    private static CSVReader createCsvReader(Reader reader) {
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
        CSVReaderBuilder builder = new CSVReaderBuilder(reader)
                .withCSVParser(parser);
        return builder.build();
    }

    private static Horario createHorario(String[] fields) {
        if (fields.length != HEADER_FIELDS.length) {
            return null; // skip empty lines
        }

        // check if header row
        if (Arrays.equals(fields, HEADER_FIELDS)) {
            return null; // skip header row
        }
        List<String> curso = Arrays.asList(fields[0].split(","));
        String uc = fields[1];
        String turno = fields[2];
        List<String> turma = Arrays.asList(fields[3].split(","));
        int inscritosTurno = fields[4].isEmpty() ? 0 : Integer.parseInt(fields[4]);
        String diaSemana = fields[5];
        String horaInicio = fields[6];
        String horaFim = fields[7];
        String dataAula = fields[8];
        String sala = fields[9];
        int capacidade = fields[10].isEmpty() ? 0 : Integer.parseInt(fields[10]);

        return new Horario.Builder(
            curso,
            uc,
            turno,
            turma,
            inscritosTurno,
            diaSemana,
            horaInicio,
            horaFim,
            dataAula,
            sala,
            capacidade).build();
    }

    private static final String[] HEADER_FIELDS = {"Curso", "Unidade Curricular", "Turno", "Turma", "Inscritos no turno",
            "Dia da semana", "Hora início da aula", "Hora fim da aula", "Data da aula", "Sala atribuída à aula", "Lotação da sala"};
}
