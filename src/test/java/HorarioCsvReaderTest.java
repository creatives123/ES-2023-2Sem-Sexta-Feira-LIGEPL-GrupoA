import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import Models.Horario;
import services.HorarioCsvReader;

class HorarioCsvReaderTest {

    @Test
    void testReadCsv() {
        String filePath = "src/test/resources/test_horarios.csv";
        List<Horario> horarios = HorarioCsvReader.readCsv(filePath);

        // Verify that the correct number of horarios were read from the CSV
        assertEquals(3, horarios.size());

        // Verify the details of each horario
        Horario horario1 = horarios.get(0);
        assertEquals("0001", horario1.getCurso());
        assertEquals("A", horario1.getUnidadeCurricular());
        assertEquals("Segunda", horario1.getTurno());
        assertEquals("10:00", horario1.getTurma());
        assertEquals(90, horario1.getInscritos());
        assertEquals("Sala A", horario1.getDiaSemana());
        assertEquals("João", horario1.getHoraInicio());
        assertEquals("Matemática", horario1.getHoraFim());
        assertEquals("Fundamental II", horario1.getDataAula());
        assertEquals("Semana A", horario1.getSala());
        assertEquals(30, horario1.getLotacao());

        Horario horario2 = horarios.get(1);
        assertEquals("0002", horario2.getCurso());
        assertEquals("B", horario2.getUnidadeCurricular());
        assertEquals("Terça", horario2.getTurno());
        assertEquals("14:00", horario2.getTurma());
        assertEquals(120, horario2.getInscritos());
        assertEquals("Sala B", horario2.getDiaSemana());
        assertEquals("Maria", horario2.getHoraInicio());
        assertEquals("História", horario2.getHoraFim());
        assertEquals("Ensino Médio", horario2.getDataAula());
        assertEquals("Semana B", horario2.getSala());
        assertEquals(25, horario2.getLotacao());

        Horario horario3 = horarios.get(2);
        assertEquals("0003", horario3.getCurso());
        assertEquals("C", horario3.getUnidadeCurricular());
        assertEquals("Quarta", horario3.getTurno());
        assertEquals("16:30", horario3.getTurma());
        assertEquals(60, horario3.getInscritos());
        assertEquals("Sala C", horario3.getDiaSemana());
        assertEquals("José", horario3.getHoraInicio());
        assertEquals("Física", horario3.getHoraFim());
        assertEquals("Ensino Médio", horario3.getDataAula());
        assertEquals("Semana A", horario3.getSala());
        assertEquals(20, horario3.getLotacao());
    }

}
