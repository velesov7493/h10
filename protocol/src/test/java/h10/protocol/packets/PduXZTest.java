package h10.protocol.packets;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PduXZTest {

    @Test
    public void whenApParse() {
        String in = "IWAPXZ,080835#";
        ProtocolDataUnit pdu = new ProtocolDataUnit();
        pdu.parse(in);
        APXZ content = pdu.getContent();
        assertEquals(80835, content.getJournalNo());
    }

    @Test
    public void whenBpPack() {
        String expected = "IWBPXZ,353456789012345,080835#";
        BPXZ content = new BPXZ();
        ProtocolDataUnit pdu = new ProtocolDataUnit("BPXZ", content);
        content.setImei("353456789012345");
        content.setJournalNo(80835);
        assertEquals(expected, pdu.toPacket());
    }
}
