package web.DatasourceServlets;

import org.junit.jupiter.api.Test;

import models.Horario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetTurnosServletTest {

    @Test
    public void testGetTurnos_NeedToGetOnlyOne() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        
        List<String> curso1 = new ArrayList<>();
        curso1.add("IGE");
        List<String> curso2 = new ArrayList<>();
        curso2.add("SS");

        Horario horario1 = new Horario();
        horario1.setTurno("turno1");
        horario1.setUnidadeCurricular("PISID");
        horario1.setCurso(curso1);

        Horario horario2 = new Horario();
        horario2.setTurno("turno2");
        horario2.setUnidadeCurricular("ES");
        horario2.setCurso(curso2);

        List<Horario> eventos = Arrays.asList(horario1, horario2);

        when(request.getSession()).thenReturn(session);
        session.setAttribute("horarios", eventos);
        when(session.getAttribute("horarios")).thenReturn(eventos);

        when(request.getParameter("course")).thenReturn("IGE");
        when(request.getParameter("uc")).thenReturn("PISID");

           
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
    
        new GetTurnosServlet().doGet(request, response);
        writer.flush();
    
        String expectedJson = "[\"turno1\"]";
        assertEquals(expectedJson, stringWriter.toString().trim());
    }

    @Test
    public void testGetTurnos_NeedToGetEmpty() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        
        List<String> curso1 = new ArrayList<>();
        curso1.add("IGE");
        List<String> curso2 = new ArrayList<>();
        curso2.add("SS");

        Horario horario1 = new Horario();
        horario1.setTurno("turno1");
        horario1.setUnidadeCurricular("PISID");
        horario1.setCurso(curso1);

        Horario horario2 = new Horario();
        horario2.setTurno("turno2");
        horario2.setUnidadeCurricular("ES");
        horario2.setCurso(curso2);

        List<Horario> eventos = Arrays.asList(horario1, horario2);

        when(request.getSession()).thenReturn(session);
        session.setAttribute("horarios", eventos);
        when(session.getAttribute("horarios")).thenReturn(eventos);

        when(request.getParameter("course")).thenReturn(null);
        when(request.getParameter("uc")).thenReturn(null);

           
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
    
        new GetTurnosServlet().doGet(request, response);
        writer.flush();
    
        String expectedJson = "[]";
        assertEquals(expectedJson, stringWriter.toString().trim());
    }
}
