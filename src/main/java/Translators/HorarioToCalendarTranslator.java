package Translators;

import java.util.ArrayList;
import java.util.List;

import models.CalendarModel;
import models.Horario;
import services.DateManager;

public class HorarioToCalendarTranslator {
    
    private HorarioToCalendarTranslator() {}

    /**
     * Converte um objeto do tipo Horario em um objeto do tipo CalendarModel.
     * 
     * @param h o objeto do tipo Horario a ser convertido
     * @return o objeto do tipo CalendarModel correspondente ao ob  jeto Horario
     */
    public static CalendarModel translateHorarioToCalendar(Horario h) {
        CalendarModel c = new CalendarModel();
        c.setHorario(h);
        c.setId(h.hashCode());
        c.setStart(DateManager.getDataHoraInicio(h));
        c.setEnd(DateManager.getDataHoraFim(h));
        c.setTitle(h.getUnidadeCurricular());
        return c;
    }

    /**
     * Converte uma lista de objetos do tipo Horario em uma lista de objetos do tipo CalendarModel.
     * 
     * @param hs a lista de objetos do tipo Horario a serem convertidos
     * @return a lista de objetos do tipo CalendarModel correspondentes aos objetos Horario
     */
    public static List<CalendarModel> translateHorariosToCalendars(List<Horario> hs) {
        
        List<CalendarModel> calendars = new ArrayList<>();

        for (Horario h : hs) {
            calendars.add(translateHorarioToCalendar(h));
        }

        return calendars;
    }
}
