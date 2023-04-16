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
    private HorarioJsonWriter() {}

    /**
     * Converte uma lista de objetos do tipo {@link Horario} em bytes
     * correspondentes ao formato JSON gerado.
     * <p>
     * @param horarios Lista de objetos do tipo {@link Horario} a serem convertidos
     *                 em JSON.
     * @return Bytes correspondentes ao JSON gerado a partir dos objetos fornecidos.
     */
    public static byte[] writeToJson(List<Horario> horarios) {
        // No caso de não vir cada, então não escreve nada.
        if (horarios == null) return new byte[0];

        try {
            return new ObjectMapper().writeValueAsBytes(horarios);
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}