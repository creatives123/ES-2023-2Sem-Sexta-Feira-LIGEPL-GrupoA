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

/**
 * Responsável por converter {@link Horario}s existentes
 * num ficheiro CSV para exportação.
 */
public class HorarioCsvWriter {

    // Construtor privado para impedir instanciação da classe
    private HorarioCsvWriter() {
    }

    /**
     * 
     * Converte uma lista de objetos {@link Horario} para um "array" de bytes em
     * CSV.
     * <p>
     * 
     * @param horarios Lista de objetos {@link Horario}
     * @return "array" de bytes que contem os dados de CSV
     */
    public static byte[] writeToCsv(List<Horario> horarios) {

        if (horarios == null)
            return new byte[0];
        StringWriter writer = new StringWriter();

        try (CSVWriter csvWriter = new CSVWriter(writer, ';', ICSVWriter.NO_QUOTE_CHARACTER,
                ICSVWriter.NO_ESCAPE_CHARACTER, ICSVWriter.DEFAULT_LINE_END)) {
            csvWriter.writeNext(CSVConfig.HEADER_FIELDS);
            processSchedules(horarios, csvWriter);
            csvWriter.flush();
            return writer.toString().getBytes(StandardCharsets.UTF_8);
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    /**
     * Adiciona uma lista de {@link Horario}s a um {@link CSVWriter} linha a linha
     * 
     * @param horarios  Lista de objetos Horario
     * @param csvWriter Objeto CSVWriter
     */
    private static void processSchedules(List<Horario> horarios, CSVWriter csvWriter) {
        for (Horario h : horarios) {
            addLine(h, csvWriter);
        }
    }

    /**
     * Adiciona um objeto {@link Horario} a um {@link CSVWriter} dado
     * 
     * @param h         Objecto Horario
     * @param csvWriter Objeto CSVWriter
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
     * @param list  Lista de valores a serem adicionados
     * @param value Valor a ser adicionado na lista
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
