package h10.protocol.packets;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PduJZTest {

    @Test
    public void whenApParse() {
        String in = "IWAPJZ,080835#";
        ProtocolDataUnit pdu = new ProtocolDataUnit();
        pdu.parse(in);
        APJZ content = pdu.getContent();
        assertEquals(80835, content.getJournalNo());
    }

    @Test
    public void whenBpPack() {
        String expected = "IWBPJZ,353456789012345,080835,110,75#";
        BPJZ content = new BPJZ();
        ProtocolDataUnit pdu = new ProtocolDataUnit("BPJZ", content);
        content.setImei("353456789012345");
        content.setJournalNo(80835);
        content.setSystolicBp(110);
        content.setDiastolicBp(75);
        assertEquals(expected, pdu.toPacket());
    }
}
