package web.CalendarServlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Horario;

public class WebCallImporterServlet extends HttpServlet {
  public static final String WEBCAL_HORARIO ="Webcal Horario";

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
    request.getSession().removeAttribute(WEBCAL_HORARIO);
    String uri = request.getParameter("uri");

        try {
            List<Horario> events = importFromUrl(uri);
            request.getSession().setAttribute(WEBCAL_HORARIO, events);
            response.sendRedirect(request.getContextPath() + "/index.jsp"); //redirect to horario page display

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
  }

  public static List<Horario> importFromUrl(String uri) throws Exception {

    // Convert the webcal URL to an HTTP URL
    if (uri.startsWith("webcal://")) {
      uri = "https://" + uri.substring(9);
    }

    URL url = new URL(uri);
    // Read the WebCal calendar from the URL
    List<Horario> events = new ArrayList<>();
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Content-Type", "text/calendar");
    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    String line;
    StringBuilder content = new StringBuilder();
    while ((line = reader.readLine()) != null) {
      content.append(line);
    }

    // Parse the WebCal calendar events using regular expressions
    Pattern pattern = Pattern.compile("BEGIN:VEVENT(.+?)END:VEVENT", Pattern.DOTALL);
    Matcher matcher = pattern.matcher(content.toString());
    while (matcher.find()) {

      // Extract the event properties from the match groups
      String eventContent = matcher.group(0);
      String summary = findValue(eventContent, "SUMMARY");
      String description = findValue(eventContent, "DESCRIPTION");
      String location = findValue(eventContent, "LOCATION");
      String startStr = findValue(eventContent, "DTSTART");
      String endStr = findValue(eventContent, "DTEND");

      // Create a new Horario object
      System.out.println("  ");
      System.out.println("Event content:");
      System.out.println(eventContent);
      System.out.println("Summary: ");
      System.out.println(summary);
      System.out.println("Description: ");
      System.out.println(description);
      System.out.println("Location: ");
      System.out.println(location);
      System.out.println("Start: ");
      System.out.println(startStr);
      System.out.println("End: ");
      System.out.println(endStr);
      System.out.println("  ");

      //TODO: Criar um objeto Horario e adicionar à lista a devolver 
      Horario horario = new Horario();
      
      events.add(horario);

    }

    return events;
  }

  private static String findValue(String content, String name) {
    String pattern = name + "(.*?)\\n";
    Pattern p = Pattern.compile(pattern);
    Matcher m = p.matcher(content);
    if (m.find()) {
      return m.group(1).trim();
    }
    return null;
  }

  // public static void main(String[] args) {
  //   try {
  //     // Import the WebCal calendar from the URL
  //     importFromUrl(
  //         "webcal://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=egcoo@iscte.pt&password=VPV5WcgZrMDC13ZjUHA5bvDgVdyTrrNRAf1o7pY3XwQX6xeIQq2MNgmpexBfJndMXiPl6m2J3zste39kySbkE46tFMBi4Pu3zUk1h2XWcxzbPGuOef9ybLzzaAvE7SAE");
  //   } catch (Exception e) {
  //     e.printStackTrace();
  //   }
  // }
}