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
 * A classe GetCoursesServlet é um servlet que trata os pedidos GET para obter a lista de cursos disponíveis na aplicação.
 * 
 * Este servlet obtém a lista de eventos do calendário global da aplicação a partir da sessão do usuário e extrai a lista de cursos
 * únicos presentes nos eventos. Em seguida, retorna a lista de cursos como uma lista de strings em formato JSON.
 */
public class GetCoursesServlet extends HttpServlet {

    /**
     * Trata os pedidos GET para obter a lista de cursos disponíveis na aplicação.
     * 
     * @param request o objeto HttpServletRequest contendo informações sobre o pedido
     * @param response o objeto HttpServletResponse utilizado para enviar a resposta
     * @throws IOException se ocorrer um erro ao ler ou escrever no fluxo de entrada ou saída
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = new ObjectMapper().writeValueAsString(getCourses(request));
    
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    /**
     * Obtém a lista de cursos disponíveis na aplicação a partir da sessão do usuário.
     * 
     * @param request o objeto HttpServletRequest contendo informações sobre o pedido
     * @return uma lista de strings contendo os cursos disponíveis na aplicação
     */
    private List<String> getCourses(HttpServletRequest request) {
        List<Horario> eventos = CommonManager.getHorariosFromSession(request.getSession());
        Set<String> cursos = new HashSet<>();

        for (Horario evento : eventos) {
            for(String curso : evento.getCurso()) {
                cursos.add(curso);
            }
        }
        return new ArrayList<>(cursos);
    }
}
