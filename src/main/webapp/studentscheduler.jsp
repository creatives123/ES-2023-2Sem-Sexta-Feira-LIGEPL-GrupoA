<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Horario" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.CalendarWrapper" %>

<%
    // Check if the session variable exists and is not empty
    if (session.getAttribute("horarios") == null || session.getAttribute("horarios").equals("")) {
        // If the session variable is empty or doesn't exist, redirect to another page
        response.sendRedirect("index.jsp");
    }
%>


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
</head>

<body>
<div class="main">
    <a href="index.jsp" style="text-decoration: none">
        <svg xmlns="http://www.w3.org/2000/svg" width="64" height="64" fill="currentColor" class="bi bi-house-door-fill"
             viewBox="0 0 16 16">
            <path d="M6.5 14.5v-3.505c0-.245.25-.495.5-.495h2c.25 0 .5.25.5.5v3.5a.5.5 0 0 0 .5.5h4a.5.5 0 0 0 .5-.5v-7a.5.5 0 0 0-.146-.354L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.354 1.146a.5.5 0 0 0-.708 0l-6 6A.5.5 0 0 0 1.5 7.5v7a.5.5 0 0 0 .5.5h4a.5.5 0 0 0 .5-.5Z"/>
        </svg>
        Inicio </a>
    <div class="mb-3 row">
        <h2>Projecto ES 2022/2023</h2>
    </div>
    <hr/>
    <div>
        Selecionar UCs para adicionar no horário:
        <div class="row">
            <div class="col">
                <label for="cursos"></label><select class="form-select" name="cursos" id="cursos" required></select>
            </div>
            <div class="col">

            </div>
            <div class="col">

            </div>
        </div>
        <div class="row" id="divUCs" style="display:none">
            <div class="col">
                <label for="ucs"></label><select class="form-select" name="ucs" id="ucs" required></select>
            </div>
            <div class="col">

            </div>
            <div class="col">

            </div>
        </div>
        <div class="row" id="divTurnos" style="display:none">
            <div class="col">
                <label for="turnos"></label><select class="form-select" name="turnos" id="turnos" required></select>
            </div>
            <div class="col">

            </div>
            <div class="col">

            </div>
        </div>
        <div class="row" id="divAdd" style="display:none">
            <div class="col mt-3">
                <label for="add"></label>
                <button id="add" type="button" class="btn btn-primary">Adicionar</button>
            </div>
            <div class="col">

            </div>
            <div class="col">

            </div>
        </div>
        <hr/>
        <div id="calendar" class="calendar"></div>
        <hr/>
        <div class="row row-cols-auto">
            <div class="col">
                <a href="DownloadServlet?type=json&horario=student_horario">
                    <button class="btn btn-primary">Download JSON</button>
                </a>
            </div>
            <div class="col">
                <a href="DownloadServlet?type=csv&horario=student_horario">
                    <button class="btn btn-primary">Download CSV</button>
                </a>
            </div>
        </div>
    </div>
    <br>
    <hr>
    <div class="row">
        <div class="col" id="Overcrowded">
            <h5>UCs com sobrelotação:</h5>
        </div>
        <div class="col" id="Overlapped">
            <h5>UCs com sobreposição:</h5>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        $("#cursos").change(function () {

        });
        var eventsDatasource = [];

        function getCalendarData() {
            $.ajax({
                url: 'StudentCalendarServlet',
                type: 'GET',
                dataType: 'json',
                success: function (response) {
                    eventsDatasource = response.events;
                    calendar.refetchEvents();
                    // Generate table with overCrowdedEvents data
                    generateEventsTable(response.overCrowdedEvents, "Overcrowded");
                    generateEventsTable(response.overlappedEvents, "Overlapped");
                },
                error: function (xhr, status, error) {
                    console.log(xhr.statusText);
                }
            });
        }

        function deleteEvents(uc, turno) {
            $.ajax({
                url: 'StudentCalendarServlet?uc=' + uc + "&turno=" + turno,
                type: 'DELETE'
            }).then(function () {
                getCalendarData();
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
            },
            eventClick: function (info) {
                console.log(info.event.extendedProps.horario);
                var turno = info.event.extendedProps.horario.turno;
                var uc = info.event.extendedProps.horario.unidadeCurricular;

                console.log(turno);
                console.log(uc);

                if (confirm("Quer mesmo apagar esta UC do seu calendário?")) {
                    deleteEvents(uc, turno);
                }
            }
        });

        calendar.render();

        $.get("GetCoursesServlet", {}, function (cursos) {
            $("#cursos").append("<option selected disabled value=''>Escolha um curso</option>");
            $.each(cursos, function (i, curso) {
                $("#cursos").append("<option value='" + curso + "'>" + curso + "</option>");
            });
        });

        $("#cursos").change(function () {
            var course = $(this).val();
            $.get("GetUCsServlet", {course: course}, function (ucs) {
                // tanto limpa as ucs como limpa os turnos.
                $("#ucs").empty();
                $("#turnos").empty();

                $("#ucs").append("<option selected disabled value=''>Escolha uma UC</option>");
                $.each(ucs, function (i, uc) {
                    $("#ucs").append("<option value='" + uc + "'>" + uc + "</option>");
                });
            });
            if ($(this).val()) {
                $("#divUCs").show();
            } else {
                $("#divUCs").hide();
            }
        });

        $("#ucs").change(function () {
            var course = $("#cursos").val();
            var uc = $(this).val();
            $.get("GetTurnosServlet", {course: course, uc: uc}, function (turnos) {
                $("#turnos").empty();
                $("#turnos").append("<option selected disabled value=''>Escolha um turno</option>");
                $.each(turnos, function (i, turno) {
                    $("#turnos").append("<option value='" + turno + "'>" + turno + "</option>");
                });
            });
            if ($(this).val()) {
                $("#divTurnos").show();
            } else {
                $("#divTurnos").hide();
            }
        });

        $('#turnos').on('change', function () {
            var cursosVal = $('#cursos').val();
            var ucsVal = $('#ucs').val();
            var turnosVal = $('#turnos').val();

            if (cursosVal !== '' && ucsVal !== '' && turnosVal !== '') {
                $('#divAdd').show();
            } else {
                $('#divAdd').hide();
            }
        });

        $(document).on('click', '#add', function () {
            var curso = $('#cursos').val();
            var uc = $('#ucs').val();
            var turno = $('#turnos').val();
            if (curso !== null && uc !== null && turno !== null) {
                $.post("StudentCalendarServlet", {curso: curso, uc: uc, turno: turno}, function () {
                    getCalendarData();
                    alert("UC adicionada ao calendário com sucesso!");
                });
            }
        });

        function generateEventsTable(Events, divId) {
            var table = document.createElement("table");
            table.classList.add("table"); // Add Bootstrap classes
            var headerRow = table.insertRow(0);
            var headerCell = document.createElement("th");
            headerCell.innerHTML = "UC Name + Turno";
            headerRow.appendChild(headerCell);
            var headerCell = document.createElement("th");
            headerCell.innerHTML = "Ocurrencias";
            headerRow.appendChild(headerCell);
            var i = 0;
            for (var key in Events) {
                var row = table.insertRow(i+1);
                var cell = row.insertCell(0);
                cell.innerHTML = key;
                var cell = row.insertCell(1);
                cell.innerHTML = Events[key];
                i++;
            }

            // Replace existing table with new one in the specified div
            var div = document.getElementById(divId);
            if (div) {
                var existingTable = div.querySelector("table");
                if (existingTable) {
                    existingTable.parentNode.removeChild(existingTable);
                }
                table.id = "overcrowded-events-table";
                div.appendChild(table);
            }
        }

    });
</script>
</body>
</html>