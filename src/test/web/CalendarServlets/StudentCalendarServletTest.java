package web.CalendarServlets;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import models.Horario;
import services.CommonManager;

/**
 * A classe AddEventServletTest é uma classe de teste unitário para o servlet AddEventServlet.
 * 
 * Esta classe usa o framework JUnit e a biblioteca Mockito para simular as dependências do servlet (HttpServletRequest, HttpServletResponse, 
 * HttpSession, CommonManager, etc.) e testar a lógica de negócios do servlet.
 */
@RunWith(MockitoJUnitRunner.class)
public class StudentCalendarServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @InjectMocks
    private StudentCalendarServlet servlet;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(request.getSession()).thenReturn(session);
    }

    /**
     * Testa o método doGet do servlet, verificando se um evento é adicionado corretamente ao calendário do estudante na sessão.
     * 
     * @throws ServletException se ocorrer um erro durante o processamento do pedido
     * @throws IOException se ocorrer um erro ao ler ou escrever no fluxo de entrada ou saída
     */
    @Test
    public void testDoGet() throws ServletException, IOException {
        // Setup
        Horario event1 = new Horario();
        event1.setCurso("Engenharia Informática");
        event1.setUnidadeCurricular("Programação II");
        event1.setTurno("TP1");

        Horario event2 = new Horario();
        event2.setCurso("Engenharia Informática");
        event2.setUnidadeCurricular("Programação II");
        event2.setTurno("TP2");

        ArrayList<Horario> events = new ArrayList<>(Arrays.asList(event1, event2));
        ArrayList<Horario> studentSchedule = new ArrayList<>(Arrays.asList(event1));

        when(CommonManager.getHorariosFromSession(session)).thenReturn(events);
        when(CommonManager.getStudentHorarioFromSession(session)).thenReturn(studentSchedule);

        when(request.getParameter("curso")).thenReturn("Engenharia Informática");
        when(request.getParameter("uc")).thenReturn("Programação II");
        when(request.getParameter("turno")).thenReturn("TP2");

        // Exercise
        servlet.doGet(request, response);

        // Verify
        verify(session, times(1)).setAttribute(eq("studentHorario"), eq(studentSchedule));
        verify(request, never()).setAttribute(anyString(), any());
    }

    @Test
    public void testDoPost() throws ServletException, IOException {
        Horario event1 = new Horario();
        
        event1.setCurso("Engenharia Informática");
        event1.setUnidadeCurricular("Informática de Gestão");
        event1.setTurno("L0718TP13");
        

        when(request.getParameter("curso")).thenReturn("Curso 1");
        when(request.getParameter("uc")).thenReturn("UC 1");
        when(request.getParameter("turno")).thenReturn("Turno 3");

        servlet.doPost(request, response);

        Assert.assertEquals(4, horarios.size());
        Assert.assertEquals("Curso 1", horarios.get(3).getCurso());
        Assert.assertEquals("UC 1", horarios.get(3).getUnidadeCurricular());
        Assert.assertEquals("Turno 3", horarios.get(3).getTurno());
    }


    

}
