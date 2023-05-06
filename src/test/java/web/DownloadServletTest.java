package web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DownloadServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Test
    public void testDoGetJson() throws IOException {
        DownloadServlet servlet = new DownloadServlet();

        when(request.getParameter("type")).thenReturn("json");
        when(request.getParameter("horario")).thenReturn("horarios");
        when(request.getSession()).thenReturn(session);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        when(response.getOutputStream()).thenReturn(new ServletOutputStreamWrapper(outputStream));

        servlet.doGet(request, response);

        verify(response, times(1)).setContentType("application/json");
        verify(response, times(1)).setHeader("Content-Disposition", "attachment; filename=Horario.json");
    }

    @Test
    public void testDoGetCsv() throws IOException {
        DownloadServlet servlet = new DownloadServlet();

        when(request.getParameter("type")).thenReturn("csv");
        when(request.getParameter("horario")).thenReturn("horarios");
        when(request.getSession()).thenReturn(session);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        when(response.getOutputStream()).thenReturn(new ServletOutputStreamWrapper(outputStream));

        servlet.doGet(request, response);

        verify(response, times(1)).setContentType("text/csv");
        verify(response, times(1)).setHeader("Content-Disposition", "attachment; filename=Horario.csv");
    }

}
