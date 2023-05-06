package services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateManager {
    private DateManager() {}

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
    

    public static Date castStringExtendToDate(String input) {
        DateFormat inputFormat = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss");
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


    public static String getScheduleCorrectTimeFormat(String input) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(input, inputFormat);
    
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(outputFormat);
    }
    


}
