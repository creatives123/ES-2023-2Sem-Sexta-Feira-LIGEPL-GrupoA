
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

/**
 * Classe responsável por testar os métodos da Classe {@link HorarioCsvReader}
 * <p>
 * Métodos:
 * <p>
 * {@link #testProcessCsvStream_EmptyFile()}
 * <p>
 * {@link #testProcessCsvStream__Input_Filled()}
 * <p>
 * {@link #testProcessCsvStream__Input_OneScheduleWell_OtherScheduleWithMorefieldsThanSupposed_Exception()}
 * <p>
 * {@link #testProcessCsvStream_Input_LettersOnNumbers_Fields_Need_To_NotAdd_Filled()}
 * <p>
 * {@link #testProcessCsvStream_Input_NoLines_Valid_Filled()}
 */

class HorarioCsvReaderTest {

    /**
     * Responsável por testar se o ficheiro está vazio.
     * Passa uma string vazia para simular uma situação
     * de haver um ficheiro que não tem nenhuma linha válida.
     * 
     * @throws IOException            Se ocorrer um erro de I/O.
     * @throws CsvValidationException Se ocorrer um erro a validar o ficheiro CSV.
     */
    @Test
    void testProcessCsvStream_EmptyFile() throws IOException, CsvValidationException {
        String inputCsv = "";
        InputStream inputStream = new ByteArrayInputStream(inputCsv.getBytes(StandardCharsets.UTF_8));

        // Vamos correr o método a ser testado
        IOException exception = Assertions.assertThrows(IOException.class,
                () -> HorarioCsvReader.processCsvStream(inputStream));
        Assertions.assertEquals("Ficheiro CSV sem nenhuma linha valida", exception.getMessage());
    }

    /**
     * Cria uma string para representar um ficheiro CSV.
     * Cria uma lista de {@link Horario}s com essa informação, 
     * usando {@link HorarioCSVReader} e verifica se foram criados 
     * o cabeçalho e a informacao de um {@link Horario} de acordo 
     * com o conteúdo da String. Finalmente compara o tamanho do 
     * que foi lido com o tamanho correspondente a uma linha 
     * válida da lista de {@link Horario}s
     * 
     * @throws IOException            Se ocorrer um erro de I/O.
     * @throws CsvValidationException Se ocorrer um erro a validar o ficheiro CSV.
     */
    @Test
    void testProcessCsvStream__Input_Filled() throws IOException, CsvValidationException {
        String inputCsv = "Curso;Unidade Curricular;Turno;Turma;Inscritos no turno;Dia da semana;Hora início da aula;Hora fim da aula;Data da aula;Sala atribuída à aula;Lotação da sala\n"
                + "LEI;Programacao;T1;1;50;Segunda-feira;09:00;11:00;2022-01-01;B102;60\n";

        InputStream inputStream = new ByteArrayInputStream(inputCsv.getBytes(StandardCharsets.UTF_8));

        List<Horario> horarios = HorarioCsvReader.processCsvStream(inputStream);

        Assertions.assertEquals(1, horarios.size());
    }

    /**
     * Cria uma string para representar um ficheiro CSV.
     * Uma string correta e outro string com mais campos do que necessario
     * Cria uma lista de {@link Horario}s com essa informação, 
     * usando {@link HorarioCSVReader} e verifica se foi criada 
     * a informacao pretendida de um {@link Horario} de acordo 
     * com o conteúdo da String. Finalmente compara o tamanho do 
     * que foi lido com o tamanho correspondente a uma linha 
     * válida da lista de {@link Horario}s
     * 
     * @throws IOException            Se ocorrer um erro de I/O.
     * @throws CsvValidationException Se ocorrer um erro a validar o ficheiro CSV.
     */

    @Test
    void testProcessCsvStream__Input_OneScheduleWell_OtherScheduleWithMorefieldsThanSupposed_Exception()
            throws IOException, CsvValidationException {
        String inputCsv = "LEI;Programacao;T1;1;50;Segunda-feira;09:00;11:00;2022-01-01;B102;60;20;80;50\n"
                + "LEI;Programacao;T1;1;50;Segunda-feira;09:00;11:00;2022-01-01;B102;60\n";

        InputStream inputStream = new ByteArrayInputStream(inputCsv.getBytes(StandardCharsets.UTF_8));
        List<Horario> horarios = HorarioCsvReader.processCsvStream(inputStream);

        Assertions.assertEquals(1, horarios.size());
    }

    /**
     * Cria uma string para representar um ficheiro CSV.
     * Passa uma string onde um dos campos sao letras inves
     * de numeros para simular uma situacao de haver um ficheiro 
     * que nao tem nenhuma linha valida.
     * Cria uma lista de {@link Horario}s com essa informação, 
     * usando {@link HorarioCSVReader} e verifica se foi criada 
     * a informacao pretendida de um {@link Horario} de acordo 
     * com o conteúdo da String. Finalmente compara o tamanho do 
     * que foi lido com o tamanho correspondente a uma linha 
     * válida da lista de {@link Horario}s
     * 
     * @throws IOException            Se ocorrer um erro de I/O.
     * @throws CsvValidationException Se ocorrer um erro a validar o ficheiro CSV.
     */

    @Test
    void testProcessCsvStream_Input_LettersOnNumbers_Fields_Need_To_NotAdd_Filled()
            throws IOException, CsvValidationException {
        String inputCsv = "Curso;Unidade Curricular;Turno;Turma;Inscritos no turno;Dia da semana;Hora início da aula;Hora fim da aula;Data da aula;Sala atribuída à aula;Lotação da sala\n"
                + "LEI;Programacao;T1;bb;a;Segunda-feira;09:00;11:00;2022-01-01;B102;aaaa\n"
                + "LEI;Programacao;T1;1;50;Segunda-feira;09:00;11:00;2022-01-01;B102;60\n";

        InputStream inputStream = new ByteArrayInputStream(inputCsv.getBytes(StandardCharsets.UTF_8));

        List<Horario> horarios = HorarioCsvReader.processCsvStream(inputStream);

        Assertions.assertEquals(1, horarios.size());
    }

     /**
     * Cria uma string para representar um ficheiro CSV.
     * Passa uma string invalida para simular uma situacao
     * de haver um ficheiro que nao tem nenhuma linha valida.
     *
     * @throws IOException            Se ocorrer um erro de I/O.
     * @throws CsvValidationException Se ocorrer um erro a validar o ficheiro CSV.
     */

    @Test
    void testProcessCsvStream_Input_NoLines_Valid_Filled() throws IOException, CsvValidationException {
        String inputCsv = "Curso;Unidade Curricular;Turno;Turma;Inscritos no turno;Dia da semana;Hora início da aula;Hora fim da aula;Data da aula;Sala atribuída à aula;Lotação da sala\n"
                + "LEI;Programacao;T1;bb;a;Segunda-feira;09:00;11:00;2022-01-01;B102;aaaa\n";

        InputStream inputStream = new ByteArrayInputStream(inputCsv.getBytes(StandardCharsets.UTF_8));

        // Vamos correr o método a ser testado
        IOException exception = Assertions.assertThrows(IOException.class,
                () -> HorarioCsvReader.processCsvStream(inputStream));
        Assertions.assertEquals("Ficheiro CSV sem nenhuma linha valida", exception.getMessage());
    }
}