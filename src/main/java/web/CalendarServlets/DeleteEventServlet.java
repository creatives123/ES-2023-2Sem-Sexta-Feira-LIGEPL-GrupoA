package web.CalendarServlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Horario;
import services.CommonManager;

public class DeleteEventServlet extends HttpServlet {
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String curso = request.getParameter("curso");
        String uc = request.getParameter("uc");
        String turno = request.getParameter("turno");

        List<Horario> horarios = CommonManager.getStudentHorarioFromSession(request.getSession());
        horarios.removeIf(h ->
            (h.getUnidadeCurricular().equals(uc))
            && h.getCurso().contains(curso)
            && h.getTurno().equals(turno));

        request.getSession().setAttribute("student_horario", horarios);
    }
}