package h10.protocol.subunits;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeviceStatusTest {

    @Test
    public void whenParse() {
        String in = "06000908000102";
        DeviceStatus status = new DeviceStatus();
        status.parse(in);
        assertEquals(60, status.getGsmSignalLevel());
        assertEquals(9, status.getGsmSattellitesCount());
        assertEquals(80, status.getBatteryLevel());
        assertEquals(1, status.getFortificationState());
        assertEquals(WorkingMode.POWER_SAVING, status.getWorkingMode());
    }
}