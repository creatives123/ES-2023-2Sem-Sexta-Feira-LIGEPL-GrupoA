package services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;

import models.Horario;
/**
 * Esta classe testa a classe CommonManager.
 */
class CommonManagerTest {

    /**
     * Testa o método getHorariosFromSession() quando a sessão contém objetos Horario.
     * Deve devolver uma lista de objetos Horario correspondentes aos objetos da sessão.
     */

    @Test
    void testGetHorariosFromSession_whenSessionContainsHorarios_shouldReturnList() {
        HttpSession session = mock(HttpSession.class);
        List<Horario> expectedHorarios = Arrays.asList(new Horario(), new Horario());
        when(session.getAttribute("horarios")).thenReturn(expectedHorarios);

        List<Horario> result = CommonManager.getHorariosFromSession(session);
        assertEquals(expectedHorarios, result);
    }

    /**
     * Testa o método getHorariosFromSession() quando a sessão nao contém objetos Horario.
     * Deve devolver uma lista vazia. 
     */
    @Test
    void testGetHorariosFromSession_whenSessionDoesNotContainHorarios_shouldReturnEmptyList() {
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("horarios")).thenReturn(null);

        List<Horario> result = CommonManager.getHorariosFromSession(session);

        assertEquals(new ArrayList<>(), result);
    }

    /**
     * Testa o método getCoursesFromSession() quando a sessão contém cursos (String).
     * Deve devolver uma lista de cursos correspondentes aos objetos da sessão.
     */
    @Test
    void testGetCoursesFromSession_whenSessionContainsCourses_shouldReturnList() {
        HttpSession session = mock(HttpSession.class);
        List<String> expectedCourses = Arrays.asList("Math", "Science");
        when(session.getAttribute("courses")).thenReturn(expectedCourses);

        List<String> result = CommonManager.getCoursesFromSession(session);

        assertEquals(expectedCourses, result);
    }

    /**
     * Testa o método getCoursesFromSession() quando a sessão não contém cursos (String).
     * Deve retornar uma lista vazia.
     */
    @Test
    void testGetCoursesFromSession_whenSessionDoesNotContainCourses_shouldReturnEmptyList() {
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("courses")).thenReturn(null);

        List<String> result = CommonManager.getCoursesFromSession(session);

        assertEquals(new ArrayList<>(), result);
    }

    /**
     * Testa o método getStudentHorarioFromSession() quando a sessão contém Horarios de Estudante.
     * Deve retornar uma lista de Horarios correspondentes aos da sessão.
     */
    @Test
    void testGetStudentHorarioFromSession_whenSessionContainsStudentHorario_shouldReturnList() {
        HttpSession session = mock(HttpSession.class);
        List<Horario> expectedHorarios = Arrays.asList(new Horario(), new Horario());
        when(session.getAttribute("student_horario")).thenReturn(expectedHorarios);

        List<Horario> result = CommonManager.getStudentHorarioFromSession(session);

        assertEquals(expectedHorarios, result);
    }

    /**
     * Testa o método getStudentHorarioFromSession() quando a sessão não contém Horaios de Estudante.
     * Deve retornar uma lista vazia.
     */
    @Test
    void testGetStudentHorarioFromSession_whenSessionDoesNotContainStudentHorario_shouldReturnEmptyList() {
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("student_horario")).thenReturn(null);

        List<Horario> result = CommonManager.getStudentHorarioFromSession(session);

        assertEquals(new ArrayList<>(), result);
    }

    /**
     * Testa o método addToStudentHorarioFromSession() quando a sessão contém Horarios de Estudante.
     * Deve adicionar um dado horário à lista de Horarios na sessão.
     */
    @Test
    void testAddToStudentHorarioFromSession_whenSessionContainsStudentHorario_shouldAddToHorarioList() {
        HttpSession session = mock(HttpSession.class);
        Horario horario = new Horario();
        List<Horario> expectedHorarios = new ArrayList<>(Arrays.asList(horario));
        when(session.getAttribute("student_horario")).thenReturn(expectedHorarios);

        CommonManager.addToStudentHorarioFromSession(session, horario);

        verify(session, times(1)).setAttribute("student_horario", expectedHorarios);
        assertTrue(expectedHorarios.contains(horario));
    }    
}
