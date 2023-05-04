package web.DatasourceServlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.Horario;
import services.CommonManager;

public class GetTurnosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = new ObjectMapper().writeValueAsString(getTurnos(request));
    
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private List<String> getTurnos(HttpServletRequest request) {
        String course = request.getParameter("course");
        String uc = request.getParameter("uc");

        List<Horario> events = CommonManager.getHorariosFromSession(request.getSession());
        Set<String> turnos = new HashSet<>();

        for (Horario event : events) {
            if (event.getCurso().contains(course) && event.getUnidadeCurricular().equals(uc)) {
                turnos.add(event.getTurno());
            }
        }
        return new ArrayList<>(turnos);
    }
}

