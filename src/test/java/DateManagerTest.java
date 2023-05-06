import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import models.Horario;
import services.DateManager;

/**
 * The DateManagerTest class contains JUnit tests for the {@link DateManager} class.
 */
class DateManagerTest {

    /**
     * Testa o método {@link DateManager#getScheduleCorrectTimeFormat(String)}.
     */
    @Test
    void testGetScheduleCorrectTimeFormat() {
        // Arrange
        String input = "10/05/2023 14:30:00";
        String expectedOutput = "2023-05-10 14:30:00";
        
        // Act
        String actualOutput = DateManager.getScheduleCorrectTimeFormat(input);
        
        // Assert
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Testa o método {@link DateManager#getDataHoraInicio(Horario)}.
     */
    @Test
    void testGetDataHoraInicio() {
        // Arrange
        Horario h = new Horario();
        h.setDataAula("10/05/2023");
        h.setHoraInicio("14:30:00");
        String expectedOutput = "2023-05-10 14:30:00";
        
        // Act
        String actualOutput = DateManager.getDataHoraInicio(h);
        
        // Assert
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Testa o método {@link DateManager#getDataHoraInicio(Horario)} quanto o input está null.
     */
    @Test
    void testGetDataHoraInicioNull() {
        // Arrange
        Horario h = null;
        Executable executable = () -> DateManager.getDataHoraInicio(h);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, executable);
        assertEquals("h is null", exception.getMessage());
    }

    /**
     * Testa o método {@link DateManager#getDataHoraFim(Horario)}.
     */
    @Test
    void testGetDataHoraFim() {
        // Arrange
        Horario h = new Horario();
        h.setDataAula("10/05/2023");
        h.setHoraFim("16:30:00");
        String expectedOutput = "2023-05-10 16:30:00";
        
        // Act
        String actualOutput = DateManager.getDataHoraFim(h);
        
        // Assert
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Testa o método {@link DateManager#getDataHoraFim(Horario)}.
     */
    @Test
    void testGetDataHoraFimNull() {
        // Arrange
        Horario h = null;
        Executable executable = () -> DateManager.getDataHoraFim(h);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, executable);
        assertEquals("h is null", exception.getMessage());
    }

    /**
     * Testa o método {@link DateManager#sameInterval(Horario, Horario)}.
     */
    @DisplayName("Test sameInterval method")
    @ParameterizedTest(name = "test {index}: h1={0}, h2={1}, expected={2}")
    @MethodSource("sameIntervalArguments")
    void testSameInterval(Horario h1, Horario h2, boolean expected) {
        // Act
        boolean actual = DateManager.sameInterval(h1, h2);

        // Assert
        assertEquals(expected, actual);
    }

    static Collection<Object[]> sameIntervalArguments() {
        Horario h1 = new Horario();
        h1.setDataAula("10/05/2023");
        h1.setHoraInicio("14:30:00");
        h1.setHoraFim("16:30:00");

        Horario h2 = new Horario();
        h2.setDataAula("10/05/2023");
        h2.setHoraInicio("16:00:00");
        h2.setHoraFim("18:00:00");

        Horario h3 = new Horario();
        h3.setDataAula("11/05/2023");
        h3.setHoraInicio("09:00:00");
        h3.setHoraFim("12:00:00");
    
        Horario h4 = new Horario();
        h4.setDataAula("10/05/2023");
        h4.setHoraInicio("12:00:00");
        h4.setHoraFim("14:00:00");
    
        Horario h5 = new Horario();
        h5.setDataAula("11/05/2023");
        h5.setHoraInicio("08:00:00");
        h5.setHoraFim("09:00:00");
    
        Horario h6 = new Horario();
        h6.setDataAula("11/05/2023");
        h6.setHoraInicio("09:00:00");
        h6.setHoraFim("12:00:00");
    
        Horario h7 = new Horario();
        h7.setDataAula("11/05/2023");
        h7.setHoraInicio("12:00:00");
        h7.setHoraFim("15:00:00");
    
        return Arrays.asList(new Object[][] {
            {h1, h2, true},
            {h2, h1, true},
            {h1, h3, false},
            {h3, h1, false},
            {h1, h4, false},
            {h4, h1, false},
            {h3, h4, false},
            {h4, h3, false},
            {h5, h6, false},
            {h6, h5, false},
            {h6, h7, false},
            {h7, h6, false},

        });
    }
}    
