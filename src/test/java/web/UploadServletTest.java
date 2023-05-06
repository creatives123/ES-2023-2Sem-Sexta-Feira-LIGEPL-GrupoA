package web;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

import static org.mockito.Mockito.*;

/**
 * Classe de testes para o UploadServlet.
 */
public class UploadServletTest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private ServletFileUpload servletFileUpload;

    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        servletFileUpload = mock(ServletFileUpload.class);
    }

    /**
     * Testa o upload de um ficheiro CSV.
     */
    @Test
    public void testDoPostCsvFileUpload() throws Exception {
        // Define os mocks necessários
        when(request.getContentType()).thenReturn("multipart/form-data");
        when(request.getSession()).thenReturn(session);

        // Prepara um ficheiro CSV como um array de bytes
        String csvContent = "col1,col2,col3\nvalue1,value2,value3";
        InputStream csvInputStream = new ByteArrayInputStream(csvContent.getBytes(StandardCharsets.UTF_8));

        // Define o mock para o upload de ficheiros
        when(servletFileUpload.parseRequest(request)).thenReturn(Collections.singletonList(new MockFileItem(csvInputStream, "test.csv")));

        // Chama o método doPost
        UploadServlet servlet = new UploadServlet();
        servlet.doPost(request, response);

       // Verifica se o atributo da sessão "horarios" foi removido
        verify(session).removeAttribute(UploadServlet.HORARIOS_SESSION);

        // Verifica se o atributo da sessão "messageUpload" foi definido
        verify(session).setAttribute(eq("messageUpload"), anyString()); // Substituir "messageUpload" pelo nome real do atributo

        // Verifica se o utilizador é redirecionado para a página index.jsp
        verify(response).sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
