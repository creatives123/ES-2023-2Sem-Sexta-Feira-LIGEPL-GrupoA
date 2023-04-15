package services;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.opencsv.CSVWriter;

import models.Horario;

public class HorarioCsvWriter {
    
    public static byte[] writeToCsv(List<Horario> horarios) {
        // Create a StringWriter to write the CSV data to memory
        StringWriter writer = new StringWriter();
    
        // Create a CSVWriter with default settings
        CSVWriter csvWriter = new CSVWriter(writer);
        try {
            // Write the header row
            csvWriter.writeNext(HorarioCsvReader.HEADER_FIELDS);
    
            // Write each object as a row
            for (Horario h : horarios) {
                csvWriter.writeNext(new String[]{h.getCurso().toString().replaceAll("[\\[\\]]", ""), h.getUnidadeCurricular(), h.getTurno(), h.getTurma().toString().replaceAll("[\\[\\]]", ""), Integer.toString(h.getInscritos()), h.getDiaSemana(), h.getHoraInicio(), h.getHoraFim(), h.getDataAula(), h.getSala(), Integer.toString(h.getLotacao())});
            }
    
            // Flush the CSVWriter to ensure all data is written to the StringWriter
            csvWriter.flush();
    
            // Return the CSV data as a byte array
            return writer.toString().getBytes(StandardCharsets.UTF_8);
        } catch (IOException e) {
            // In case of exception, print the stack trace and return an empty byte array
            e.printStackTrace();
            return new byte[0];
        } finally {
            // Close the CSVWriter and StringWriter to release resources
            try {
                csvWriter.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
