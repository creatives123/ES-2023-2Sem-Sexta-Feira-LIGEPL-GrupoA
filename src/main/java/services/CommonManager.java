package services;

import java.util.List;

import javax.servlet.http.HttpSession;

import models.Horario;

/**
 * Responsável por abrir uma sessão HTTP para a obtenção 
 * de Horarios
 * <p>
 * Métodos:
 * <p>
 * {@link #getHorariosFromSession(HttpSession)}
 */
public class CommonManager {

    private CommonManager() {}

    /**
     * Obtém uma lista de Horários da HttpSession.
     *
     * @param session a HttpSession da qual se pretende obter a lista de Horários
     * @return uma lista de Horários obtida da HttpSession
     * @throws IllegalStateException se a HttpSession não contiver uma lista de Horários
     */
    @SuppressWarnings("unchecked")
    public static List<Horario> getHorariosFromSession(HttpSession session) {
        Object horariosObject = session.getAttribute("horarios");
        if (horariosObject != null) return (List<Horario>) horariosObject;
        throw new IllegalStateException("Horarios not found in session");
    }
}