package web.DatasourceServlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Horario;
import services.CommonManager;

public class DeleteEventsServlet extends HttpServlet {

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String uc = request.getParameter("uc");
        String turno = request.getParameter("turno");

        List<Horario> horarios = CommonManager.getStudentHorarioFromSession(request.getSession());
        horarios.removeIf(h -> (h.getUnidadeCurricular().equals(uc) && h.getTurno().equals(turno)));
        request.getSession().setAttribute("student_horario", horarios);
    }
}