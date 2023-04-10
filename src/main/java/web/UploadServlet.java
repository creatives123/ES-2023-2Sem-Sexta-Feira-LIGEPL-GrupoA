package web;
import Models.Horario;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import services.HorarioCsvReader;

import javax.servlet.http.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class UploadServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = request.getParameter("url");
        String message;
        if (url == null) { // arquivo local
            message = processFile(request);
        } else {
            System.out.println(url);
            message = processURL(request);
        }
        // Redireciona o usuário de volta para a página de upload
        request.getSession().setAttribute("messageUpload", message);
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    public String processURL(HttpServletRequest request) throws IOException {
        return "Ainda a ser criado";
    }

    public String processFile(HttpServletRequest request) {
        String mensage = "";
        String contentType = request.getContentType();
        if (contentType != null && contentType.startsWith("multipart/")) {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (isMultipart) {
                // cria um objeto FileItemFactory
                FileItemFactory factory = new DiskFileItemFactory();

                // cria um objeto ServletFileUpload
                ServletFileUpload upload = new ServletFileUpload(factory);
                try {
                    // faz o parsing dos itens do request
                    List<FileItem> items = upload.parseRequest(request);
                    // processa os itens do request
                    for (FileItem item : items) {
                        if (!item.isFormField()) {
                            String fileName = item.getName();
                            if (fileName.endsWith(".csv")) {
                                Path tempFile = Files.createTempFile("horarios", ".csv");
                                File file = tempFile.toFile();
                                try {
                                    item.write(file);
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }

                                List<Horario> horarios = HorarioCsvReader.readCsv(file.getAbsolutePath());
                                request.getSession().setAttribute("horarios", horarios);
                                // Processa a lista de horários
                                mensage = "Ficheiro CSV importado com sucesso.";
                                mensage += "<br> Total de entradas no ficheiro: " + horarios.size();

                                // TODO deve ser apagado no fim
                                mensage += "<br><br> Caminho do ficheiro: " + file.getAbsolutePath();
                            } else if (fileName.endsWith(".json")) {
                                Path tempFile = Files.createTempFile("horarios", ".json");
                                File file = tempFile.toFile();
                                try {
                                    item.write(file);
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }

                                // TODO meter aqui a função para ler o JSON
                                //List<Horario> horarios = readJson(new FileInputStream(file));
                                //request.getSession().setAttribute("horarios", horarios);
                                // Processa a lista de horários
                                mensage = "Arquivo JSON importado com sucesso.";
                                mensage += "<br> Total de entradas no ficheiro: ";

                                // TODO deve ser apagado no fim
                                mensage += "<br><br> Caminho do ficheiro: " + file.getAbsolutePath();
                            } else {
                                mensage = "O arquivo selecionado não é suportado. Selecione um arquivo CSV ou JSON.";
                            }
                        }
                    }

                } catch (FileUploadException | RuntimeException e) {
                    e.printStackTrace();
                    mensage = "Ocorreu um erro ao importar o ficheiro: " + e.getMessage();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return mensage;
    }
}