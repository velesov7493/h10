package h10.protocol.packets;

import h10.protocol.subunits.GsmData;
import h10.protocol.subunits.GsmEntry;
import h10.protocol.subunits.WirelessEntry;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class Pdu02Test {

    @Test
    public void whenApParse() {
        String in = "IWAP02,zh_cn|1,bt1|b0-b4-48-ec-d7-d1|64,0,7,460,0,9520|3671|13,9520|3672|12,9520|3673|11,9520|3674|10,9520|3675|9,9520|3676|8,9520|3677|7,4,1|D8-24-BD-79-FA-1F|59&2|3C-46-D8-6D-CE-01|81&3|0C-4C-39-1A-7C-65|69&4|70-A8-E3-5D-D7-C0|65#";
        ProtocolDataUnit pdu = new ProtocolDataUnit();
        pdu.parse(in);
        AP02 content = pdu.getContent();
        assertEquals("zh_cn", content.getLanguage());
        assertEquals("bt1", content.getBluetoothBase(0).getName());
        assertEquals("b0-b4-48-ec-d7-d1", content.getBluetoothBase(0).getMacAddress());
        assertEquals(64, content.getBluetoothBase(0).getSignalPower());
        assertFalse(content.isAddressNeeded());
        assertEquals(7, content.getGsmBasesCount());
        assertEquals(460, content.getMobileCountryCode());
        assertEquals(0, content.getMobileNetworkCode());
        assertEquals(9520, content.getGsmBase(0).getLocalAreaCode());
        assertEquals(3671, content.getGsmBase(0).getCellId());
        assertEquals(13, content.getGsmBase(0).getSignalPower());
        assertEquals(9520, content.getGsmBase(1).getLocalAreaCode());
        assertEquals(3672, content.getGsmBase(1).getCellId());
        assertEquals(12, content.getGsmBase(1).getSignalPower());
        assertEquals(9520, content.getGsmBase(2).getLocalAreaCode());
        assertEquals(3673, content.getGsmBase(2).getCellId());
        assertEquals(11, content.getGsmBase(2).getSignalPower());
        assertEquals(9520, content.getGsmBase(3).getLocalAreaCode());
        assertEquals(3674, content.getGsmBase(3).getCellId());
        assertEquals(10, content.getGsmBase(3).getSignalPower());
        assertEquals(9520, content.getGsmBase(4).getLocalAreaCode());
        assertEquals(3675, content.getGsmBase(4).getCellId());
        assertEquals(9, content.getGsmBase(4).getSignalPower());
        assertEquals(9520, content.getGsmBase(5).getLocalAreaCode());
        assertEquals(3676, content.getGsmBase(5).getCellId());
        assertEquals(8, content.getGsmBase(5).getSignalPower());
        assertEquals(9520, content.getGsmBase(6).getLocalAreaCode());
        assertEquals(3677, content.getGsmBase(6).getCellId());
        assertEquals(7, content.getGsmBase(6).getSignalPower());
        assertEquals("1", content.getWifiBase(0).getName());
        assertEquals("D8-24-BD-79-FA-1F", content.getWifiBase(0).getMacAddress());
        assertEquals(59, content.getWifiBase(0).getSignalPower());
        assertEquals("2", content.getWifiBase(1).getName());
        assertEquals("3C-46-D8-6D-CE-01", content.getWifiBase(1).getMacAddress());
        assertEquals(81, content.getWifiBase(1).getSignalPower());
        assertEquals("3", content.getWifiBase(2).getName());
        assertEquals("0C-4C-39-1A-7C-65", content.getWifiBase(2).getMacAddress());
        assertEquals(69, content.getWifiBase(2).getSignalPower());
        assertEquals("4", content.getWifiBase(3).getName());
        assertEquals("70-A8-E3-5D-D7-C0", content.getWifiBase(3).getMacAddress());
        assertEquals(65, content.getWifiBase(3).getSignalPower());
    }

    @Test
    public void whenEmptyBpPack() {
        String expected = "IWBP02#";
        ProtocolDataUnit pdu = new ProtocolDataUnit("BP02", null);
        assertEquals(expected, pdu.toPacket());
    }

    @Test
    public void whenPack() {
        String expected = "IWBP02F16D3357025E5753715C3A535753776D275953903100300037003900F753#";
        BP02 content = new BP02();
        content.setAddress("深圳市南山区南海大道1079号");
        ProtocolDataUnit pdu = new ProtocolDataUnit("BP02", content);
        assertEquals(expected, pdu.toPacket());
    }

    @Test
    public void whenBpAddressParse() {
        String in = "IWBP02F16D3357025E5753715C3A535753776D275953903100300037003900F753#";
        String expected = "深圳市南山区南海大道1079号";
        ProtocolDataUnit pdu = new ProtocolDataUnit();
        pdu.parse(in);
        BP02 content = pdu.getContent();
        assertEquals(expected, content.getAddress());
    }

    @Test
    public void whenEmptyBpParse() {
        String in = "IWBP02#";
        ProtocolDataUnit pdu = new ProtocolDataUnit();
        pdu.parse(in);
        BP02 content = pdu.getContent();
        assertNull(content.getAddress());
    }
}
