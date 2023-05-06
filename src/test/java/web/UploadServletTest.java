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

    @Test
    public void testDoPostCsvFileUpload() throws Exception {
        // Set up necessary mocks
        when(request.getContentType()).thenReturn("multipart/form-data");
        when(request.getSession()).thenReturn(session);

        // Prepare a CSV file as a byte array
        String csvContent = "col1,col2,col3\nvalue1,value2,value3";
        InputStream csvInputStream = new ByteArrayInputStream(csvContent.getBytes(StandardCharsets.UTF_8));

        // Set up the file upload mock
        when(servletFileUpload.parseRequest(request)).thenReturn(Collections.singletonList(new MockFileItem(csvInputStream, "test.csv")));

        // Call the doPost method
        UploadServlet servlet = new UploadServlet();
        servlet.doPost(request, response);

       // Verify that the session attribute "horarios" is removed
        verify(session).removeAttribute(UploadServlet.HORARIOS_SESSION);

        // Verify that the session attribute "messageUpload" is set
        verify(session).setAttribute(eq("messageUpload"), anyString()); // Replace "messageUpload" with the actual attribute name you use

        // Verify that the user is redirected to the index.jsp page
        verify(response).sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
