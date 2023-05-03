package Translators;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import models.CalendarModel;
import models.Horario;

public class HorarioToCalendarTranslator {
    
    private HorarioToCalendarTranslator() {}

    public static CalendarModel translateHorarioToCalendar(Horario h) {
        CalendarModel c = new CalendarModel();

        c.setId(h.hashCode());
        c.setStart(getCorrectTimeFormat(h.getDataAula().concat(" ").concat(h.getHoraInicio())));
        c.setEnd(getCorrectTimeFormat(h.getDataAula().concat(" ").concat(h.getHoraFim())));

        c.setTitle(h.getUnidadeCurricular());
        c.setColor(null);

        return c;
    }

    public static List<CalendarModel> translateHorariosToCalendars(List<Horario> hs) {
        
        List<CalendarModel> calendars = new ArrayList<>();

        for (Horario h : hs) {
            calendars.add(translateHorarioToCalendar(h));
        }

        return calendars;
    }

    private static String getCorrectTimeFormat(String input) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(input, inputFormat);
    
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(outputFormat);
    }
}
