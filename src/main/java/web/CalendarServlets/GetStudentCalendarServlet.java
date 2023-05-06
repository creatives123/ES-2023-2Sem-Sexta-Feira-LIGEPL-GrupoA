package web.CalendarServlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import Translators.HorarioToCalendarTranslator;
import models.CalendarModel;
import models.CalendarWrapper;
import models.Horario;
import services.CommonManager;

public class GetStudentCalendarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = new ObjectMapper().writeValueAsString(getCalendars(request));
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private CalendarWrapper getCalendars(HttpServletRequest request) {
        List<Horario> events = CommonManager.getStudentHorarioFromSession(request.getSession());
        List<CalendarModel> calendars = HorarioToCalendarTranslator.translateHorariosToCalendars(events);
        return treatEvents(events, calendars);
    }

    private CalendarWrapper treatEvents(List<Horario> events, List<CalendarModel> calendars) {
        CalendarWrapper calendarWrapper = new CalendarWrapper();
        int overCrowdedEventsCounter = 0;
        int overLappedEventsCounter = 0;

        for (Horario h : events) {
            for(CalendarModel m : calendars) {
                if (h.equals(m.getHorario())) {
                    if (m.getHorario().isOverCrowded()) {
                        m.setColor("yellow");
                        overCrowdedEventsCounter++;
                    }
                }
                else {
                    if (m.getHorario().sameInterval(h)) {
                        m.setColor("orange");
                        overLappedEventsCounter++;
                    }
                }
            }
        }

        calendarWrapper.setEvents(calendars);
        calendarWrapper.setOverCrowdedEventsCounter(overCrowdedEventsCounter);
        calendarWrapper.setOverlappedEventsCounter(overLappedEventsCounter);

        return calendarWrapper;
    }
}