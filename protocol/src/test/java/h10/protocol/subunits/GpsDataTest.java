package h10.protocol.subunits;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class GpsDataTest {

    @Test
    public void whenParse() {
        String in = "080524A2232.9806N11404.9355E000.1061830323.8706000908000102";
        GpsData gps = new GpsData();
        gps.parse(in);
        DeviceStatus status = gps.getStatus();
        assertEquals(LocalDateTime.of(2008, 5, 24, 6, 18, 30), gps.getDateTime());
        assertTrue(gps.isValid());
        assertFalse(gps.isZeroCoordinates());
        assertEquals(22.549676, gps.getLatitude(), 0.000001);
        assertEquals(114.082258, gps.getLongitude(), 0.000001);
        assertEquals(LatitudeHemisphere.NORTH, gps.getFromEquator());
        assertEquals(LongitudeHemisphere.EAST, gps.getFromGreenwich());
        assertEquals(0.1, gps.getSpeed(), 0.05);
        assertEquals(323.87, gps.getAzimuth(), 0.005);
        assertEquals(status.getGsmSignalLevel(), 60);
        assertEquals(status.getBatteryLevel(), 80);
        assertEquals(status.getFortificationState(), 1);
        assertEquals(status.getWorkingMode(), WorkingMode.getByNumber(2));
    }
}