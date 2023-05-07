package web.CalendarServlets;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
/**
 * Classe de teste para {@link GetCalendarServlet}.
 */
@RunWith(MockitoJUnitRunner.class)
public class GetCalendarServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    /**
     * Testa o método {@link GetCalendarServlet#doGet(HttpServletRequest, HttpServletResponse)}.
     * @throws IOException se ocorrer um erro durante a execução do método testado.
     */
    @Test
    public void testDoGet() throws IOException {
        GetCalendarServlet servlet = new GetCalendarServlet();

        when(request.getParameter("horario")).thenReturn("horarios");
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request, response);

        verify(response, times(1)).setContentType("application/json");
        verify(response, times(1)).setCharacterEncoding("UTF-8");

        writer.flush();
        String jsonResponse = stringWriter.toString();

        // You may need to adjust this assertion to match the expected JSON output based on your test data
        assertEquals("[]", jsonResponse);
    }
}
