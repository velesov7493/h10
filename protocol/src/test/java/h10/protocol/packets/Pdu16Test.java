package h10.protocol.packets;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Pdu16Test {

    @Test
    public void whenApParse() {
        String in = "IWAP16,080835#";
        ProtocolDataUnit pdu = new ProtocolDataUnit();
        pdu.parse(in);
        AP16 content = pdu.getContent();
        assertEquals(80835, content.getJournalNo());
    }

    @Test
    public void whenBpPack() {
        String expected = "IWBP16,353456789012345,080835#";
        BP16 content = new BP16();
        ProtocolDataUnit pdu = new ProtocolDataUnit("BP16", content);
        content.setImei("353456789012345");
        content.setJournalNo(80835);
        assertEquals(expected, pdu.toPacket());
    }
}
