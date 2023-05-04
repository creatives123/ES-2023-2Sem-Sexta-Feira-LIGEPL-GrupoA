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

public class GetUCsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = new ObjectMapper().writeValueAsString(getUCs(request));
    
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private List<String> getUCs(HttpServletRequest request) {
        String course = request.getParameter("course");
        List<Horario> events = CommonManager.getHorariosFromSession(request.getSession());

        Set<String> ucs = new HashSet<>();

        for (Horario event : events) {
            if (event.getCurso().contains(course)) {
                ucs.add(event.getUnidadeCurricular());
            }
        }
        return new ArrayList<>(ucs);
    }
}