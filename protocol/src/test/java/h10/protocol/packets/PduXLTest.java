package h10.protocol.packets;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PduXLTest {

    @Test
    public void whenApParse() {
        String in = "IWAPXL,080835#";
        ProtocolDataUnit pdu = new ProtocolDataUnit();
        pdu.parse(in);
        APXL content = pdu.getContent();
        assertEquals(80835, content.getJournalNo());
    }

    @Test
    public void whenBpPack() {
        String expected = "IWBPXL,353456789012345,080835#";
        BPXL content = new BPXL();
        ProtocolDataUnit pdu = new ProtocolDataUnit("BPXL", content);
        content.setImei("353456789012345");
        content.setJournalNo(80835);
        assertEquals(expected, pdu.toPacket());
    }
}