<%@ page import="javax.servlet.http.*" %>
<%@ page import="java.nio.file.Paths" %>
<%@ page import="services.HorarioCsvReader" %>
<%@ page import="java.io.IOException" %>
<%@ page import="Models.Horario" %>
<%@ page import="java.util.List" %>

<!doctype html>
<html lang="en">
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ISCTE Calendário</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>

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
            width: 600px;
        }
    </style>
</head>

<body>
<div class="main" >
    <h2>Projecto ES</h2>
    <form method="post" enctype="multipart/form-data">
        <label for="arquivo">Selecione um arquivo CSV ou JSON:</label>
        <input type="file" id="arquivo" name="arquivo" accept=".csv,.json">
        <button type="submit">Enviar</button>
    </form>


    <%-- Processar arquivo e gerar mensagem --%>
    <%
        String mensagem = "";
        String contentType = request.getContentType();
        if (contentType != null && contentType.startsWith("multipart/")) {
            try {
                Part filePart = request.getPart("arquivo");
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

                if (fileName.endsWith(".csv")) {
                    List<Horario> horarios = HorarioCsvReader.readCsv(filePart.getInputStream().toString());
                    // Processa a lista de horários
                    mensagem = "Arquivo CSV importado com sucesso.";
                } else if (fileName.endsWith(".json")) {
                    //List<Horario> horarios = readJson(filePart.getInputStream());
                    // Processa a lista de horários
                    mensagem = "Arquivo JSON importado com sucesso.";
                } else {
                    mensagem = "O arquivo selecionado não é suportado. Selecione um arquivo CSV ou JSON.";
                }
            } catch (IOException | ServletException e) {
                e.printStackTrace();
                mensagem = "Ocorreu um erro ao importar o arquivo: " + e.getMessage();
            }
        }
    %>

    <%-- Exibir mensagem --%>
    <% if (!mensagem.isEmpty()) { %>
    <p><%= mensagem %></p>
    <% } %>

    <div class="dropdown-divider"></div>
</div>
</body>
</html>
