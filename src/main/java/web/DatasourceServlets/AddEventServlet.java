package web.DatasourceServlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Horario;
import services.CommonManager;

public class AddEventServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      
        String curso = request.getParameter("curso");
        String uc = request.getParameter("uc");
        String turno = request.getParameter("turno");

        List<Horario> events = CommonManager.getHorariosFromSession(request.getSession());
        List<Horario> studentSchedule = CommonManager.getStudentHorarioFromSession(request.getSession());

        for(Horario h : events) {
            if (h.getCurso().contains(curso) && h.getUnidadeCurricular().equals(uc) && h.getTurno().equals(turno)
                    && !studentSchedule.contains(h)) {

                CommonManager.addToStudentHorarioFromSession(request.getSession(), h);
            }
        }
    }
}