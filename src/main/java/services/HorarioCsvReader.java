package services;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Models.Horario;

/**
 * A classe `HorarioCsvReader` fornece funcionalidade para ler e processar um
 * ficheiro CSV contendo dados
 * relacionados com horários de cursos (`Horario` objects).
 * <p>
 * O ficheiro CSV deve ter as seguintes colunas, por ordem:
 * - Curso (nome do curso)
 * - Unidade Curricular (unidade curricular)
 * - Turno (turno)
 * - Turma (grupo de aula)
 * - Inscritos no turno (número de alunos inscritos no turno)
 * - Dia da semana (dia da semana)
 * - Hora início da aula (hora de início da aula)
 * - Hora fim da aula (hora de fim da aula)
 * - Data da aula (data da aula)
 * - Sala atribuída à aula (sala de aula atribuída)
 * - Lotação da sala (capacidade da sala de aula)
 * <p>
 * O delimitador usado no ficheiro CSV é o ponto e vírgula (;).
 * <p>
 * Esta classe não deve ser instanciada, já que todos os seus métodos são
 * estáticos.
 */

public class HorarioCsvReader {

    private HorarioCsvReader() {
    }

    /**
     * Lê um fluxo de entrada de um ficheiro CSV contendo dados relacionados com
     * horários de cursos (`Horario` objects)
     * e cria uma lista de objetos `Horario` correspondentes.
     *
     * O ficheiro CSV deve ter as seguintes colunas, por ordem:
     * - Curso (nome do curso)
     * - Unidade Curricular (unidade curricular)
     * - Turno (turno)
     * - Turma (grupo de aula)
     * - Inscritos no turno (número de alunos inscritos no turno)
     * - Dia da semana (dia da semana)
     * - Hora início da aula (hora de início da aula)
     * - Hora fim da aula (hora de fim da aula)
     * - Data da aula (data da aula)
     * - Sala atribuída à aula (sala de aula atribuída)
     * - Lotação da sala (capacidade da sala de aula)
     *
     * O delimitador usado no ficheiro CSV é o ponto e vírgula (;).
     *
     * @param inputStream o fluxo de entrada a ser lido
     * @return uma lista de objetos `Horario` criados a partir do ficheiro CSV
     * @throws IOException            se ocorrer um erro de I/O ao ler o fluxo de
     *                                entrada
     * @throws CsvValidationException se ocorrer um erro ao validar o ficheiro CSV
     */
    public static List<Horario> processCsvStream(InputStream inputStream) throws IOException, CsvValidationException {
        // Cria uma lista vazia para armazenar os horários lidos do ficheiro CSV
        List<Horario> horarios = new ArrayList<>();

        // Cria um leitor CSV usando o fluxo de entrada e o conjunto de caracteres UTF-8
        try (CSVReader reader = createCsvReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String[] line;

            // Lê cada linha do ficheiro CSV até que não haja mais linhas
            while ((line = reader.readNext()) != null) {
                // Cria um objeto `Horario` com os dados da linha atual do ficheiro CSV
                Horario horario = createHorario(line);
                // Adiciona o objeto `Horario` à lista se ele for válido (não nulo)
                if (horario != null) {
                    horarios.add(horario);
                }
            }
        }

        // Verifica se a lista de horários está vazia
        if (horarios.isEmpty()) {
            // Se a lista estiver vazia, lança uma exceção
            throw new IOException("Ficheiro CSV sem nenhuma linha valida");
        }

        // Retorna a lista de horários
        return horarios;
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
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
        CSVReaderBuilder builder = new CSVReaderBuilder(reader)
                .withCSVParser(parser);
        return builder.build();
    }

    /**
     * Cria um objeto {@code Horario} com base nas informações passadas no array de
     * campos.
     * 
     * @param fields um array de Strings contendo os campos do horário
     * @return um objeto {@code Horario} criado com base nos campos passados ou null
     *         caso o array não tenha o tamanho correto ou seja o cabeçalho
     */

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

    /**
     * Array de strings que define a ordem e nome dos campos do cabeçalho de um
     * ficheiro CSV válido contendo informações sobre
     * horários de aulas. Os campos são, pela ordem: "Curso", "Unidade Curricular",
     * "Turno", "Turma", "Inscritos no turno",
     * "Dia da semana", "Hora início da aula", "Hora fim da aula", "Data da aula",
     * "Sala atribuída à aula" e "Lotação da sala".
     */

    private static final String[] HEADER_FIELDS = { "Curso", "Unidade Curricular", "Turno", "Turma",
            "Inscritos no turno",
            "Dia da semana", "Hora início da aula", "Hora fim da aula", "Data da aula", "Sala atribuída à aula",
            "Lotação da sala" };
}
