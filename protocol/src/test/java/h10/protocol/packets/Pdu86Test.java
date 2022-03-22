package h10.protocol.packets;

import h10.protocol.subunits.TestingType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Pdu86Test {

    @Test
    public void whenApParse() {
        String in = "IWAP86,080835#";
        ProtocolDataUnit pdu = new ProtocolDataUnit("AP86");
        pdu.parse(in);
        AP86 content = pdu.getContent();
        assertEquals(80835, content.getJournalNo());
    }

    @Test
    public void whenBpPack() {
        String expected = "IWBP86,353456789012345,080835,1,720#";
        BP86 content = new BP86();
        ProtocolDataUnit pdu = new ProtocolDataUnit("BP86", content);
        content.setImei("353456789012345");
        content.setJournalNo(80835);
        content.setTestingType(TestingType.HEART_RATE);
        content.setMinutes(720);
        assertEquals(expected, pdu.toPacket());
    }
}
