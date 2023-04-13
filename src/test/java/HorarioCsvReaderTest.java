
import com.opencsv.exceptions.CsvValidationException;
import models.Horario;
import services.HorarioCsvReader;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HorarioCsvReaderTest {

    @Test
    public void testProcessCsvStream() throws IOException, CsvValidationException {
        String csv = "Curso;Unidade Curricular;Turno;Turma;Inscritos no turno;Dia da semana;Hora início da aula;Hora fim da aula;Data da aula;Sala atribuída à aula;Lotação da sala\n"
                +
                "Curso 1;Unidade Curricular 1;T01;T001;20;Segunda-feira;14:00;16:00;2023-04-10;Sala 1;25\n" +
                "Curso 2;Unidade Curricular 2;T02;T002;18;Terça-feira;15:00;17:00;2023-04-11;Sala 2;20\n" +
                "Curso 3;Unidade Curricular 3;T01;T003;15;Quarta-feira;16:00;18:00;2023-04-12;Sala 3;30\n";

        InputStream inputStream = new ByteArrayInputStream(csv.getBytes(StandardCharsets.UTF_8));
        List<Horario> horarios = HorarioCsvReader.processCsvStream(inputStream);

        assertNotNull(horarios);
        assertEquals(3, horarios.size());

        Horario horario1 = horarios.get(0);
        List<String> cursoList1 = horario1.getCurso();
        String[] cursoArray1 = cursoList1.toArray(new String[0]);
        assertArrayEquals(new String[] { "Curso 1" }, cursoArray1);      
        assertEquals("Unidade Curricular 1", horario1.getUnidadeCurricular());
        assertEquals("T01", horario1.getTurno());
        List<String> turmaList1 = horario1.getTurma();
        String[] turmaArray1 = turmaList1.toArray(new String[0]);
        assertArrayEquals(new String[] { "T001" }, turmaArray1);
        assertEquals(20, horario1.getInscritos());
        assertEquals("Segunda-feira", horario1.getDiaSemana());
        assertEquals("14:00", horario1.getHoraInicio());
        assertEquals("16:00", horario1.getHoraFim());
        assertEquals("2023-04-10", horario1.getDataAula());
        assertEquals("Sala 1", horario1.getSala());
        assertEquals(25, horario1.getLotacao());

        Horario horario2 = horarios.get(1);
        List<String> cursoList2 = horario2.getCurso();
        String[] cursoArray2 = cursoList2.toArray(new String[0]);
        assertArrayEquals(new String[] { "Curso 2" }, cursoArray2);  
        assertEquals("Unidade Curricular 2", horario2.getUnidadeCurricular());
        assertEquals("T02", horario2.getTurno());
        List<String> turmaList2 = horario2.getTurma();
        String[] turmaArray2 = turmaList2.toArray(new String[0]);
        assertArrayEquals(new String[] { "T002" }, turmaArray2);
        assertEquals(18, horario2.getInscritos());
        assertEquals("Terça-feira", horario2.getDiaSemana());
        assertEquals("15:00", horario2.getHoraInicio());
        assertEquals("17:00", horario2.getHoraFim());
        assertEquals("2023-04-11", horario2.getDataAula());
        assertEquals("Sala 2", horario2.getSala());
        assertEquals(20, horario2.getLotacao());

        Horario horario3 = horarios.get(2);
        List<String> cursoList3 = horario3.getCurso();
        String[] cursoArray3 = cursoList3.toArray(new String[0]);
        assertArrayEquals(new String[] { "Curso 3" }, cursoArray3);  
        assertEquals("Unidade Curricular 3", horario3.getUnidadeCurricular());
        assertEquals("T01", horario3.getTurno());
        List<String> turmaList3 = horario3.getTurma();
        String[] turmaArray3 = turmaList3.toArray(new String[0]);
        assertArrayEquals(new String[] { "T003" }, turmaArray3);
        assertEquals(15, horario3.getInscritos());
        assertEquals("Quarta-feira", horario3.getDiaSemana());
        assertEquals("16:00", horario3.getHoraInicio());
        assertEquals("18:00", horario3.getHoraFim());
        assertEquals("2023-04-12", horario3.getDataAula());
        assertEquals("Sala 3", horario3.getSala());
        assertEquals(30, horario3.getLotacao());
    }
}
