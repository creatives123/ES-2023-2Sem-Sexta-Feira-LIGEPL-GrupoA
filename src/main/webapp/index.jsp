<%@ page import="Models.Horario" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
            margin: 150px auto;
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
            <!-- Form para receber um ficheiro local -->
            <form method="post" enctype="multipart/form-data" action="UploadServlet">
                <div class="mb-3">
                    <label for="ficheiro" class="form-label">Escolher o ficheiro a Importar (CSV ou JSON)</label>
                    <input class="form-control" type="file" id="ficheiro" name="ficheiro" accept=".csv,.json" required>
                </div>
                <div class="mb-3">
                    <button type="submit" class="btn btn-primary mb-3">Upload</button>
                </div>
            </form>
            <!-- Form para receber um url do ficheiro -->
            <form method="post" action="UploadServlet">
                <div class="mb-3">
                    <label for="url" class="form-label">Indique um endereço url onde se encontra o ficheiro CSV /
                        JSON</label>
                    <input class="form-control" type="text" id="url" name="url" required>
                </div>
                <div class="mb-3">
                    <button type="submit" class="btn btn-primary mb-3">Upload</button>
                </div>
            </form>
        </div>
        <div class="mb-3">
            <%-- Exibir mensagem --%>
            <% if (session.getAttribute("messageUpload") != null) { %>
            <%= (String)session.getAttribute("messageUpload") %>
            <% } %>
        </div>
    </div>
    <% if (session.getAttribute("horarios") != null) { %>
    <hr/>
    <div class="row row-cols-auto">
        <div class="col">
            <a href="DownloadServlet?type=json">
                <button class="btn btn-primary">Download JSON</button>
            </a>
        </div>
        <div class="col">
            <a href="DownloadServlet?type=csv">
                <button class="btn btn-primary">Download CSV</button>
            </a>
        </div>
    </div>
    <% } %>


</div>
</body>
</html>
