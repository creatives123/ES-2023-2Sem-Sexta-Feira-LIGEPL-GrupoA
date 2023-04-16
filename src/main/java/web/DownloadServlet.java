package web;

import models.Horario;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static services.HorarioJsonWriter.writeToJson;
import static services.HorarioCsvWriter.writeToCsv;

/**
 *  Servlet responsável por fazer download uma lista de {@link Horario}
 *  para um ficheiro CSV ou JSON, obtendo essa lista a partir da sessão HTTP 
 *  
 */
public class DownloadServlet extends HttpServlet {

    private static final String FILENAME = "Horario";

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

    /**
     * Manipula uma solicitação GET para fazer download um ficheiro de horários no formato JSON ou CSV.
     *
     * @param request  A solicitação HTTP recebida.
     * @param response A resposta HTTP a ser enviada.
     * @throws IOException Se houver um erro de entrada/saída ao processar a solicitação.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String type = request.getParameter("type");
            byte[] bytes = null;
            String contentType = null;
            String filename = FILENAME;

            if ("json".equals(type)) {
                bytes = writeToJson(getHorariosFromSession(request.getSession()));
                contentType = "application/json";
                filename += ".json";
            } else if ("csv".equals(type)) {
                bytes = writeToCsv(getHorariosFromSession(request.getSession()));
                contentType = "text/csv";
                filename += ".csv";
            } else {
                response.sendError(400, "Invalid request type");
                return;
            }

            response.setContentType(contentType);
            response.setHeader("Content-Disposition", "attachment; filename=" + filename);
            response.getOutputStream().write(bytes);

        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Ocorreu um erro ao processar a solicitação: " + e.getMessage());
        }
    }
}