package web;

import models.Horario;
import services.CommonManager;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            String varSession = request.getParameter("horario");
            byte[] bytes = null;
            String contentType = null;
            String filename = FILENAME;

            if ("json".equals(type)) {
                bytes = writeToJson(getHorariosFromSession(request, varSession));
                contentType = "application/json";
                filename += ".json";
            } else if ("csv".equals(type)) {
                bytes = writeToCsv(getHorariosFromSession(request, varSession));
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

    private List<Horario> getHorariosFromSession(HttpServletRequest request, String varSession) {
        if(varSession.equals("horarios")){
            return CommonManager.getHorariosFromSession(request.getSession());
        } else if(varSession.equals("webcalHorario")){
            return CommonManager.getIcalHorariosFromSession(request.getSession());
        } else {
            return CommonManager.getStudentHorarioFromSession(request.getSession());
        }
    }
}