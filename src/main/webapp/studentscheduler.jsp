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
    <select name="cursos" id="cursos"></select>
    <br/>
    <select name="ucs" id="ucs"></select>
    <br/>
    <select name="turnos" id="turnos"></select>
    <br/>
    <button id="add" style="display:none">Adicionar</button>
    <br/>
    <br/>
        <div id="calendar"></div>
    </div>

    <script>
      $(document).ready(function () {

        var eventsDatasource = [];

        function getCalendarData() {
            $.ajax({
                url: 'StudentCalendarServlet',
                type: 'GET',
                dataType: 'json',
                success: function (response) {
                    eventsDatasource = response.events;
                    calendar.refetchEvents();
                },
                error: function (xhr, status, error) {
                    console.log(xhr.statusText);
                }
            });
        }

        function deleteEvents(uc, turno) {
            $.ajax({
                url: 'StudentCalendarServlet?uc=' + uc +"&turno=" + turno,
                type: 'DELETE'
            }).then (function () {
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
                week:'Semanal',
                month:'Mensal'
            },
            navLinks: true,
            editable: false,
            dayMaxEvents: true,
            events: function(info, successCallback, failureCallback) {
                successCallback(eventsDatasource);
            },
            eventClick: function(info) {
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
          $.each(cursos, function (i, curso) {
              $("#cursos").append("<option value='" + curso + "'>" + curso + "</option>");
          });
        });

        $("#cursos").change(function () {
            var course = $(this).val();
            $.get("GetUCsServlet", { course: course }, function (ucs) {
                // tanto limpa as ucs como limpa os turnos.
                $("#ucs").empty();
                $("#turnos").empty();

                $("#ucs").append("<option selected disabled value=''>Escolha uma UC</option>");
                $.each(ucs, function (i, uc) {
                    $("#ucs").append("<option value='" + uc + "'>" + uc + "</option>");
                });
            });
        });

        $("#ucs").change(function () {
            var course = $("#cursos").val();
            var uc = $(this).val();
            $.get("GetTurnosServlet", { course: course, uc: uc }, function (turnos) {
                $("#turnos").empty();
                $("#turnos").append("<option selected disabled value=''>Escolha um turno</option>");
                $.each(turnos, function (i, turno) {
                    $("#turnos").append("<option value='" + turno + "'>" + turno + "</option>");
                });
            });
        });

        $('#cursos, #ucs, #turnos').on('change', function () {
            var cursosVal = $('#cursos').val();
            var ucsVal = $('#ucs').val();
            var turnosVal = $('#turnos').val();
            
            if (cursosVal !== '' && ucsVal !== '' && turnosVal !== '') {
              $('#add').show();
            } else {
              $('#add').hide();
            }
        });

        $("#add").click(function () {
            var curso = $('#cursos').val();
            var uc = $('#ucs').val();
            var turno = $('#turnos').val();
            $.post("StudentCalendarServlet", { curso: curso, uc: uc, turno: turno }, function () {
                getCalendarData();
            });
        });
    });
  </script>
</body>
</html>