package h10.protocol.packets;

import h10.protocol.subunits.DeviceStatus;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PduHPTest {

    @Test
    public void whenApParse() {
        String in = "IWAPHP,60,130,85,95,90#";
        ProtocolDataUnit pdu = new ProtocolDataUnit();
        pdu.parse(in);
        APHP content = pdu.getContent();
        assertEquals(60, content.getHeartRate());
        assertEquals(130, content.getSystolicPressure());
        assertEquals(85, content.getDiastolicPressure());
        assertEquals(95, content.getSaturationO2());
        assertEquals(90, content.getBloodSugar());
    }

    @Test(expected = ClassCastException.class)
    public void whenApWrongContentThenClassCastError() {
        String in = "IWAPHP,60,130,85,95,90#";
        ProtocolDataUnit pdu = new ProtocolDataUnit("APHP");
        pdu.parse(in);
        DeviceStatus content = pdu.getContent();
        assertEquals(60, content.getFortificationState());
    }

    @Test
    public void whenBpPack() {
        String expected = "IWBPHP#";
        ProtocolDataUnit pdu = new ProtocolDataUnit("BPHP", null);
        assertEquals(expected, pdu.toPacket());
    }
}
