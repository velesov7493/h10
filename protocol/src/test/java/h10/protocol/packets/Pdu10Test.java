package h10.protocol.packets;

import h10.protocol.subunits.*;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class Pdu10Test {

    @Test
    public void whenApParse() {
        String in = "IWAP10080524A2232.9806N11404.9355E000.1061830323.8706000908000102,460,0,9520,3671,00,zh-cn,00,HOME|74-DE-2B-44-88-8C|97&HOME1|74-DE-2B-44-88-8C|97&HOME2|74-DE-2B-44-88-8C|97&HOME3|74-DE-2B-44-88-8C|97#";
        ProtocolDataUnit pdu = new ProtocolDataUnit();
        pdu.parse(in);
        AP10 content = pdu.getContent();
        DeviceStatus status = content.getStatus();

        assertTrue(content.isValid());
        assertFalse(content.isZeroCoordinates());
        assertEquals(LocalDateTime.of(2008, 5, 24, 6, 18, 30), content.getDateTime());
        assertEquals(22.549676, content.getLatitude(), 0.000001);
        assertEquals(114.082258, content.getLongitude(), 0.000001);
        assertEquals(LatitudeHemisphere.NORTH, content.getFromEquator());
        assertEquals(LongitudeHemisphere.EAST, content.getFromGreenwich());
        assertEquals(0.1, content.getSpeed(), 0.05);
        assertEquals(323.87, content.getAzimuth(), 0.005);
        assertEquals(60, status.getGsmSignalLevel());
        assertEquals(80, status.getBatteryLevel());
        assertEquals(1, status.getFortificationState());
        assertEquals(WorkingMode.getByNumber(2), status.getWorkingMode());

        assertTrue(content.isMoving());
        assertEquals(460, content.getMobileCountryCode());
        assertEquals(0, content.getMobileNetworkCode());
        assertEquals(9520, content.getLocalAreaCode());
        assertEquals(3671, content.getCellId());

        assertEquals(AlarmState.NO_ALARM, content.getAlarmState());
        assertEquals("zh-cn", content.getLanguage());
        assertFalse(content.isAddressNeeded());

        assertEquals(4, content.getWifiCount());
        assertEquals("HOME", content.getWifiPoint(0).getName());
        assertEquals("74-DE-2B-44-88-8C", content.getWifiPoint(0).getMacAddress());
        assertEquals(97, content.getWifiPoint(0).getSignalPower());
        assertEquals("HOME1", content.getWifiPoint(1).getName());
        assertEquals("74-DE-2B-44-88-8C", content.getWifiPoint(1).getMacAddress());
        assertEquals(97, content.getWifiPoint(1).getSignalPower());
        assertEquals("HOME2", content.getWifiPoint(2).getName());
        assertEquals("74-DE-2B-44-88-8C", content.getWifiPoint(2).getMacAddress());
        assertEquals(97, content.getWifiPoint(2).getSignalPower());
        assertEquals("HOME3", content.getWifiPoint(3).getName());
        assertEquals("74-DE-2B-44-88-8C", content.getWifiPoint(3).getMacAddress());
        assertEquals(97, content.getWifiPoint(3).getSignalPower());
    }

    @Test
    public void whenBpWithAddressPack() {
        String expected = "IWBP106df157335e0253575c71533a53576d7759279053003100300037003953f7002000200068007400740070003a002f002f007700770077002e006700700073002e0063006f006d002f006d00610070002e0061007300700078003f006c00610074003d00320033002e0031003200330026006c006e0067003d003100310033002e003100320033#".toUpperCase();
        BP10 content = new BP10();
        content.setAddress("深圳市南山区南海大道1079号  http://www.gps.com/map.aspx?lat=23.123&lng=113.123");
        ProtocolDataUnit pdu = new ProtocolDataUnit("BP10", content);
        assertEquals(expected, pdu.toPacket());
    }

    @Test
    public void whenEmptyBpPack() {
        String expected = "IWBP10#";
        ProtocolDataUnit pdu = new ProtocolDataUnit("BP10", null);
        assertEquals(expected, pdu.toPacket());
    }
}