package services;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;
import com.opencsv.ICSVWriter;

import Config.CSVConfig;
import models.Horario;

public class HorarioCsvWriter {

    // Construtor privado para impedir instanciação da classe
    private HorarioCsvWriter() {}

    /**
     * 
     * Converte uma lista de objetos Horario para um "array" de bytes em CSV.
     * <p>
     * @param horarios Lista de objetos Horario
     * @return "array" de bytes que contem os dados de CSV
     * @throws IOException Se ocorrer um erro de I/O
     */

    public static byte[] writeToCsv(List<Horario> horarios) {

        if (horarios == null) return new byte[0];

        // Criar StringWriter para escrever os dados CSV na memória
        StringWriter writer = new StringWriter();

        // Criar CSVWriter
        try (CSVWriter csvWriter = new CSVWriter(writer, ';', ICSVWriter.NO_QUOTE_CHARACTER,
                ICSVWriter.NO_ESCAPE_CHARACTER, ICSVWriter.DEFAULT_LINE_END)) {

            // Escrever a linha de cabeçalho
            csvWriter.writeNext(CSVConfig.HEADER_FIELDS);

            // Escrever os horarios
            processSchedules(horarios, csvWriter);

            // Limpar o CSVWriter para garantir que todos os dados sao escritos no
            // StringBuilder
            csvWriter.flush();

            // Devolver os dados CSV data como um "array" de bytes
            return writer.toString().getBytes(StandardCharsets.UTF_8);

        } catch (IOException | IllegalArgumentException e) {
            // No caso de ocorrer uma exceção, é impresso o erro na consola
            e.printStackTrace();
            return new byte[0];
        }
    }

    /**
     * @param horarios Lista de objetos Horario
     * @param csvWriter
     */
    private static void processSchedules(List<Horario> horarios, CSVWriter csvWriter) {
        for (Horario h : horarios) {
            addLine(h, csvWriter);
        }
    }

    /**
     * @param h Objecto Horario
     * @param csvWriter
     */
    private static void addLine(Horario h, CSVWriter csvWriter) {
        List<String> line = new ArrayList<>();
        addToListIfNotNull(line, h.getCurso());
        addToListIfNotNull(line, h.getUnidadeCurricular());
        addToListIfNotNull(line, h.getTurno());
        addToListIfNotNull(line, h.getTurma());
        addToListIfNotNull(line, Integer.toString(h.getInscritos()));
        addToListIfNotNull(line, h.getDiaSemana());
        addToListIfNotNull(line, h.getHoraInicio());
        addToListIfNotNull(line, h.getHoraFim());
        addToListIfNotNull(line, h.getDataAula());
        addToListIfNotNull(line, h.getSala());
        addToListIfNotNull(line, Integer.toString(h.getLotacao()));
        csvWriter.writeNext(line.toArray(new String[0]));
    }

    /**
     * @param list
     * @param value
     */
    private static void addToListIfNotNull(List<String> list, Object value) {
        if (value == null) {
            list.add(String.valueOf(CSVConfig.SEPARATOR));
        } else if (value instanceof List) {
            list.add(String.join(",", (List<String>) value));
        } else {
            list.add(value.toString());
        }
    }
}