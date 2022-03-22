package h10.protocol.packets;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class PduXYTest {

    @Test
    public void whenApParse() {
        String in = "IWAPXY,080835#";
        ProtocolDataUnit pdu = new ProtocolDataUnit();
        pdu.parse(in);
        APXY content = pdu.getContent();
        assertEquals(80835, content.getJournalNo());
    }

    @Test
    public void whenBpPack() {
        String expected = "IWBPXY,353456789012345,080835#";
        BPXY content = new BPXY();
        ProtocolDataUnit pdu = new ProtocolDataUnit("BPXY", content);
        content.setImei("353456789012345");
        content.setJournalNo(80835);
        assertEquals(expected, pdu.toPacket());
    }
}
