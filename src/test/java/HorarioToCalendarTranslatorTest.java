import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

import Translators.HorarioToCalendarTranslator;
import models.CalendarModel;
import models.Horario;

public class HorarioToCalendarTranslatorTest {
    
    @Test
    void testTranslateHorarioToCalendar() {
        Horario h = new Horario();
        h.setDataAula("25/01/2023");
        h.setHoraInicio("9:30:00");
        h.setHoraFim("11:00:00");
        h.setUnidadeCurricular("Matemática");
    
        // CalendarModel result = HorarioToCalendarTranslator.translateHorarioToCalendar(h);
    
        // assertEquals(result.getHorario(), h);
        // assertEquals(result.getId(), h.hashCode());
        // assertEquals(result.getStart(), h.getDataHoraInicio());
        // assertEquals(result.getEnd(), h.getDataHoraFim());
        // assertEquals(result.getTitle(), h.getUnidadeCurricular());
    }    
}