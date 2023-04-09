<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="services.HorarioCsvReader" %>
<%@ page import="Models.Horario" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="java.io.File" %>
<%@ page import="java.nio.file.Path" %>
<%@ page import="java.nio.file.Files" %>

<!doctype html>
<html lang="en">
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ISCTE Calendário</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
            crossorigin="anonymous"></script>

    <style>
        body {
            background-color: #0D28C2;
        }

        .main {
            background-color: rgba(255, 255, 255, 0.5);
            margin: 300px auto;
            padding: 50px;
            border-radius: 5px;
            color: white;
            font-weight: bold;
            width: 1000px;
        }
    </style>
</head>

<body>
<div class="main">
    <div class="mb-3 row">
        <h2>Projecto ES 2022/2023</h2>
    </div>
    <hr/>
    <div>
        <div class="mb-3">
            <h3>Importar dados:</h3>
        </div>
        <div class="mb-3">
            <form method="post" enctype="multipart/form-data">
                <div class="mb-3">
                    <label for="ficheiro" class="form-label">Escolher o ficheiro a Importar (CSV ou JSON)</label>
                    <input class="form-control" type="file" id="ficheiro" name="ficheiro" accept=".csv,.json" required>
                </div>
                <div class="mb-3">
                    <button type="submit" class="btn btn-primary mb-3">Upload</button>
                </div>
            </form>
        </div>
        <div class="mb-3">
            <%-- Processar arquivo e gerar mensagem --%>
            <%
                String mensagem = "";
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
                                        // Processa a lista de horários
                                        mensagem = "Ficheiro CSV importado com sucesso.";
                                        mensagem += "<br> Total de entradas no ficheiro: " + horarios.size();

                                        // TODO deve ser apagado no fim
                                        mensagem += "<br><br> Caminho do ficheiro: " + file.getAbsolutePath();
                                    }else if (fileName.endsWith(".json")) {
                                        Path tempFile = Files.createTempFile("horarios", ".csv");
                                        File file = tempFile.toFile();
                                        try {
                                            item.write(file);
                                        } catch (Exception e) {
                                            throw new RuntimeException(e);
                                        }

                                        // TODO meter aqui a função para ler o JSON
                                        //List<Horario> horarios = readJson(new FileInputStream(file));
                                        // Processa a lista de horários
                                        mensagem = "Arquivo JSON importado com sucesso.";
                                        mensagem += "<br> Total de entradas no ficheiro: ";

                                        // TODO deve ser apagado no fim
                                        mensagem += "<br><br> Caminho do ficheiro: " + file.getAbsolutePath();
                                    } else {
                                        mensagem = "O arquivo selecionado não é suportado. Selecione um arquivo CSV ou JSON.";
                                    }
                                }
                            }

                        } catch (FileUploadException | RuntimeException e) {
                            e.printStackTrace();
                            mensagem = "Ocorreu um erro ao importar o ficheiro: " + e.getMessage();
                        }

                    }
                }
            %>
            <%-- Exibir mensagem --%>
            <% if (!mensagem.isEmpty()) { %>
            <%= mensagem %>
            <% } %>
        </div>
    </div>
    <hr/>


</div>
</body>
</html>
