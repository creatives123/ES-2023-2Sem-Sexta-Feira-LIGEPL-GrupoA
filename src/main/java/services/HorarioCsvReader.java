package services;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import Config.CSVConfig;

import java.io.*;
import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import models.Horario;

/**
 * A classe 'HorarioCsvReader' fornece funcionalidade para ler e processar um
 * ficheiro CSV contendo dados
 * relacionados com horários de cursos (`Horario` objects).
 * <p>
 * O ficheiro CSV deve ter as seguintes colunas, por ordem:
 * <p>
 * - Curso (nome do curso)
 * <p>
 * - Unidade Curricular (unidade curricular)
 * <p>
 * - Turno (turno)
 * <p>
 * - Turma (grupo de aula)
 * <p>
 * - Inscritos no turno (número de alunos inscritos no turno)
 * <p>
 * - Dia da semana (dia da semana)
 * <p>
 * - Hora início da aula (hora de início da aula)
 * <p>
 * - Hora fim da aula (hora de fim da aula)
 * <p>
 * - Data da aula (data da aula)
 * <p>
 * - Sala atribuída à aula (sala de aula atribuída)
 * <p>
 * - Lotação da sala (capacidade da sala de aula)
 * <p>
 * O delimitador usado no ficheiro CSV é o ponto e vírgula (;).
 * <p>
 * Esta classe não é possível ser instanciada, já que todos os seus métodos são
 * estáticos.
 */

public class HorarioCsvReader {

    private HorarioCsvReader() {}

    /**
     * Lê um fluxo de entrada de um ficheiro CSV contendo dados relacionados com
     * horários de cursos ({@link Horario} objects)
     * e cria uma lista de objetos {@link Horario} correspondentes.
     * <p>
     * O ficheiro CSV deve ter as seguintes colunas, por ordem:
     * <p>
     * - Curso (nome do curso) 
     * <p>
     * - Unidade Curricular (unidade curricular)
     * <p>
     * - Turno (turno)
     * <p>
     * - Turma (grupo de aula)
     * <p>
     * - Inscritos no turno (número de alunos inscritos no turno)
     * <p>
     * - Dia da semana (dia da semana)
     * <p>
     * - Hora início da aula (hora de início da aula)
     * <p>
     * - Hora fim da aula (hora de fim da aula)
     * <p>
     * - Data da aula (data da aula)
     * <p>
     * - Sala atribuída à aula (sala de aula atribuída)
     * <p>
     * - Lotação da sala (capacidade da sala de aula)
     * <p>
     * O delimitador usado no ficheiro CSV é o ponto e vírgula (;).
     *
     * @param inputStream o fluxo de entrada a ser lido
     * @return uma lista de objetos 'Horario' criados a partir do ficheiro CSV
     * @throws IOException            se ocorrer um erro de I/O ao ler o fluxo de
     *                                entrada
     * @throws CsvValidationException se ocorrer um erro ao validar o ficheiro CSV
     */
    public static List<Horario> processCsvStream(InputStream inputStream) throws IOException, CsvValidationException {
        List<Horario> horarios = new ArrayList<>();

        // Cria um leitor CSV usando o fluxo de entrada e o conjunto de caracteres UTF-8
        try (CSVReader reader = createCsvReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            processSchedules(horarios, reader);
        }

        if (horarios.isEmpty()) {
            throw new IOException("Ficheiro CSV sem nenhuma linha valida");
        }
        return horarios;
    }

    private static void processSchedules(List<Horario> horarios, CSVReader reader) throws IOException, CsvValidationException {
        String[] line;
        while ((line = reader.readNext()) != null) {
            Horario horario = createHorario(line);
            if (horario != null) {
                horarios.add(horario);
            }
        }
    }

    /**
     * Cria um objeto CSVReader para ler um arquivo CSV com separador ';'
     *
     * @param reader O objeto Reader que encapsula a stream de entrada do arquivo
     *               CSV
     * @return O objeto CSVReader configurado para ler o arquivo CSV com separador
     *         ';'
     */
    private static CSVReader createCsvReader(Reader reader) {
        CSVParser parser = new CSVParserBuilder().withSeparator(CSVConfig.SEPARATOR).build();
        return new CSVReaderBuilder(reader).withCSVParser(parser).build();
    }

    /**
     * Cria um objeto {@code Horario} com base nas informações passadas no array de
     * campos.
     *
     * @param fields um array de ‘Strings’ contendo os campos do horário
     * @return um objeto {@code Horario} criado com base nos campos passados ou null
     *         caso o array não tenha o tamanho correto, ou seja, o cabeçalho
     */

    private static Horario createHorario(String[] fields) {
        // Ignorar linhas vazias ou com campos em falta
        // Ignorar a primeira linha que contem os nomes dos campos
        if (fields.length != CSVConfig.HEADER_FIELDS.length || Arrays.equals(fields, CSVConfig.HEADER_FIELDS)) {
            return null; 
        }

        try {
            return new Horario(fields);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
