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

public class GetCoursesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = new ObjectMapper().writeValueAsString(getCourses(request));
    
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private List<String> getCourses(HttpServletRequest request) {
        List<Horario> events = CommonManager.getHorariosFromSession(request.getSession());
        Set<String> courses = new HashSet<>();

        for (Horario event : events) {
            for(String curso : event.getCurso()) {
                courses.add(curso);
            }
        }
        return new ArrayList<>(courses);
    }
}
