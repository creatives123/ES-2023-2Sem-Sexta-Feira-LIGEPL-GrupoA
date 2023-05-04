<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="models.Horario" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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
                    url: 'GetStudentCalendarServlet',
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
        
        <label for="languages">List of Languages:</label>
        <select name="languages" id="languages">
          <c:forEach items="${session.getAttribute(horarios)}" var="horario">
            <option value="${horario.getId()}">${horario.getUnidadeCurricular()}</option>
          </c:forEach>
        </select>
        
        
        <select name="userRole">
            <c:forEach var="horario" items="${horarios}">
              <option value="${horario}">${horario}</option>
            </c:forEach>
        </select>


<!-- Curso -->
<!-- <div class="btn-group"> -->
    <!-- <button type="button" class="btn btn-danger dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
      <span class="sr-only">Curso</span>
    </button> -->
    <!-- <div class="dropdown-menu"> -->
        <!-- <select style="width: 100px" name="dsfssf" id="1">
            <option>teste</option> -->
        <!-- <c:forEach items="${horarios}" var="horario">
            <c:forEach items="${horario.getCurso()}" var="curso">
                <c:if test="${not cursoList.contains(curso)}">
                    <c:set var="cursoList" value="${cursoList + curso}"/>
                    <option>${curso}</option>
                </c:if>
            </c:forEach>
        </c:forEach> -->
        <!-- </select> -->
    <!-- </div>     -->
  <!-- </div> -->
  
  <c:if test="${not empty sessionScope.horarios}">


  <!-- Turno -->
  <div class="btn-group">
    <button type="button" class="btn btn-danger dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
      <span class="sr-only">Turno</span>
    </button>
    <div class="dropdown-menu">
      <c:forEach items="${horarios}" var="horario">
        <c:if test="${not turnoList.contains(horario.getTurno())}">
          <c:set var="turnoList" value="${turnoList + horario.getTurno()}"/>
          <a class="dropdown-item" href="#">${horario.getTurno()}</a>
        </c:if>
      </c:forEach>
    </div>
  </div>

      <!-- $horarios is in session -->
    <!-- Put your code here that uses $horarios -->
</c:if>
  
  <!-- Unidade Curricular -->
  <div class="btn-group">
    <button type="button" class="btn btn-danger dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
      <span class="sr-only">Unidade Curricular</span>
    </button>
    <div class="dropdown-menu">
      <c:forEach items="${horarios}" var="horario">
        <c:if test="${not ucList.contains(horario.getUnidadeCurricular())}">
          <c:set var="ucList" value="${ucList + horario.getUnidadeCurricular()}"/>
          <a class="dropdown-item" href="#">${horario.getUnidadeCurricular()}</a>
        </c:if>
      </c:forEach>
    </div>
  </div>
  
  

  
    <br/>
    
        <div id="calendar"></div>

    </div>
</body>
</html>


