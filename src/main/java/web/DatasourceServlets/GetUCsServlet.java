package web.DatasourceServlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.Horario;
import services.CommonManager;

/**
 * A classe GetUCsServlet é um servlet que trata os pedidos GET para obter a lista de unidades curriculares disponíveis para um determinado curso.
 * 
 * Este servlet obtém a lista de eventos do calendário global da aplicação a partir da sessão do usuário e extrai a lista de unidades curriculares únicas para o
 * curso especificado nos parâmetros do pedido. Em seguida, retorna a lista de unidades curriculares como uma lista de strings em formato JSON.
 */
public class GetUCsServlet extends HttpServlet {

    /**
     * Trata os pedidos GET para obter a lista de unidades curriculares disponíveis para um determinado curso.
     * 
     * @param request o objeto HttpServletRequest contendo informações sobre o pedido
     * @param response o objeto HttpServletResponse utilizado para enviar a resposta
     * @throws IOException se ocorrer um erro ao ler ou escrever no fluxo de entrada ou saída
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = new ObjectMapper().writeValueAsString(getUCs(request));
    
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    /**
     * Obtém a lista de unidades curriculares disponíveis para um determinado curso a partir da sessão do usuário.
     * 
     * @param request o objeto HttpServletRequest contendo informações sobre o pedido
     * @return uma lista de strings contendo as unidades curriculares disponíveis para o curso especificado nos parâmetros do pedido
     */
    private List<String> getUCs(HttpServletRequest request) {
        String curso = request.getParameter("course");
        List<Horario> eventos = CommonManager.getHorariosFromSession(request.getSession());

        Set<String> ucs = new HashSet<>();

        for (Horario evento : eventos) {
            if (evento.getCurso().contains(curso)) {
                ucs.add(evento.getUnidadeCurricular());
            }
        }
        return new ArrayList<>(ucs);
    }
}
