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
     * @return uma lista de {@link Horario}s que contém informação de todo o semestre obtida através da {@link HttpSession}
     * @throws IllegalStateException se a {@link HttpSession} não contiver uma lista de {@link Horario}s
     */
    @SuppressWarnings("unchecked")
    public static List<Horario> getHorariosFromSession(HttpSession session) {
        Object horariosObject = session.getAttribute("horarios");
        if (horariosObject != null) return (List<Horario>) horariosObject;
        else return new ArrayList<>();
    }

    /**
    * Devolve a lista dos cursos guardada na sessão.
    * Se o atributo "courses" estiver vazio será devolvida uma lista vazia.
    * @param session a {@link HttpSession} da qual se pretende obter a lista de cursos (String)
    * @return uma lista de Strings que representa os cursos na sessão, ou uma lista vazia caso a variável de sessão esteja vazia.
    */
    @SuppressWarnings("unchecked")
    public static List<String> getCoursesFromSession(HttpSession session) {
        Object courses = session.getAttribute("courses");
        if (courses != null) return (List<String>) courses;
        else return new ArrayList<>();
    }

    /** horario do estudante
    * Devolve a lista dos cursos guardada na sessão.
    * @param session a {@link HttpSession} da qual se pretende obter a lista de {@link Horario}s
    * @return uma lista de objeto Horario objects pertencentes a um estudante, ou uma lista vazia caso a variável de sessão esteja vazia.
    */
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

    /**
    * Devolve a lista de {@link Horario} objetos guardados na 'the HttpSession' no atributo "webcalHorario".
    * Se o atributo estiver razio é devolvida uma lista vazia.
    * @param session a "HttpSession" de onde se vai extrair o atributo "webcalHorario".
    * @return a lista objetos Horario, ou uma lista vazia caso a variável de sessão esteja vazia.
    */
    @SuppressWarnings("unchecked")
    public static List<Horario> getIcalHorariosFromSession(HttpSession session) {
        Object horariosObject = session.getAttribute("webcalHorario");
        if (horariosObject != null) return (List<Horario>) horariosObject;
        else return new ArrayList<>();
    }
}