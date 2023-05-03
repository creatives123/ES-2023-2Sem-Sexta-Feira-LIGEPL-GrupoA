package web.CalendarServlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import Translators.HorarioToCalendarTranslator;
import models.CalendarModel;
import models.Horario;

public class GetStudentCalendarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = new ObjectMapper().writeValueAsString(getCalendars());
    
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private List<CalendarModel> getCalendars() {
        List<Horario> events = new ArrayList<>(); // create a new empty list of Horario objects
        return HorarioToCalendarTranslator.translateHorariosToCalendars(events);
    }
}
