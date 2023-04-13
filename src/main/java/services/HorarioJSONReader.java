package services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

import models.Horario;

public class HorarioJSONReader {
    public static List<Horario> readJSON(String filePath) {
        try {
            return new ObjectMapper().readValue(new File(filePath), new TypeReference<List<Horario>>(){});
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Lê um stream de dados JSON, decodifica-o usando UTF-8 e converte-o numa lista de objetos Horario usando a biblioteca Jackson.
     *
     * @param inputStream O stream de dados JSON a ser lido.
     * @return A lista de objetos Horario lidos a partir do stream de dados JSON.
     * @throws IOException Se ocorrer um erro de I/O durante a leitura do stream de dados.
     */
    public static List<Horario> processJsonStream(InputStream inputStream) throws IOException {
        // Cria um BufferedReader para ler o fluxo de dados do InputStream, usando UTF-8 como codificação de caracteres
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        // Usa a biblioteca ObjectMapper para converter o fluxo de dados JSON numa lista de objetos Horario
        return new ObjectMapper().readValue(reader, new TypeReference<List<Horario>>() {
        });
    }


}