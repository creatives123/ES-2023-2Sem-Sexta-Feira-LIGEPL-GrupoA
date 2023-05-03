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
}