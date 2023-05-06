package web.CalendarServlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import Translators.HorarioToCalendarTranslator;
import models.CalendarModel;
import models.Horario;
import services.CommonManager;
/**
 * A classe GetCalendarServlet é um servlet que trata os pedidos GET para obter o calendário de um estudante.
 * 
 * Este servlet escreve numa String um objectMapper com base no pedido que é feito à lista getCalendars.
 * Na response é escrita a string passada, ou seja, retorna um json.
 * 
 */
public class GetCalendarServlet extends HttpServlet {

    /**
     * Trata os pedidos GET para obter o calendário de um estudante.
     * 
     * @param request o objeto HttpServletRequest contendo informações sobre o pedido
     * @param response o objeto HttpServletResponse utilizado para enviar a resposta
     * @throws IOException se ocorrer um erro ao ler ou escrever no fluxo de entrada ou saída
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = new ObjectMapper().writeValueAsString(getCalendars(request));
    
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private List<CalendarModel> getCalendars(HttpServletRequest request) {
        List<Horario> events = CommonManager.getHorariosFromSession(request.getSession());
        return HorarioToCalendarTranslator.translateHorariosToCalendars(events);
    }
}