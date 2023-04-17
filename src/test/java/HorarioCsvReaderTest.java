
import com.opencsv.exceptions.CsvValidationException;

import models.Horario;
import services.HorarioCsvReader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

class HorarioCsvReaderTest {

    @Test
    void testProcessCsvStream_EmptyFile() throws IOException, CsvValidationException {
        String inputCsv = "";
        InputStream inputStream = new ByteArrayInputStream(inputCsv.getBytes(StandardCharsets.UTF_8));

        // Vamos correr o método a ser testado
        IOException exception = Assertions.assertThrows(IOException.class, () -> HorarioCsvReader.processCsvStream(inputStream));
        Assertions.assertEquals("Ficheiro CSV sem nenhuma linha valida", exception.getMessage());
    }

    @Test
    void testProcessCsvStream__Input_Filled() throws IOException, CsvValidationException {
        String inputCsv = "Curso;Unidade Curricular;Turno;Turma;Inscritos no turno;Dia da semana;Hora início da aula;Hora fim da aula;Data da aula;Sala atribuída à aula;Lotação da sala\n" 
                            + "LEI;Programacao;T1;1;50;Segunda-feira;09:00;11:00;2022-01-01;B102;60\n";

        InputStream inputStream = new ByteArrayInputStream(inputCsv.getBytes(StandardCharsets.UTF_8));

        List<Horario> horarios = HorarioCsvReader.processCsvStream(inputStream);

        Assertions.assertEquals(horarios.size(), 1);
    }

    @Test
    void testProcessCsvStream__Input_OneScheduleWell_OtherScheduleWithMorefieldsThanSupposed_Exception() throws IOException, CsvValidationException {
        String inputCsv = "LEI;Programacao;T1;1;50;Segunda-feira;09:00;11:00;2022-01-01;B102;60;20;80;50\n" 
        + "LEI;Programacao;T1;1;50;Segunda-feira;09:00;11:00;2022-01-01;B102;60\n";

        InputStream inputStream = new ByteArrayInputStream(inputCsv.getBytes(StandardCharsets.UTF_8));
        List<Horario> horarios = HorarioCsvReader.processCsvStream(inputStream);

        Assertions.assertEquals(1, horarios.size());
    }


    @Test
    void testProcessCsvStream_Input_LettersOnNumbers_Fields_Need_To_NotAdd_Filled() throws IOException, CsvValidationException {
        String inputCsv = "Curso;Unidade Curricular;Turno;Turma;Inscritos no turno;Dia da semana;Hora início da aula;Hora fim da aula;Data da aula;Sala atribuída à aula;Lotação da sala\n" 
                            + "LEI;Programacao;T1;bb;a;Segunda-feira;09:00;11:00;2022-01-01;B102;aaaa\n"
                            + "LEI;Programacao;T1;1;50;Segunda-feira;09:00;11:00;2022-01-01;B102;60\n";

        InputStream inputStream = new ByteArrayInputStream(inputCsv.getBytes(StandardCharsets.UTF_8));

         List<Horario> horarios = HorarioCsvReader.processCsvStream(inputStream);
         
         Assertions.assertEquals(1, horarios.size());
    }

    @Test
    void testProcessCsvStream_Input_NoLines_Valid_Filled() throws IOException, CsvValidationException {
        String inputCsv = "Curso;Unidade Curricular;Turno;Turma;Inscritos no turno;Dia da semana;Hora início da aula;Hora fim da aula;Data da aula;Sala atribuída à aula;Lotação da sala\n" 
                            + "LEI;Programacao;T1;bb;a;Segunda-feira;09:00;11:00;2022-01-01;B102;aaaa\n";

        InputStream inputStream = new ByteArrayInputStream(inputCsv.getBytes(StandardCharsets.UTF_8));

       // Vamos correr o método a ser testado
       IOException exception = Assertions.assertThrows(IOException.class, () -> HorarioCsvReader.processCsvStream(inputStream));
       Assertions.assertEquals("Ficheiro CSV sem nenhuma linha valida", exception.getMessage());
    }
}