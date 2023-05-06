package web.DatasourceServlets;


import static org.junit.Assert.assertEquals;
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

import models.Horario;

public class GetCoursesServletTest {

    @Mock
    HttpServletRequest request;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCourses() throws IOException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        
        // Cria uma lista de horarios
        List<Horario> horarios = new ArrayList<>();

        // Cria alguns objetos Horario e adiciona-os à lista
        Horario horario1 = new Horario();
        horario1.setCurso(Arrays.asList("LEI"));
        
        horarios.add(horario1);
        
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        session.setAttribute("horarios", horarios);
        when(session.getAttribute("horarios")).thenReturn(horarios);
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
    
        new GetCoursesServlet().doGet(request, response);
        writer.flush();
    
        String expectedJson = "[\"LEI\"]";
        assertEquals(expectedJson, stringWriter.toString().trim());
    }
}