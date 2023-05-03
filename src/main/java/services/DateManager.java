package services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    


}
