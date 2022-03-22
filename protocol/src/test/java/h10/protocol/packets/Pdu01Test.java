package h10.protocol.packets;

import h10.protocol.subunits.*;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class Pdu01Test {

    @Test
    public void whenApParse() {
        String in = "IWAP01080524A2232.9806N11404.9355E000.1061830323.8706000908000102,460,0,9520,3671,Home|74-DE-2B-44-88-8C|97&Home1|74-DE-2B-44-88-8D|98&Home2|74-DE-2B-44-88-8E|99&Home3|74-DE-2B-44-88-8F|99#";
        ProtocolDataUnit pdu = new ProtocolDataUnit();
        pdu.parse(in);
        AP01 content = pdu.getContent();
        DeviceStatus status = content.getStatus();
        assertEquals("AP01", pdu.getCommandId());

        assertTrue(content.gpsIsValid());
        assertFalse(content.gpsIsZeroCoordinates());
        assertEquals(LocalDateTime.of(2008, 5, 24, 6, 18, 30), content.getGpsDateTime());
        assertEquals(22.549676, content.getGpsLatitude(), 0.000001);
        assertEquals(114.082258, content.getGpsLongitude(), 0.000001);
        assertEquals(LatitudeHemisphere.NORTH, content.getGpsFromEquator());
        assertEquals(LongitudeHemisphere.EAST, content.getGpsFromGreenwich());
        assertEquals(0.1, content.getGpsSpeed(), 0.05);
        assertEquals(323.87, content.getGpsAzimuth(), 0.005);
        assertEquals(60, status.getGsmSignalLevel());
        assertEquals(80, status.getBatteryLevel());
        assertEquals(1, status.getFortificationState());
        assertEquals(WorkingMode.POWER_SAVING, status.getWorkingMode());

        assertTrue(content.isMoving());
        assertEquals(460, content.getMobileCountryCode());
        assertEquals(0, content.getMobileNetworkCode());
        assertEquals(9520, content.getLocalAreaCode());
        assertEquals(3671, content.getCellId());

        assertEquals(4, content.getWifiBasesCount());
        assertEquals("Home", content.getWifiBase(0).getName());
        assertEquals("74-DE-2B-44-88-8C", content.getWifiBase(0).getMacAddress());
        assertEquals(97, content.getWifiBase(0).getSignalPower());
        assertEquals("Home1", content.getWifiBase(1).getName());
        assertEquals("74-DE-2B-44-88-8D", content.getWifiBase(1).getMacAddress());
        assertEquals(98, content.getWifiBase(1).getSignalPower());
        assertEquals("Home2", content.getWifiBase(2).getName());
        assertEquals("74-DE-2B-44-88-8E", content.getWifiBase(2).getMacAddress());
        assertEquals(99, content.getWifiBase(2).getSignalPower());
        assertEquals("Home3", content.getWifiBase(3).getName());
        assertEquals("74-DE-2B-44-88-8F", content.getWifiBase(3).getMacAddress());
        assertEquals(99, content.getWifiBase(3).getSignalPower());
    }

    @Test
    public void whenBpPack() {
        String expected = "IWBP01#";
        ProtocolDataUnit pdu = new ProtocolDataUnit("BP01", null);
        assertEquals(expected, pdu.toPacket());
    }
}
