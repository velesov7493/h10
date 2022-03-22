package h10.protocol.packets;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PduHTTest {

    @Test
    public void whenApParse() {
        String in = "IWAPHT,60,130,85#";
        ProtocolDataUnit pdu = new ProtocolDataUnit();
        pdu.parse(in);
        APHT content = pdu.getContent();
        assertEquals(60, content.getHeartRate());
        assertEquals(130, content.getSystolicPressure());
        assertEquals(85, content.getDiastolicPressure());
    }

    @Test
    public void whenBpPack() {
        String expected = "IWBPHT#";
        ProtocolDataUnit pdu = new ProtocolDataUnit("BPHT", null);
        assertEquals(expected, pdu.toPacket());
    }
}
