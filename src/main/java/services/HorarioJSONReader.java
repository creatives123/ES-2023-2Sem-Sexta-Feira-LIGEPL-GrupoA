package services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

import models.Horario;

/**
 * Classe responsável por ler ficheiros JSON de forma a 
 * criar Listas de objetos {@link Horario}
 */
public class HorarioJSONReader {
    
    // Construtor privado para impedir instanciação da classe
    private HorarioJSONReader() {}

    /**
     * Lê um stream de dados JSON, descodifica-o usando UTF-8 e converte-o numa lista
     * de objetos {@link Horario} usando a biblioteca Jackson e ObjectMapper
     * <p>
     * @param inputStream O stream de dados JSON a ser lido.
     * @return A lista de objetos {@link Horario} lidos a partir do stream de dados JSON.
     * @throws IOException Se ocorrer um erro de I/O durante a leitura do stream de
     *         dados.
     */
    public static List<Horario> processJsonStream(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        return new ObjectMapper().readValue(reader, new TypeReference<List<Horario>>() {});
    }
}