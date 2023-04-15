package services;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.opencsv.CSVWriter;
import com.opencsv.ICSVWriter;

import models.Horario;

public class HorarioCsvWriter {

    // Construtor privado para impedir instanciação da classe
    private HorarioCsvWriter() {
    }

    /**
     * 
     * Converte uma lista de objetos Horario para um "array" de bytes em CSV.
     * <p>
     * @param horarios Lista de objetos Horario
     * @return "array" de bytes que contem os dados de CSV
     * @throws IOException Se ocorrer um erro de I/O
     */

    public static byte[] writeToCsv(List<Horario> horarios) {

        // Criar StringWriter para escrever os dados CSV na memória
        StringWriter writer = new StringWriter();

        // Criar CSVWriter
        try (CSVWriter csvWriter = new CSVWriter(writer, ';', ICSVWriter.NO_QUOTE_CHARACTER,
                ICSVWriter.NO_ESCAPE_CHARACTER, ICSVWriter.DEFAULT_LINE_END)) {
            // Escrever a linha de cabeçalho
            csvWriter.writeNext(HorarioCsvReader.HEADER_FIELDS);

            // Escrever cada objeto como uma linha
            horarios.forEach(h -> csvWriter.writeNext(new String[] { h.getCurso().toString().replaceAll("[\\[\\]]", ""),
                    h.getUnidadeCurricular(), h.getTurno(), h.getTurma().toString().replaceAll("[\\[\\]]", ""),
                    Integer.toString(h.getInscritos()), h.getDiaSemana(), h.getHoraInicio(), h.getHoraFim(),
                    h.getDataAula(), h.getSala(), Integer.toString(h.getLotacao()) }));

            // Limpar o CSVWriter para garantir que todos os dados sao escritos no
            // StringBuilder
            csvWriter.flush();

            // Devolver os dados CSV data como um "array" de bytes
            return writer.toString().getBytes(StandardCharsets.UTF_8);

        } catch (IOException e) {
            // No caso de ocorrer uma exceção, é impresso o erro na consola
            e.printStackTrace();
            return new byte[0];
        }
    }

}
