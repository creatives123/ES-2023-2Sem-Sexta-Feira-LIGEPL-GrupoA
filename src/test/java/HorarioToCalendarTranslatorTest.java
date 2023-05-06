import models.CalendarModel;
import models.Horario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Translators.HorarioToCalendarTranslator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

/**
 * Testa a classe HorarioToCalendarTranslator.
 */
class HorarioToCalendarTranslatorTest {

    private static Horario horario;
    private static CalendarModel expectedCalendar;

    /**
     * Prepara as variáveis necessárias para os testes.
     */
    @BeforeAll
    public static void setUp() {
        horario = new Horario();
        horario.setUnidadeCurricular("Física");
        horario.setDataAula("01/01/2022");
        horario.setHoraInicio("14:30:00");
        horario.setHoraFim("16:00:00");
        horario.setSala("A1.01");
        
        expectedCalendar = new CalendarModel();
        expectedCalendar.setHorario(horario);
        expectedCalendar.setId(horario.hashCode());
        expectedCalendar.setStart("2022-01-01 14:30:00");
        expectedCalendar.setEnd("2022-01-01 16:00:00");
        expectedCalendar.setTitle("Física");
    }

    /**
     * Testa a tradução de um objeto Horario para um objeto CalendarModel.
     */
    @Test
    void testTranslateHorarioToCalendar() {
        CalendarModel actualCalendar = HorarioToCalendarTranslator.translateHorarioToCalendar(horario);
        assertNotNull(actualCalendar);
        assertEquals(expectedCalendar.getHorario(), actualCalendar.getHorario());
        assertEquals(expectedCalendar.getId(), actualCalendar.getId());
        assertEquals(expectedCalendar.getStart(), actualCalendar.getStart());
        assertEquals(expectedCalendar.getEnd(), actualCalendar.getEnd());
        assertEquals(expectedCalendar.getTitle(), actualCalendar.getTitle());
    }

    /**
     * Testa a tradução de uma lista de objetos Horario para uma lista de objetos CalendarModel.
     */
    @Test
    void testTranslateHorariosToCalendars() {
        assertEquals(1, HorarioToCalendarTranslator.translateHorariosToCalendars(List.of(horario)).size());
    }
}
