package services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import models.Horario;

/**
 * Classe utilitária que fornece métodos para gerir datas.
 */
public class DateManager {

    /**
     * Construtor privado para evitar a instanciação da classe.
     */
    private DateManager() {}

    /**
     * Converte uma string no formato "dd/MM/yyyy HH:mm:ss" para uma string no formato "yyyy-MM-dd HH:mm:ss".
     *
     * @param input a string a ser convertida.
     * @return a string no formato "yyyy-MM-dd HH:mm:ss".
     */
    public static String getScheduleCorrectTimeFormat(String input) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(input, inputFormat);
    
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(outputFormat);
    }

    public static String getDataHoraInicio(Horario h) throws IllegalArgumentException {
        if (h == null) {
            throw new IllegalArgumentException("h is null");
        }
        return DateManager.getScheduleCorrectTimeFormat(h.getDataAula().concat(" ").concat(h.getHoraInicio()));
    }

    public static String getDataHoraFim(Horario h) throws IllegalArgumentException {
        if (h == null) {
            throw new IllegalArgumentException("h is null");
        }
        return DateManager.getScheduleCorrectTimeFormat(h.getDataAula().concat(" ").concat(h.getHoraFim()));
    }
    
    public static boolean sameInterval(Horario h1, Horario h2) {

        if (h1 == null || h2 == null)  throw new IllegalArgumentException("h1 or h2 is null");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime inicio1 = LocalDateTime.parse(getDataHoraInicio(h1), formatter);
        LocalDateTime fim1 = LocalDateTime.parse(getDataHoraFim(h1), formatter);
        LocalDateTime inicio2 = LocalDateTime.parse(getDataHoraInicio(h2), formatter);
        LocalDateTime fim2 = LocalDateTime.parse(getDataHoraFim(h2), formatter);
    
        return (inicio1.isBefore(fim2) && fim1.isAfter(inicio2))
                || (inicio2.isBefore(fim1) && fim2.isAfter(inicio1))
                || (inicio1.equals(inicio2) && fim1.equals(fim2));
    }
}
