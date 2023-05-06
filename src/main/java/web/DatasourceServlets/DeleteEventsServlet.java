package web.DatasourceServlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Horario;
import services.CommonManager;

/**
 * A classe DeleteEventServlet é um servlet que trata os pedidos GET para remover um evento ao calendário de um estudante.
 * 
 * Este servlet recebe os parâmetros do pedido ( unidade curricular e turno) e verifica se existe um evento correspondente na lista
 * de eventos do calendário global da aplicação. Se houver um evento correspondente o estudante ao clicar em cima do evento acaba por remover
 * o mesmo do calendário. Cada turno tem os seus eventos, ou seja, se remover uma uc de um turno essa mesma uc não é removida de outro turno.
 * 
 */

public class DeleteEventsServlet extends HttpServlet {

    /**
     * Trata os pedidos GET para remover um evento ao calendário de um estudante.
     * 
     * @param request o objeto HttpServletRequest contendo informações sobre o pedido
     * @param response o objeto HttpServletResponse utilizado para enviar a resposta
     * @throws IOException se ocorrer um erro ao ler ou escrever no fluxo de entrada ou saída
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String uc = request.getParameter("uc");
        String turno = request.getParameter("turno");

        List<Horario> horarios = CommonManager.getStudentHorarioFromSession(request.getSession());
        horarios.removeIf(h -> (h.getUnidadeCurricular().equals(uc) && h.getTurno().equals(turno)));
        request.getSession().setAttribute("student_horario", horarios);
    }
}