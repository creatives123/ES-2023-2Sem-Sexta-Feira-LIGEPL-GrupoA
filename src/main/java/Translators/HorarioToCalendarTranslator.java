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
        c.setHorario(h);
        c.setId(h.hashCode());
        c.setStart(h.getDataHoraInicio());
        c.setEnd(h.getDataHoraFim());
        c.setTitle(h.getUnidadeCurricular());
        return c;
    }

    public static List<CalendarModel> translateHorariosToCalendars(List<Horario> hs) {
        
        List<CalendarModel> calendars = new ArrayList<>();

        for (Horario h : hs) {
            calendars.add(translateHorarioToCalendar(h));
        }

        return calendars;
    }
}
