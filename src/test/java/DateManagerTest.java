import org.junit.jupiter.api.Test;

import services.DateManager;

import java.time.ZoneId;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DateManagerTest {

    private static final ZoneId ZONE_ID = ZoneId.of("Europe/Lisbon");

    @Test
    public void testGetScheduleCorrectTimeFormat() {
        String formattedDate = DateManager.getScheduleCorrectTimeFormat("10/05/2022 14:30:00");
        assertNotNull(formattedDate);
        assertEquals("2022-05-10 14:30:00", formattedDate);
    }
}
