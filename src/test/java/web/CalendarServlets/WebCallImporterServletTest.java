package web.CalendarServlets;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.io.UnsupportedEncodingException;
/**
 * Esta classe testa a funcionalidade do {@link WebCallImporterServlet}.
 */
public class WebCallImporterServletTest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    /**
     * Testa o método {@link WebCallImporterServlet#importFromUrl(String, HttpServletRequest)}.
     */
    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
    }

    /**
     * Testa o método {@link WebCallImporterServlet#doGet(HttpServletRequest, HttpServletResponse)}.
     * 
     * @throws Exception se ocorrer um erro durante a execução do método testado.
     */
    @Test
    public void testDoGet() throws Exception {
        // Set up necessary mocks
        when(request.getParameter("uri")).thenReturn("webcal://test.example.com/test.ics");
        when(request.getSession()).thenReturn(session);
        when(request.getContextPath()).thenReturn("/test");

        // Call the doGet method
        WebCallImporterServlet servlet = new WebCallImporterServlet();
        servlet.doGet(request, response);

        // Verify that the session attribute "webcalHorario" is removed
        verify(session).removeAttribute(WebCallImporterServlet.WEBCAL_HORARIO);

        // Verify that the session attribute "messageUpload" is set
        verify(session).setAttribute(eq("messageUpload"), anyString());

        // Verify that the user is redirected to the index.jsp page
        verify(response).sendRedirect(request.getContextPath() + "/index.jsp");
    }

    /**
     * Testa o método {@link WebCallImporterServlet#importFromUrl(String, HttpServletRequest)}.
     * 
     * @throws UnsupportedEncodingException se ocorrer um erro de codificação durante a execução do método testado.
     */
    @Test
    public void testCalendarBuilderError() throws UnsupportedEncodingException {
        // Mock a HttpURLConnection that returns valid WebCal content, but the CalendarBuilder throws an exception
        String validContent = "BEGIN:VCALENDAR\r\n" +
                "VERSION:2.0\r\n" +
                "BEGIN:VEVENT\r\n" +
                "SUMMARY:Test Event\r\n" +
                "DTSTART:20230506T090000Z\r\n" +
                "DTEND:20230506T110000Z\r\n" +
                "LOCATION:Test Location\r\n" +
                "END:VEVENT\r\n" +
                "END:VCALENDAR";
        HttpURLConnectionStub connectionStub = new HttpURLConnectionStub(validContent);
        connectionStub.setThrowIOException(true);

        String message = WebCallImporterServlet.importFromUrl("https://test.example.com/test.ics", request);
        assertEquals("Erro ao obter InputStream", message);
    }
   
}
