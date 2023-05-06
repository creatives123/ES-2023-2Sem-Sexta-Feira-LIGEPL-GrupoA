package web.CalendarServlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import Translators.HorarioToCalendarTranslator;
import models.CalendarModel;
import models.CalendarWrapper;
import models.Horario;
import services.CommonManager;

/**
 * A classe GetStudentCalendarServlet é um servlet que trata os pedidos GET para obter os eventos do calendário de um estudante.
 * 
 * Este servlet obtém os eventos do calendário de um estudante da sessão, converte-os em objetos CalendarModel usando o HorarioToCalendarTranslator e depois
 * retorna uma resposta JSON contendo os CalendarModels e informações adicionais sobre os eventos.
 */
public class GetStudentCalendarServlet extends HttpServlet {

    /**
     * Trata os pedidos GET para obter os eventos do calendário de um estudante.
     * 
     * @param request o objeto HttpServletRequest contendo informações sobre o pedido
     * @param response o objeto HttpServletResponse utilizado para enviar a resposta
     * @throws IOException se ocorrer um erro ao escrever o JSON da resposta no fluxo de saída
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = new ObjectMapper().writeValueAsString(getCalendars(request));
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    /**
     * Obtém os eventos do calendário de um estudante a partir da sessão e converte-os em objetos CalendarModel.
     * 
     * @param request o objeto HttpServletRequest contendo informações sobre o pedido
     * @return um objeto CalendarWrapper contendo os objetos CalendarModel convertidos e informações adicionais sobre os eventos
     */
    private CalendarWrapper getCalendars(HttpServletRequest request) {
        List<Horario> eventos = CommonManager.getStudentHorarioFromSession(request.getSession());
        List<CalendarModel> calendarios = HorarioToCalendarTranslator.translateHorariosToCalendars(eventos);
        return tratarEventos(eventos, calendarios);
    }

    /**
     * Atualiza a cor dos objetos CalendarModel com base na sua lotação e sobreposição com outros eventos.
     * 
     * @param eventos a lista de objetos Horario que representam os eventos do calendário do estudante
     * @param calendarios a lista de objetos CalendarModel que representam os eventos do calendário do estudante
     * @return um objeto CalendarWrapper contendo os objetos CalendarModel atualizados e informações adicionais sobre os eventos
     */
    private CalendarWrapper tratarEventos(List<Horario> eventos, List<CalendarModel> calendarios) {
        CalendarWrapper calendarWrapper = new CalendarWrapper();
        int contadorEventosLotados = 0;
        int contadorEventosSobrepostos = 0;

        for (Horario h : eventos) {
            for(CalendarModel m : calendarios) {
                if (h.equals(m.getHorario())) {
                    if (m.getHorario().isOverCrowded()) {
                        m.setColor("amarelo");
                        contadorEventosLotados++;
                    }
                }
                else {
                    if (m.getHorario().sameInterval(h)) {
                        m.setColor("laranja");
                        contadorEventosSobrepostos++;
                    }
                }
            }
        }

        calendarWrapper.setEvents(calendarios);
        calendarWrapper.setOverCrowdedEventsCounter(contadorEventosLotados);
        calendarWrapper.setOverlappedEventsCounter(contadorEventosSobrepostos);

        return calendarWrapper;
    }
    
}