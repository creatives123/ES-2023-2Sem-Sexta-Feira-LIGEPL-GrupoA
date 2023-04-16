import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import models.Horario;
import services.HorarioJsonWriter;

public class HorarioJsonWriterTest {
    
    @Test
    public void testWriteToJson_WithNullList() {
        assertEquals(0, HorarioJsonWriter.writeToJson(null).length);
    }

    @Test
    public void testWriteToJsonWith_NonEmptyList() {
        List<Horario> horarios = new ArrayList<>();

        // Cria alguns objetos Horario e adiciona-os à lista
        Horario horario1 = new Horario();
        horario1.setCurso(Arrays.asList("LEI"));
        horario1.setUnidadeCurricular("Programacao");
        horario1.setTurno("T1");
        horario1.setTurma(Arrays.asList("1"));
        horario1.setInscritos(50);
        horario1.setDiaSemana("Segunda-feira");
        horario1.setHoraInicio("09:00");
        horario1.setHoraFim("11:00");
        horario1.setDataAula("2022-01-01");
        horario1.setSala("B102");
        horario1.setLotacao(60);

        Horario horario2 = new Horario();
        horario2.setCurso(Arrays.asList("MEI"));
        horario2.setUnidadeCurricular("Estatistica");
        horario2.setTurno("T2");
        horario2.setTurma(Arrays.asList("2"));
        horario2.setInscritos(40);
        horario2.setDiaSemana("Terca-feira");
        horario2.setHoraInicio("14:00");
        horario2.setHoraFim("16:00");
        horario2.setDataAula("2022-01-02");
        horario2.setSala("B103");
        horario2.setLotacao(50);

        horarios.add(horario1);
        horarios.add(horario2);
   
        byte[] jsonBytes = HorarioJsonWriter.writeToJson(horarios);
        assertTrue(jsonBytes.length > 0);
    }
}