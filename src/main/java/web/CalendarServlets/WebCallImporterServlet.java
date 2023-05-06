package web.CalendarServlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Horario;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.component.VEvent; 



public class WebCallImporterServlet extends HttpServlet {
  public static final String WEBCAL_HORARIO ="WebcalHorario";

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws IOException {
    request.getSession().removeAttribute(WEBCAL_HORARIO);
    String message ="";
          String uri = request.getParameter("uri");
            
            message = importFromUrl(uri, request);
 
            request.getSession().setAttribute("messageUpload", message);
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            
        
  }

  public static String importFromUrl(String uri, HttpServletRequest request) {
    List<Horario> events = new ArrayList<>();
    
    //Convert the webcal URL to an HTTP URL
    if (uri.startsWith("webcal://")) {
        uri = "https://" + uri.substring(9);
    }

    //create url
    URL url;
    try {
      url = new URL(uri);
    } catch (MalformedURLException e) {
      return "URL inválido";
    }

    // Read the WebCal calendar from the URL
    HttpURLConnection conn;
    try {
      conn = (HttpURLConnection) url.openConnection();
    } catch (IOException e) {
      return "Erro ao abrir ligação HTTP";
    }

    try {
      conn.setRequestMethod("GET");
    } catch (ProtocolException e) {
      return "Erro no Protocolo";
    }

    conn.setRequestProperty("Content-Type", "text/calendar");

    InputStream inputStream;
    try {
      inputStream = conn.getInputStream();
    } catch (IOException e) {
      return "Erro ao obter InputStream";
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    StringBuilder content = new StringBuilder();
    String line;

    
    try {
      while ((line = reader.readLine()) != null) {
          content.append(line).append(System.lineSeparator()); // Append the line separator after each line
          reader.close();
      }
    } catch (IOException e) {
      return "Erro a obter conteúdo WebCal";
    }

    StringReader sin = new StringReader(content.toString());
    CalendarBuilder builder = new CalendarBuilder();
    Calendar calendar;
    try {
      calendar = builder.build(sin);
    } catch (IOException e) {
      return "Erro ao construir Calendar";
    } catch (ParserException e) {
      return "Erro ao obter dados para o Calendar";
    }

    // Process the events in the calendar object
    for (Object obj : calendar.getComponents("VEVENT")) {
        VEvent vEvent = (VEvent) obj;
        String[] fields = new String[10];      

        
        fields[0] = "";
        fields[1] = vEvent.getSummary().toString();
        fields[2] = "";
        fields[3] = "";
        fields[4] = "";
        fields[5] = "";
        fields[6] = "";
        fields[7] = "";
        fields[8] = "";
        fields[9] = "";

        
        //System.out.println("getName: " + vEvent.getName().toString());

        System.out.println();
        
        System.out.println(vEvent.getProperties().toString());
        // System.out.println("getDescription:  " + vEvent.getDescription().toString());
        // //System.out.println("getClassification: " + vEvent.getClassification());
        // //System.out.println("getCreated: " + vEvent.getCreated());
        // System.out.println("getLocation: " + vEvent.getLocation());
        // System.out.println("getStartDate: " + vEvent.getStartDate());
        // System.out.println("getEndDate: " + vEvent.getEndDate());
        // //System.out.println("getStatus: " + vEvent.getStatus());
        
        System.out.println();
        System.out.println("******------------_------------******");
        // for(String i : fields)
        //   if (!i.isEmpty())
        //     System.out.println(i.toString());
        
        

        
        //events.add(horario);

        //TODO fazer o set da lista de horarios lá para a sessão como está no uploadServlet
        request.getSession().setAttribute(WEBCAL_HORARIO, events);
    }
    
    return "O Horário foi importado com sucesso";
}


}
