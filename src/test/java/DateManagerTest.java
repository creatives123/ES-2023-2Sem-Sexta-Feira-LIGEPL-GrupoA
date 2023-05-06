import org.junit.jupiter.api.Test;

import services.DateManager;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DateManagerTest {

    private static final ZoneId ZONE_ID = ZoneId.of("Europe/Lisbon");

    @Test
    public void testCastStringToDate() {
        Date date = DateManager.castStringToDate("01/01/2022");
        assertNotNull(date);
        LocalDateTime localDateTime = date.toInstant().atZone(ZONE_ID).toLocalDateTime();
        assertEquals(2022, localDateTime.getYear());
        assertEquals(1, localDateTime.getMonthValue());
        assertEquals(1, localDateTime.getDayOfMonth());
    }

    @Test
    public void testCastStringExtendToDate() {
        Date date = DateManager.castStringExtendToDate("Sat Jan 01 2022 14:30:00 GMT-0300 (Brasilia Standard Time)");
        assertNotNull(date);
        LocalDateTime localDateTime = date.toInstant().atZone(ZONE_ID).toLocalDateTime();
        assertEquals(2022, localDateTime.getYear());
        assertEquals(1, localDateTime.getMonthValue());
        assertEquals(1, localDateTime.getDayOfMonth());
        assertEquals(0, localDateTime.getHour());
        assertEquals(30, localDateTime.getMinute());
        assertEquals(0, localDateTime.getSecond());
    }

    @Test
    public void testGetScheduleCorrectTimeFormat() {
        String formattedDate = DateManager.getScheduleCorrectTimeFormat("10/05/2022 14:30:00");
        assertNotNull(formattedDate);
        assertEquals("2022-05-10 14:30:00", formattedDate);
    }
}
