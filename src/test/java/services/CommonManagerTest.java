package services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
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

public class CommonManagerTest {

    @Test
    public void testGetHorariosFromSession_whenSessionContainsHorarios_shouldReturnList() {
        HttpSession session = mock(HttpSession.class);
        List<Horario> expectedHorarios = Arrays.asList(new Horario(), new Horario());
        when(session.getAttribute("horarios")).thenReturn(expectedHorarios);

        List<Horario> result = CommonManager.getHorariosFromSession(session);
        assertEquals(expectedHorarios, result);
    }

    @Test
    public void testGetHorariosFromSession_whenSessionDoesNotContainHorarios_shouldReturnEmptyList() {
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("horarios")).thenReturn(null);

        List<Horario> result = CommonManager.getHorariosFromSession(session);

        assertEquals(new ArrayList<>(), result);
    }

    @Test
    public void testGetCoursesFromSession_whenSessionContainsCourses_shouldReturnList() {
        HttpSession session = mock(HttpSession.class);
        List<String> expectedCourses = Arrays.asList("Math", "Science");
        when(session.getAttribute("courses")).thenReturn(expectedCourses);

        List<String> result = CommonManager.getCoursesFromSession(session);

        assertEquals(expectedCourses, result);
    }

    @Test
    public void testGetCoursesFromSession_whenSessionDoesNotContainCourses_shouldReturnEmptyList() {
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("courses")).thenReturn(null);

        List<String> result = CommonManager.getCoursesFromSession(session);

        assertEquals(new ArrayList<>(), result);
    }

    @Test
    public void testGetStudentHorarioFromSession_whenSessionContainsStudentHorario_shouldReturnList() {
        HttpSession session = mock(HttpSession.class);
        List<Horario> expectedHorarios = Arrays.asList(new Horario(), new Horario());
        when(session.getAttribute("student_horario")).thenReturn(expectedHorarios);

        List<Horario> result = CommonManager.getStudentHorarioFromSession(session);

        assertEquals(expectedHorarios, result);
    }

    @Test
    public void testGetStudentHorarioFromSession_whenSessionDoesNotContainStudentHorario_shouldReturnEmptyList() {
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("student_horario")).thenReturn(null);

        List<Horario> result = CommonManager.getStudentHorarioFromSession(session);

        assertEquals(new ArrayList<>(), result);
    }

    @Test
    public void testAddToStudentHorarioFromSession_whenSessionContainsStudentHorario_shouldAddToHorarioList() {
        HttpSession session = mock(HttpSession.class);
        Horario horario = new Horario();
        List<Horario> expectedHorarios = new ArrayList<>(Arrays.asList(horario));
        when(session.getAttribute("student_horario")).thenReturn(expectedHorarios);

        CommonManager.addToStudentHorarioFromSession(session, horario);

        verify(session, times(1)).setAttribute(eq("student_horario"), eq(expectedHorarios));
        assertTrue(expectedHorarios.contains(horario));
    }    
}
