package h10.protocol.packets;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Pdu49Test {

    @Test
    public void whenApParse() {
        String in = "IWAP49,68#";
        ProtocolDataUnit pdu = new ProtocolDataUnit();
        pdu.parse(in);
        AP49 content = pdu.getContent();
        assertEquals(68, content.getHeartRate());
    }

    @Test
    public void whenBpPack() {
        String expected = "IWBP49#";
        ProtocolDataUnit pdu = new ProtocolDataUnit("BP49", null);
        assertEquals(expected, pdu.toPacket());
    }
}
