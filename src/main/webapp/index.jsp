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
            
    <script src="https://code.jquery.com/jquery-3.3.1.js"
    integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
    crossorigin="anonymous"></script>

    <link href='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.10.2/fullcalendar.min.css' rel='stylesheet' />
    <link href='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.10.2/fullcalendar.print.min.css' rel='stylesheet' media='print' />
    
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js'></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js'></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.10.2/fullcalendar.min.js'></script>
    <script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.6/index.global.min.js'></script>
    
    <script>
        $(document).ready(function () {

            var eventsDatasource = [];

            function getCalendarData() {
                $.ajax({
                    url: 'GetCalendarServlet',
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
                    week:'Semanal',
                    month:'Mensal'
                },
                navLinks: true,
                editable: false,
                dayMaxEvents: true,
                events: function(info, successCallback, failureCallback) {
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
            color:rgb(255, 255, 255);
        }

    </style>
</head>

<body>
<div class="main">

<div id="calendar"></div>

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
        </div>
        <div class="mb-3">
            <%-- Exibir mensagem --%>
            <% if (session.getAttribute("messageUpload") != null) { %>
            <%= (String) session.getAttribute("messageUpload") %>
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
