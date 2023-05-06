// package web.CalendarServlets;

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.net.HttpURLConnection;
// import java.net.MalformedURLException;
// import java.net.URL;
// import java.net.URLConnection;
// import java.util.ArrayList;
// import java.util.Calendar;
// import java.util.List;
// import java.util.TimeZone;
// import java.util.regex.Matcher;
// import java.util.regex.Pattern;

// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

// import models.Horario;

// public class WebCallImporterServlet extends HttpServlet {
//   public static final String WEBCAL_HORARIO ="WebcalHorario";

//   @Override
//   protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws IOException {
//     request.getSession().removeAttribute(WEBCAL_HORARIO);
//     String message ="";
    
//     String uri = request.getParameter("uri");
      
//     message = importFromUrl(uri, request);

//     request.getSession().setAttribute("messageUpload", message);
//     response.sendRedirect(request.getContextPath() + "/index.jsp");
//   }

// }
