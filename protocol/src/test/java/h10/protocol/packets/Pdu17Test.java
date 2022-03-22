package h10.protocol.packets;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Pdu17Test {

    @Test
    public void whenBpPack() {
        String expected = "IWBP17,353456789012345,080835#";
        BP17 content = new BP17();
        ProtocolDataUnit pdu = new ProtocolDataUnit("BP17", content);
        content.setImei("353456789012345");
        content.setJournalNo(80835);
        assertEquals(expected, pdu.toPacket());
    }

    @Test
    public void whenApParse() {
        String in = "IWAP17,080835#";
        ProtocolDataUnit pdu = new ProtocolDataUnit();
        pdu.parse(in);
        AP17 content = pdu.getContent();
        assertEquals(80835, content.getJournalNo());
    }
}
