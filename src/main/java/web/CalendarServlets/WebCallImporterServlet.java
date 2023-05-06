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
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Horario;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.component.VEvent;
import services.DateManager;

/**
 * Servlet que importa um calendário no formato Webcal e o converte em uma lista de horários.
 */
public class WebCallImporterServlet extends HttpServlet {
  public static final String WEBCAL_HORARIO ="webcalHorario";
/**
    Este método é usado para tratar 'GET requests' do cliente.
    Remove o atributo "WEBCAL_HORARIO" da sessão e importa eventos de calendários de um URL introduzido pelo utilizador,
    usando o método "importFromUrl(String, HttpServletRequest)".
    A mensagem proveniente desse método resulta message is set as a session attribute "messageUpload" and the user is redirected to the index.jsp page.
    @param request {@link HttpServletRequest} é o objeto que contém o 'request' do cliente
    @param response {@link HttpServletResponse} é o objeto que o contém a 'response' do 'servlet's response
    @throws IOException if an input or output exception occurs while the servlet is handling the GET request
    */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws IOException {
    // Caso o atributo "WEBCAL_HORARIO" esteja preenchido, é descartado
    request.getSession().removeAttribute(WEBCAL_HORARIO);
    String message ="";
    String uri = request.getParameter("uri");
    // Import calendar events from the user-provided URL
     message = importFromUrl(uri, request);
     // Set the resulting message as a session attribute "messageUpload" and redirect the user to the index.jsp page
     request.getSession().setAttribute("messageUpload", message);
     response.sendRedirect(request.getContextPath() + "/index.jsp");   
  }

  /**
   * Importa um calendário no formato Webcal a partir de uma URL.
   *
   * @param uri a URL que aponta para o calendário Webcal.
   * @param request o objeto HttpServletRequest que contém a sessão do usuário.
   * @return uma mensagem de feedback indicando se a importação foi bem sucedida ou não.
   */
  public static String importFromUrl(String uri, HttpServletRequest request) {
    List<Horario> events = new ArrayList<>();

    // Converter a URL webcal para uma URL HTTP
    if (uri.startsWith("webcal://")) {
      uri = "https://" + uri.substring(9);
    }

    // Criar a URL
    URL url;
    try {
      url = new URL(uri);
    } catch (MalformedURLException e) {
      return "URL inválido";
    }

    // Ler o calendário Webcal a partir da URL
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
    conn.setRequestProperty("Accept-Charset", "UTF-8");
    
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
        content.append(line).append(System.lineSeparator()); // Adicionar o separador de linha após cada linha
      }
      reader.close();
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
      String[] fields = new String[11];

      fields[0] = "";
      fields[1] = vEvent.getSummary().toString().substring(vEvent.getSummary().toString().indexOf(':') + 1);  
      fields[2] = "";
      fields[3] = "";
      fields[4] = "0";
      fields[5] = "";
      fields[6] = DateManager.getCorrectFormatTimeFromWebCal(vEvent.getStartDate().toString().substring(vEvent.getStartDate().toString().indexOf(":") +1));
      fields[7] = DateManager.getCorrectFormatTimeFromWebCal(vEvent.getEndDate().toString().substring(vEvent.getEndDate().toString().indexOf(":") +1));
      fields[8] = DateManager.getCorrectFormatDateFromWebCal(vEvent.getStartDate().toString().substring(vEvent.getStartDate().toString().indexOf(":") +1));
      fields[9] = vEvent.getLocation().toString().substring(vEvent.getLocation().toString().indexOf(':') + 1).replace("\\", ""); 
      fields[10] = "0";
      System.out.println();

      int i = 0;
      for(String e : fields) {
        fields[i++] = e.replaceAll("\r", "").replaceAll("\n", "");
      }

      Horario horario = new Horario(fields);
      events.add(horario);

     request.getSession().setAttribute(WEBCAL_HORARIO, events);
    }

    return "O Horário foi importado com sucesso";
  }

  // public static void main(String[] args){
  //   importFromUrl("webcal://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=egcoo@iscte.pt&password=VPV5WcgZrMDC13ZjUHA5bvDgVdyTrrNRAf1o7pY3XwQX6xeIQq2MNgmpexBfJndMXiPl6m2J3zste39kySbkE46tFMBi4Pu3zUk1h2XWcxzbPGuOef9ybLzzaAvE7SAE");
  // }
}
