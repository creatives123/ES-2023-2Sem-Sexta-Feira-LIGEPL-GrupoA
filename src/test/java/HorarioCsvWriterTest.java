import models.Horario;
import services.HorarioCsvWriter;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Classe responsável por testar os métodos da Classe {@link HorarioCsvReader}
 * <p>
 * Métodos:
 * <p>
 * {@link #testWriteTo_CsvWithEmptyList()}
 * <p>
 * {@link #testWriteToCsv_WithSingleHorario()}
 * <p>
 * {@link #testWriteToCsv_WithMultipleHorarios()}
 * <p>
 * {@link #testWriteToCsvWith_OneHorario_AllFieldsFilled()}
 * <p>
 * {@link #testWriteToCsv_WithNullList()}
 * <p>
 * {@link #testWriteToCsvWithHorario_WithNullFields()}
 * 
 */

class HorarioCsvWriterTest {

    /**
     * Cria uma lista de {@link Horario}s vazia.
     * Escreve os valores do objeto dentro da lista
     *  usando {@link HorarioCSVWriter}. 
     * Cria uma string para representar uma linha
     * do ficheiro CSV.
     * Finalmente, verifica se escreveu no ficheiro
     * a string expectável.
     */
    @Test
     void testWriteTo_CsvWithEmptyList() {
        List<Horario> horarios = new ArrayList<>();
        byte[] csvBytes = HorarioCsvWriter.writeToCsv(horarios);
        String expected = "Curso;Unidade Curricular;Turno;Turma;Inscritos no turno;Dia da semana;Hora início da aula;Hora fim da aula;Data da aula;Sala atribuída à aula;Lotação da sala\n";
        assertEquals(expected, new String(csvBytes, StandardCharsets.UTF_8));
    }

    /**
     * Cria um objeto {@link Horario} com todos os 
     * seus campos válidos.
     * Cria uma lista de {@link Horario}s com os
     * valores do objeto {@link Horario} criados.
     * Escreve os valores do objeto dentro
     * da lista usando {@link HorarioCSVWriter}.
     * Verifica se escreveu no ficheiro a string expectavel.
     * Verifica se os arrays de bytes sao iguais.
     * 
     * @throws IOException Se ocorrer um erro de I/O.
     */

    @Test
    void testWriteToCsv_WithSingleHorario() throws IOException {
        // Cria um objeto Horario
        Horario horario = new Horario();
        horario.setCurso(Arrays.asList("LEI"));
        horario.setUnidadeCurricular("Programacao");
        horario.setTurno("T1");
        horario.setTurma(Arrays.asList("1"));
        horario.setInscritos(50);
        horario.setDiaSemana("Segunda-feira");
        horario.setHoraInicio("09:00");
        horario.setHoraFim("11:00");
        horario.setDataAula("2022-01-01");
        horario.setSala("B102");
        horario.setLotacao(60);
    
        // Cria uma lista com o objeto Horario
        List<Horario> horarios = Arrays.asList(horario);
    
        // Chama o método que será testado
        byte[] csvBytes = HorarioCsvWriter.writeToCsv(horarios);
    
        // É necessário colocar o expectado com os \n porque o csv tem sempre no fim de cada linha
        // esse separador.
        String expected = "Curso;Unidade Curricular;Turno;Turma;Inscritos no turno;Dia da semana;Hora início da aula;Hora fim da aula;Data da aula;Sala atribuída à aula;Lotação da sala\n" 
                            + "LEI;Programacao;T1;1;50;Segunda-feira;09:00;11:00;2022-01-01;B102;60\n";
    
        assertArrayEquals(expected.getBytes(StandardCharsets.UTF_8), csvBytes);
    }

    /**
     * Cria dois objetos {@link Horario} com todos os 
     * seus campos válidos.
     * Cria uma lista de {@link Horario}s com os
     * valores dos objetos {@link Horario} criados.
     * Escreve os valores dos objetos dentro
     * da lista usando {@link HorarioCSVWriter}.
     * Verifica se escreveu no ficheiro a string expectavel.
     * Verifica se os arrays de bytes sao iguais.
     * 
     * @throws IOException Se ocorrer um erro de I/O.
     */

    @Test
    void testWriteToCsv_WithMultipleHorarios() throws IOException {
        // Cria uma lista de horarios
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

        byte[] csvBytes = HorarioCsvWriter.writeToCsv(horarios);

        String expected = "Curso;Unidade Curricular;Turno;Turma;Inscritos no turno;Dia da semana;Hora início da aula;Hora fim da aula;Data da aula;Sala atribuída à aula;Lotação da sala\n"
                        + "LEI;Programacao;T1;1;50;Segunda-feira;09:00;11:00;2022-01-01;B102;60\n"
                        + "MEI;Estatistica;T2;2;40;Terca-feira;14:00;16:00;2022-01-02;B103;50\n";

        // Verifica se os arrays de bytes são iguais
        assertArrayEquals(expected.getBytes(StandardCharsets.UTF_8), csvBytes);
    }

    /**
     * Cria um objeto {@link Horario} com todos os 
     * seus campos válidos.
     * Cria uma lista de {@link Horario}s com os
     * valores do objeto {@link Horario} criados.
     * Escreve os valores dos objetos dentro
     * da lista usando {@link HorarioCSVWriter}.
     * Verifica se escreveu no ficheiro a string expectavel.
     * Verifica se os arrays de bytes sao iguais.
     * 
     * @throws IOException Se ocorrer um erro de I/O.
     */

    @Test
    void testWriteToCsvWith_OneHorario_AllFieldsFilled() throws IOException {
        Horario horario = new Horario();
        horario.setCurso(Arrays.asList("Matemática"));
        horario.setUnidadeCurricular("Cálculo I");
        horario.setTurno("Manhã");
        horario.setTurma(Arrays.asList("A"));
        horario.setInscritos(30);
        horario.setDiaSemana("Segunda-feira");
        horario.setHoraInicio("08:00");
        horario.setHoraFim("10:00");
        horario.setDataAula("2022-05-02");
        horario.setSala("Sala 101");
        horario.setLotacao(40);
        List<Horario> horarios = Arrays.asList(horario);
        byte[] csvBytes = HorarioCsvWriter.writeToCsv(horarios);
        String expected = "Curso;Unidade Curricular;Turno;Turma;Inscritos no turno;Dia da semana;Hora início da aula;Hora fim da aula;Data da aula;Sala atribuída à aula;Lotação da sala\n"
                        + "Matemática;Cálculo I;Manhã;A;30;Segunda-feira;08:00;10:00;2022-05-02;Sala 101;40\n";
        assertArrayEquals(expected.getBytes(StandardCharsets.UTF_8), csvBytes);
    }
    
    /**
     * Verifica se os arrays de bytes são
     * iguais aos que estao a ser escritos pelo 
     * {@link HorarioCSVWriter}. Se forem diferentes
     * ocorre um erro.
     *  
     * @throws IOException Se ocorrer um erro de I/O.
     */

    @Test
    void testWriteToCsv_WithNullList() throws IOException {
        // Verifica se os arrays de bytes são iguais
        assertArrayEquals(new byte[0], HorarioCsvWriter.writeToCsv(null));
    }

   /**
     * Cria um objeto {@link Horario} com alguns
     * campos a null e outros validos.
     * Cria uma lista de {@link Horario}s com os
     * valores do objeto {@link Horario} criados.
     * Escreve os valores do objeto dentro
     * da lista usando {@link HorarioCSVWriter}.
     * Verifica se escreveu no ficheiro a string expectavel.
     * Verifica se os arrays de bytes sao iguais.
     * 
     * @throws IOException Se ocorrer um erro de I/O.
     */
    @Test
    void testWriteToCsvWithHorario_WithNullFields() throws IOException {
        // Cria um objeto Horario com alguns campos a null
        Horario horario = new Horario();
        horario.setCurso(null);
        horario.setUnidadeCurricular("Programacao");
        horario.setTurno(null);
        horario.setTurma(Arrays.asList("1"));
        horario.setInscritos(50);
        horario.setDiaSemana("Segunda-feira");
        horario.setHoraInicio(null);
        horario.setHoraFim(null);
        horario.setDataAula("2022-01-01");
        horario.setSala(null);
        horario.setLotacao(60);

        List<Horario> horarios = Arrays.asList(horario);

        byte[] csvBytes = HorarioCsvWriter.writeToCsv(horarios);

        String expected = "Curso;Unidade Curricular;Turno;Turma;Inscritos no turno;Dia da semana;Hora início da aula;Hora fim da aula;Data da aula;Sala atribuída à aula;Lotação da sala\n"
        + ";;Programacao;;;1;50;Segunda-feira;;;;;"
        + "2022-01-01;;;60\n";
        
        // Verifica se os arrays de bytes são iguais
        assertArrayEquals(expected.getBytes(StandardCharsets.UTF_8), csvBytes);
    }
}