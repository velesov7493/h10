package h10.protocol.packets;

import h10.protocol.subunits.DeviceStatus;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Pdu03Test {

    @Test
    public void whenApParse() {
        String in = "IWAP03,06000908000102,5555,30#";
        ProtocolDataUnit pdu = new ProtocolDataUnit();
        pdu.parse(in);
        AP03 content = pdu.getContent();
        DeviceStatus status = content.getStatus();
        assertEquals(60, status.getGsmSignalLevel());
        assertEquals(9, status.getGsmSattellitesCount());
        assertEquals(80, status.getBatteryLevel());
        assertEquals(1, status.getFortificationState());
        assertEquals(5555, content.getStepsCount());
        assertEquals(30, content.getRollsFrequency());
    }

    @Test
    public void whenBpPack() {
        String expected = "IWBP03#";
        ProtocolDataUnit pdu = new ProtocolDataUnit("BP03", null);
        assertEquals(expected, pdu.toPacket());
    }
}
