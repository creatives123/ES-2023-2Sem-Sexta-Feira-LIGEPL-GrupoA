package services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Classe utilitária que fornece métodos para gerir datas.
 */
public class DateManager {

    /**
     * Construtor privado para evitar a instanciação da classe.
     */
    private DateManager() {}

    /**
     * Converte uma string no formato "dd/MM/yyyy" para um objeto {@link Date}.
     *
     * @param input a string a ser convertida.
     * @return o objeto {@link Date} correspondente.
     */
    public static Date castStringToDate(String input) {
        DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
    
        try {
            date = inputFormat.parse(input);
            DateFormat outputFormat = new SimpleDateFormat("yyyy-mm-dd");
            String outputString = outputFormat.format(date);
            date = outputFormat.parse(outputString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    
        return date;
    }
    

    /**
     * Converte uma string no formato "E MMM dd yyyy HH:mm:ss" para um objeto {@link Date}.
     *
     * @param input a string a ser convertida.
     * @return o objeto {@link Date} correspondente.
     */
    public static Date castStringExtendToDate(String input) {
        DateFormat inputFormat = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss" );
        Date date = null;
    
        try {
            date = inputFormat.parse(input);
            DateFormat outputFormat = new SimpleDateFormat("yyyy-mm-dd");
            String output = outputFormat.format(date);
            date = outputFormat.parse(output);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    
        return date;
    }

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
}
