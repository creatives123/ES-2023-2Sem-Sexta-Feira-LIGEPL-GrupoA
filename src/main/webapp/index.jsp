<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Horario" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.CalendarWrapper" %>

<!doctype html>
<html lang="en">
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ISCTE Calendário</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css"
          integrity="sha512-GQGU0fMMi238uA+a/bdWJfpUGKUkBdgfFdgBm72SUQ6BeyWjoY/ton0tEjH+OSH9iP4Dfh+7HM0I9f5eR0L/4w=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.min.js"
            integrity="sha512-OvBgP9A2JBgiRad/mM36mkzXSXaJE9BEIENnVEmeZdITvwT09xnxLtT4twkCa8m/loMbPHsvPl0T8lRGVBwjlQ=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <link href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css' rel='stylesheet'>


    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js'></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js'></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/6.1.6/index.min.js"
            integrity="sha512-xCMh+IX6X2jqIgak2DBvsP6DNPne/t52lMbAUJSjr3+trFn14zlaryZlBcXbHKw8SbrpS0n3zlqSVmZPITRDSQ=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/6.1.6/index.global.min.js"
            integrity="sha512-hNfQGfd5KlxAn+rNdPxQD8cTM6BDTpP44Oy9fxJO0VfSfrjwd0QWLQZhbtCEMDwq99jdMfshh3p3K58lJrH27g=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
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

        .fc-daygrid-day-number, .c-popover-title {
            color: rgb(0, 0, 0);
        }

        .fc-col-header-cell-cushion {
            color: rgb(255, 255, 255);
        }

        .calendar table {
            background-color: honeydew;
        }

        .calendar th {
            background-color: #0D28C2;
        }
        .calendar span {
            color: black;
        }

        .calendar td {
            color: black;
        }

    </style>

    <script>
        $(document).ready(function () {

            var eventsDatasource = [];

            function getCalendarData() {
                $.ajax({
                    url: 'GetCalendarServlet?horario=horarios',
                    type: 'GET',
                    dataType: 'json',
                    success: function (response) {
                        eventsDatasource = response;
                        calendar.refetchEvents();
                    },
                    error: function (xhr, status, error) {
                        console.log(xhr.statusText);
                    }
                });
            }

            var calendarEl = $('#calendar').get(0);
            window.calendar = new FullCalendar.Calendar(calendarEl, {
                locale: 'pt',
                themeSystem: 'bootstrap5',
                headerToolbar: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'dayGridMonth,timeGridWeek,timeGridDay'
                },
                buttonText: {
                    today: 'Hoje',
                    day: 'Diário',
                    week: 'Semanal',
                    month: 'Mensal'
                },
                navLinks: true,
                editable: false,
                dayMaxEvents: true,
                events: function (info, successCallback, failureCallback) {
                    successCallback(eventsDatasource);
                }
            });

            getCalendarData();
            calendar.render();
        });

    </script>

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

        .fc-daygrid-day-number, .c-popover-title {
            color: rgb(0, 0, 0);
        }

        .fc-col-header-cell-cushion {
            color: rgb(255, 255, 255);
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
            <!-- "Form" para receber um ficheiro local -->
            <form method="post" enctype="multipart/form-data" action="UploadServlet">
                <div class="mb-3">
                    <label for="ficheiro" class="form-label">Escolher o ficheiro a Importar (CSV ou JSON)</label>
                    <input class="form-control" type="file" id="ficheiro" name="ficheiro" accept=".csv,.json" required>
                </div>
                <div class="mb-3">
                    <button type="submit" class="btn btn-primary mb-3">Upload</button>
                </div>
            </form>
            <!-- "Form" para receber um url do ficheiro -->
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
            <!-- "Form" para receber um url de webcal -->
            <form method="get" action="WebCallImporterServlet">
                <div class="mb-3">
                    <label for="url" class="form-label">Indique um endereço url onde se encontra o ficheiro webcal
                    </label>
                    <input class="form-control" type="text" id="uri" name="uri" required>
                </div>
                <div class="mb-3">
                    <button type="submit" class="btn btn-primary mb-3">Upload</button>
                </div>
            </form>
        </div>
        <div class="mb-3">
            <%-- Exibir mensagem --%>
            <% if (session.getAttribute("messageUpload") != null) { %>
            <%= (String) session.getAttribute("messageUpload") %>
            <% } %>
        </div>
    </div>
    <% if (session.getAttribute("webcalHorario") != null) { %>
    <div class="row row-cols-auto">
        <div class="col">
            <a href="icalscheduler.jsp">
                <button class="btn btn-primary">Visualizar WebCal Inportado</button>
            </a>
        </div>
    </div>
    <% } %>
    <% if (session.getAttribute("horarios") != null) { %>
    <hr/>
    <div class="row row-cols-auto">
        <div class="col">
            <a href="DownloadServlet?type=json&horario=horarios">
                <button class="btn btn-primary">Download JSON</button>
            </a>
        </div>
        <div class="col">
            <a href="DownloadServlet?type=csv&horario=horarios">
                <button class="btn btn-primary">Download CSV</button>
            </a>
        </div>
        <div class="col">
            <a href="studentscheduler.jsp">
                <button class="btn btn-primary">Criar Novo Horário Estudante</button>
            </a>
        </div>
    </div>
    <br>
    <div>
        <h3>Horário Importado:</h3>
        <hr>
        <div id="calendar" class="calendar"></div>
    </div>
    <% } %>
</div>
</body>
</html>