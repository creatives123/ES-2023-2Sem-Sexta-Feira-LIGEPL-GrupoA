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
/**
 * Este é o arquivo de teste para a classe DownloadServlet. Ele testa os métodos doGet() que geram o download de arquivos JSON ou CSV
 */
@RunWith(MockitoJUnitRunner.class)
public class DownloadServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    /**
     * Testa o método doGet() que gera o download de um arquivo JSON.
     * @throws IOException se houver um erro ao lidar com a entrada ou saída de dados
     */
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

    /**
     * Testa o método doGet() que gera o download de um arquivo CSV.
     * @throws IOException se houver um erro ao lidar com a entrada ou saída de dados
     */
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
