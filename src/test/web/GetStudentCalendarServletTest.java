package web.CalendarServlets;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.CalendarModel;
import models.CalendarWrapper;
import models.Horario;
import services.CommonManager;

/**
 * A classe GetStudentCalendarServletTest é uma classe de teste unitário para o servlet GetStudentCalendarServlet.
 * 
 * Esta classe usa o framework JUnit e a biblioteca Mockito para simular as dependências do servlet (HttpServletRequest, HttpServletResponse, 
 * CommonManager, HorarioToCalendarTranslator, etc.) e testar a lógica de negócios do servlet.
 */
@RunWith(MockitoJUnitRunner.class)
public class GetStudentCalendarServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private GetStudentCalendarServlet servlet;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Testa o método doGet do servlet, verificando se a resposta JSON gerada pelo servlet corresponde ao JSON esperado.
     * 
     * @throws ServletException se ocorrer um erro durante o processamento do pedido
     * @throws IOException se ocorrer um erro ao escrever o JSON da resposta no fluxo de saída
     */

    @Test
    public void testDoGet() throws ServletException, IOException {
        // Setup
        Horario event1 = new Horario();
        event1.setId(1);
        Horario event2 = new Horario();
        event2.setId(2);
        Horario event3 = new Horario();
        event3.setId(3);

        CalendarModel calendar1 = new CalendarModel();
        calendar1.setHorario(event1);
        CalendarModel calendar2 = new CalendarModel();
        calendar2.setHorario(event2);
        CalendarModel calendar3 = new CalendarModel();
        calendar3.setHorario(event3);

        when(CommonManager.getStudentHorarioFromSession(request.getSession())).thenReturn(Arrays.asList(event1, event2, event3));
        when(HorarioToCalendarTranslator.translateHorariosToCalendars(Arrays.asList(event1, event2, event3))).thenReturn(Arrays.asList(calendar1, calendar2, calendar3));

        StringWriter stringWriter = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));

        // Exercise
        servlet.doGet(request, response);

        // Verify
        verify(response).setContentType("application/json");
        verify(response).setCharacterEncoding("UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();
        CalendarWrapper expectedWrapper = servlet.treatEvents(Arrays.asList(event1, event2, event3), Arrays.asList(calendar1, calendar2, calendar3));
        String expectedJson = objectMapper.writeValueAsString(expectedWrapper);

        assertEquals(expectedJson, stringWriter.toString());
    }
}
