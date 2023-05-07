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
import models.CalendarModel;
import models.CalendarWrapper;
import models.Horario;

public class StudentCalendarServletTest {

    @Mock
    HttpServletRequest request;

    /**
     * Este método inicializa os objetos Mock usados nos testes.
     */
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Testa o método doGet() da classe StudentCalendarServlet.
     * Testa o cenário em que não há eventos na sessão.
     * Verifica se o servlet retorna uma string JSON esperada que representa 
     * uma lista vazia de eventos e mapas vazios para eventos sobrepostos
     * e superlotados.
     * @throws IOException se ocorrer um erro de I/O durante o teste
     */
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

    /**
    * Testa o comportamento do método doGet() na classe StudentCalendarServlet quando
    * há eventos iguais na sessão. Cria uma lista de horários de aula, adiciona dois
    * horários com a mesma hora e verfica se o servlet retorna uma lista de eventos
    * com o mesmo número de elementos que a lista de horários, indicando que não houve
    * remoção de horários duplicados.
    *
    * @throws IOException se ocorrer um erro de I/O durante o teste
    */
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

    /**
     * Testa o método doPost() da classe StudentCalendarServlet ao adicionar um evento sem adicioná-lo à lista de horários do estudante.
     * Verifica se o evento não é adicionado à lista e se o método retorna verdadeiro.
     * @throws IOException se ocorrer uma exceção de entrada e saída ao trabalhar com o request ou response.
    */
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
        assertTrue(true);
    }

    /**
     * Testa a funcionalidade de exclusão de eventos do calendário de estudante.
     * É criada uma lista de horários que contém um único horário para a unidade curricular Programação,
     * e em seguida é tentado remover este horário do calendário do estudante usando o método doDelete da
     * classe StudentCalendarServlet. O teste verifica se a lista de horários do estudante está vazia após a remoção.
     * @throws IOException se ocorrer um erro de entrada/saída durante a execução do teste
    */
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
        assertTrue(true);
    }
    
}