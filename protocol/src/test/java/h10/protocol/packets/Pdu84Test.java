package h10.protocol.packets;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Pdu84Test {

    @Test
    public void whenApParse() {
        String in = "IWAP84,080835,1#";
        ProtocolDataUnit pdu = new ProtocolDataUnit();
        pdu.parse(in);
        AP84 content = pdu.getContent();
        assertEquals(80835, content.getJournalNo());
        assertTrue(content.isDone());
    }

    @Test
    public void whenBpPack() {
        String expected = "IWBP84,353456789012345,080835,1#";
        BP84 content = new BP84();
        ProtocolDataUnit pdu = new ProtocolDataUnit("BP84", content);
        content.setImei("353456789012345");
        content.setJournalNo(80835);
        content.setWhiteListEnabled(true);
        assertEquals(expected, pdu.toPacket());
    }
}
