package h10.protocol.packets;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class Pdu00Test {

    @Test
    public void whenApParse() {
        String in = "IWAP00353456789012345#";
        ProtocolDataUnit pdu = new ProtocolDataUnit();
        pdu.parse(in);
        AP00 content = pdu.getContent();
        assertEquals("353456789012345", content.getImei());
        assertEquals("AP00", pdu.getCommandId());
    }

    @Test
    public void whenBpPack() {
        BP00 content = new BP00();
        content.setTimeZone(3);
        content.setDateTime(LocalDateTime.of(2021, 11, 29, 9, 0, 0));
        ProtocolDataUnit pdu = new ProtocolDataUnit("BP00", content);
        assertEquals("IWBP0020211129090000,3#", pdu.toPacket());
    }
}
