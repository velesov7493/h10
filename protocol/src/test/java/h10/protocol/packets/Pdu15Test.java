package h10.protocol.packets;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Pdu15Test {

    @Test
    public void whenApParse() {
        String in = "IWAP15,080835#";
        ProtocolDataUnit pdu = new ProtocolDataUnit();
        pdu.parse(in);
        AP15 content = pdu.getContent();
        assertEquals(80835, content.getJournalNo());
        assertEquals("AP15", pdu.getCommandId());
    }

    @Test
    public void whenBpPack() {
        String expected = "IWBP15,353456789012345,080835,30#";
        BP15 content = new BP15();
        ProtocolDataUnit pdu = new ProtocolDataUnit("BP15", content);
        content.setImei("353456789012345");
        content.setJournalNo(80835);
        content.setMinutes(30);
        assertEquals(expected, pdu.toPacket());
    }
}