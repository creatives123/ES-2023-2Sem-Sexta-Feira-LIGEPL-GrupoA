package services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import models.Horario;

/**
 * Responsável por abrir uma sessão HTTP para a obtenção 
 * de {@link Horario}s
 * <p>
 * Métodos:
 * <p>
 * {@link #getHorariosFromSession(HttpSession)}
 */
public class CommonManager {

    private CommonManager() {}

    /**
     * Obtém uma lista de {@link Horario}s da {@link HttpSession}.
     *
     * @param session a {@link HttpSession} da qual se pretende obter a lista de {@link Horario}s
     * @return uma lista de {@link Horario}s obtida da {@link HttpSession}
     * @throws IllegalStateException se a {@link HttpSession} não contiver uma lista de {@link Horario}s
     */
    @SuppressWarnings("unchecked")
    public static List<Horario> getHorariosFromSession(HttpSession session) {
        Object horariosObject = session.getAttribute("horarios");
        if (horariosObject != null) return (List<Horario>) horariosObject;
        else return new ArrayList<>();
    }

    /**
     * @param session
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<String> getCoursesFromSession(HttpSession session) {
        Object courses = session.getAttribute("courses");
        if (courses != null) return (List<String>) courses;
        else return new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public static List<Horario> getStudentHorarioFromSession(HttpSession session) {
        Object horariosObject = session.getAttribute("student_horario");
        if (horariosObject != null) return (List<Horario>) horariosObject;
        else return new ArrayList<>();
    }

    public static void addToStudentHorarioFromSession(HttpSession session, Horario horario) {
        List<Horario> horarios = getStudentHorarioFromSession(session);
        horarios.add(horario);
        session.setAttribute("student_horario", horarios);
    }
}