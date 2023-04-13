package services;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import models.Horario;

/**
 * Classe utilitária responsável por escrever objetos do tipo {@link Horario} em
 * formato JSON.
 * Utiliza a biblioteca Jackson para converter os objetos em bytes
 * correspondentes ao JSON gerado.
 */
public class HorarioJsonWriter {

    // Construtor privado para impedir instanciação da classe
    private HorarioJsonWriter() {
    }

    /**
     * Converte uma lista de objetos do tipo {@link Horario} em bytes
     * correspondentes ao formato JSON gerado.
     * <p>
     * @param horarios Lista de objetos do tipo {@link Horario} a serem convertidos
     *                 em JSON.
     * @return Bytes correspondentes ao JSON gerado a partir dos objetos fornecidos.
     */
    public static byte[] writeToJson(List<Horario> horarios) {
        // Cria um objeto ObjectMapper para converter os objetos em JSON
        ObjectMapper mapper = new ObjectMapper();

        try {
            // Converte a lista de objetos em bytes correspondentes ao JSON gerado
            return mapper.writeValueAsBytes(horarios);
        } catch (IOException e) {
            // Em caso de exceção, imprime o stack trace e retorna um array vazio de bytes
            e.printStackTrace();
        }
        return new byte[0];
    }
}
