// package web.CalendarServlets;


// import static org.junit.Assert.assertEquals;

// import java.util.ArrayList;
// import java.util.List;

// import javax.servlet.http.HttpServletRequest;

// import org.junit.Before;
// import org.junit.Test;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;

// import models.Horario;
// import services.CommonManager;
// import web.DatasourceServlets.GetCoursesServlet;

// public class GetCoursesServletTest {

//     @Mock
//     HttpServletRequest request;

//     @Before
//     public void setUp() throws Exception {
//         MockitoAnnotations.initMocks(this);
//     }

//     @Test
//     public void testGetCourses() {
//         List<String> cursos = new ArrayList<>();
//         cursos.add("Informatica");
//         cursos.add("Gestao");
//         cursos.add("Arquitura");
//         List<Horario> eventos = new ArrayList<>();
//         Horario horario1 = new Horario();
//         horario1.setCurso(cursos);
//         horario1.setCurso(cursos);
//         Horario horario2 = new Horario();
//         horario2.setCurso(cursos);
//         eventos.add(horario1);
//         eventos.add(horario2);

//         // Mock the call to CommonManager.getHorariosFromSession
//         javax.servlet.http.HttpSession session = request.getSession();
//         org.mockito.Mockito.when(CommonManager.getHorariosFromSession(session)).thenReturn(eventos);

//         List<String> expectedCourses = List.of("Matemática", "Física", "Português", "História");
//         List<String> actualCourses = getCourses(request);
//         assertEquals(expectedCourses, actualCourses);
//     }
// }