package web;

import models.Horario;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static services.HorarioJsonWriter.writeToJson;
@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String type = request.getParameter("type");

        if ("json".equals(type)) {

            byte[] jsonBytes = writeToJson((List<Horario>) request.getSession().getAttribute("horarios"));

            // Definir o tipo de conteúdo a ser enviado para o navegador
            response.setContentType("application/json");

            // Definir o cabeçalho de resposta para indicar que um arquivo está sendo enviado
            response.setHeader("Content-Disposition", "attachment; filename=Horario.json");

            response.getOutputStream().write(jsonBytes);
        } else if ("csv".equals(type)) {
            // TODO Falta o código para gerar um CSV com o horario
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            // tipo de arquivo desconhecido
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
