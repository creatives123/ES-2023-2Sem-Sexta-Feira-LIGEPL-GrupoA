package web.CalendarServlets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import models.CalendarModel;
import models.CalendarWrapper;
import models.Horario;
import services.CommonManager;
import web.DatasourceServlets.GetCoursesServlet;

public class StudentCalendarServletTest {

    @Mock
    HttpServletRequest request;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDoGetWithEqualEvent_Empty() throws IOException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
    
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
    
        
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        new StudentCalendarServlet().doGet(request, response);
        writer.flush();
    
        String expectedJson = "{\"overlappedEvents\":{},\"overCrowdedEvents\":{},\"events\":[]}";
        assertEquals(expectedJson, stringWriter.toString().trim());
    }

    @Test
    public void testDoGetWithEqualEvent() throws IOException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        
        // Cria uma lista de horarios
        List<Horario> horarios = new ArrayList<>();        
        Horario horario = new Horario();
        horario.setCurso(Arrays.asList("LEI"));
        horario.setUnidadeCurricular("Programacao");
        horario.setTurno("T1");
        horario.setTurma(Arrays.asList("1"));
        horario.setInscritos(60);   
        horario.setDiaSemana("Segunda-feira");
        horario.setHoraInicio("09:00:00");
        horario.setHoraFim("11:00:00");
        horario.setDataAula("01/01/2022");
        horario.setSala("B102");
        horario.setLotacao(50);

        Horario horario2 = new Horario();
        horario2.setCurso(Arrays.asList("LEI"));
        horario2.setUnidadeCurricular("Programacao");
        horario2.setTurno("T1");
        horario2.setTurma(Arrays.asList("1"));
        horario2.setInscritos(60);
        horario2.setDiaSemana("Segunda-feira");
        horario2.setHoraInicio("10:00:00");
        horario2.setHoraFim("11:00:00");
        horario2.setDataAula("01/01/2022");
        horario2.setSala("B102");
        horario2.setLotacao(50);

        horarios.add(horario2);

        List<CalendarModel> calendario = new ArrayList<>();
        CalendarModel cal = new CalendarModel();
        cal.setHorario(horario);
        cal.setColor("blue");
        cal.setTitle("Horario");
        cal.setStart("2022-01-01 09:00:00");
        cal.setEnd("2022-01-01 11:00:00");
        calendario.add(cal);

        CalendarModel cal2 = new CalendarModel();
        cal2.setHorario(horario2);
        cal2.setColor("blue");
        cal2.setTitle("Horario  df");
        cal2.setStart("2022-01-01 10:00:00");
        cal2.setEnd("2022-01-01 11:00:00");
        calendario.add(cal2);

        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        session.setAttribute("horarios", horarios);
        when(session.getAttribute("horarios")).thenReturn(horarios);
        
        session.setAttribute("student_horario", horarios);
        when(session.getAttribute("student_horario")).thenReturn(horarios);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
    
        new StudentCalendarServlet().doGet(request, response);
        writer.flush();
    
        CalendarWrapper wrapper = new ObjectMapper().readValue(stringWriter.toString().trim(), CalendarWrapper.class);
        assertEquals(wrapper.getEvents().size(), horarios.size());
    }


    @Test
    public void testAddEventWithout_adding() throws IOException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        
        // Cria uma lista de horarios
        List<Horario> horarios = new ArrayList<>();
        List<Horario> horarios2 = new ArrayList<>();        

        Horario horario = new Horario();
        horario.setCurso(Arrays.asList("LEI"));
        horario.setUnidadeCurricular("Programacao");
        horario.setTurno("T1");
        horario.setTurma(Arrays.asList("1"));
        horario.setInscritos(60);   
        horario.setDiaSemana("Segunda-feira");
        horario.setHoraInicio("09:00:00");
        horario.setHoraFim("11:00:00");
        horario.setDataAula("01/01/2022");
        horario.setSala("B102");
        horario.setLotacao(50);
        horarios.add(horario);

        Horario horario2 = new Horario();
        horario2.setCurso(Arrays.asList("LEIa"));
        horario2.setUnidadeCurricular("Programacao");
        horario2.setTurno("T1");
        horario2.setTurma(Arrays.asList("1"));
        horario2.setInscritos(60);
        horario2.setDiaSemana("Segunda-feira");
        horario2.setHoraInicio("10:00:00");
        horario2.setHoraFim("11:00:00");
        horario2.setDataAula("01/01/2022");
        horario2.setSala("B102");
        horario2.setLotacao(50);
        horarios2.add(horario2);

        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        session.setAttribute("horarios", horarios);
        when(session.getAttribute("horarios")).thenReturn(horarios);
        
        session.setAttribute("student_horario", horarios);
        when(session.getAttribute("student_horario")).thenReturn(horarios2);

        when(request.getParameter("course")).thenReturn("LEI");
        when(request.getParameter("uc")).thenReturn("Programacao");
        when(request.getParameter("turno")).thenReturn("T1");
    
        new StudentCalendarServlet().doPost(request, response);
    }

    @Test
    public void testDeleteEvents() throws IOException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        
        // Cria uma lista de horarios
        List<Horario> horarios = new ArrayList<>();

        Horario horario = new Horario();
        horario.setCurso(Arrays.asList("LEI"));
        horario.setUnidadeCurricular("Programacao");
        horario.setTurno("T1");
        horario.setTurma(Arrays.asList("1"));
        horario.setInscritos(60);   
        horario.setDiaSemana("Segunda-feira");
        horario.setHoraInicio("09:00:00");
        horario.setHoraFim("11:00:00");
        horario.setDataAula("01/01/2022");
        horario.setSala("B102");
        horario.setLotacao(50);
        horarios.add(horario);

        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        session.setAttribute("student_horario", horarios);
        when(session.getAttribute("student_horario")).thenReturn(horarios);

        when(request.getParameter("uc")).thenReturn("Programacao");
        when(request.getParameter("turno")).thenReturn("T1");
    
        new StudentCalendarServlet().doDelete(request, response);
    }
    
}