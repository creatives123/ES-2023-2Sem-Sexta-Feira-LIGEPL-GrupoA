package services;

import java.io.BufferedReader;
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

import models.Horario;

public class WebCalImporter {

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
      String eventContent = matcher.group();
      String summary = findValue(eventContent, "SUMMARY:");
      String description = findValue(eventContent, "DESCRIPTION:");
      String location = findValue(eventContent, "LOCATION:");
      String startStr = findValue(eventContent, "DTSTART:");
      String endStr = findValue(eventContent, "DTEND:");

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

      //TODO: Criar um objeto Horario
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

  public static void main(String[] args) {
    try {
      // Import the WebCal calendar from the URL
      importFromUrl(
          "webcal://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=egcoo@iscte.pt&password=VPV5WcgZrMDC13ZjUHA5bvDgVdyTrrNRAf1o7pY3XwQX6xeIQq2MNgmpexBfJndMXiPl6m2J3zste39kySbkE46tFMBi4Pu3zUk1h2XWcxzbPGuOef9ybLzzaAvE7SAE");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
