package web.CalendarServlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Horario;
import services.CommonManager;

/**
 * A classe AddEventServlet é um servlet que trata os pedidos GET para adicionar um evento ao calendário de um estudante.
 * 
 * Este servlet recebe os parâmetros do pedido (curso, unidade curricular e turno) e verifica se existe um evento correspondente na lista
 * de eventos do calendário global da aplicação. Se houver um evento correspondente e o estudante ainda não tiver adicionado este evento ao seu calendário,
 * o evento é adicionado ao calendário do estudante na sessão.
 */
public class AddEventServlet extends HttpServlet {

    /**
     * Trata os pedidos GET para adicionar um evento ao calendário de um estudante.
     * 
     * @param request o objeto HttpServletRequest contendo informações sobre o pedido
     * @param response o objeto HttpServletResponse utilizado para enviar a resposta
     * @throws IOException se ocorrer um erro ao ler ou escrever no fluxo de entrada ou saída
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      
        String curso = request.getParameter("curso");
        String uc = request.getParameter("uc");
        String turno = request.getParameter("turno");

        List<Horario> eventos = CommonManager.getHorariosFromSession(request.getSession());
        List<Horario> horarioDoEstudante = CommonManager.getStudentHorarioFromSession(request.getSession());

        for(Horario h : eventos) {
            if (h.getCurso().contains(curso) && h.getUnidadeCurricular().equals(uc) && h.getTurno().equals(turno)
                    && !horarioDoEstudante.contains(h)) {
                CommonManager.addToStudentHorarioFromSession(request.getSession(), h);
            }
        }
    }
}
