package web.CalendarServlets;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class WebCallImporterServletTest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
    }

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
   
}
